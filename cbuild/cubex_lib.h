#define NULL 0

enum typeIDs {
	ITERABLE_ID = -1,
	ITERATOR_ID = -2,
	INTEGER_ID = -3,
	BOOLEAN_ID = -4,
	CHARACTER_ID = -5,
	STRING_ID = -6,
	INDEXITERATOR_ID = -7,
	APPENDITERABLE_ID = -8,
	APPENDITERATOR_ID = -9,
	RANGEITERABLE_ID = -10
};

/*  _object is classier than void* */
typedef void* _object;

/*  the generic object type */
struct Object_t {
	int id; 
	struct Object_t* prev; 
	struct Object_t* tail; 
	int ref_count; 
	int field_count;
	_object *fields;
};
typedef struct Object_t* Object;

/*  the iterable prototype */
struct Iterable_t {
	int id; 
	struct Object_t* prev; 
	struct Object_t* tail; 
	int ref_count; 
	int field_count;
	_object *fields;
	_object (*iter)(_object);
};
typedef struct Iterable_t* Iterable;

/*  the iterator prototype (holds state machine) */
struct _Iterator_t {
	int id; 
	struct Object_t* prev; 
	struct Object_t* tail; 
	int ref_count; 
	int field_count;
	_object *fields;
	_object (*next)(_object);
	_object parent;
};
typedef struct _Iterator_t* _Iterator;

struct Integer_t {
	int id; 
	struct Object_t* prev; 
	struct Object_t* tail; 
	int ref_count; 
	int field_count;
	_object *fields;
	int value;
};
typedef struct Integer_t* Integer;

struct Boolean_t {
	int id; 
	struct Object_t* prev; 
	struct Object_t* tail; 
	int ref_count; 
	int field_count;
	_object *fields;
	int value;
};
typedef struct Boolean_t* Boolean;

struct Character_t {
	int id; 
	struct Object_t* prev; 
	struct Object_t* tail; 
	int ref_count; 
	int field_count;
	_object *fields;
	char value;
};
typedef struct Character_t* Character;

struct String_t {
	int id; 
	struct Object_t* prev; 
	struct Object_t* tail; 
	int ref_count; 
	int field_count;
	_object *fields;
	_object (*iter)(_object);
	char* value;
	int length;
};
typedef struct String_t* String;

typedef struct _IndexIterator_t {
	int id; 
	struct Object_t* prev; 
	struct Object_t* tail; 
	int ref_count; 
	int field_count;
	_object *fields;
	_object (*next)(_object);
	_object parent;
	int position;
} *_IndexIterator;

typedef struct _AppendIterable_t {
	int id; 
	struct Object_t* prev; 
	struct Object_t* tail; 
	int ref_count; 
	int field_count;
	_object *fields;
	_object (*iter)(_object);
	_object left;
	_object right;
} *_AppendIterable;

typedef struct _AppendIterator_t {
	int id; 
	struct Object_t* prev; 
	struct Object_t* tail; 
	int ref_count; 
	int field_count;
	_object *fields;
	_object (*next)(_object);
	_object parent;
	_object leftIter;
	_object rightIter;
} *_AppendIterator;

typedef struct _RangeIterable_t {
	int id; 
	struct Object_t* prev; 
	struct Object_t* tail; 
	int ref_count; 
	int field_count;
	_object *fields;
	_object (*iter)(_object);
	int len;
	int offset;
} *_RangeIterable;

Object freeList = NULL;

void _add_obj(Object o) {
	o->tail = freeList;
	if (freeList){
		freeList->prev = o;
	}
	freeList = o;
}

void _del_obj(Object o) {
	if (o->prev) {
		o->prev->tail = o->tail;
	}
	if (o->tail) {
		o->tail->prev = o->prev;
	}
	if (!(o->tail || o->prev)) {
		freeList = NULL;
	}
}

