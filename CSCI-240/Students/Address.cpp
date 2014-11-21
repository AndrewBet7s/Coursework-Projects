// Address.cpp
// Naeem Tai
/* contains address data */

#include "Address.h"
#include <iostream>
#include <sstream>

using namespace std;

// empty constructor
// set adress data to unknown
Address::Address()
{
    adrLineOne = "# Anonymus Street";
    adrLineTwo = "Park Name";
    city = "Unknown City";
    state = "Unknown State";
    zip = 12345;
}

// copy constructor
Address::Address(const Address& original){
    adrLineOne = original.adrLineOne;
    adrLineTwo = original.adrLineTwo;
    city = original.city;
    state = original.state;
    zip = original.zip;
}

// custom constructor
// set address data as given
Address::Address(string al1, string al2, string c, string s, int z){
    adrLineOne = al1;
    adrLineTwo = al2;
    city = c;
    state = s;
    zip = z;
}


/* setters */

// set line one of address
void Address::setAdrLineOne(string al1){
    adrLineOne = al1;
}

// set line two of address
void Address::setAdrLineTwo(string al2){
    adrLineTwo = al2;
}

// set city of Address
void Address::setCity(string c){
    city = c;
}

// set state of Address
void Address::setState(string s){
    state = s;
}

// set zip code of address
void Address::setZip(int z){
    zip = z;
}


/* getters */

// get line one of address
string Address::getAdrLineOne(){
    return adrLineOne;
}

// get line two of address
string Address::getAdrLineTwo(){
    return adrLineTwo;
}

// get city of address
string Address::getCity(){
    return city;
}

// get state of address
string Address::getState(){
    return state;
}

// get zip code of address
int Address::getZip(){
    return zip;
}


/* additional methods */

// print address
void Address::printAddress(){
    cout << adrLineOne << ", " << adrLineTwo << ", " << city << ", " << state << ", " << zip << endl;
}

// return address data as a string
string Address::getAddress(){
    stringstream a;
    a << adrLineOne << " " << adrLineTwo << " " << city << " " << state << " " << zip;
    return a.str();
}
