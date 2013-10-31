#define NULL 0

typedef void* object;

struct Iterable_t {
	object curr;
	int (*next)(struct Iterable_t*);
	struct Iterable_t* nextIter;
};

typedef struct Iterable_t* Iterable;

Iterable Iterable_construct(object o) {
	Iterable a = x3malloc(sizeof(struct Iterable_t));
	a->curr = o;
	a->next = NULL;
	a->nextIter = NULL;
	return a;
}

struct Integer_t {
	int value;
};

typedef struct Integer_t* Integer;

Integer Integer_construct(int n) {
	Integer a = x3malloc(sizeof(struct Integer_t));
	a->value = n;
	return a;
}

struct Boolean_t {
	int value;
};

typedef struct Boolean_t* Boolean;

Boolean Boolean_construct(int b) {
	Boolean a = x3malloc(sizeof(struct Boolean_t));
	a->value = b;
	return a;
}

int next(Iterable i) {
	int n = (i->next)(i);
	if (n) {
		return n;
	} else {
		Iterable j = i->nextIter;
		if(j) {
			i->curr = j->curr;
			i->next = j->next;
			i->nextIter = j->nextIter;
			return 1;
		} else {
			return 0;
		}
	}
}

Iterable copy(Iterable a) {
	if(!a) {
		return NULL;
	}
	Iterable b = Iterable_construct(a->curr);
	b->next = a->next;
	b->nextIter = a->nextIter;
	return b;
}

Iterable append(Iterable a, Iterable b) {
	Iterable c = copy(a);
	Iterable d = c;
	while(d->nextIter) {
		d->nextIter = copy(d->nextIter);
		d = d->nextIter;
	}
	d->nextIter = b;
	return c;
}

Boolean Boolean_negate(Boolean b) {
	return Boolean_construct(!b->value);
}

Boolean Boolean_and(Boolean a, Boolean b) {
	return Boolean_construct(a->value && b->value);
}

Boolean Boolean_or(Boolean a, Boolean b) {
	return Boolean_construct(a->value || b->value);
}

int common_iterable_next(Iterable i) {
	i->curr = NULL;
	return 0;
}

Iterable Boolean_through(Integer lower, Integer upper, Boolean includeLower, Boolean includeUpper) {
	int _lower = lower->value;
	int _upper = upper->value;
	int _iL = includeLower->value;
	int _iU = includeUpper->value;

	int low = _lower || (!_iL);
	int high =_upper && (!_iU);
	if (low > high) {
		return NULL;
	} else {
		Iterable i = Iterable_construct(lower);
		i->next = common_iterable_next;
		i->nextIter = NULL;
		Iterable prev = i;
		int j;
		for (j = low + 1; j <= high; j++) {
			Iterable n = Iterable_construct(Integer_construct(j));
			n->next = common_iterable_next;
			n->nextIter = NULL;
			prev->nextIter = n;
		}
		return i;
	}
}

int Boolean_onwards_next(Iterable b) {
	if(b->curr && ((Boolean) b->curr)->value) {
		return 0;
	} else {
		b->curr = Boolean_construct(1);
		return 1;
	}
}

Iterable Boolean_onward(Boolean a, Boolean inclusive) {
	int start = a->value + (!(inclusive->value));
	if (start == 2) {
		return NULL;
	}
	Iterable i = Iterable_construct(Boolean_construct(start));
	i->next = Boolean_onwards_next;
	i->nextIter = NULL;
	return i;
}

Boolean Boolean_lessThan(Boolean a, Boolean b, Boolean strict) {
	if (strict->value) {
		return Boolean_construct(a->value < b->value);
	} else {
		return Boolean_construct(a->value <= b->value);
	}
}

Boolean Boolean_equals(Boolean a, Boolean b) {
	return Boolean_construct(a->value == b->value);
}


Integer Integer_negative(Integer a) {
	return Integer_construct(-(a->value));
}

Integer Integer_times(Integer a, Integer b) {
	return Integer_construct(a->value * b->value);
}

Iterable Integer_divide(Integer a, Integer b) {
	if (b->value == 0) {
		return NULL;
	} else {
		Iterable i = Iterable_construct(Integer_construct(a->value / b->value));
		i->next = common_iterable_next;
		i->nextIter = NULL;
		return i;
	}
}

