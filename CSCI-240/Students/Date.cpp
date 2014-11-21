// Date.cpp
// Naeem Tai
/* contains date data */

#include <iostream>
#include <sstream>
#include <stdlib.h>
#include "Date.h"

using namespace std;

// empty constructor
// set default month, day, and year
Date::Date()
{
    month = 1;
    day = 1;
    year = 1900;
}

// copy constructor
Date::Date(const Date& original){
    month = original.month;
    day = original.day;
    year = original.year;
}

// custom constructor
// set month, day, and date as given
Date::Date(int m, int d, int y)
{
    month = m;
    day = d;
    year = y;
}


/* setters */

// set month of the date
void Date::setMonth(int m){
    month = m;
}

// set day of the date
void Date::setDay(int d){
    day = d;
}

// set year of the date
void Date::setYear(int y){
    year = y;
}


/* getters */

// get month of the date
int Date::getMonth(){
    return month;
}

// get day of the date
int Date::getDay(){
    return day;
}

// get month of the date
int Date::getYear(){
    return year;
}


/* additional methods */

// print date
void Date::printDate(){
    cout << month << "/" << day << "/" << year << endl;
}

// set date data by separating a string
void Date::setData(string piece){
    // separate date data by /
    string value;
    istringstream ss(piece);
    int countt = 1;
    // set date data
    while (getline(ss, value, '/')){
        if(countt == 1){
            month = atoi(value.c_str());
        }
        else if(countt == 2){
            day = atoi(value.c_str());
        }
        else if(countt == 3){
            year = atoi(value.c_str());
        }
        countt++;
    }
}

// return date as a string
string Date::getDate(){
    stringstream d;
    d << month << "/" << day << "/" << year;
    return d.str();
}