_object _allocate(int id, int field_count) {
	Object o;
	if (id < 0) {
		switch(id) {
			case ITERABLE_ID: o = x3malloc(sizeof(struct Iterable_t)); break;
			case ITERATOR_ID: o = x3malloc(sizeof(struct _Iterator_t)); break;
			case INTEGER_ID: o = x3malloc(sizeof(struct Integer_t)); break;
			case BOOLEAN_ID: o = x3malloc(sizeof(struct Boolean_t)); break;
			case CHARACTER_ID: o = x3malloc(sizeof(struct Character_t)); break;
			case STRING_ID: o = x3malloc(sizeof(struct String_t)); break;
			case INDEXITERATOR_ID: o = x3malloc(sizeof(struct _IndexIterator_t)); break;
			case APPENDITERABLE_ID: o = x3malloc(sizeof(struct _AppendIterable_t)); break;
			case APPENDITERATOR_ID: o = x3malloc(sizeof(struct _AppendIterator_t)); break;
			case RANGEITERABLE_ID: o = x3malloc(sizeof(struct _RangeIterable_t)); break;
		}	
	} else {
		o = x3malloc(sizeof(struct Object_t));
	}
	o->id = id;
	o->prev = NULL;
	o->tail = NULL;
	_add_obj(o);
	o->ref_count = 0;
	o->field_count = field_count;
	_object *fields;
	if (field_count) {
		fields = x3malloc(sizeof(_object) * field_count);
		int i;
		for (i = 0; i < field_count; i++) {
			fields[i] = NULL;
		}
	} else {
		fields = NULL;
	}
	o->fields = fields;
	return o;
}

void _incr(_object ptr) {
	Object o = ptr;
	if(o) {
		o->ref_count += 1;
	}
}

void _decr(_object ptr) {
	Object o = ptr;
	if(o) {
		o->ref_count -= 1;
		if (o->ref_count <= 0) {
			_del_obj(o);
			int i;
			for (i = 0; i < o->field_count; i++) {
				_decr(o->fields[i]);
			}
			x3free(o->fields);
			if(o->id == -6) {
				x3free(((String) o)->value);
			}
			x3free(o);
		}
	}
}

void _free_all_the_things() {
	Object o = freeList;
	while(o) {
		Object next = o->tail;
		int i;
		x3free(o->fields);
		if(o->id == 4) {
			x3free(((String) o)->value);
		}
		x3free(o);
		o = next;
	}
}

_object _indexNext(_object o) {
	_IndexIterator i = o;
	int position = i->position;
	Iterable parent = i->parent;
	if (position < parent->field_count) {
		_object ret = parent->fields[position];
		i->position += 1;
		return ret;
	} else {
		return NULL;
	}
}

_object _indexIter(_object o) {
	Iterable i = o;
	_IndexIterator it = _allocate(INDEXITERATOR_ID, 0);
	it->parent = i;
	it->next = _indexNext;
	it->position = 0;
	return it;
}

_object Iterable_construct(_object ptrs[], int size) {
	Iterable a = _allocate(ITERATOR_ID, size);
	int i;
	for (i = 0; i < size; i++) {
		a->fields[i] = ptrs[i];
	}
	a->iter = _indexIter;
	return a;
}

_object _appendNext(_object o) {
	_AppendIterator i = o;
	_AppendIterable parent = i->parent;
	_Iterator leftIter = i->leftIter;
	_Iterator rightIter = i->rightIter;
	_object ret;
	if (ret = leftIter->next(leftIter)) {
		return ret;
	} else {
		return rightIter->next(rightIter);
	}
}

_object _appendIter(_object o) {
	_AppendIterable a = o;
	_AppendIterator i = _allocate(APPENDITERATOR_ID, 0);
	Iterable left = a->left;
	Iterable right = a->right;
	i->leftIter = left->iter(left);
	i->rightIter = right->iter(right);
	i->parent = a;
	i->next = _appendNext;
	return i;
}

