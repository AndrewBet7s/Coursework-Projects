// main.cpp
// Naeem Tai
/* to be ran by users
	inherits Student, Address, and Date
	imports data from file and outputs to a new file
	user controlled in continous loop
		gives users options on what to output
		gives users options to search by a piece of data from student records
		gives users an option to terminate the program */

#include <iostream>
#include <fstream>
#include <string.h>
#include "Date.h"
#include "Address.h"
#include "Student.h"

using namespace std;

int main()
{
    string record;

    // declare arrays of 50 students on heap
    Student *arr = new Student[50];
    Student *sorted = new Student[50];

    // open data.dat for input
    ifstream datafile;
    datafile.open("data.dat", ios::in);
    // check for presence of the file
    if(!datafile){
        cout << "Can't open the specified file." << endl;
    }
    else{
        int countt = 0;
        string test;
        // read each line from the file and pass on to student class to set data in arr array
        while(!datafile.eof()){
            getline(datafile, record);
            arr[countt].setData(record);
            countt++;
        }
    }
    // close data.dat
    datafile.close();

    // copy arr array to sorted array
    for (int x = 0; x < 50; x++){
        sorted[x] = arr[x];
    }
    // sort array of students using insertion sort
    // sort sorted array by last name
    int i, j;
    Student tmp;
    for(i = 1; i < 50; i++) {
        j = i;
        // strcmp to compare two string
        while(j > 0 && strcmp((sorted[j-1].getLName()).c_str(), (sorted[j].getLName()).c_str()) > 0) {
            tmp = sorted[j];
            sorted[j] = sorted[j-1];
            sorted[j-1] = tmp;
            j--;
        }
    }

    // output sorted data to a new file
    ofstream newfile;
    newfile.open("sortedData.txt");
    for(i = 0; i < 50; i++){
        newfile << sorted[i].getStudent();
    }
    // close sortedData.txt
    newfile.close();

    // loop untill user wants to exit
    bool keepGoing = true;
    string choice = "";
    string subChoice = "";
    string given = "";
    int found = 0;
    while (keepGoing){
        cout << "\n\n\n" << endl;
        cout << "Hello world!" << endl;

        // give user options and get input
        choice = "";
        cout << "1. Print Student Records" << endl;
        cout << "2. Print Last & First Names in order" << endl;
        cout << "3. Print Student Records in order" << endl;
        cout << "4. Search Student Records" << endl;
        cout << "Anything else to Exit" << endl;
        cout << "What would you like to do? ";
        cin >> choice;
        cout << "" << endl;

        // perfrom action of user's choice
        if(choice == "1"){
            cout << "-> Printing Student Records..." << endl;
            cout << "" << endl;
            // print student records
            for (int x = 0; x < 50; x++){
                cout << arr[x].getStudent();
            }
        }
        else if(choice == "2"){
            cout << "-> Printing Students' First & Last Names in Alphabetical Order..." << endl;
            cout << "" << endl;
            // print last and first names of students in order
            for (int x = 0; x < 50; x++){
                cout << sorted[x].getLName() << " "  << sorted[x].getFName() << endl;
            }
        }
        else if(choice == "3"){
            cout << "-> Printing Student Records in Alphabtical Order..." << endl;
            cout << "" << endl;
            // print student data in order of last names
            for (int x = 0; x < 50; x++){
                cout << sorted[x].getStudent();
            }
        }
        else if(choice == "4"){
            // give search options and recieve user input
            subChoice = "";
            cout << "Please choose an option." << endl;
            cout << "1. By Last Name" << endl;
            cout << "2. By First Name" << endl;
            cout << "3. By State" << endl;
            cout << "4. Return to Main Menu" << endl;
            cout << "Anything else to exit." << endl;
            cout << "What would you like to search by? ";
            cin >> subChoice;

            // perfroms search of user's choice
            given = "";
            found = 0;
            if(subChoice == "1"){
                // perform search by last name
                cout << "Enter Last Name to search: ";
                // get last name from user to search for
                cin >> given;
                cout << "" << endl;
                // search for students with user entered last name
                for(int x = 0; x < 50; x++){
                    if(sorted[x].getLName() == given){
                        sorted[x].printStudent();
                        found++;
                    }
                }
                cout << "" << endl;
                cout << "... " << found << " records found." << endl;
            }
            else if(subChoice == "2"){
                // perform search by first name
                cout << "Enter First Name to search: ";
                // get first name from user to search for
                cin >> given;
                cout << "" << endl;
                // search for students with user entered first name
                for(int x = 0; x < 50; x++){
                    if(sorted[x].getFName() == given){
                        sorted[x].printStudent();
                        found++;
                    }
                }
                cout << "" << endl;
                cout << "... " << found << " records found." << endl;
            }
            else if(subChoice == "3"){
                // perfrom search by state
                cout << "Enter State to search: ";
                // get state from user to search for
                cin >> given;
                cout << "" << endl;
                // search for students with user entered state
                for(int x = 0; x < 50; x++){
                    if((sorted[x].getAddress()).getState() == given){
                        sorted[x].printStudent();
                        found++;
                    }
                }
                cout << "" << endl;
                cout << "... " << found << " records found." << endl;
            }
            else if(subChoice == "4"){
                cout << "" << endl;
                cout << "Returning to Main Menu..." << endl;
            }
            else{
                // do nothing (exit)
                cout << "-> Exiting the program..." << endl;
                keepGoing = false;
            }
        }
        else{
            // do nothing (exit)
            cout << "-> Exiting the program..." << endl;
            keepGoing = false;
        }
    }

    // delete arr and sorted arrays from heap
    delete[] arr;
    delete[] sorted;

    return 0;
}