Iterable Integer_modulo(Integer a, Integer b) {
	if (b->value == 0) {

		return NULL;
	} else {
		Iterable i = Iterable_construct(Integer_construct(a->value % b->value));
		i->next = common_iterable_next;
		i->nextIter = NULL;
		return i;
	}
}



Integer Integer_plus(Integer a, Integer b) {
	return Integer_construct(a->value + b->value);
}

Integer Integer_minus(Integer a, Integer b) {
	return Integer_construct(a->value - b->value);
}

Iterable Integer_through(Integer lower, Integer upper, Boolean includeLower, Boolean includeUpper) {
	int _lower = lower->value;
	int _upper = upper->value;
	int _iL = includeLower->value;
	int _iU = includeUpper->value;

	int low = _lower || (!_iL);
	int high =_upper && (!_iU);

	if (high > low) {
		return NULL;
	} else {
		Iterable i = Iterable_construct(Integer_construct(low));
		i->next = common_iterable_next;
		i->nextIter = NULL;
		Iterable prev = i;
		int j;
		for (j = low + 1; j <= high; j++) {
			Iterable n = Iterable_construct(Integer_construct(j));
			n->next = common_iterable_next;
			n->nextIter = NULL;
			prev->nextIter = n;
		}
		return i;
	}
}

int Integer_onwards_next(Iterable i) {
	((Integer)(i->curr))->value += 1;
	return 1;
}


Iterable Integer_onwards(Integer a, Boolean inclusive) {
	int start = a->value + !(inclusive->value);
	Iterable i = Iterable_construct(Integer_construct(start));
	i->next = Integer_onwards_next;
	i->nextIter = NULL;
	return i;
}

Boolean Integer_lessThan(Integer a, Integer b, Boolean strict) {
	if (strict->value) {
		return Boolean_construct(a->value < b->value);
	} else {
		return Boolean_construct(a->value <= b->value);
	}
}

Boolean Integer_equals(Integer a, Integer b) {
	return Boolean_construct(a->value == b->value);
}


struct Character_t {
	char value;
};

typedef struct Character_t* Character;

Character Character_construct(char c) {
	Character a = x3malloc(sizeof(struct Character_t));
	a->value = c;
	return a;
}

Integer Character_unicode(Character c) {
	return Integer_construct(charuni(c->value));
}

Boolean Character_equals(Character a, Character b) {
	return Boolean_construct(a->value == b->value);
}

struct String_t {
	object curr;
	int (*next)(struct Iterable_t*);
	struct Iterable_t* nextIter;
};

typedef struct String_t* String;

String String_construct(char* s) {
	if (!s) {
		return NULL;
	} else {
		if (!(*s)) {
			return NULL;
		} else {
			String i = (String) Iterable_construct(Character_construct(*s));
			i->next = common_iterable_next;
			String prev = i;
			s++;
			while(*s) {
				Iterable j = Iterable_construct(Character_construct(*s));
				j->next = common_iterable_next;
				prev->nextIter = j;
				prev = j;
				s++;
			}
			return i;
		}
	}
}

int Iterable_length(Iterable i) {
	int len;
	Iterable _a = copy(i);
	while(_a && _a->curr) {
		len += 1;
		next(_a);
	}
	return len;
}

int strcmp(const char *s1, const char *s2) {
  int ret = 0;
  while (!(ret = *(unsigned char *) s1 - *(unsigned char *) s2) && *s2) ++s1, ++s2;
  if (ret < 0)
    ret = -1;
  else if (ret > 0)
    ret = 1 ;
  return ret;
}


char* String_buffer(String s, int *len) {
	*len = Iterable_length((Iterable) s);
	char* buff = x3malloc(sizeof(char) * *len);
	int iter = 0;
	Iterable _a = copy((Iterable) s);
	while(_a && _a->curr) {
		buff[iter++] = ((Character) _a->curr)->value;
		next(_a);
	}
	return buff;
}

Boolean String_equals(String a, String b) {
	int len1, len2;
	char* buff_a = String_buffer(a, &len1);
	char* buff_b = String_buffer(b, &len2);
	return Boolean_construct(strcmp(buff_a, buff_b));
}

void print(String s) {
	int len;
	char* buff = String_buffer(s, &len);
	print_line(buff, len);
}
