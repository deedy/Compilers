/* iterables:
	Iterables are represented as linked lists of linked lists
	Each one has a current value, a next function, and a nextIter
	The next function updates the iterable, setting curr to the next value
	nextIter is the continuation in an append
	in a ++ b ++ c, a->nextIter = b, and b->nextIter = c, while c->nextIter = null
	the appending of already appended iterables in this way is a deep copy
	on the higher-level linked list
	
*/
struct Iterable_t {
	// current value
	void *curr;
	// function to update iterator
	// returns 1 if iterable continues
	// else returns 0
	int (*next)(Iterable_t*);
	// where to go when this iterator finishes
	// used for appending
	Iterable_t* nextIter;
}
typedef struct Iterable_t* Iterable;

// update an iterable
void next(Iterable i) {
	// derefernece the generator function
	int n = *(i->next)(i);
	if (n) {
		// next item found, iterable updated
		return n;
	} else {
		// return the next iterable
		// null if this is not part of an append
		Iterable j = i->nextIter;
		if(j) {
			// update fields
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
	Iterable b = malloc(sizeof(struct Iterable));
	b->curr = a->curr;
	b->next = a->next;
	b->nextIter = a->nextIter;
	return b;
}

Iterable append(Iterable a, Iterable b) {
	// deep copy a
	Iterable c = copy(a);
	Iterable d = c;
	while(d->next) {
		// similar to List.append in OCaml
		d->next = copy(d->next);
		d = d->next;
	}
	d->next = b;
	return c;
}



int Boolean_negate(int b) {
	return !b;
}

int Boolean_and(int a, int b) {
	return a && b;
}

int Boolean_or(int a, int b) {
	return a || b;
}

int common_iterable_next(Iterable i) {
	return 0;
}

int Boolean_through(int lower, int upper, int includeLower, int includeUpper) {
	int low = lower || (!includeLower);
	int high = upper && (!includeUpper);
	if (low > high) {
		return NULL;
	} else {
		Iterable i = malloc(sizeof(struct Iterable_t));
		i->next = common_iterable_next;
		i->nextIter = NULL;
		Iterable prev = i;
		for (int j = low + 1; j <= high; j++) {
			Iterable n = malloc(sizeof(struct Iterable_t));
			n->next = common_iterable_next;
			n->nextIter = NULL;
			prev->nextIter = n;
		}
	}
}

int Boolean_onwards_next(Iterable b) {
	if(b->curr) {
		return 0;
	} else {
		b->curr = 1;
		return 1;
	}
}

int Boolean_onward(int a, int inclusive) {
	int start = a + (!inclusive);
	if (start == 2) {
		return NULL;
	}
	Iterable i = malloc(sizeof(struct Iterable_t));
	i->curr = start;
	i->next = Boolean_onwards_next;
	i->nextIter = NULL;
	return i;
}

int Boolean_lessThan(int a, int b, int strict) {
	if (strict) {
		return a < b;
	} else {
		return a <= b;
	}
}

int Boolean_equals(int a, int b) {
	return a == b;
}

int Integer_negative(int a) {
	return -a;
}

int Integer_times(int a, int b) {
	return a * b;
}

Iterable Integer_divide(int a, int b) {
	if (b == 0) {
		// return empty iterable
		return NULL;
	} else {
		// return iterable with one thing
		Iterable i = x3malloc(sizeof(struct Iterable_t));
		i->curr = a / b;
		i->next = common_iterable_next;
		i->nextIter = NULL;
		return i;
	}
}

Iterable Integer_modulo(int a, int b) {
	if (b == 0) {
		// return empty iterable
		return NULL;
	} else {
		// return iterable with one thing
		Iterable i = x3malloc(sizeof(struct Iterable_t));
		i->curr = a % b;
		i->next = common_iterable_next;
		i->nextIter = NULL;
		return i;
	}
}

int Integer_plus(int a, int b) {
	return a + b;
}

int Integer_plus(int a, int b) {
	return a - b;
}

int Integer_through(int lower, int upper, int includeLower, int includeUpper) {	
	// break into many iterables of size 1
	int low = lower + (!includeLower);
	int high = upper - (!includeUpper);
	if (high > low) {
		return NULL
	} else {
		Iterable i = malloc(sizeof(struct Iterable_t));
		i->next = common_iterable_next;
		i->nextIter = NULL;
		Iterable prev = i;
		for (int j = low + 1; j <= high; j++) {
			Iterable n = malloc(sizeof(struct Iterable_t));
			n->next = common_iterable_next;
			n->nextIter = NULL;
			prev->nextIter = n;
		}
		return i;
	}
}

int Integer_onwards_next(Iterable i) {
	i->curr += 1;
	return 1;
}

Iterable Integer_onwards(int a, int inclusive) {
	int start = a + (!inclusive);
	Iterable i = malloc(sizeof(struct Iterable_t));
	i->curr = start;
	i->next = Integer_onwards_next;
	i->nextIter = NULL;
	return i;
}

int Integer_lessThan(int a, int b, int strict) {
	if (strict) {
		return a <= b;
	} else {
		return a < b;
	}
}

int Integer_equals(int a, int b) {
	return a == b;
}

