#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

_object safeDiv(_object o0, _object o1, _object o2) {
Object l = o0;
_incr(l);

Object r = o1;
_incr(r);

Object d = o2;
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
_ret = i;
_incr(_ret);
_decr(m);
return _ret;
_iter0 = _iter0->next(_iter0);
} x3free(_iter0);
_ret = d;
_incr(_ret);
_decr(m);
return _ret;
}

_object safeModulo(_object o0, _object o1, _object o2) {
Object l = o0;
_incr(l);

Object r = o1;
_incr(r);

Object d = o2;
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
_ret = i;
_incr(_ret);
_decr(m);
return _ret;
_iter1 = _iter1->next(_iter1);
} x3free(_iter1);
_ret = d;
_incr(_ret);
_decr(m);
return _ret;
}

_object intToStr(_object o0) {
Object i = o0;
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
_ret = string(_append(pref, ret));
_incr(_ret);
_decr(ret);
_decr(pref);
_decr(i);
return _ret;
}

_object _prog_main() {
Object _tmp = NULL;

Object _ret = NULL;

_IterNode _iter2 = _iterator(_append(Integer_through(Integer_construct(1), Integer_construct(5), Boolean_construct(1), Boolean_construct(1)), Iterable_construct(Integer_construct(6))));
while (_iter2) {
_object i = _iter2->curr;
_ret = Iterable_construct(intToStr(i));
_incr(_ret);

return _ret;
_iter2 = _iter2->next(_iter2);
} x3free(_iter2);
_ret = Iterable_construct(String_construct("nope"));
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
}
