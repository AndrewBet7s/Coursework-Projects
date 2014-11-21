""" cardGame.py
    Author: Naeem Tai
    Date: 10/30/2012
    Program Overview: Basic card game framework.
                      Keeps track of card locations.
                      Assign exactly five cards to each player.
                      Only assign cards that are avilable from deck.
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
playerName = ("deck", "player", "computer")

def main():
    # put all cards in deck
    clearDeck()

    # assign five cards to player and computer
    for i in range(5):
        assignCard(PLAYER)
        assignCard(COMP)

    # show all cards and their locations
    showDeck()
    # show the cards player has
    showHand(PLAYER)
    # show the cards computer has
    showHand(COMP)          

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

# print a list of all cards and their locations
def showDeck():
    print
    print
    print "Location of all cards:"
    print "# \t    Card \t\tLocation"
    pos = 0
    for i in range(0, len(suitName)):
        for j in range(0, len(rankName)):
            space = "\t\t"
            if (len(rankName[j] + " of " + suitName[i]) >= 16):
                space = "\t"            
            print str(pos) + "\t" + rankName[j] + " of " + suitName[i] + space + playerName[cardLoc[pos]]
            pos += 1

# show the cards a player (player or computer) has
def showHand(player):
    print
    print
    print "Displaying " + playerName[player] + "'s Hand:"
    print "# \t    Card"
    pos = 0
    for i in range(0, len(suitName)):
        for j in range(0, len(rankName)):
            if (cardLoc[pos] == player):
                print str(pos) + "\t" + rankName[j] + " of " + suitName[i]
            pos += 1

# determine if a card is available to be assigned to a player
def cardAvailable(pos):
    available = False
    if (cardLoc[pos] == 0):
        available = True
    return available
    
if __name__ == "__main__":
    main()
