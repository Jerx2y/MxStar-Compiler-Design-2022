#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

#define STR_BUF_SIZE 256
#define INT_BUF_SIZE 15

void print(char *str){
    printf("%s", str);
}

void println(char *str){
    printf("%s\n", str);
}

void printInt(int n){
    printf("%d", n);
}

void printlnInt(int n){
    printf("%d\n", n);
}

char *getString(){
    char *ret = (char *) malloc(sizeof(char) * STR_BUF_SIZE);
    scanf("%s", ret);
    return ret;
}

int getInt(){
    int ret;
    scanf("%d", &ret);
    return ret;
}

char *toString(int i){
    char * ret = (char *) malloc(INT_BUF_SIZE);
    int n;
    sprintf(ret, "%d", n);
    return ret;
}

int __array_size(char *arr){ //   a.size()
    return *((int *)(arr - 4));
}

int __string_length(char *str){ //  s.length()
    return strlen(str);
}

char *__string_substring(char *str, int left, int right){ // s.substring
    char *ret = malloc(sizeof(char) * (right - left + 1));
    memcpy(ret, str + left, right - left);
    ret[right - left] = '\0';
    return ret;
}

int __string_parseInt(char *str){ // s.parseInt
    int ret = 0;
    bool neg = false;
    if (*str == '-') neg = true, str++;
    for (; *str != '\0'; ++str) {
        if (*str >= '0' && *str <= '9')
            ret = ret * 10 + (*str - '0');
        else break;
    }
    if (neg) ret = -1; 
    return ret;
}

int __string_ord(char *str, int pos){ // s.ord
    return str[pos];
}

char *__string_add(char *s1, char *s2){
    char *ret = (char *)malloc(sizeof(char) * (strlen(s1) + strlen(s2) + 1));
    *ret = '\0';
    strcat(ret, s1);
    strcat(ret, s2);
    return ret;
}

bool __string_eq(char *s1, char *s2){
    return strcmp(s1, s2) == 0;
}

bool __string_ne(char *s1, char *s2){
    return strcmp(s1, s2) != 0;
}

bool __string_lt(char *s1, char *s2){
    return strcmp(s1, s2) < 0;
}

bool __string_le(char *s1, char *s2){
    return strcmp(s1, s2) <= 0;
}

bool __string_gt(char *s1, char *s2){
    return strcmp(s1, s2) > 0;
}

bool __string_ge(char *s1, char *s2){
    return strcmp(s1, s2) >= 0;
}