Iterable _append(_object o1, _object o2) {
	_AppendIterable a = _allocate(APPENDITERABLE_ID, 0);
	a->left = o1;
	a->right = o2;
	a->iter = _appendIter;
	return a;
}


_object Boolean_construct(int b) {
	Boolean a = _allocate(BOOLEAN_ID, 0);
	a->value = b;
	return a;
}

_object Boolean_negate(_object o) {
	Boolean b = o;
	_incr(b);
	Boolean _ret = Boolean_construct(!b->value);
	_decr(b);
	return _ret;
}

_object Boolean_and(_object o1, _object o2) {
	Boolean a = o1;
	Boolean b = o2;
	_incr(a);
	_incr(b);
	Boolean _ret = Boolean_construct(a->value && b->value);
	_decr(a);
	_decr(b);
	return _ret;
}

_object Boolean_or(_object o1, _object o2) {
	Boolean a = o1;
	Boolean b = o2;
	_incr(a);
	_incr(b);
	Boolean _ret = Boolean_construct(a->value || b->value);
	_decr(a);
	_decr(b);
	return _ret;
}

_object Boolean_through(_object o1, _object o2, _object o3, _object o4) {
	Boolean lower = o1;
	Boolean upper = o2;
	Boolean includeLower = o3;
	Boolean includeUpper = o4;
	_incr(lower);
	_incr(upper);
	_incr(includeLower);
	_incr(includeUpper);

	int _lower = lower->value;
	int _upper = upper->value;
	int _iL = includeLower->value;
	int _iU = includeUpper->value;

	int low = _lower || (!_iL);
	int high =_upper && (!_iU);

	Iterable _ret;
	if (low > high) {
		_ret = Iterable_construct((_object[]){}, 0);
	} else if (low == high) {
		_ret = Iterable_construct((_object[]){ Boolean_construct(low) }, 1);
	} else {
		_ret = Iterable_construct((_object[]){ Boolean_construct(0), Boolean_construct(1) }, 1);
	}

	_decr(lower);
	_decr(upper);
	_decr(includeLower);
	_decr(includeUpper);

	return _ret;
}

_object Boolean_onward(_object o1, _object o2) {
	Boolean a = o1;
	Boolean inclusive = o2;
	_incr(a);
	_incr(inclusive);
	Iterable _ret = Boolean_through(a, inclusive, Boolean_construct(1), Boolean_construct(1));
	_decr(a);
	_decr(inclusive);
	return _ret;
}

_object Boolean_lessThan(_object o1, _object o2, _object o3) {
	Boolean a = o1;
	Boolean b = o2;
	Boolean strict = o3;
	Boolean _ret;
	_incr(a);
	_incr(b);
	_incr(strict);

	if (strict->value) {
		_ret = Boolean_construct(a->value < b->value);
	} else {
		_ret = Boolean_construct(a->value <= b->value);
	}

	_decr(a);
	_decr(b);
	_decr(strict);

	return _ret;
}

_object Boolean_equals(_object o1, _object o2) {
	Boolean a = o1;
	Boolean b = o2;
	Boolean _ret;
	_incr(a);
	_incr(b);

	_ret = Boolean_construct(a->value == b->value);

	_decr(a);
	_decr(b);
	return _ret;
}


_object Integer_construct(int n) {
	Integer a = _allocate(INTEGER_ID, 0);
	a->value = n;
	return a;
}

_object Integer_negative(_object o) {
	Integer a = o;
	Integer _ret;
	_incr(a);
	_ret = Integer_construct(-(a->value));
	_decr(a);
	return _ret;
}

_object Integer_times(_object o1, _object o2) {
	Integer a = o1;
	Integer b = o2;
	Integer _ret;
	_incr(a);
	_incr(b);
	_ret = Integer_construct(a->value * b->value);
	_decr(a);
	_decr(b);
	return _ret;
}

