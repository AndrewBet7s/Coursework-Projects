//bubbleSort.c
//famous bubble sort
//implement the swap algorithm with pointers

#include <stdio.h>

#define MAX 9

//function prototypes
void printValues();
void sort();
void swap(int*, int*);

int values[] = {7, 3, 9, 4, 6, 1, 2, 8, 5};

int main(){
    printf("   BUBBLE SORT: \n");
    printf("Before: \n");
    printValues();
    sort();
    printf("After: \n");
    printValues();

    return (0);
} // end main

// print the array
void printValues(){
    int i = 0;
    printf("[");
    for(i = 0; i < MAX; i++){
        printf ("%d ", values[i]);
    }
    printf ("]");
    printf ("\n");
} // end printValues

// swap two values at two given memory addresses
void swap(int* a, int* b){
    int temp = *a;
    *a = *b;
    *b = temp;
    printValues();
} // end swap

// sort an integer array using bubble sort
void sort(){
    int i = 0;
    for(i = 0; i < MAX - 1; i++){
        int j = 0;
        for(j = 0; j < MAX - 1; j++){
            // compare two values at intermidiate memory addresses
            if (*(values+j) > *(values+j+1)){
                swap((values+j), (values+j+1));
            }
        }
    }
} // end sort
