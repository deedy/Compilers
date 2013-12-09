#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

_object Printer_print(_object _o0);
_object ConstantPrinter_print(_object _o0);
_object Doubler_print(_object _o0);
_object _prog_main();
_object Printer_print(_object _o0) {
	Object _obj = _o0;
	Object _if_tmp1 = NULL;
	Object _if_tmp2 = NULL;
	Object _ret = NULL;
	_incr(_obj);
	_if_tmp1 = (Object) Boolean_construct(((Object) _obj)->id == 7);
	if (((Boolean) _if_tmp1)->value) {
		_ret = (Object) Doubler_print(_obj);
		_incr(_ret);
		return _ret;
	} else {
		_if_tmp2 = (Object) Boolean_construct(((Object) _obj)->id == 6);
		if (((Boolean) _if_tmp2)->value) {
			_ret = (Object) ConstantPrinter_print(_obj);
			_incr(_ret);
			return _ret;
		} else {
			_ret = (Object) NULL;
			_incr(_ret);
			return _ret;
		}
	}
}

_object ConstantPrinter() {
	Object _tmp = NULL;
	Object _ret = NULL;
	Object _obj = _allocate(6, 0);
	return _obj;
}

_object ConstantPrinter_print(_object _o0) {
	Object _obj = _o0;
	Object _ret = NULL;
	_incr(_obj);
	_ret = (Object) Iterable_construct(String_construct("Constant"));
	_incr(_ret);
	return _ret;
}

_object Doubler(_object _o0) {
	Object s = _o0;
	Object _tmp = NULL;
	Object _ret = NULL;
	Object _obj = _allocate(7, 1);
	_obj->fields[0] = (Object) s;
	_incr(_obj->fields[0]);
	return _obj;
}

_object Doubler_print(_object _o0) {
	Object _obj = _o0;
	Object _ret = NULL;
	_incr(_obj);
	_ret = (Object) _append(Iterable_construct(_obj->fields[0]), Iterable_construct(_obj->fields[0]));
	_incr(_ret);
	return _ret;
}

_object _prog_main() {
	Object c = NULL;
	Object _ret = NULL;
	Object _if_tmp3 = NULL;
	Object _tmp = NULL;
	Object out = NULL;
	Object ps = NULL;
	_tmp = (Object) ps;
	ps = (Object) NULL;
	_incr(ps);
	_decr(_tmp);
	_tmp = (Object) c;
	c = (Object) Integer_construct(0);
	_incr(c);
	_decr(_tmp);
	_IterNode _iter0 = _iterator(input);
	while (_iter0) {
		_object i = _iter0->curr;
		_if_tmp3 = (Object) Integer_equals(c, Integer_construct(0));
		if (((Boolean) _if_tmp3)->value) {
			_tmp = (Object) ps;
			ps = (Object) _append(ps, Iterable_construct(ConstantPrinter()));
			_incr(ps);
			_decr(_tmp);
			_tmp = (Object) c;
			c = (Object) Integer_construct(1);
			_incr(c);
			_decr(_tmp);
		} else {
			_tmp = (Object) ps;
			ps = (Object) _append(ps, Iterable_construct(Doubler(i)));
			_incr(ps);
			_decr(_tmp);
			_tmp = (Object) c;
			c = (Object) Integer_construct(0);
			_incr(c);
			_decr(_tmp);
		}
		_iter0 = _iter0->next(_iter0);
	}
	_decr(_iter0);
	_tmp = (Object) out;
	out = (Object) NULL;
	_incr(out);
	_decr(_tmp);
	_IterNode _iter1 = _iterator(ps);
	while (_iter1) {
		_object p = _iter1->curr;
		_tmp = (Object) out;
		out = (Object) _append(out, Printer_print(p));
		_incr(out);
		_decr(_tmp);
		_iter1 = _iter1->next(_iter1);
	}
	_decr(_iter1);
	_ret = (Object) out;
	_incr(_ret);
	return _ret;
}

void cubex_main() {
	__init();
	_object _i = _prog_main();
	_IterNode _i_iter = _iterator(_i);
	while(_i_iter) {
	_print(_i_iter->curr);
	_i_iter = _i_iter->next(_i_iter);
	}
	x3free(_i_iter);
	_free_all_the_things();
}