_object Integer_divide(_object o1, _object o2) {
	Integer a = o1;
	Integer b = o2;
	Iterable _ret;
	_incr(a);
	_incr(b);
	if (b->value == 0) {
		_ret = Iterable_construct((_object[]){}, 0);
	} else {
		Iterable i = Iterable_construct((_object[]){ Integer_construct(a->value / b->value) }, 1);
		_ret = i;
	}
	_decr(a);
	_decr(b);
	return _ret;
}

_object Integer_modulo(_object o1, _object o2) {
	Integer a = o1;
	Integer b = o2;
	Iterable _ret;
	_incr(a);
	_incr(b);
	if (b->value == 0) {
		_ret = Iterable_construct((_object[]){}, 0);
	} else {
		Iterable i = Iterable_construct((_object[]){ Integer_construct(a->value % b->value) }, 1);
		_ret = i;
	}
	_decr(a);
	_decr(b);
	return _ret;
}

_object Integer_plus(_object o1, _object o2) {
	Integer a = o1;
	Integer b = o2;
	Integer _ret;
	_incr(a);
	_incr(b);
	_ret = Integer_construct(a->value + b->value);
	_decr(a);
	_decr(b);
	return _ret;
}

_object Integer_minus(_object o1, _object o2) {
	Integer a = o1;
	Integer b = o2;
	Integer _ret;
	_incr(a);
	_incr(b);
	_ret = Integer_construct(a->value - b->value);
	_decr(a);
	_decr(b);
	return _ret;
}

_object _rangeNext(_object o) {
	_IndexIterator i = o;
	int pos = i->position;
	_RangeIterable r = i->parent;
	if (pos == r->len) {
		return NULL;
	} else {
		_object ret = Integer_construct(pos + r->offset);
		i->position += 1;
		return ret;
	}
}

_object _rangeIter(_object o) {
	_RangeIterable i = o;
	_IndexIterator it = _allocate(INDEXITERATOR_ID, 0);
	it->parent = i;
	it->next = _rangeNext;
	it->position = 0;
	return it;
}

_object _range(int a, int b) {
	_RangeIterable r = _allocate(RANGEITERABLE_ID, 0);
	r->offset = a;
	r->len = b - a;
	r->iter = _rangeIter;
	return r;
}

_object Integer_through(_object o1, _object o2, _object o3, _object o4) {
	Integer lower = o1;
	Integer upper = o2;
	Boolean includeLower = o3;
	Boolean includeUpper = o4;
	_incr(lower);
	_incr(upper);
	_incr(includeLower);
	_incr(includeUpper);

	int _lower = lower->value;
	int _upper = upper->value;
	int _iL = includeLower->value;
	int _iU = includeUpper->value;

	int low = _lower + (!_iL);
	int high =_upper - (!_iU);

	Iterable _ret;
	if (low > high) {
		_ret = _range(0, 0);
	} else {
		_ret = _range(low, high + 1);
	}

	_decr(lower);
	_decr(upper);
	_decr(includeLower);
	_decr(includeUpper);

	return _ret;
}

_object Integer_onwards(_object o1, _object o2) {
	Integer a = o1;
	Boolean inclusive = o2;
	int start = a->value + !(inclusive->value);
	return _range(start, start - 2);
}

_object Integer_lessThan(_object o1, _object o2, _object o3) {
	Integer a = o1;
	Integer b = o2;
	Boolean strict = o3;
	Boolean _ret;
	_incr(a);
	_incr(b);
	_incr(strict);

	if (strict->value) {
		_ret = Boolean_construct(a->value < b->value);
	} else {
		_ret = Boolean_construct(a->value <= b->value);
	}

	_decr(a);
	_decr(b);
	_decr(strict);

	return _ret;
}

_object Integer_equals(_object o1, _object o2) {
	Integer a = o1;
	Integer b = o2;
	Boolean _ret;
	_incr(a);
	_incr(b);

	_ret = Boolean_construct(a->value == b->value);

	_decr(a);
	_decr(b);
	return _ret;
}


