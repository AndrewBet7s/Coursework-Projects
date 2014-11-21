""" guess.py
    Author: Naeem Tai
    Date: 10/17/2012
    Program Overview: Generate a random number. Receive user input and output
                      if the number is high or low until user guesses it right.
                      Keep track of how many tries it takes to guess it
                      right.
"""

import random

# generate a random number
correct = random.randint(1, 100)

guess = 0
tries = 0

# receive user's guess untill it is correct
while (guess != correct):
    tries +=1
    # receive user's guess
    guess = raw_input("Enter your guess (1-100): ")
    guess = int(guess)
    # compare guess with correct and provide feedback
    if (guess == correct):
        print "That is correct!"
    elif (guess > correct):
        print "Too high!"
    else:
        print "Too Low!"
print
# tell user how many tries it took to guess right
print "It took you %d tries." % tries