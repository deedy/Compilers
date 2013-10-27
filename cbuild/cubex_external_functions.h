#ifndef _CUBEX_EXT
#define _CUBEX_EXT

void print_line(char* str, int len);
int next_line_len();
void read_line(char* buffer);
void* x3malloc(int size);
void x3free(void* ptr);
char unichar(int uni);
int charuni(char c);

#endif
