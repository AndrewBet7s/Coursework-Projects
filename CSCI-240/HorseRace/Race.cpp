//Race.cpp

#include "Race.h"
#include <iostream>

using namespace std;

// empty constructor
// get the length of the race and number of horses, asign horses to array h
Race::Race(){
    getLength();
    getHorses();
    for(int i = 0; i < horses; i++){
        h[i] = Horse();
    }
    getMostFavorite();
    getLeastFavorite();
}

// get race length
void Race::getLength(){
    length = 0;
    while(length < 2 || length > 75){
        cout << "How long would you like the race to be? (2-75): ";
        cin >> length;
        cin.ignore();
    }
}

// get number of horses for the race
void Race::getHorses(){
    horses = 0;
    while(horses < 2 || horses > 10){
        cout << "How many horses would you like to be on the track? (2-10): ";
        cin >> horses;
        cin.ignore();
    }
}

// print lane for the given horse
void Race::printLane(int horseNum){
    for(int i = 1; i < length; i++){
        if(i == h[horseNum].getPosition()){
            cout << horseNum;
        }
        else{
            cout << ".";
        }
    }
    cout << "|" << endl;

    // check for the win for the given horse
    if(h[horseNum].getPosition() == length){
        cout << "Horse " << horseNum << " wins!" << endl;
    }
}

// start and run the race
void Race::start(){
    // print initial lanes for all horses
    for(int i = 0; i < horses; i++){
        Race::printLane(i);
    }

    bool keepGoing = true;
    // loop till any of the horses wins the race
    while(keepGoing){
        cout << "" << endl;
        cout << "Press enter for another turn: ";
        // wait for key press
        cin.ignore();
        // give each horse a chance to advance and print lanes
        for(int i = 0; i < horses; i++){
            h[i].advance();
            if(h[i].getPosition() == length){
                keepGoing = false;
            }
            Race::printLane(i);
        }
    }
}

// determine most favorite horse based on chances of advancement
void Race::getMostFavorite(){
    int most = 0;
    for(int i = 1; i < horses; i++){
        if(h[i].getChance() > h[most].getChance()){
            most = i;
        }
    }
    cout << "Most Favorite Horse: Horse " << most << endl;
}

// determine least favorite horse based on chances of advancement
void Race::getLeastFavorite(){
    int least = 0;
    for(int i = 1; i < horses; i++){
        if(h[i].getChance() < h[least].getChance()){
            least = i;
        }
    }
    cout << "Least Favorite Horse: Horse " << least << endl;
}
