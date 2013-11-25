#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

_object Interone_toIter(_object _o0, _object _o1);
_object Intertwo_twoIter(_object _o0, _object _o1, _object _o2);
_object Emptier_toIter(_object _o0, _object _o1);
_object Emptier_twoIter(_object _o0, _object _o1, _object _o2);
_object Copier_toIter(_object _o0, _object _o1);
_object Copier_twoIter(_object _o0, _object _o1, _object _o2);
_object Doubler_toIter(_object _o0, _object _o1);
_object Doubler_twoIter(_object _o0, _object _o1, _object _o2);
_object Dynamicasaurus_toIter(_object _o0, _object _o1);
_object Dynamicasaurus_twoIter(_object _o0, _object _o1, _object _o2);
_object _prog_main();
_object Interone_toIter(_object _o0, _object _o1) {
	Object _obj = _o0;
	Object t = _o1;
	Object _if_tmp1 = NULL;
	Object _if_tmp2 = NULL;
	Object _ret = NULL;
	Object _if_tmp3 = NULL;
	Object _if_tmp4 = NULL;
	_if_tmp1 = (Object) Boolean_construct(((Object) _obj)->id == 10);
	if (((Boolean) _if_tmp1)->value) {
		_ret = (Object) Dynamicasaurus_toIter(_obj, t);
		_incr(_ret);
		return _ret;
	} else {
		_if_tmp2 = (Object) Boolean_construct(((Object) _obj)->id == 9);
		if (((Boolean) _if_tmp2)->value) {
			_ret = (Object) Doubler_toIter(_obj, t);
			_incr(_ret);
			return _ret;
		} else {
			_if_tmp3 = (Object) Boolean_construct(((Object) _obj)->id == 8);
			if (((Boolean) _if_tmp3)->value) {
				_ret = (Object) Copier_toIter(_obj, t);
				_incr(_ret);
				return _ret;
			} else {
				_if_tmp4 = (Object) Boolean_construct(((Object) _obj)->id == 7);
				if (((Boolean) _if_tmp4)->value) {
					_ret = (Object) Emptier_toIter(_obj, t);
					_incr(_ret);
					return _ret;
				} else {
					_ret = (Object) NULL;
					_incr(_ret);
					return _ret;
				}
			}
		}
	}
}

_object Intertwo_twoIter(_object _o0, _object _o1, _object _o2) {
	Object _obj = _o0;
	Object t1 = _o1;
	Object t2 = _o2;
	Object _ret = NULL;
	Object _if_tmp7 = NULL;
	Object _if_tmp6 = NULL;
	Object _if_tmp5 = NULL;
	Object _if_tmp8 = NULL;
	_if_tmp5 = (Object) Boolean_construct(((Object) _obj)->id == 10);
	if (((Boolean) _if_tmp5)->value) {
		_ret = (Object) Dynamicasaurus_twoIter(_obj, t1, t2);
		_incr(_ret);
		return _ret;
	} else {
		_if_tmp6 = (Object) Boolean_construct(((Object) _obj)->id == 9);
		if (((Boolean) _if_tmp6)->value) {
			_ret = (Object) Doubler_twoIter(_obj, t1, t2);
			_incr(_ret);
			return _ret;
		} else {
			_if_tmp7 = (Object) Boolean_construct(((Object) _obj)->id == 8);
			if (((Boolean) _if_tmp7)->value) {
				_ret = (Object) Copier_twoIter(_obj, t1, t2);
				_incr(_ret);
				return _ret;
			} else {
				_if_tmp8 = (Object) Boolean_construct(((Object) _obj)->id == 7);
				if (((Boolean) _if_tmp8)->value) {
					_ret = (Object) Emptier_twoIter(_obj, t1, t2);
					_incr(_ret);
					return _ret;
				} else {
					_ret = (Object) NULL;
					_incr(_ret);
					return _ret;
				}
			}
		}
	}
}

_object Emptier() {
	Object _tmp = NULL;
	Object _ret = NULL;
	Object _obj = _allocate(6, 0);
	return _obj;
}

_object Emptier_toIter(_object _o0, _object _o1) {
	Object _obj = _o0;
	Object t = _o1;
	Object _ret = NULL;
	_ret = (Object) NULL;
	_incr(_ret);
	return _ret;
}

_object Emptier_twoIter(_object _o0, _object _o1, _object _o2) {
	Object _obj = _o0;
	Object t1 = _o1;
	Object t2 = _o2;
	Object _ret = NULL;
	_ret = (Object) NULL;
	_incr(_ret);
	return _ret;
}

_object Copier() {
	Object _tmp = NULL;
	Object _ret = NULL;
	Object _obj = _allocate(7, 0);
	return _obj;
}

_object Copier_toIter(_object _o0, _object _o1) {
	Object _obj = _o0;
	Object t = _o1;
	Object _ret = NULL;
	_ret = (Object) Iterable_construct(t);
	_incr(_ret);
	return _ret;
}

_object Copier_twoIter(_object _o0, _object _o1, _object _o2) {
	Object _obj = _o0;
	Object t1 = _o1;
	Object t2 = _o2;
	Object _ret = NULL;
	_ret = (Object) _append(Iterable_construct(t1), Iterable_construct(t2));
	_incr(_ret);
	return _ret;
}

_object Doubler() {
	Object _tmp = NULL;
	Object _ret = NULL;
	Object _obj = _allocate(8, 0);
	return _obj;
}

