""" guessReverse.py
    Author: Naeem Tai
    Date: 10/17/2012
    Program Overview: Tell the user to guess a number. Automatically generate a
                      a number between limits based on user's feedback of the 
                      number being high, low, or correct. Only allow an integer
                      input. Keep track of how many tries it takes to guess it
                      right. Forces correct answer when low and high limits are
                      the same so that user cannot deny. Eliminate error when 
                      low limit becomes greater than high limit so that user 
                      cannot cheat.
"""

import random

print "Please think of an integer between 1 & 100."
print "I'll guess your number."
print "You tell me if I'm too high, too low, or correct."
print

low = 1
high = 100

# guess a number between limits untill it is right
notCorrect = True
tries = 0
while (notCorrect):
    tries += 1
    print
    guess = random.randint(low, high)
    print "I guess: %d" % guess
    feedback = ""
    # recieve user's feedback only as h, l, or c and adjust limits
    notValid = True
    while (notValid):
        feedback = raw_input("Too (h)igh, Too (l)ow, or (c)orrect? ")
        feedback = feedback.lower()
        # analyze user's feedback and adjust limits
        if (feedback == "c"):
            notValid = False
            notCorrect = False
        elif (feedback == "h"):
            notValid = False
            high = guess - 1
        elif (feedback == "l"):
            notValid = False
            low = guess + 1
    # when there is only one number left (nothing else to guess)
    if (low == high):
        notCorrect = False
        print
        print "This is it."
        print "%d has to be your number." % low
    
    # eliminate error when low limit becomes greater than high limit
    if (low > high):
        notCorrect = False
        print
        print "Low Limit approached greater than High Limit."
        print "You are cheating."
        
# display how many tries it took to guess correct
print
print
print "I got it!"
print "It took me %d tries." % tries