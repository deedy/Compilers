#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

_object _prog_main();
_object _prog_main() {
	Object _ret = NULL;
	_ret = (Object) input;
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
	_decr(input);
}