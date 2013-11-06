#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

_object Math_fst(_object _o0, _object _o1, _object _o2);
_object Math_pow(_object _o0, _object _o1, _object _o2);
_object Math_mod(_object _o0, _object _o1, _object _o2);
_object _prog_main();
_object Math() {
Object _tmp = NULL;
Object _ret = NULL;
Object _obj = _allocate(6, 1);
_tmp = _obj->fields[0];
_obj->fields[0] = (Object) Integer_construct(0);
_incr(_obj->fields[0]);
_decr(_tmp);

return _obj;}

_object Math_fst(_object _o0, _object _o1, _object _o2) {
Object _obj = _o0;
_incr(_obj);

Object i = _o1;
_incr(i);

Object fallback = _o2;
_incr(fallback);

Object _tmp = NULL;

Object _ret = NULL;

_IterNode _iter0 = _iterator(i);
while (_iter0) {
_object e = _iter0->curr;
_ret = (Object) e;
_incr(_ret);

return _ret;
_iter0 = _iter0->next(_iter0);
} x3free(_iter0);
_ret = (Object) fallback;
_incr(_ret);

return _ret;
}

_object Math_pow(_object _o0, _object _o1, _object _o2) {
Object _obj = _o0;
_incr(_obj);

Object a = _o1;
_incr(a);

Object n = _o2;
_incr(n);

Object ret = NULL;

Object p = NULL;

Object _tmp = NULL;

Object _ret = NULL;

_tmp = ret;
ret = (Object) Integer_construct(1);
_incr(ret);
_decr(_tmp);

_tmp = p;
p = (Object) Integer_construct(0);
_incr(p);
_decr(_tmp);

while (((Boolean) Integer_lessThan(p, n, Boolean_construct(1)))->value) {
_tmp = n;
n = (Object) Integer_plus(n, Integer_construct(1));
_incr(n);
_decr(_tmp);

_tmp = ret;
ret = (Object) Integer_times(ret, a);
_incr(ret);
_decr(_tmp);

}
_ret = (Object) ret;
_incr(_ret);
_decr(ret);
_decr(p);
_decr(n);

return _ret;
}

_object Math_mod(_object _o0, _object _o1, _object _o2) {
Object _obj = _o0;
_incr(_obj);

Object a = _o1;
_incr(a);

Object b = _o2;
_incr(b);

Object _tmp = NULL;

Object _ret = NULL;

_ret = (Object) fst(Integer_modulo(a, b), Integer_negative(Integer_construct(1)));
_incr(_ret);

return _ret;
}

_object _prog_main() {
Object ret = NULL;

Object m = NULL;

Object i = NULL;

Object _tmp = NULL;

Object _ret = NULL;

_tmp = m;
m = (Object) Math();
_incr(m);
_decr(_tmp);

_tmp = ret;
ret = (Object) NULL;
_incr(ret);
_decr(_tmp);

_tmp = i;
i = (Object) Math_mod(m, Integer_construct(13), Integer_construct(5));
_incr(i);
_decr(_tmp);

while (((Boolean) Integer_lessThan(Integer_construct(0), i, Boolean_construct(1)))->value) {
_tmp = ret;
ret = (Object) _append(ret, Iterable_construct(String_construct("x")));
_incr(ret);
_decr(_tmp);

}
_ret = (Object) ret;
_incr(_ret);
_decr(ret);
_decr(m);
_decr(i);

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
