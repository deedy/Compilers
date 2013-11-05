#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

_object rev(_object o0) {
Object lst = o0;
_incr(lst);

Object _tmp = NULL;

Object _ret = NULL;

_ret = (Object) NULL;
_incr(_ret);

return _ret;
}

_object _prog_main() {
Object _tmp = NULL;

Object _ret = NULL;

_ret = (Object) rev(input);
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
