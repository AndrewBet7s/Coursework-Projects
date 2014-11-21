// numberGuess.cpp
// combine compGuess.cpp and userGuess.cpp

#include <iostream>
#include <string>
#include <time.h>
#include <cstdlib>

using namespace std;

void compGuess();
void userGuess();

main(){
    int choice = 0;
    cout << "Please choose a corresponding program number you wish to execute." << endl;
    cout << "1: You guess a number, and computer tries to reveal it." << endl;
    cout << "2: Computer guesses a number, and you try to reveal it." << endl;
    cout << "3: Exit." << endl;

    while(choice < 1 || choice > 3){
        cin >> choice;
    }

    if(choice == 1){
        compGuess();
    }
    else{
        if(choice == 2){
            userGuess();
        }
        else{
            if(choice == 3){
                cout << "Thank you!" << endl;
                return(0);
            }
        }
    }
}

void compGuess(){
    cout << string(5, '\n');

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

    cout << string(5, '\n');

    main();
}

void userGuess(){
    cout << string(5, '\n');

    srand(time(NULL));
    int correct = rand() % 100;
    int guess;
    bool keepgoing = true;
    int turn = 0;
    cout << "correct: " << correct << endl;
    while(keepgoing){
        turn++;
        cout << turn << ": please enter a number: ";
        cin >> guess;

        if (guess < correct){
            cout << "Too Low" << endl;
        } else if (guess > correct){
            cout << "Too High" << endl;
        } else {
            cout << "Correct!" << endl;
            keepgoing = false;
        } // end if
    } // end while
    cout << "It took " << turn << " turns." << endl;

    cout << string(5, '\n');

    main();
}
