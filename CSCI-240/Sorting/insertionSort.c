//insertionSort.c
//famous insertion sort
//implement the swap algorithm with pointers

#include <stdio.h>

#define MAX 9

//function prototypes
void printValues();
void sort();
void swap(int*, int*);

int values[] = {7, 3, 9, 4, 6, 1, 2, 8, 5};

int main(){
    printf("   INSERTION SORT: \n");
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
    int i, j, temp;
    for(i = 1; i < MAX; i++){
        j = i;
        // keep checking untill index 0 or value at lower index is smaller
        while(j > 0 && (*(values+j-1) > *(values+j))){
            swap((values+j-1), (values+j));
            j--;
        }
    }
} // end sort
