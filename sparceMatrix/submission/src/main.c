//
//  main.c
//  text2bin
//
//  Created by akant on 3/2/16.
//
//

#define _XOPEN_SOURCE 500

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#include "getRealTime.h"

#include "main.h"

int main(int argc, const char * argv[]) {
    int file_descriptor;
    struct stat stbuf;
    file_descriptor = open(argv[1], O_RDONLY);
    if (file_descriptor < 0) {
        perror("open");
        exit(-1);
    }
    
    if ((fstat(file_descriptor, &stbuf) != 0) || (!S_ISREG(stbuf.st_mode))) {
        perror("open");
        exit(-1);
    }
    
    off_t file_size = stbuf.st_size;
    
    // print some memory usage info
    char buf[10];
    int maxbufsize = MAX_RBUF_SIZE;
    if (MAX_TBUF_SIZE > maxbufsize){
        maxbufsize = MAX_TBUF_SIZE;
    }
    format_size(buf, maxbufsize);
    fprintf(stderr, "Max Auxillary Buffers:\t%s\n", buf);
    format_size(buf, file_size/2);
    fprintf(stderr, "Matrix Size Upper Bound:\t%s\n", buf);
    format_size(buf, file_size/2 + (size_t) maxbufsize);
    fprintf(stderr, "TOTAL MEMORY UPPER BOUND:\t%s\n\n", buf);
    
    fprintf(stderr, "Parsing matrix...");
    double start = getRealTime();
    
    // worst case size needed for matrix = file_size/2
    struct bmatrix mat;
    mat.cols = 0;
    mat.rows = 0;
    mat.size = 0;
    mat.data = (char*) malloc(file_size/2);
    
    if (mat.data == NULL) {
        printf("Not enought memory to materialize matrix");
        exit(-2);
    }
    
    parse_matrix(file_descriptor, &mat, file_size);
    double parse_time = getRealTime();
    fprintf(stderr, "OK\tElapsed Wall Time %.3fs\n", (parse_time - start));
    
    struct maxima max;
    max.el = 0;
    max.maxcols = (int*) calloc(mat.cols, sizeof(int));
    
    if (max.maxcols == NULL) {
        printf("Not enought memory to materialize maxcols");
        exit(-2);
    }
    
    max.maxrows = (int*) calloc(mat.rows, sizeof(int));
    
    if (max.maxrows == NULL) {
        printf("Not enought memory to materialize maxrows");
        exit(-2);
    }
    
    compute_maxima(&mat, &max);
    
    fprintf(stderr, "Writing Transpose..");
    serialize_columnmaj_t(&mat, &max, "out-transpose.bin");
    double transpose_time = getRealTime();
    fprintf(stderr, "OK\tElapsed Wall Time %.3fs\n", (transpose_time - start));
    
    fprintf(stderr, "Writing Original..");
    serialize_columnmaj(&mat, &max, "out.bin");
    fprintf(stderr, "OK\tElapsed Wall Time %.3fs\n", (getRealTime() - start));
    
    free(mat.data);
    free(max.maxcols);
    free(max.maxrows);
    return 0;
}

void serialize_columnmaj_t(struct bmatrix const* mat, struct maxima const* max, char const* fname) {
    FILE *fout;
    fout = fopen(fname, "wb");
    int header[5];
    header[0] = 0;
    header[1] = mat->rows;
    header[2] = mat->cols;
    header[3] = max->el;
    header[4] = 0;
    fwrite(header, sizeof(int), 5, fout);
    fwrite(max->maxrows, sizeof(int), mat->rows, fout);
    fwrite(mat->data, 1, mat->size, fout);
    if(fclose(fout) != 0) {
        fprintf(stderr, "Error closing output matrix file.\n");
        exit(-1010);
    }
}

void serialize_columnmaj(struct bmatrix const* mat, struct maxima const* max, char const* fname) {
    FILE *fout;
    fout = fopen(fname, "wb");
    int header[5];
    header[0] = 0;
    header[1] = mat->cols;
    header[2] = mat->rows;
    header[3] = max->el;
    header[4] = 0;
    fwrite(header, sizeof(int), 5, fout);
    fwrite(max->maxcols, sizeof(int), mat->cols, fout);
    
    int tbuf_size = MAX_TBUF_SIZE;
    if (tbuf_size > mat->size) {
        tbuf_size = (int) mat->size;
    }
    char* tbuf = (char*) malloc(tbuf_size);
    if (tbuf == NULL) {
        printf("Not enought memory to materialize transposition buffer");
        exit(-2);
    }
    
    off_t offset = 0;
    while (offset < mat->size) {
        size_t read = transpose(mat, offset, tbuf, tbuf_size);
        fwrite(tbuf, 1, read, fout);
        offset += read;
    }
    
    
    free(tbuf);
    if(fclose(fout) != 0) {
        fprintf(stderr, "Error closing output matrix file.\n");
        exit(-1010);
    }}

