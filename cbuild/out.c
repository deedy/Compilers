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
	Object _ret = NULL;
	_incr(_obj);
	_ret = (Object) NULL;
	_incr(_ret);
	return _ret;
}

_object Printer_print(_object _o0) {
	Object _obj = _o0;
	Object _ret = NULL;
	_incr(_obj);
	_ret = (Object) NULL;
	_incr(_ret);
	return _ret;
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
	Object _ret = NULL;
	_incr(_obj);
	_ret = (Object) _obj->fields[0];
	_incr(_ret);
	return _ret;
}

_object SinglePrinter_print(_object _o0) {
	Object _obj = _o0;
	Object _ret = NULL;
	_incr(_obj);
	_ret = (Object) Iterable_construct(_obj->fields[0]);
	_incr(_ret);
	return _ret;
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
	Object _ret = NULL;
	Object _tmp = NULL;
	Object nnn = NULL;
	_incr(_obj);
	_tmp = (Object) ret;
	ret = (Object) NULL;
	_incr(ret);
	_decr(_tmp);
	_tmp = (Object) nnn;
	nnn = (Object) _obj->fields[0];
	_incr(nnn);
	_decr(_tmp);
	while (((Boolean) Integer_lessThan(Integer_construct(0), nnn, Boolean_construct(1)))->value) {
		_tmp = (Object) ret;
		ret = (Object) _append(ret, Iterable_construct(_obj->fields[1]));
		_incr(ret);
		_decr(_tmp);
		_tmp = (Object) nnn;
		nnn = (Object) Integer_minus(nnn, Integer_construct(1));
		_incr(nnn);
		_decr(_tmp);
	}
	_ret = (Object) ret;
	_incr(_ret);
	return _ret;
}

_object _prog_main() {
	Object _if_tmp1 = NULL;
	Object c = NULL;
	Object _ret = NULL;
	Object _if_tmp2 = NULL;
	Object _tmp = NULL;
	Object m = NULL;
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
	_tmp = (Object) m;
	m = (Object) Integer_construct(0);
	_incr(m);
	_decr(_tmp);
	_IterNode _iter0 = _iterator(input);
	while (_iter0) {
		_object i = _iter0->curr;
		_incr(i);
		_tmp = (Object) m;
		m = (Object) Integer_plus(m, Integer_construct(1));
		_incr(m);
		_decr(_tmp);
		_if_tmp1 = (Object) Integer_equals(c, Integer_construct(0));
		_incr(_if_tmp1);
		if (((Boolean) _if_tmp1)->value) {
			_decr(_if_tmp1);
			_tmp = (Object) ps;
			ps = (Object) _append(ps, Iterable_construct(ConstantPrinter()));
			_incr(ps);
			_decr(_tmp);
			_tmp = (Object) c;
			c = (Object) Integer_construct(1);
			_incr(c);
			_decr(_tmp);
		} else {
			_decr(_if_tmp1);
			_if_tmp2 = (Object) Integer_equals(c, Integer_construct(1));
			_incr(_if_tmp2);
			if (((Boolean) _if_tmp2)->value) {
				_decr(_if_tmp2);
				_tmp = (Object) ps;
				ps = (Object) _append(ps, Iterable_construct(SinglePrinter(i)));
				_incr(ps);
				_decr(_tmp);
				_tmp = (Object) c;
				c = (Object) Integer_construct(2);
				_incr(c);
				_decr(_tmp);
			} else {
				_decr(_if_tmp2);
				_tmp = (Object) ps;
				ps = (Object) _append(ps, Iterable_construct(Multiplier(i, m)));
				_incr(ps);
				_decr(_tmp);
				_tmp = (Object) c;
				c = (Object) Integer_construct(0);
				_incr(c);
				_decr(_tmp);
			}
		}
		_iter0 = _iter0->next(_iter0);
	}
	x3free(_iter0);
	_tmp = (Object) out;
	out = (Object) NULL;
	_incr(out);
	_decr(_tmp);
	_IterNode _iter1 = _iterator(ps);
	while (_iter1) {
		_object p = _iter1->curr;
		_incr(p);
		_tmp = (Object) out;
		out = (Object) _append(out, Iterable_construct(SinglePrinter_line(p)));
		_incr(out);
		_decr(_tmp);
		_iter1 = _iter1->next(_iter1);
	}
	x3free(_iter1);
	_IterNode _iter2 = _iterator(ps);
	while (_iter2) {
		_object p = _iter2->curr;
		_incr(p);
		_tmp = (Object) out;
		out = (Object) _append(out, SinglePrinter_print(p));
		_incr(out);
		_decr(_tmp);
		_iter2 = _iter2->next(_iter2);
	}
	x3free(_iter2);
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
	_decr(_i);
	_decr(input);
}
