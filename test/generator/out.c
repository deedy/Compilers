#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

_object Printer_line(_object _o0);
_object Printer_print(_object _o0);
_object SinglePrinter_line(_object _o0);
_object SinglePrinter_print(_object _o0);
_object Multiplier_print(_object _o0);
_object _prog_main();
_object Printer_line(_object _o0) {
	Object _obj = _o0;
	_incr(_obj);
	return NULL;
}

_object Printer_print(_object _o0) {
	Object _obj = _o0;
	_incr(_obj);
	return NULL;
}

_object SinglePrinter(_object _o0) {
	Object s = _o0;
	Object _tmp = NULL;
	Object _ret = NULL;
	Object _obj = _allocate(6, 1);
	_obj->fields[0] = (Object) s;
	_incr(_obj->fields[0]);
	return _obj;
}

_object SinglePrinter_line(_object _o0) {
	Object _obj = _o0;
	_incr(_obj);
	return _obj->fields[0];
}

_object SinglePrinter_print(_object _o0) {
	Object _obj = _o0;
	_incr(_obj);
	return Iterable_construct((_object[]){_obj->fields[0]}, 1);
}

_object ConstantPrinter() {
	Object _tmp = NULL;
	Object _ret = NULL;
	Object _obj = _allocate(7, 0);
	return _obj;
}

_object Multiplier(_object _o0, _object _o1) {
	Object s = _o0;
	Object n = _o1;
	Object _tmp = NULL;
	Object _ret = NULL;
	Object _obj = _allocate(8, 4);
	_obj->fields[1] = (Object) s;
	_obj->fields[0] = (Object) n;
	_obj->fields[2] = (Object) String_construct("");
	_obj->fields[3] = (Object) _obj->fields[0];
	while (((Boolean) Integer_lessThan(Integer_construct(0), _obj->fields[3], Boolean_construct(1)))->value) {
		_obj->fields[2] = (Object) string(_append(_obj->fields[2], _obj->fields[1]));
		_obj->fields[3] = (Object) Integer_minus(_obj->fields[3], Integer_construct(1));
	}
	_incr(_obj->fields[0]);
	_incr(_obj->fields[1]);
	_incr(_obj->fields[2]);
	_incr(_obj->fields[3]);
	return _obj;
}

_object Multiplier_print(_object _o0) {
	Object _obj = _o0;
	Object ret = NULL;
	Object nnn = NULL;
	_incr(_obj);
	ret = (Object) Iterable_construct((_object[]){}, 0);
	nnn = (Object) _obj->fields[0];
	while (((Boolean) Integer_lessThan(Integer_construct(0), nnn, Boolean_construct(1)))->value) {
		ret = (Object) _append(ret, Iterable_construct((_object[]){_obj->fields[1]}, 1));
		nnn = (Object) Integer_minus(nnn, Integer_construct(1));
	}
	return ret;
}

_object _prog_main() {
	Object c = NULL;
	Object p = NULL;
	Object m = NULL;
	Object out = NULL;
	Object i = NULL;
	Object ps = NULL;
	ps = (Object) Iterable_construct((_object[]){}, 0);
	c = (Object) Integer_construct(0);
	m = (Object) Integer_construct(0);
	Iterable _iterable0 = (Iterable)input;
	_Iterator _iter0 = _iterable0->iter(_iterable0);
	while (i = _iter0->next(_iter0)) {
		m = (Object) Integer_plus(m, Integer_construct(1));
		if (((Boolean) Integer_equals(c, Integer_construct(0)))->value) {
			ps = (Object) _append(ps, Iterable_construct((_object[]){ConstantPrinter()}, 1));
			c = (Object) Integer_construct(1);
		} else {
			if (((Boolean) Integer_equals(c, Integer_construct(1)))->value) {
				ps = (Object) _append(ps, Iterable_construct((_object[]){SinglePrinter(i)}, 1));
				c = (Object) Integer_construct(2);
			} else {
				ps = (Object) _append(ps, Iterable_construct((_object[]){Multiplier(i, m)}, 1));
				c = (Object) Integer_construct(0);
			}
		}
	}
	out = (Object) Iterable_construct((_object[]){}, 0);
	Iterable _iterable1 = (Iterable)ps;
	_Iterator _iter1 = _iterable1->iter(_iterable1);
	while (p = _iter1->next(_iter1)) {
		out = (Object) _append(out, Iterable_construct((_object[]){SinglePrinter_line(p)}, 1));
	}
	Iterable _iterable2 = (Iterable)ps;
	_Iterator _iter2 = _iterable2->iter(_iterable2);
	while (p = _iter2->next(_iter2)) {
		out = (Object) _append(out, SinglePrinter_print(p));
	}
	return out;
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
