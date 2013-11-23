#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

<<<<<<< HEAD
_object _prog_main();
_object _prog_main() {
Object f = NULL;

Object d = NULL;

Object e = NULL;

Object b = NULL;

Object c = NULL;
=======
_object Multiplier_print(_object _o0);
_object _prog_main();
_object Multiplier(_object _o0, _object _o1) {
Object s = _o0;
Object n = _o1;
Object _tmp = NULL;
Object _ret = NULL;
Object _obj = _allocate(6, 2);
_tmp = _obj->fields[1];
_obj->fields[1] = (Object) s;
_incr(_obj->fields[1]);
_decr(_tmp);

_tmp = _obj->fields[0];
_obj->fields[0] = (Object) n;
_incr(_obj->fields[0]);
_decr(_tmp);

return _obj;}
>>>>>>> 38b66c2c8f8b9fd531098dea329f5a316690e25d

Object a = NULL;

Object _0 = NULL;

<<<<<<< HEAD
Object _1 = NULL;
=======
Object v = NULL;
>>>>>>> 38b66c2c8f8b9fd531098dea329f5a316690e25d

Object _tmp = NULL;

Object _ret = NULL;

_tmp = a;
a = (Object) Integer_construct(1);
_incr(a);
_decr(_tmp);

<<<<<<< HEAD
_tmp = b;
b = (Object) Integer_construct(0);
_incr(b);
_decr(_tmp);

_tmp = _1;
_1 = (Object) Integer_construct(0);
_incr(_1);
_decr(_tmp);

if (((Boolean) Boolean_construct(1))->value) {
_tmp = _1;
_1 = (Object) Integer_plus(a, b);
_incr(_1);
=======
_tmp = v;
v = (Object) _obj->fields[0];
_incr(v);
_decr(_tmp);

while (((Boolean) Integer_lessThan(Integer_construct(0), v, Boolean_construct(1)))->value) {
_tmp = v;
v = (Object) Integer_minus(v, Integer_construct(1));
_incr(v);
_decr(_tmp);

_tmp = ret;
ret = (Object) _append(ret, Iterable_construct(_obj->fields[1]));
_incr(ret);
_decr(_tmp);

}
_ret = (Object) ret;
_incr(_ret);
_decr(ret);
_decr(v);

return _ret;
}

_object _prog_main() {
Object c = NULL;

Object ds = NULL;

Object out = NULL;

Object _tmp = NULL;

Object _ret = NULL;

_tmp = ds;
ds = (Object) NULL;
_incr(ds);
>>>>>>> 38b66c2c8f8b9fd531098dea329f5a316690e25d
_decr(_tmp);

_tmp = c;
c = (Object) _1;
_incr(c);
_decr(_tmp);

<<<<<<< HEAD
} else {
_tmp = b;
b = (Object) Integer_construct(1);
_incr(b);
_decr(_tmp);

_tmp = _0;
_0 = (Object) Integer_plus(a, b);
_incr(_0);
_decr(_tmp);

_tmp = c;
c = (Object) _0;
_incr(c);
_decr(_tmp);

_tmp = e;
e = (Object) _0;
_incr(e);
_decr(_tmp);

_tmp = a;
a = (Object) Integer_construct(24);
_incr(a);
_decr(_tmp);

_tmp = _1;
_1 = (Object) Integer_plus(a, b);
_incr(_1);
_decr(_tmp);

_tmp = f;
f = (Object) _1;
_incr(f);
_decr(_tmp);

}
_tmp = d;
d = (Object) _1;
_incr(d);
_decr(_tmp);

_ret = (Object) Iterable_construct(String_construct("sup"));
=======
_IterNode _iter0 = _iterator(input);
while (_iter0) {
_object i = _iter0->curr;
_tmp = ds;
ds = (Object) _append(ds, Iterable_construct(Multiplier(i, c)));
_incr(ds);
_decr(_tmp);

_tmp = c;
c = (Object) Integer_plus(c, Integer_construct(1));
_incr(c);
_decr(_tmp);

_iter0 = _iter0->next(_iter0);
} x3free(_iter0);
_tmp = out;
out = (Object) NULL;
_incr(out);
_decr(_tmp);

_IterNode _iter1 = _iterator(ds);
while (_iter1) {
_object d = _iter1->curr;
_tmp = out;
out = (Object) _append(out, Multiplier_print(d));
_incr(out);
_decr(_tmp);

_iter1 = _iter1->next(_iter1);
} x3free(_iter1);
_ret = (Object) out;
>>>>>>> 38b66c2c8f8b9fd531098dea329f5a316690e25d
_incr(_ret);
_decr(f);
_decr(d);
_decr(e);
_decr(b);
_decr(c);
<<<<<<< HEAD
_decr(a);
_decr(_0);
_decr(_1);
=======
_decr(ds);
_decr(out);
>>>>>>> 38b66c2c8f8b9fd531098dea329f5a316690e25d

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
}
