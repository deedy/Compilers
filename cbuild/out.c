#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

_object rest(_object _o0);
_object last(_object _o0, _object _o1);
_object length(_object _o0);
_object _prog_main();
_object rest(_object _o0) {
Object l = _o0;
_incr(l);

Object ret = NULL;

Object passed = NULL;

Object _tmp = NULL;

Object _ret = NULL;

_tmp = ret;
ret = (Object) NULL;
_incr(ret);
_decr(_tmp);

_tmp = passed;
passed = (Object) Boolean_construct(0);
_incr(passed);
_decr(_tmp);

_IterNode _iter0 = _iterator(l);
while (_iter0) {
_object e = _iter0->curr;
if (((Boolean) passed)->value) {
_tmp = ret;
ret = (Object) _append(ret, Iterable_construct(e));
_incr(ret);
_decr(_tmp);

} else {

}
_tmp = passed;
passed = (Object) Boolean_construct(1);
_incr(passed);
_decr(_tmp);

_iter0 = _iter0->next(_iter0);
} x3free(_iter0);
_ret = (Object) ret;
_incr(_ret);
_decr(ret);
_decr(passed);
_decr(l);

return _ret;
}

_object last(_object _o0, _object _o1) {
Object l = _o0;
_incr(l);

Object sexxxpanther_default_swag_default_ = _o1;
_incr(sexxxpanther_default_swag_default_);

Object _tmp = NULL;

Object _ret = NULL;

_IterNode _iter1 = _iterator(l);
while (_iter1) {
_object e = _iter1->curr;
_ret = (Object) last(rest(l), e);
_incr(_ret);
_decr(l);
_decr(sexxxpanther_default_swag_default_);

return _ret;
_iter1 = _iter1->next(_iter1);
} x3free(_iter1);
_ret = (Object) sexxxpanther_default_swag_default_;
_incr(_ret);
_decr(l);
_decr(sexxxpanther_default_swag_default_);

return _ret;
}

_object length(_object _o0) {
Object l = _o0;
_incr(l);

Object ret = NULL;

Object _tmp = NULL;

Object _ret = NULL;

_tmp = ret;
ret = (Object) Integer_construct(0);
_incr(ret);
_decr(_tmp);

_IterNode _iter2 = _iterator(l);
while (_iter2) {
_object e = _iter2->curr;
_tmp = ret;
ret = (Object) Integer_plus(ret, Integer_construct(1));
_incr(ret);
_decr(_tmp);

_iter2 = _iter2->next(_iter2);
} x3free(_iter2);
_ret = (Object) ret;
_incr(_ret);
_decr(ret);
_decr(l);

return _ret;
}

_object _prog_main() {
Object _tmp = NULL;

Object _ret = NULL;

_ret = (Object) _append(Iterable_construct(last(input, String_construct(""))), Iterable_construct(string(Iterable_construct(character(Integer_plus(last(Integer_through(Integer_construct(5), length(input), Boolean_construct(1), Boolean_construct(1)), Integer_construct(3)), Integer_construct(30)))))));
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
