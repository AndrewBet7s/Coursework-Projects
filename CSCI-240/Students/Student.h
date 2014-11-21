// Student.h
// Naeem Tai

#ifndef STUDENT_H
#define STUDENT_H

#include <string>
#include "Date.h"
#include "Address.h"

using namespace std;

class Student
{
    private:
        string lName;
        string fName;
        Address adr;
        Date dob;
        Date doc;
        double gpa;
        int crHrCom;
    public:
        Student(); // constructor
        Student(const Student& original); // copy constructor
        // setters
        void setFName(string fN);
        void setLName(string lN);
        void setAddress(Address a);
        void setDob(string d);
        void setDoc(string d);
        void setGpa(double g);
        void setCcHrCom(int chc);
        // getters
        string getFName();
        string getLName();
        Address getAddress();
        Date getDob();
        Date getDoc();
        double getGpa();
        int getCrHrCom();
        // additional methods
        void printStudent();
        void setData(string record);
        string getStudent();
};

#endif // STUDENT_H