int transpose(struct bmatrix const* mat, off_t offset, char* tbuf, int const tbuf_size) {
    int wptr = 0;
    off_t ioffset = (offset%mat->rows) * ((off_t) mat->cols) + offset/((off_t) mat->rows);
    
    while (wptr < tbuf_size) {
        tbuf[wptr] = mat->data[ioffset];
        ++wptr;
        
        if (ioffset == mat->size - 1) {
            break;
        }
        
        ioffset += mat->cols;
        if (ioffset >= mat->size) {
            ioffset %= mat->size;
            ++ioffset;
        }
    }
    return wptr;
}

void compute_maxima(struct bmatrix const* mat, struct maxima* max) {
    size_t i = 0;
    for (int row=0; row < mat->rows; ++row) {
        for (int col=0; col < mat->cols; ++col) {
            if (max->maxrows[row] < mat->data[i]) {
                max->maxrows[row] = mat->data[i];
            }
            
            if (max->maxcols[col] < mat->data[i]) {
                max->maxcols[col] = mat->data[i];
            }
            
            if (max->el < mat->data[i]) {
                max->el = mat->data[i];
            }
            ++i;
        }
    }
    
    ++max->el;
    for (int row=0; row < mat->rows; ++row) {
        ++max->maxrows[row];
    }
    for (int col=0; col < mat->cols; ++col) {
        ++max->maxcols[col];
    }
}

void parse_matrix(const int fd, struct bmatrix* mat, const off_t fsize) {
    off_t rbuf_size = MAX_RBUF_SIZE;
    if (fsize < rbuf_size) {
        rbuf_size = fsize;
    }
    
    char* rbuf = (char*) malloc(rbuf_size);
    
    off_t offset = 0;
    off_t wptr = 0;
    int col_size = 0;
    
    while (offset < fsize) {
        const ssize_t read_size = pread(fd, rbuf, rbuf_size, offset);
        int rptr = 0;
        
        while (1) {
            char value = parse_char(rbuf, &rptr, read_size);
            
            if (rptr >= read_size) {
                if (offset + rptr >= fsize) { // we finished reading the file, and last read was valid
                    mat->data[wptr] = value;
                    //++(mat->rows);
                } else { // we have more work ahead, and last read is (potentially) invalid
                    while (rptr >= read_size || (rbuf[rptr] != '\t' && rbuf[rptr] != '\n')) {
                        --rptr;
                    }
                    ++rptr;
                }
                break;
            }
            
            mat->data[wptr] = value;
            ++wptr;
            ++col_size;
            
            char curr = rbuf[rptr];
            if (curr == '\t') {
                // what we expect
            } else if (curr == '\n' || curr == '\r') {
                ++(mat->rows);
                if (mat->cols > 0) {
                    if (mat->cols != col_size) {
                        printf("Missing one or more values at row %i", mat->rows);
                        exit(-2000);
                    }
                } else {
                    mat->cols = col_size;
                }
                col_size = 0;
                if (curr == '\r') {
                    ++rptr;
                }
            } else {
                printf("unexpected character: '%c'", curr);
                exit(-1000);
            }
            
            ++rptr;
            
            if (rptr == read_size) {
                break;
            }
        }
        
        offset += rptr;
    }
    
    mat->size = ((size_t) mat->cols) * ((size_t) mat->rows);
    
    free(rbuf);
}

char parse_char(char const* array, int* ptr, const ssize_t read_size) {
    // reads a byte starting at ptr, incements ptr until either the character is
    // not a number or read_size is reached
    // does not check for overflow
    char res = 0;
    
    char negative = 0;
    if (array[*ptr] == '-') {
        negative = 1;
        ++(*ptr);
    }
    
    while (*ptr < read_size && array[*ptr] >= '0' && array[*ptr] <= '9') {
        res *= 10;
        res += array[*ptr] - '0';
        ++(*ptr);
    }
    
    if (negative) {
        return -res;
    }
    
    return res;
}

void format_size(char* buf, size_t sz) {
    char *strings[] = {"B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB" };
    int s = 0;
    long double sd = (long double) sz;
    while (sd >= 1024) {
        s++;
        sd /= 1024;
    }
    sprintf(buf, "%.1Lf %s", sd, strings[s]);
}
