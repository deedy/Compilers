#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

_object append(_object _o0, _object _o1);
_object _prog_main();
_object __generated_name_swag08C14();
_object __generated_name_swagP9KE7G();
_object __generated_name_swagXFEQW();
_object append(_object _o0, _object _o1) {
	Object a = _o0;
	Object b = _o1;
	_incr(a);
	_incr(b);
	return _append(a, b);
}

_object _prog_main() {
	return __generated_name_swag08C14();
}

_object __generated_name_swag08C14() {
	Object _it = NULL;
	_it = (Object) Iterable_construct((_object[]){}, 0);
	_it = (Object) _append(_it, Iterable_construct((_object[]){string(append(__generated_name_swagP9KE7G(), __generated_name_swagXFEQW()))}, 1));
	return _it;
}

_object __generated_name_swagP9KE7G() {
	Object _it = NULL;
	Object i = NULL;
	_it = (Object) Iterable_construct((_object[]){}, 0);
	Iterable _iterable0 = (Iterable)String_construct("hello");
	_Iterator _iter0 = _iterable0->iter(_iterable0);
	while (i = _iter0->next(_iter0)) {
		_it = (Object) _append(_it, Iterable_construct((_object[]){i}, 1));
	}
	return _it;
}

_object __generated_name_swagXFEQW() {
	Object _it = NULL;
	Object i = NULL;
	_it = (Object) Iterable_construct((_object[]){}, 0);
	Iterable _iterable1 = (Iterable)String_construct(" world");
	_Iterator _iter1 = _iterable1->iter(_iterable1);
	while (i = _iter1->next(_iter1)) {
		_it = (Object) _append(_it, Iterable_construct((_object[]){i}, 1));
	}
	return _it;
}

void cubex_main() {
	__init();
	_object _i = _prog_main();
	_Iterator _i_iter = ((Iterable)_i)->iter(_i);
	_object _j;	while(_j = _i_iter->next(_i_iter)) {
		_print(_j);
	}
	_free_all_the_things();
}
