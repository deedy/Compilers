#include "stdio.h"

struct Object_t {
	int id;  		/* object type */
	struct Object_t *parent;
	int ref_count; 	/* reference count */
	int field_count;/* number of fields */
	struct Object_t **fields;	/* array of field objects */
	/* 	all objects must have these fields
		at the top of the struct. This allows
		referencing base classes both as 
		regular and special objects
	*/
};

typedef struct Object_t* Object;

struct Iterable_t;

struct _IterNode_t {
	Object curr; /* current value of the iterator */
	/* 	feed in this pointer and it will return:
		this pointer: in place update
		another pointer: the next pointer in the appends chain
			should free this pointer
	*/
	struct _IterNode_t* (*next)(struct _IterNode_t*);
	struct Iterable_t* nextIter; /* the next iterable in the append chain */
};

typedef struct _IterNode_t* _IterNode;

struct Iterable_t {
	int id;
	Object parent;
	int ref_count;
	int field_count;
	Object *fields; /* [first, nextIter] */
	_IterNode (*next)(_IterNode);	/* function to update iterator */
};

typedef struct Iterable_t* Iterable;

struct Integer_t {
	int id;
	Object parent;
	int ref_count;
	int field_count;
	Object *fields;
	int value;
};

typedef struct Integer_t* Integer;

struct Boolean_t {
	int id;
	Object parent;
	int ref_count;
	int field_count;
	Object *fields;
	int value;
};

typedef struct Boolean_t* Boolean;

struct Character_t {
	int id;
	Object parent;
	int ref_count;
	int field_count;
	Object *fields;
	char value;
};

typedef struct Character_t* Character;

struct String_t {
	int id;
	Object parent;
	int ref_count;
	int field_count;
	Object *fields;
	_IterNode (*next)(_IterNode);
	char* value;
};

typedef struct String_t* String;

Object allocate(int id, int field_count) {
	// call to base class constructor
	Object o;
	if (id < 5) {
		switch (id) {
			case 0:
				o = x3malloc(sizeof(struct Iterable_t));
				break;
			case 1:
				o = x3malloc(sizeof(struct Integer_t));
				break;
			case 2:
				o = x3malloc(sizeof(struct Boolean_t));
				break;
			case 3:
				o = x3malloc(sizeof(struct Character_t));
				break;
			case 4:
				o = x3malloc(sizeof(struct String_t));
				break;
			default:
				return NULL;
		}
	} else {
		o = x3malloc(sizeof(struct Object_t));	
	}
	o->id = id;
	o->parent = NULL;
	o->ref_count = 0;
	o->fields = x3malloc(sizeof(Object) * field_count);
	int i;
	for (i = 0; i < field_count; i++) {
		o->fields[i] = NULL;
	}
	o->field_count = field_count;
}

/* 	Assert that some variable is pointing at o */
void incr(void *ptr) {
	Object o = ptr;
	if(o) {
		o->ref_count += 1;
	}
}

/* 	Assert that a reference to o is lost */
void decr(void *ptr) {
	Object o = ptr;
	if(o) {
		o->ref_count -= 1;
		if (o->ref_count <= 0) {
			int i;
			for (i = 0; i < o->field_count; i++) {
				decr(o->fields[i]);
			}
			x3free(o->fields);
			decr(o->parent);
			if(o->id == 4) {
				x3free(((String) o)->value);
			}
			x3free(o);
		}
	}
}

_IterNode iterator(Iterable i) {
	if (!i) {
		return NULL;
	}
	_IterNode n = x3malloc(sizeof(struct _IterNode_t));
	n->curr = i->fields[0];
	n->nextIter = (Iterable) i->fields[1];
	n->next = i->next;
	return n;
}

_IterNode common_next(_IterNode node) {
	if (!node) {
		return NULL;
	} else {
		_IterNode ret = iterator(node->nextIter);
		x3free(node);
		return ret;
	}
}

Iterable Iterable_construct(void *ptr) {
	Object o = ptr;
	Iterable a = (Iterable) allocate(0, 2);
	incr(o);
	a->fields[0] = o;
	a->fields[1] = NULL;
	a->next = common_next;
	return a;
}

Iterable copy(Iterable a) {
	if(!a) {
		return NULL;
	}
	Iterable b = Iterable_construct(a->fields[0]);
	b->next = a->next;
	return b;
}

