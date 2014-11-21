""" guessExtra.py
    Author: Naeem Tai
    Date: 10/17/2012
    Program Overview: Generate a random number. Receive user input and output
                      if the number is high or low until user guesses it right.
                      Only allow an integer input. Provide low and high limits
                      for user's convinience while guessing. Keep track of how 
                      many tries it takes to guess it right.
"""

import random

# generate a random number
correct = random.randint(1, 100)
print "I have guessed an integer between 1 & 100."
print "It's your turn to guess it."
print

guess = 0
tries = 0
low = 1
high = 100

# receive user's guess untill it is correct
while (guess != correct):
    tries +=1
    notValid = True
    # recieve only an integer as user's guess
    while (notValid):
        try:
            guess = raw_input("Enter your guess (%d-%d): " %(low, high))
            guess = int(guess)
            notValid = False
        except ValueError:
            print "Not an INTEGER."
            notValid = True
    print
    # compare guess with correct and prvide feedback
    if (guess == correct):
        print "That is correct!"
    elif (guess >= correct):
        print "Too high!"
        if (high >= guess):
            high = guess - 1
    else:
        print "Too Low!"
        if (low <= guess):
            low = guess + 1
    print    
print
# tell user how many tries it took to guess right
print "It took you %d tries." % tries