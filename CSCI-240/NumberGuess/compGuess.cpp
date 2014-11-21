// compGuess.cpp
// Naeem Tai 1/29/2013
/* Implement an algorithm where:
    user guesses the number, and computer reveals it in maximum of 7 tries.
*/

#include <iostream>
#include <string>

using namespace std;

main(){
    cout << "Guess a number between 1 and 100, and I will reveal it in maximum 7 tries." << endl;
    int high = 100;
    int low = 0;
    int guess;
    int tries = 0;
    char response = ' ';
    bool keepgoing = true;

    while(keepgoing){
        tries++;
        guess = (high + low) / 2;
        cout << tries << ": Is your number " << guess << "?" << endl;
        cout << "  h: too high, l: too low, c: correct" << endl;
        cin >> response;
        if(response == 'c'){
            keepgoing = false;
            cout << "It took me " << tries << " tries to guess your number." << endl;
        }
        else{
            if(response == 'h'){
                high = guess - 1;
            }
            else{
                if(response == 'l'){
                    low = guess + 1;
                }
                else{
                    cout << "  Unrecognized Input!" << endl;
                    tries--;
                }
            }
        }
        if(tries > 7){
            keepgoing = false;
            cout << "Couldn't do it! I need to improve this program." << endl;
        }
    }
}
