//Horse.cpp

#include "Horse.h"
#include <iostream>
#include<stdlib.h>
#include <time.h>

using namespace std;

// give each horse a random chance of advancement between 40-80%
Horse::Horse(){
    position = 1;
    chance = rand() % 41 + 40;
    chance = chance / 100;
}

// determine if the horse can advance to next position
void Horse::advance(){
    int coin = rand() % 10;
    determine = coin * chance;
    if(determine >= 2.5){
        position++;
    }
}

// return the horse's current postion
int Horse::getPosition(){
    return position;
}

// return the horse's chances of advancement
double Horse::getChance(){
    return chance;
}
