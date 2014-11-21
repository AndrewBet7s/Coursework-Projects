""" cardGameBlackjack.py
    Author: Naeem Tai
    Date: 11/06/2012
    Program Overview: Blackjack card game.
                      Ace is counted as 11.
                      Two player game : User vs. Computer.
"""

from random import *

NUMCARDS = 52
DECK = 0
PLAYER = 1
COMP = 2

cardLoc = [0] * NUMCARDS
suitName = ("hearts", "diamonds", "spades", "clubs")
rankName = ("Ace", "Two", "Three", "Four", "Five", "Six", "Seven", 
            "Eight", "Nine", "Ten", "Jack", "Queen", "King")
rankValue = (11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10)
playerName = ("deck", "player", "computer")

def main():
    # put all cards in deck
    clearDeck()

    # assign five cards to player and computer
    for i in range(2):
        assignCard(PLAYER)
        assignCard(COMP)

    keepPlaying = True
    i = 0
    while (keepPlaying):
        # player move
        keepPlaying = playerMove()
        print
        # computer move
        if keepPlaying:
            keepPlaying = computerMove()
        if (i >= 3 and keepPlaying):
            showdown()
            keepPlaying = False
        i += 1
    
    print
    print
    print "Thank you for playing!"
    
# showdown
def showdown():
    print
    print
    print
    print "<<<<<<<<<<<<<< Showdown >>>>>>>>>>>>>>"
    print
    playerTotal = showTotal(PLAYER)
    compTotal = showTotal(COMP)
    print "Player's Total: " + str(playerTotal)
    print "Computer's Total: " + str(compTotal)
    if playerTotal > compTotal:
        finish(PLAYER)
    elif compTotal > playerTotal:
        finish(COMP)
    else:
        print
        print
        print "It is a Draw!"

# put all cards in deck
def clearDeck():
    pos = 0
    for i in range(0, len(suitName)):
        for j in range(0, len(rankName)):
            cardLoc[pos] = DECK
            pos += 1        
            
# pick a random available card and assign it to a player (player or computer)
def assignCard(player):
    pos = randint(0, 51)
    while (cardAvailable(pos) == False):
        pos = randint(0, 51)
    cardLoc[pos] = player

# show the cards a player (player or computer) has
def showHand(player):
    print
    print "Displaying " + playerName[player] + "'s Hand:"
    print "# \tValue\t    Card"
    pos = 0
    for i in range(0, len(suitName)):
        for j in range(0, len(rankName)):
            if (cardLoc[pos] == player):
                print str(pos) + "\t" + str(rankValue[j]) + "\t" + rankName[j] + " of " + suitName[i]
            pos += 1

# return total value player has
def showTotal(player):
    total = 0
    pos = 0
    for i in range(0, len(suitName)):
        for j in range(0, len(rankName)):
            if (cardLoc[pos] == player):
                total += rankValue[j]
            pos += 1
    return total

# determine if a card is available to be assigned to a player
def cardAvailable(pos):
    available = False
    if (cardLoc[pos] == 0):
        available = True
    return available

# player move
def playerMove():
    print
    print
    print "---------- PLAYER's turn ----------"
    showHand(PLAYER)
    total = showTotal(PLAYER)
    print "-> Current Total: " + str(total)
    play = True
    goThrough = True
    if total < 17:
        print
        print "Assigning a new card..."
        assignCard(PLAYER)
        showHand(PLAYER)
        total = showTotal(PLAYER)
        goThrough = False
        print "-> New Total: " + str(total)
    if total > 21:
        print
        print "Busted >>>"
        finish (COMP)
        play = False
    elif total == 21:
        print
        print "Blackjack $$$"
        finish (PLAYER)        
        play = False
    elif (total >= 17 and goThrough == True):
        print
        response = raw_input("Draw a new card? y/n : ")
        if response == "y":
            print "Assigning a new card..."
            assignCard(PLAYER)
            showHand(PLAYER)
            total = showTotal(PLAYER)
        else:
            print
            print "Pass <<<"
    return play

# computer move
def computerMove():
    print
    print
    print "---------- COMPUTER's turn ----------"
    showHand(COMP)    
    total = showTotal(COMP)
    print "-> Current Total: " + str(total)
    play = True
    goThrough = True
    if total < 17:
        print
        print "Assigning a new card..."
        assignCard(COMP)
        showHand(COMP)
        total = showTotal(COMP)
        goThrough = False
        print "-> New Total: " + str(total)    
    if total > 21:
        print
        print "Busted >>>"
        finish (PLAYER)
        play = False
    elif total == 21:
        print
        print "Blackjack $$$"
        finish (COMP)
        play = False
    elif (total >= 17 and goThrough == True):
        print
        print "Pass <<<"
    return play

# print winner
def finish(player):
    print
    print
    print
    print "=============== Result ==============="
    print
    print playerName[player] + " Wins!"

if __name__ == "__main__":
    main()
