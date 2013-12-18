#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#include "cubex_external_functions.h"
#include "cubex_private.h"

/* Woooo globals! */
static int curr_input = 1;
static int input_len;
static char** input;

void initialize(int argc, char** argv) {
  input_len = argc;
  input = argv;
}

void print_line(char* str, int len) {
  char* buf;
  buf = (char*) malloc(sizeof(char) * (len + 1));
  buf[len] = (char) 0; /* build a null-termianted string */
  memcpy(buf, str, len);
  puts(buf);
  free(buf);
}

int next_line_len() {
  char* next_line;

  if (curr_input == input_len) {
    return 0;
  }

  next_line = input[curr_input];

  return strlen(next_line);
}

void read_line(char* buffer) {
  if (curr_input < input_len) {
    memcpy((void*) buffer, (void*) (input[curr_input]), next_line_len());
  }
  curr_input = curr_input + 1;
}

void* x3malloc(int size) {
  return malloc(size);
}

void x3free(void* ptr) {
  free(ptr);
}

char unichar(int uni) {
  return (char) uni;
}

int charuni(char c) {
  return (int) c;
}
