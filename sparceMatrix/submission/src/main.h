//
//  main.h
//  text2bin
//
//  Created by akant on 3/2/16.
//
//

#ifndef main_h
#define main_h

#define MAX_RBUF_SIZE 1*1024*1024
#define MAX_TBUF_SIZE 1*1024*1024

/*
Stores a matrix of chars in a row-major format
*/
struct bmatrix {
    int rows;
    int cols;
    size_t size;
    char* data;
};

/*
Stores the global, column-wise and row-wise maxima of a matrix
*/
struct maxima {
    int el;
    int* maxrows;
    int* maxcols;
};

/*
Fast parsing of a char starting at array[ptr]. This function expects a 
valid ptr into array, and increments ptr until either read_size is reached,
or array[ptr] is not a valid numeric (or sign) character.
*/
inline char parse_char(char const* array, int* ptr, const ssize_t read_size);

/*
Parses the text input in the file descriptor fd into the bmatrix structure mat.
Expects the total size of the input file in fsize.
*/
void parse_matrix(const int fd, struct bmatrix* mat, const off_t fsize);

/*
Fills the maxima structure max for a given matrix mat
*/
void compute_maxima(struct bmatrix const* mat, struct maxima* max);

/*
Writes the transposition of bmatrix mat and its maxima information into the binary file fname.
Note that because this is a column-major ordering format, the transpose of the matrix is the 
row-major ordering, which we already have.
*/
void serialize_columnmaj_t(struct bmatrix const* mat, struct maxima const* max, char const* fname);

/*
Writes the original bmatrix mat and its maxima information into the binary file fname. This requires
transposition as the internal layout is row-major but the output is column-major.
*/
void serialize_columnmaj(struct bmatrix const* mat, struct maxima const* max, char const* fname);

/*
Writes a chunk of the column-major ordering of bmatrix mat in the buffer array tbuf, 
starting at offset and finishing at tbuf_size (or earlier).
*/
int transpose(struct bmatrix const* mat, off_t offset, char* tbuf, int const tbuf_size);

/*
Utility function borrowed from the original dbuild
*/
void format_size(char* buf, size_t sz);

#endif /* main_h */