_object Character_construct(char c) {
	Character a = _allocate(CHARACTER_ID, 0);
	a->value = c;
	return a;
}

_object Character_unicode(_object o) {
	Character c = o;
	Integer _ret;
	_incr(c);
	_ret = Integer_construct(charuni(c->value));
	_decr(c);
	return _ret;
}

_object Character_equals(_object o1, _object o2) {
	Boolean a = o1;
	Boolean b = o2;
	Boolean _ret;
	_incr(a);
	_incr(b);

	_ret = Boolean_construct(a->value == b->value);

	_decr(a);
	_decr(b);
	return _ret;
}

int strCmp(const char *s1, const char *s2) {
	while(*s1 && *s2) {
		if (*s1 != *s2) return 0;
		s1++; s2++;
	}
	if (*s1 || *s2) {
		return 0;
	} else {
		return 1;
	}
}

void strCpy(const char *src, char *dest) {
	while ((*dest++ = *src++)); 
}

int strLen(const char *str) {
	int l = 0;
	while (*str) {
		str++;
		l++;
	}
	return l;
}

_object _StringNext(_object o) {
	_IndexIterator i = o;
	int position = i->position;
	String parent = i->parent;
	if (position < parent->length) {
		_object ret = Character_construct(parent->value[position]);
		i->position += 1;
		return ret;
	} else {
		return NULL;
	}
}

_object _StringIter(_object o) {
	Iterable i = o;
	_IndexIterator it = _allocate(INDEXITERATOR_ID, 0);
	it->parent = i;
	it->next = _StringNext;
	it->position = 0;
	return it;
}

_object String_construct(const char* s) {
	String str = _allocate(STRING_ID, 0);
	int len = strLen(s);
	str->length = len;
	char* buff = x3malloc(sizeof(char) * (len + 1));
	int i;
	for (i = 0; i <= len; i++) {
		buff[i] = 0;
	}
	strCpy(s, buff);
	str->value = buff;
	str->iter = _StringIter;
	return str;
}


_object String_equals(_object o1, _object o2) {
	String a = o1;
	String b = o2;
	Boolean _ret;
	_incr(a);
	_incr(b);
	_ret = Boolean_construct(strCmp(a->value, b->value));
	_decr(a);
	_decr(b);
	return _ret;
}

void _print(_object o) {
	String s = o;
	print_line(s->value, s->length);
}

_object character(_object o) {
	Integer unicode = o;
	Character _ret;
	_incr(unicode);
	_ret = Character_construct((char) unicode->value);
	_decr(unicode);
	return _ret;
}

_object string(_object o) {
	Iterable i = o;
	int totalLen = 0;
	_Iterator j = i->iter(i);
	_object c;
	while (c = j->next(j)) {
		totalLen += 1;
	}
	char *buff = x3malloc(sizeof(char) * (totalLen + 1));
	int index;
	for(index = 0; index <= totalLen; index++) {
		buff[index] = 0;
	}
	index = 0;
	j = i->iter(i);
	while (c = j->next(j)) {
		buff[index] = ((Character) c)->value;
		index += 1;
	}
	String ret = String_construct(buff);
	x3free(buff);
	return ret;
}

_object input = NULL;

void __init() {
	input = Iterable_construct((_object[]){}, 0);
	while (1) {
		int next_input_len = next_line_len();
		if (next_input_len) {
			char *buff = x3malloc(sizeof(char) * (next_input_len + 1));
			int j;
		for(j = 0; j <= next_input_len; j++) {
			buff[j] = 0;
		}
		read_line(buff);
		String line = String_construct(buff);
		x3free(buff);
		Iterable i = Iterable_construct((_object[]){line}, 1);
		input = _append(input, i);
		} else {
			break;
		}
	}
	_incr(input);
}