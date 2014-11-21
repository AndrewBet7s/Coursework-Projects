//selectionSort.c
//famous selection sort
//implement the swap algorithm with pointers

#include <stdio.h>

#define MAX 9

//function prototypes
void printValues();
void sort();
void swap(int*, int*);

int values[] = {7, 3, 9, 4, 6, 1, 2, 8, 5};

int main(){
    printf("   SELECTION SORT: \n");
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
    int i, minIndex, j, temp;
    for(i = 0; i < MAX - 1; i++){
        minIndex = i;
        for(j = i + 1; j < MAX; j++){
            if(*(values+j) < *(values+minIndex)){
                minIndex = j;
            }
        }
        if(minIndex != i){
            swap((values+i), (values+minIndex));
        }
    }
} // end sort