_object Doubler_toIter(_object _o0, _object _o1) {
	Object _obj = _o0;
	Object t = _o1;
	Object _ret = NULL;
	_ret = (Object) _append(Iterable_construct(t), Iterable_construct(t));
	_incr(_ret);
	return _ret;
}

_object Doubler_twoIter(_object _o0, _object _o1, _object _o2) {
	Object _obj = _o0;
	Object t1 = _o1;
	Object t2 = _o2;
	Object _ret = NULL;
	_ret = (Object) _append(Iterable_construct(t1), _append(Iterable_construct(t1), _append(Iterable_construct(t2), Iterable_construct(t2))));
	_incr(_ret);
	return _ret;
}

_object Dynamicasaurus(_object _o0) {
	Object i = _o0;
	Object _tmp = NULL;
	Object _ret = NULL;
	Object _obj = _allocate(9, 1);
	_obj->fields[0] = (Object) i;
	_incr(_obj->fields[0]);
	return _obj;
}

_object Dynamicasaurus_toIter(_object _o0, _object _o1) {
	Object _obj = _o0;
	Object t = _o1;
	Object ret = NULL;
	Object _ret = NULL;
	Object _tmp = NULL;
	_tmp = (Object) ret;
	ret = (Object) NULL;
	_incr(ret);
	_decr(_tmp);
	_IterNode _iter0 = _iterator(Integer_through(Integer_construct(1), _obj->fields[0], Boolean_construct(1), Boolean_construct(1)));
	while (_iter0) {
		_object x = _iter0->curr;
		_tmp = (Object) ret;
		ret = (Object) _append(ret, Iterable_construct(t));
		_incr(ret);
		_decr(_tmp);
		_iter0 = _iter0->next(_iter0);
	}
	_decr(_iter0);
	_ret = (Object) ret;
	_incr(_ret);
	return _ret;
}

_object Dynamicasaurus_twoIter(_object _o0, _object _o1, _object _o2) {
	Object _obj = _o0;
	Object t1 = _o1;
	Object t2 = _o2;
	Object _ret = NULL;
	_ret = (Object) _append(Dynamicasaurus_toIter(_obj, t1), Dynamicasaurus_toIter(_obj, t2));
	_incr(_ret);
	return _ret;
}

_object _prog_main() {
	Object ret = NULL;
	Object _swag_do_swag_ = NULL;
	Object e = NULL;
	Object dy = NULL;
	Object c = NULL;
	Object _ret = NULL;
	Object _tmp = NULL;
	Object _swag_int_swag_ = NULL;
	_tmp = (Object) ret;
	ret = (Object) NULL;
	_incr(ret);
	_decr(_tmp);
	_tmp = (Object) _swag_int_swag_;
	_swag_int_swag_ = (Object) Integer_negative(Integer_construct(1));
	_incr(_swag_int_swag_);
	_decr(_tmp);
	_IterNode _iter1 = _iterator(input);
	while (_iter1) {
		_object i = _iter1->curr;
		_tmp = (Object) e;
		e = (Object) Emptier();
		_incr(e);
		_decr(_tmp);
		_tmp = (Object) ret;
		ret = (Object) _append(ret, Emptier_toIter(e, i));
		_incr(ret);
		_decr(_tmp);
		_tmp = (Object) ret;
		ret = (Object) _append(ret, Emptier_twoIter(e, string(_append(i, String_construct("E"))), string(_append(i, String_construct("E2")))));
		_incr(ret);
		_decr(_tmp);
		_tmp = (Object) c;
		c = (Object) Copier();
		_incr(c);
		_decr(_tmp);
		_tmp = (Object) ret;
		ret = (Object) _append(ret, Copier_toIter(c, i));
		_incr(ret);
		_decr(_tmp);
		_tmp = (Object) ret;
		ret = (Object) _append(ret, Copier_twoIter(c, string(_append(i, String_construct("C"))), string(_append(i, String_construct("C2")))));
		_incr(ret);
		_decr(_tmp);
		_tmp = (Object) _swag_do_swag_;
		_swag_do_swag_ = (Object) Doubler();
		_incr(_swag_do_swag_);
		_decr(_tmp);
		_tmp = (Object) ret;
		ret = (Object) _append(ret, Doubler_toIter(_swag_do_swag_, i));
		_incr(ret);
		_decr(_tmp);
		_tmp = (Object) ret;
		ret = (Object) _append(ret, Doubler_twoIter(_swag_do_swag_, string(_append(i, String_construct("Do"))), string(_append(i, String_construct("Do2")))));
		_incr(ret);
		_decr(_tmp);
		_tmp = (Object) dy;
		dy = (Object) Dynamicasaurus(_swag_int_swag_);
		_incr(dy);
		_decr(_tmp);
		_tmp = (Object) ret;
		ret = (Object) _append(ret, Dynamicasaurus_toIter(dy, i));
		_incr(ret);
		_decr(_tmp);
		_tmp = (Object) ret;
		ret = (Object) _append(ret, Dynamicasaurus_twoIter(dy, string(_append(i, String_construct("Dy"))), string(_append(i, String_construct("Dy2")))));
		_incr(ret);
		_decr(_tmp);
		_tmp = (Object) _swag_int_swag_;
		_swag_int_swag_ = (Object) Integer_plus(_swag_int_swag_, Integer_construct(1));
		_incr(_swag_int_swag_);
		_decr(_tmp);
		_iter1 = _iter1->next(_iter1);
	}
	_decr(_iter1);
	_ret = (Object) ret;
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
