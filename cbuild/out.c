#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

_object expensive(_object _o0);
_object prog();
_object _prog_main();
_object expensive(_object _o0) {
	Object n = _o0;
	_incr(n);
	if (((Boolean) Integer_lessThan(n, Integer_construct(0), Boolean_construct(0)))->value) {
		return Integer_construct(0);
	} else {
		return Integer_plus(expensive(Integer_minus(n, Integer_construct(1))), expensive(Integer_minus(n, Integer_construct(2))));
	}
}

_object prog() {
	Object _3 = NULL;
	Object fibb = NULL;
	Object n = NULL;
	Object sum = NULL;
	Object _0 = NULL;
	Object fib = NULL;
	Object _1 = NULL;
	Object i = NULL;
	Object _2 = NULL;
	n = (Object) Integer_construct(40);
	_1 = (Object) expensive(n);
	_1 = (Object) expensive(n);
	fib = (Object) _1;
	_0 = (Object) expensive(Integer_construct(39));
	_2 = (Object) expensive(Integer_construct(39));
	_2 = (Object) expensive(Integer_construct(39));
	fibb = (Object) _2;
	i = (Object) Integer_construct(0);
	sum = (Object) Integer_construct(0);
	_3 = (Object) Integer_lessThan(i, Integer_construct(1000), Boolean_construct(1));
	_3 = (Object) Integer_lessThan(i, Integer_construct(1000), Boolean_construct(1));
	_1 = (Object) Integer_construct(0);
	_2 = (Object) Integer_construct(0);
	_3 = (Object) Integer_construct(0);
	if (((Boolean) Integer_equals(sum, Integer_times(Integer_minus(fib, fibb), Integer_construct(1000))))->value) {
		return Iterable_construct((_object[]){String_construct("Yes")}, 1);
	} else {
	}
	return Iterable_construct((_object[]){String_construct("No")}, 1);
}

_object _prog_main() {
	return prog();
}

void cubex_main() {
	_object _i,_j
;	_Iterator _i_iter
;	__init();
	_i = _prog_main();
	_i_iter = ((Iterable)_i)->iter(_i);
	while((_j = _i_iter->next(_i_iter))) {
		_print(_j);
	}
	_free_all_the_things();
}
