#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

_object _prog_main();
_object _prog_main() {
Object f = NULL;

Object d = NULL;

Object e = NULL;

Object b = NULL;

Object c = NULL;

Object a = NULL;

Object _0 = NULL;

Object _1 = NULL;

Object _tmp = NULL;

Object _ret = NULL;

_tmp = a;
a = (Object) Integer_construct(1);
_incr(a);
_decr(_tmp);

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
_decr(_tmp);

_tmp = c;
c = (Object) _1;
_incr(c);
_decr(_tmp);

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
_incr(_ret);
_decr(f);
_decr(d);
_decr(e);
_decr(b);
_decr(c);
_decr(a);
_decr(_0);
_decr(_1);

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
