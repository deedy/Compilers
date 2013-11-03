#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"
void cubex_main() {
	__init();
	_IterNode _i_iter = _iterator(input);
	while(_i_iter) {
		_print(_i_iter->curr);
		_i_iter = _i_iter->next(_i_iter);
	}
	x3free(_i_iter);	
}