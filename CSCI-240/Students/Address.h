// Address.h
// Naeem Tai

#ifndef ADDRESS_H
#define ADDRESS_H

#include <string>

using namespace std;

class Address
{
    private:
        string adrLineOne;
        string adrLineTwo;
        string city;
        string state;
        int zip;
    public:
        Address(); // constructor
        Address(const Address& original); // copy constructor
        Address(string al1, string al2, string c, string s, int z); // custom constructor
        // setters
        void setAdrLineOne(string al1);
        void setAdrLineTwo(string al2);
        void setCity(string c);
        void setState(string s);
        void setZip(int z);
        // getters
        string getAdrLineOne();
        string getAdrLineTwo();
        string getCity();
        string getState();
        int getZip();
        // additional methods
        void printAddress();
        string getAddress();
};

#endif // ADDRESS_H
