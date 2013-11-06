#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

_object Option_hasVal(_object _o0);
_object Option_getVal(_object _o0);
_object Some_hasVal(_object _o0);
_object Some_getVal(_object _o0);
_object None_hasVal(_object _o0);
_object None_getVal(_object _o0);
_object getVal(_object _o0);
_object _prog_main();
_object Option_hasVal(_object _o0) {
Object _obj = _o0;
_incr(_obj);

Object _tmp = NULL;

Object _ret = NULL;

if (((Boolean) Boolean_construct(((Object) _obj)->id == 7))->value) {
_ret = (Object) None_hasVal(_obj);
_incr(_ret);

return _ret;
} else {
if (((Boolean) Boolean_construct(((Object) _obj)->id == 6))->value) {
_ret = (Object) Some_hasVal(_obj);
_incr(_ret);

return _ret;
} else {
_ret = (Object) NULL;
_incr(_ret);

return _ret;
}
}
}

_object Option_getVal(_object _o0) {
Object _obj = _o0;
_incr(_obj);

Object _tmp = NULL;

Object _ret = NULL;

if (((Boolean) Boolean_construct(((Object) _obj)->id == 7))->value) {
_ret = (Object) None_getVal(_obj);
_incr(_ret);

return _ret;
} else {
if (((Boolean) Boolean_construct(((Object) _obj)->id == 6))->value) {
_ret = (Object) Some_getVal(_obj);
_incr(_ret);

return _ret;
} else {
_ret = (Object) NULL;
_incr(_ret);

return _ret;
}
}
}

_object Some(_object _o0) {
Object val = _o0;
Object _tmp = NULL;
Object _ret = NULL;
Object _obj = _allocate(6, 1);
_tmp = _obj->fields[0];
_obj->fields[0] = (Object) val;
_incr(_obj->fields[0]);
_decr(_tmp);

return _obj;}

_object Some_hasVal(_object _o0) {
Object _obj = _o0;
_incr(_obj);

Object _tmp = NULL;

Object _ret = NULL;

_ret = (Object) Boolean_construct(1);
_incr(_ret);

return _ret;
}

_object Some_getVal(_object _o0) {
Object _obj = _o0;
_incr(_obj);

Object _tmp = NULL;

Object _ret = NULL;

_ret = (Object) _obj->fields[0];
_incr(_ret);

return _ret;
}

_object None(_object _o0) {
Object fallback = _o0;
Object _tmp = NULL;
Object _ret = NULL;
Object _obj = _allocate(7, 1);
_tmp = _obj->fields[0];
_obj->fields[0] = (Object) fallback;
_incr(_obj->fields[0]);
_decr(_tmp);

return _obj;}

_object None_hasVal(_object _o0) {
Object _obj = _o0;
_incr(_obj);

Object _tmp = NULL;

Object _ret = NULL;

_ret = (Object) Boolean_construct(0);
_incr(_ret);

return _ret;
}

_object None_getVal(_object _o0) {
Object _obj = _o0;
_incr(_obj);

Object _tmp = NULL;

Object _ret = NULL;

_ret = (Object) _obj->fields[0];
_incr(_ret);

return _ret;
}

_object getVal(_object _o0) {
Object o = _o0;
_incr(o);

Object _tmp = NULL;

Object _ret = NULL;

_ret = (Object) Option_getVal(o);
_incr(_ret);

return _ret;
}

_object _prog_main() {
Object a = NULL;

Object _tmp = NULL;

Object _ret = NULL;

_tmp = a;
a = (Object) Some(String_construct("wut"));
_incr(a);
_decr(_tmp);

_ret = (Object) Iterable_construct(getVal(a));
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
