#include "cubex_main.h"
#include "cubex_external_functions.h"
#include "cubex_lib.h"

_object _prog_main();
_object _prog_main() {
	return input;
}

void cubex_main() {
	__init();
	_object _i = _prog_main();
	_Iterator _i_iter = ((Iterable)_i)->iter(_i);
	_object _j;	while(_j = _i_iter->next(_i_iter)) {
		_print(_j);
	}
	_free_all_the_things();
}
