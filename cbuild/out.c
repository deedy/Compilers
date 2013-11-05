#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

_object Wut() {
Object _tmp = NULL;
Object _ret = NULL;
_tmp = _obj->fields[0];
_obj->fields[0] = (Object) String_construct("who");
_incr(_obj->fields[0]);
_decr(_tmp);

_tmp = _obj->fields[1];
_obj->fields[1] = (Object) Integer_construct(5);
_incr(_obj->fields[1]);
_decr(_tmp);

}

_object Wut_print() {
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

_ret = (Object) Iterable_construct(Wut_print(a));
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
