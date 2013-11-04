#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

_object _prog_main (  ) {
Object ret = NULL;
Object n = NULL;
Object m = NULL;
Object _tmp = NULL;
Object _ret = NULL;
_tmp = ret; ret = (Object) NULL; _incr(ret); _decr(_tmp);
_IterNode _iter0 = _iterator(input);
while (_iter0) {
 _object i = _iter0->curr; 
_tmp = n; n = (Object) Integer_construct(0); _incr(n); _decr(_tmp);
_IterNode _iter1 = _iterator(i);
while (_iter1) {
 _object c = _iter1->curr; 
_tmp = n; n = (Object) Integer_times(n, Integer_construct(10)); _incr(n); _decr(_tmp);
_tmp = n; n = (Object) Integer_minus(Integer_plus(n, Character_unicode(c)), Integer_construct(48)); _incr(n); _decr(_tmp); 
_iter1 = _iter1->next(_iter1);
} x3free(_iter1);

_tmp = n; n = (Object) Integer_plus(n, Integer_construct(1)); _incr(n); _decr(_tmp);
_tmp = m; m = (Object) NULL; _incr(m); _decr(_tmp);
while (((Boolean) Integer_lessThan(Integer_construct(0), n, Boolean_construct(1)))->value) { _IterNode _iter2 = _iterator(Integer_modulo(n, Integer_construct(10)));
while (_iter2) {
 _object k = _iter2->curr; 
_tmp = m; m = (Object) _append(Iterable_construct(character(Integer_plus(k, Integer_construct(48)))), m); _incr(m); _decr(_tmp); 
_iter2 = _iter2->next(_iter2);
} x3free(_iter2);

_IterNode _iter3 = _iterator(Integer_divide(n, Integer_construct(10)));
while (_iter3) {
 _object k = _iter3->curr; 
_tmp = n; n = (Object) k; _incr(n); _decr(_tmp); 
_iter3 = _iter3->next(_iter3);
} x3free(_iter3);
 }
_tmp = ret; ret = (Object) _append(ret, Iterable_construct(string(m))); _incr(ret); _decr(_tmp); 
_iter0 = _iter0->next(_iter0);
} x3free(_iter0);

_ret = ret; _incr(_ret); _decr(ret);_decr(n);_decr(m); return _ret;
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
