// Date.h
// Naeem Tai

#ifndef DATE_H
#define DATE_H

#include <string>

using namespace std;

class Date
{
    private:
        int month;
        int day;
        int year;
    public:
        Date(); // empty constructor
        Date(const Date& original); // copy constructor
        Date(int m, int d, int y); // custom constructor
        // setters
        void setMonth(int m);
        void setDay(int d);
        void setYear(int y);
        // getters
        int getMonth();
        int getDay();
        int getYear();
        // additional methods
        void printDate();
        void setData(string piece);
        string getDate();
};

#endif // DATE_H
