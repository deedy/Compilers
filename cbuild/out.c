#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

_object _prog_main (  ) {
Object ret = NULL;
Object i = NULL;
Object _tmp = NULL;
Object _ret = NULL;
_tmp = ret; ret = NULL; _incr(ret); _decr(_tmp);
_IterNode _iter0 = _iterator(input);
while (_iter0) {
 i = _iter0->curr; 
_tmp = ret; 
ret = (Object)_append(Iterable_construct(i), _append(ret, Iterable_construct(i)));
 _incr(ret); _decr(_tmp); 
_iter0 = _iter0->next(_iter0);
} x3free(_iter0);

_ret = ret; _incr(_ret); _decr(ret);_decr(i); return _ret;
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
