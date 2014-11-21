//main.cpp
//allow user to input length of the race and number of horses
//horses have different chances of advancement in addition to coin flip

#include <iostream>
#include "Race.h"
#include<stdlib.h>
#include <time.h>

using namespace std;

int main(){
    cout << "Welcome to Horse Race.\n" << endl;

    srand(time(NULL));

    // initialize the race
    Race r = Race();
    r.start();

    return(0);
}
