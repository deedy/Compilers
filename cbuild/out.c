#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

_object Pair_pi2(_object _o0);
_object Pair_pi1(_object _o0);
_object Map_add(_object _o0, _object _o1, _object _o2);
_object Map_lookup(_object _o0, _object _o1);
_object _prog_main();
_object Pair(_object _o0, _object _o1) {
	Object a = _o0;
	Object b = _o1;
	Object _tmp = NULL;
	Object _ret = NULL;
	Object _obj = _allocate(6, 2);
	_obj->fields[0] = (Object) a;
	_obj->fields[1] = (Object) b;
	_incr(_obj->fields[0]);
	_incr(_obj->fields[1]);
	return _obj;
}

_object Pair_pi2(_object _o0) {
	Object _obj = _o0;
	Object _ret = NULL;
	_incr(_obj);
	_ret = (Object) _obj->fields[1];
	_incr(_ret);
	_decr(_obj/*gc*/);
	return _ret;
}

_object Pair_pi1(_object _o0) {
	Object _obj = _o0;
	Object _ret = NULL;
	_incr(_obj);
	_ret = (Object) _obj->fields[0];
	_incr(_ret);
	_decr(_obj/*gc*/);
	return _ret;
}

_object Map(_object _o0) {
	Object lst = _o0;
	Object _tmp = NULL;
	Object _ret = NULL;
	Object _obj = _allocate(7, 1);
	_obj->fields[0] = (Object) lst;
	_incr(_obj->fields[0]);
	return _obj;
}

_object Map_add(_object _o0, _object _o1, _object _o2) {
	Object _obj = _o0;
	Object key = _o1;
	Object val = _o2;
	Object _ret = NULL;
	_incr(key);
	_incr(val);
	_incr(_obj);
	_ret = (Object) Map(_append(_obj->fields[0], Iterable_construct(Pair(key, val))));
	_incr(_ret);
	_decr(key/*gc*/);
	_decr(val/*gc*/);
	_decr(_obj/*gc*/);
	return _ret;
}

_object Map_lookup(_object _o0, _object _o1) {
	Object _obj = _o0;
	Object key = _o1;
	Object _if_tmp1 = NULL;
	Object _ret = NULL;
	_incr(key);
	_incr(_obj);
	_IterNode _iter0 = _iterator(_obj->fields[0]);
	while (_iter0) {
		_object p = _iter0->curr;
		_incr(p);
		_if_tmp1 = (Object) String_equals(Pair_pi1(p), key);
		_incr(_if_tmp1);
		if (((Boolean) _if_tmp1)->value) {
			_decr(_if_tmp1);
			_ret = (Object) Iterable_construct(Pair_pi2(p));
			_incr(_ret);
			_decr(p/*gc*/);
			return _ret;
		} else {
			_decr(_if_tmp1);
		}
		_iter0 = _iter0->next(_iter0);
	}
	x3free(_iter0);
	_ret = (Object) NULL;
	_incr(_ret);
	return _ret;
}

_object _prog_main() {
	Object ret = NULL;
	Object _ret = NULL;
	Object _tmp = NULL;
	Object zz = NULL;
	Object z = NULL;
	Object x = NULL;
	_tmp = (Object) x;
	x = (Object) Map(NULL);
	_incr(x);
	_decr(_tmp);
	_IterNode _iter1 = _iterator(Integer_through(Integer_construct(65), Integer_construct(90), Boolean_construct(1), Boolean_construct(1)));
	while (_iter1) {
		_object i = _iter1->curr;
		_incr(i);
		_tmp = (Object) x;
		x = (Object) Map_add(x, string(Iterable_construct(character(i))), i);
		_incr(x);
		_decr(_tmp);
		_decr(i/*gc*/);
		_iter1 = _iter1->next(_iter1);
	}
	x3free(_iter1);
	_tmp = (Object) ret;
	ret = (Object) NULL;
	_incr(ret);
	_decr(_tmp);
	_IterNode _iter2 = _iterator(input);
	while (_iter2) {
		_object i = _iter2->curr;
		_incr(i);
		_tmp = (Object) z;
		z = (Object) Map_lookup(x, i);
		_incr(z);
		_decr(_tmp);
		_decr(i/*gc*/);
		_tmp = (Object) zz;
		zz = (Object) NULL;
		_incr(zz);
		_decr(_tmp);
		_IterNode _iter3 = _iterator(z);
		while (_iter3) {
			_object j = _iter3->curr;
			_incr(j);
			_tmp = (Object) zz;
			zz = (Object) _append(zz, Iterable_construct(character(j)));
			_incr(zz);
			_decr(_tmp);
			_decr(j/*gc*/);
			_iter3 = _iter3->next(_iter3);
		}
		x3free(_iter3);
		_tmp = (Object) ret;
		ret = (Object) _append(ret, Iterable_construct(string(zz)));
		_incr(ret);
		_decr(_tmp);
		_decr(zz/*gc*/);
		_iter2 = _iter2->next(_iter2);
	}
	x3free(_iter2);
	_ret = (Object) ret;
	_incr(_ret);
	_decr(ret/*gc*/);
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
