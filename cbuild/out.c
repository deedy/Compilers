#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

<<<<<<< HEAD
_object rev(_object o0) {
Object lst = o0;
_incr(lst);

Object _tmp = NULL;

Object _ret = NULL;

_ret = (Object) NULL;
_incr(_ret);

=======
_object testAdd(_object o0, _object o1) {
Object a = o0;
_incr(a);

Object b = o1;
_incr(b);

Object _tmp = NULL;

Object _ret = NULL;

_ret = Integer_plus(a, b);
_incr(_ret);

>>>>>>> 23b49b73b9072a860b19bf0e2cdf013d9616056a
return _ret;
}

_object _prog_main() {
Object _tmp = NULL;

Object _ret = NULL;

<<<<<<< HEAD
_ret = (Object) rev(input);
=======
_ret = NULL;
>>>>>>> 23b49b73b9072a860b19bf0e2cdf013d9616056a
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
