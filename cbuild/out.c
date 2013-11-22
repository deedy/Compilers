#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

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

_object Multiplier_print(_object _o0) {
Object _obj = _o0;
_incr(_obj);

Object ret = NULL;

Object v = NULL;

Object _tmp = NULL;

Object _ret = NULL;

_tmp = ret;
ret = (Object) NULL;
_incr(ret);
_decr(_tmp);

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
_decr(_tmp);

_tmp = c;
c = (Object) Integer_construct(0);
_incr(c);
_decr(_tmp);

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
_incr(_ret);
_decr(c);
_decr(ds);
_decr(out);

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
