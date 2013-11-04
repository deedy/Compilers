#define NULL 0

typedef void* _object;

struct Object_t {
	int id;
	_object parent;
	int ref_count;
	int field_count;
	_object *fields;
};

typedef struct Object_t* Object;

struct _IterNode_t {
	_object curr;
	struct _IterNode_t* (*next)(struct _IterNode_t*);
	_object nextIter;
};

typedef struct _IterNode_t* _IterNode;

struct Iterable_t {
	int id;
	_object parent;
	int ref_count;
	int field_count;
	_object *fields;
	_IterNode (*next)(_IterNode);
};

typedef struct Iterable_t* Iterable;

struct Integer_t {
	int id;
	_object parent;
	int ref_count;
	int field_count;
	_object *fields;
	int value;
};

typedef struct Integer_t* Integer;

struct Boolean_t {
	int id;
	_object parent;
	int ref_count;
	int field_count;
	_object *fields;
	int value;
};

typedef struct Boolean_t* Boolean;

struct Character_t {
	int id;
	_object parent;
	int ref_count;
	int field_count;
	_object *fields;
	char value;
};

typedef struct Character_t* Character;

struct String_t {
	int id;
	_object parent;
	int ref_count;
	int field_count;
	_object *fields;
	_IterNode (*next)(_IterNode);
	char* value;
};

typedef struct String_t* String;

_object _allocate(int id, int field_count) {
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
	o->field_count = field_count;
	_object *fields = x3malloc(sizeof(_object) * field_count);
	int i;
	for (i = 0; i < field_count; i++) {
		fields[i] = NULL;
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
			int i;
			for (i = 0; i < o->field_count; i++) {
				_decr(o->fields[i]);
			}
			x3free(o->fields);
			_decr(o->parent);
			if(o->id == 4) {
				x3free(((String) o)->value);
			}
			x3free(o);
		}
	}
}

_IterNode _iterator(_object o) {
	Iterable i = o;
	if (!i) {
		return NULL;
	}
	_IterNode n = x3malloc(sizeof(struct _IterNode_t));
	if(!(i->fields[0])) {
		return NULL;
	}
	n->curr = i->fields[0];
	n->nextIter = (Iterable) i->fields[1];
	n->next = i->next;
	return n;
}

_IterNode _common_next(_IterNode node) {
	if (!node) {
		return NULL;
	} else {
		_IterNode ret = _iterator(node->nextIter);
		x3free(node);
		return ret;
	}
}

_object Iterable_construct(_object ptr) {
	Object o = ptr;
	Iterable a = _allocate(0, 2);
	_incr(o);
	a->fields[0] = o;
	a->fields[1] = NULL;
	a->next = _common_next;
	return a;
}

Iterable _copy(Iterable a) {
	if(!a) {
		return NULL;
	}
	Iterable b = Iterable_construct(a->fields[0]);
	b->next = a->next;
	return b;
}

Iterable _append(_object o1, _object o2) {
	Iterable a = o1;
	Iterable b = o2;
	if(!a) {
		return b;
	}
	Iterable c = _copy(a);
	c->fields[1] = _append(a->fields[1], b);
	_incr(c->fields[1]);
	return c;
}

_object Boolean_construct(int b) {
	Boolean a = _allocate(2, 0);
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
		_ret = NULL;
	} else {
		Iterable i = Iterable_construct(lower);
		Iterable j = Boolean_through(Boolean_construct(low + 1), upper, includeLower, includeUpper);
		_ret = _append(i, j);
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
	Integer a = _allocate(1, 0);
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
		_ret = NULL;
	} else {
		Iterable i = Iterable_construct(Integer_construct(a->value / b->value));
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
		_ret = NULL;
	} else {
		Iterable i = Iterable_construct(Integer_construct(a->value % b->value));
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
		_ret = NULL;
	} else {
		Iterable i = Iterable_construct(lower);
		Iterable j = Integer_through(Integer_construct(low + 1), upper, Boolean_construct(1), includeUpper);
		_ret = _append(i, j);
	}

	_decr(lower);
	_decr(upper);
	_decr(includeLower);
	_decr(includeUpper);

	return _ret;
}

_IterNode Integer_onwards_next(_IterNode i) {
	int val = ((Integer) i->curr)->value;
	_decr(i->curr);
	i->curr = Integer_construct(val + 1);
	return i;
}

_object Integer_onwards(_object o1, _object o2) {
	Integer a = o1;
	Boolean inclusive = o2;
	int start = a->value + !(inclusive->value);
	Iterable i = Iterable_construct(Integer_construct(start));
	i->next = Integer_onwards_next;
	return i;
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
	Character a = _allocate(3, 0);
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
    return s - start + 1;
}

_object strIter(const char *s) {
	if(!*s) {
		return NULL;
	} else {
		Iterable i = Iterable_construct(Character_construct(*s));
		return _append(i, strIter(s + 1));
	}
}

_object String_construct(const char* s) {
	if (!s) {
		return NULL;
	} else {
		String str = _allocate(5, 2);
		char* buff = x3malloc(sizeof(char) * strLen(s));
		strCpy(s, buff);
		Iterable i = strIter(s);
		if(i) {
			str->fields = i->fields;
			str->next = i->next;
			x3free(i);
		}
		str->value = buff;
		return str;
	}
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
	print_line(s->value, strLen(s->value));
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
	_IterNode iter = _iterator(i);
	while (iter) {
		totalLen += 1;
		iter = iter->next(iter);
	} x3free(iter);

	char *buff = x3malloc(sizeof(char) * (totalLen + 1));
	int j;
	for(j = 0; j < totalLen + 1; j++) {
		buff[j] = 0;
	}
	int index = 0;
	iter = _iterator(i);
	while (iter) {
		Character c = iter->curr;
		buff[index] = c->value;
		iter = iter->next(iter);
		index += 1;
	} x3free(iter);
	String ret = String_construct(buff);
	x3free(buff);
	return ret;
}

_object input = NULL;

void __init() {
	while (1) {
		int next_input_len = next_line_len();
		if (next_input_len) {
			char *buff = x3malloc(sizeof(char) * (next_input_len + 1));
			read_line(buff);
			String line = String_construct(buff);
			x3free(buff);
			Iterable i = Iterable_construct(line);
			input = _append(input, i);
		} else {
			break;
		}
	}
	_incr(input);
}