Iterable append(Iterable a, Iterable b) {
	if(!a) {
		return b;
	}
	Iterable c = copy(a);
	c->fields[1] = (Object) append((Iterable) a->fields[1], b);
	incr(c->fields[1]);
	return c;
}

Boolean Boolean_construct(int b) {
	Boolean a = (Boolean) allocate(2, 0);
	a->value = b;
	return a;
}

Boolean Boolean_negate(Boolean b) {
	incr(b);
	Boolean _ret = Boolean_construct(!b->value);
	decr(b);
	return _ret;
}

Boolean Boolean_and(Boolean a, Boolean b) {
	incr(a);
	incr(b);
	Boolean _ret = Boolean_construct(a->value && b->value);
	decr(a);
	decr(b);
	return _ret;
}

Boolean Boolean_or(Boolean a, Boolean b) {
	incr(a);
	incr(b);
	Boolean _ret = Boolean_construct(a->value || b->value);
	decr(a);
	decr(b);
	return _ret;
}

Iterable Boolean_through(Boolean lower, Boolean upper, Boolean includeLower, Boolean includeUpper) {
	incr(lower);
	incr(upper);
	incr(includeLower);
	incr(includeUpper);

	int _lower = lower->value;
	int _upper = upper->value;
	int _iL = includeLower->value;
	int _iU = includeUpper->value;

	int low = _lower || (!_iL);
	int high =_upper && (!_iU);

	Iterable _ret;
	if (low > high) {
		_ret = NULL;
	} else {
		Iterable i = Iterable_construct(lower);
		Iterable j = Boolean_through(Boolean_construct(low + 1), upper, includeLower, includeUpper);
		_ret = append(i, j);
	}

	decr(lower);
	decr(upper);
	decr(includeLower);
	decr(includeUpper);

	return _ret;
}

Iterable Boolean_onward(Boolean a, Boolean inclusive) {
	incr(a);
	incr(inclusive);
	Iterable _ret = Boolean_through(a, inclusive, Boolean_construct(1), Boolean_construct(1));
	decr(a);
	decr(inclusive);
	return _ret;
}

Boolean Boolean_lessThan(Boolean a, Boolean b, Boolean strict) {
	Boolean _ret;
	incr(a);
	incr(b);
	incr(strict);

	if (strict->value) {
		_ret = Boolean_construct(a->value < b->value);
	} else {
		_ret = Boolean_construct(a->value <= b->value);
	}

	decr(a);
	decr(b);
	decr(strict);

	return _ret;
}

Boolean Boolean_equals(Boolean a, Boolean b) {
	Boolean _ret;
	incr(a);
	incr(b);

	_ret = Boolean_construct(a->value == b->value);

	decr(a);
	decr(b);
	return _ret;
}

Integer Integer_construct(int n) {
	Integer a = (Integer) allocate(1, 0);
	a->value = n;
	return a;
}

Integer Integer_negative(Integer a) {
	Integer _ret;
	incr(a);
	_ret = Integer_construct(-(a->value));
	decr(a);
	return _ret;
}

Integer Integer_times(Integer a, Integer b) {
	Integer _ret;
	incr(a);
	incr(b);
	_ret = Integer_construct(a->value * b->value);
	decr(a);
	decr(b);
}

Iterable Integer_divide(Integer a, Integer b) {
	Iterable _ret;
	incr(a);
	incr(b);
	if (b->value == 0) {
		_ret = NULL;
	} else {
		Iterable i = Iterable_construct(Integer_construct(a->value / b->value));
		_ret = i;
	}
	decr(a);
	decr(b);
	return _ret;
}

Iterable Integer_modulo(Integer a, Integer b) {
	Iterable _ret;
	incr(a);
	incr(b);
	if (b->value == 0) {
		_ret = NULL;
	} else {
		Iterable i = Iterable_construct(Integer_construct(a->value % b->value));
		_ret = i;
	}
	decr(a);
	decr(b);
	return _ret;
}

Integer Integer_plus(Integer a, Integer b) {
	Integer _ret;
	incr(a);
	incr(b);
	_ret = Integer_construct(a->value + b->value);
	decr(a);
	decr(b);
	return _ret;
}

