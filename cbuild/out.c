#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

_object Wut() {
Object _tmp = NULL;
Object _ret = NULL;
Object _obj = _allocate(5, 2);
_tmp = _obj->fields[0];
_obj->fields[0] = (Object) String_construct("who");
_incr(_obj->fields[0]);
_decr(_tmp);

_tmp = _obj->fields[1];
_obj->fields[1] = (Object) String_construct("wut");
_incr(_obj->fields[1]);
_decr(_tmp);

return _obj;}

_object Wut_print2(_object o0, _object o1) {
Object _obj = o0;
_incr(_obj);

Object c = o1;
_incr(c);

Object _tmp = NULL;

Object _ret = NULL;

_ret = (Object) string(_append(c, _obj->fields[1]));
_incr(_ret);

return _ret;
}

_object Wut_print(_object o0) {
Object _obj = o0;
_incr(_obj);

Object _tmp = NULL;

Object _ret = NULL;

_ret = (Object) _obj->fields[0];
_incr(_ret);

return _ret;
}

_object _prog_main() {
Object a = NULL;

Object _tmp = NULL;

Object _ret = NULL;

_tmp = a;
a = (Object) Wut();
_incr(a);
_decr(_tmp);

_ret = (Object) _append(Iterable_construct(Wut_print(a)), Iterable_construct(Wut_print2(a, String_construct("dafuq "))));
_incr(_ret);
_decr(a);

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
