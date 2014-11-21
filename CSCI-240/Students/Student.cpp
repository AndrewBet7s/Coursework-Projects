// Student.cpp
// Naeem Tai
/* contains student data
	inherits Date and Address */

#include "Student.h"
#include <iostream>
#include <sstream>
#include <stdlib.h>

using namespace std;

// empty constructor
// set student data to unknown
Student::Student()
{
    lName = "Last";
    fName = "First";
    adr = Address();
    dob = Date();
    doc = Date();
    gpa = 0;
    crHrCom = 0;
}

// copy constructor
Student::Student(const Student& original){
    lName = original.lName;
    fName = original.fName;
    adr = original.adr;
    dob = original.dob;
    doc = original.doc;
    gpa = original.gpa;
    crHrCom = original.crHrCom;
}


/* setters */

// set first name of student
void Student::setFName(string fn){
    fName = fn;
}

// set last name of student
void Student::setLName(string ln){
    lName = ln;
}

// set adress of student
void Student::setAddress(Address a){
    adr = a;
}

// set date of birth of student
void Student::setDob(string d){
    // parse string here
}

// set anticipated date of completion of student
void Student::setDoc(string d){
    //parse string here
}

// set gpa of student
void Student::setGpa(double g){
    gpa = g;
}

// set credit hours completed of student
void Student::setCcHrCom(int chc){
    crHrCom = chc;
}


/* getters */

// get first name of student
string Student::getFName(){
    return fName;
}

// get last name of student
string Student::getLName(){
    return lName;
}

// get address of student
Address Student::getAddress(){
    return adr;
}

// get date of birth of student
Date Student::getDob(){
    return dob;
}

// get anticipated date of completion of student
Date Student::getDoc(){
    return doc;
}

// get gpa of student
double Student::getGpa(){
    return gpa;
}

// get completed credit hours of student
int Student::getCrHrCom(){
    return crHrCom;
}


/* additional methods */

// print student data
void Student::printStudent(){
    cout << "Name: " << lName << " " << fName << endl;
    cout << "   Address: ";
    adr.printAddress();
    cout << "   Date of Birth: ";
    dob.printDate();
    cout << "   Date of Graduation: ";
    doc.printDate();
    cout << "   GPA: ";
    cout << gpa << endl;
    cout << "   Credit Hours Completed: ";
    cout << crHrCom << endl;
}

// return student data as a string
string Student::getStudent(){
    stringstream s;
    s << lName << ", " << fName << ", ";
    s << adr.getAddress() << ", ";
    s << dob.getDate() << ", ";
    s << doc.getDate() << ", ";
    s << gpa << ", " << crHrCom << "\n";
    return s.str();
}

// set student data using a line of record
void Student::setData(string record){
    // separate student data by comma
    string piece;
    istringstream ss(record);
    int countt = 1;
    // set student data
    while (getline(ss, piece, ',')){
        if(countt == 1){
            lName = piece;
        }
        else if(countt == 2){
            fName = piece;
        }
        else if(countt == 3){
            adr.setAdrLineOne(piece);
        }
        else if(countt == 4){
            adr.setAdrLineTwo(piece);
        }
        else if(countt == 5){
            adr.setCity(piece);
        }
        else if(countt == 6){
            adr.setState(piece);
        }
        else if(countt == 7){
            adr.setZip(atoi(piece.c_str()));
        }
        else if(countt == 8){
            // pass a string to date class to set birth date
            dob.setData(piece);
        }
        else if(countt == 9){
            // pass a string to date class to set completion date
            doc.setData(piece);
        }
        else if(countt == 10){
            gpa = atof(piece.c_str());
        }
        else if(countt == 11){
            crHrCom = atoi(piece.c_str());
        }
        countt++;
    }
}