Integer Integer_minus(Integer a, Integer b) {
	Integer _ret;
	incr(a);
	incr(b);
	_ret = Integer_construct(a->value - b->value);
	decr(a);
	decr(b);
	return _ret;
}

Iterable Integer_through(Integer lower, Integer upper, Boolean includeLower, Boolean includeUpper) {
	incr(lower);
	incr(upper);
	incr(includeLower);
	incr(includeUpper);

	int _lower = lower->value;
	int _upper = upper->value;
	int _iL = includeLower->value;
	int _iU = includeUpper->value;

	int low = _lower + (!_iL);
	int high =_upper - (!_iU);

	Iterable _ret;
	if (low > high) {
		_ret = NULL;
	} else {
		Iterable i = Iterable_construct(lower);
		Iterable j = Integer_through(Integer_construct(low + 1), upper, includeLower, includeUpper);
		_ret = append(i, j);
	}

	decr(lower);
	decr(upper);
	decr(includeLower);
	decr(includeUpper);

	return _ret;
}

_IterNode Integer_onwards_next(_IterNode i) {
	int val = ((Integer) i->curr)->value;
	decr(i->curr);
	i->curr = (Object) Integer_construct(val + 1);
	return i;
}

Iterable Integer_onwards(Integer a, Boolean inclusive) {
	int start = a->value + !(inclusive->value);
	Iterable i = Iterable_construct(Integer_construct(start));
	i->next = Integer_onwards_next;
	return i;
}

Boolean Integer_lessThan(Integer a, Integer b, Boolean strict) {
	Boolean _ret;
	incr(a);
	incr(b);
	incr(strict);

	if (strict->value) {
		_ret = Boolean_construct(a->value < b->value);
	} else {
		_ret = Boolean_construct(a->value <= b->value);
	}

	decr(a);
	decr(b);
	decr(strict);

	return _ret;
}

Boolean Integer_equals(Integer a, Integer b) {
	Boolean _ret;
	incr(a);
	incr(b);

	_ret = Boolean_construct(a->value == b->value);

	decr(a);
	decr(b);
	return _ret;
}

Character Character_construct(char c) {
	Character a = (Character) allocate(3, 0);
	a->value = c;
	return a;
}

Integer Character_unicode(Character c) {
	Integer _ret;
	incr(c);
	_ret = Integer_construct(charuni(c->value));
	decr(c);
	return _ret;
}

Boolean Character_equals(Character a, Character b) {
	Boolean _ret;
	incr(a);
	incr(b);

	_ret = Boolean_construct(a->value == b->value);

	decr(a);
	decr(b);
	return _ret;
}

int strCmp(const char *s1, const char *s2) {
  int ret = 0;
  while (!(ret = *(unsigned char *) s1 - *(unsigned char *) s2) && *s2) ++s1, ++s2;
  if (ret < 0)
    ret = -1;
  else if (ret > 0)
    ret = 1 ;
  return ret;
}

void strCpy(const char *src, char *dest) {
	while (*dest++ = *src++); 
}

int strLen(const char *s) {
    const char *start;
    start = s;
    while(*++s);
    return s - start;
}

Iterable strIter(const char *s) {
	if(!*s) {
		return NULL;
	} else {
		Iterable i = Iterable_construct(Character_construct(*s));
		return append(i, strIter(s + 1));
	}
}

String String_construct(const char* s) {
	if (!s) {
		return NULL;
	} else {
		String str = (String) allocate(5, 0);
		str->value = x3malloc(sizeof(char) * strLen(s));
		strCpy(s, str->value);
		Iterable i = strIter(s);
		str->fields = i->fields;
		str->next = i->next;
		x3free(i);
		return str;
	}
}

Boolean String_equals(String a, String b) {
	Boolean _ret;
	incr(a);
	incr(b);
	_ret = Boolean_construct(strCmp(a->value, b->value));
	decr(a);
	decr(b);
	return _ret;
}

void print(Object o) {
	String s = (String) o;
	print_line(s->value, strLen(s->value));
}

Character character(Integer unicode) {
	Character _ret;
	incr(unicode);
	_ret = Character_construct((char) unicode->value);
	decr(unicode);
	return _ret;
}

String string(Iterable characters) {
	return (String) characters;
}
