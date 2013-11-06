#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

_object safeDiv(_object _o0, _object _o1, _object _o2) {
Object l = _o0;
_incr(l);

Object r = _o1;
_incr(r);

Object d = _o2;
_incr(d);

Object m = NULL;

Object _tmp = NULL;

Object _ret = NULL;

_tmp = m;
m = (Object) Integer_divide(l, r);
_incr(m);
_decr(_tmp);

_IterNode _iter0 = _iterator(m);
while (_iter0) {
_object i = _iter0->curr;
_ret = (Object) i;
_incr(_ret);
_decr(m);

return _ret;
_iter0 = _iter0->next(_iter0);
} x3free(_iter0);
_ret = (Object) d;
_incr(_ret);
_decr(m);

return _ret;
}

_object safeModulo(_object _o0, _object _o1, _object _o2) {
Object l = _o0;
_incr(l);

Object r = _o1;
_incr(r);

Object d = _o2;
_incr(d);

Object m = NULL;

Object _tmp = NULL;

Object _ret = NULL;

_tmp = m;
m = (Object) Integer_modulo(l, r);
_incr(m);
_decr(_tmp);

_IterNode _iter1 = _iterator(m);
while (_iter1) {
_object i = _iter1->curr;
_ret = (Object) i;
_incr(_ret);
_decr(m);

return _ret;
_iter1 = _iter1->next(_iter1);
} x3free(_iter1);
_ret = (Object) d;
_incr(_ret);
_decr(m);

return _ret;
}

_object intToStr(_object _o0) {
Object i = _o0;
_incr(i);

Object ret = NULL;

Object pref = NULL;

Object _tmp = NULL;

Object _ret = NULL;

_tmp = pref;
pref = (Object) String_construct("");
_incr(pref);
_decr(_tmp);

if (((Boolean) Integer_lessThan(i, Integer_construct(0), Boolean_construct(1)))->value) {
_tmp = pref;
pref = (Object) String_construct("-");
_incr(pref);
_decr(_tmp);

_tmp = i;
i = (Object) Integer_times(i, Integer_negative(Integer_construct(1)));
_incr(i);
_decr(_tmp);

} else {

}
_tmp = ret;
ret = (Object) Iterable_construct(character(Integer_plus(Integer_construct(48), safeModulo(i, Integer_construct(10), Integer_construct(0)))));
_incr(ret);
_decr(_tmp);

_tmp = i;
i = (Object) safeDiv(i, Integer_construct(10), Integer_construct(0));
_incr(i);
_decr(_tmp);

while (((Boolean) Integer_lessThan(Integer_construct(0), i, Boolean_construct(1)))->value) {
_tmp = ret;
ret = (Object) _append(Iterable_construct(character(Integer_plus(Integer_construct(48), safeModulo(i, Integer_construct(10), Integer_construct(0))))), ret);
_incr(ret);
_decr(_tmp);

_tmp = i;
i = (Object) safeDiv(i, Integer_construct(10), Integer_construct(0));
_incr(i);
_decr(_tmp);

}
_ret = (Object) string(_append(pref, ret));
_incr(_ret);
_decr(ret);
_decr(pref);
_decr(i);

return _ret;
}

_object fibo(_object _o0) {
Object i = _o0;
_incr(i);

Object _tmp = NULL;

Object _ret = NULL;

if (((Boolean) Integer_equals(i, Integer_construct(1)))->value) {
_ret = (Object) Integer_construct(1);
_incr(_ret);

return _ret;
} else {

}
_ret = (Object) Integer_plus(fibo(Integer_minus(i, Integer_construct(1))), fibo(Integer_minus(i, Integer_construct(2))));
_incr(_ret);

return _ret;
}

_object _prog_main() {
Object mapped = NULL;

Object _tmp = NULL;

Object _ret = NULL;

_tmp = mapped;
mapped = (Object) NULL;
_incr(mapped);
_decr(_tmp);

_IterNode _iter2 = _iterator(Integer_through(Integer_construct(1), Integer_construct(10), Boolean_construct(1), Boolean_construct(1)));
while (_iter2) {
_object i = _iter2->curr;
_tmp = mapped;
mapped = (Object) _append(mapped, Iterable_construct(intToStr(Integer_plus(i, Integer_construct(1)))));
_incr(mapped);
_decr(_tmp);

_iter2 = _iter2->next(_iter2);
} x3free(_iter2);
_ret = (Object) Iterable_construct(intToStr(Integer_construct(5)));
_incr(_ret);
_decr(mapped);

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
