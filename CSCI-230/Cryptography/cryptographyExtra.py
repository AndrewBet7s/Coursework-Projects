"""
    cryptographyExtra.py
    Author: Naeem Tai
    Date: 10/23/2012
    Program Overview: Encode or decode a string implementing a simple 
                      substitution cypher. USers are able to generate a 
                      random key. Only alphabets are encoded/decoded; 
                      numbers and special characters are left as they are.
                      Note: Decoding/Encoding of a phrase must be done
                            with the same key with which the phrase was
                            encoded/decoded.
"""

from random import randint

# generate a random key
def randomKey():
    newkey = ""
    i = 0
    while (i < len(alpha)):
        ii = randint(0, len(alpha)-1)
        if (newkey.count(alpha[ii]) == 0):
            newkey += alpha[ii]            
            i = i + 1
    return newkey

alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

# structure of how program runs by responding to user inputs
def main():
    key =   "XPMGTDHLYONZBWEARKJUFSCIQV"
    keepGoing = True
    while keepGoing:
        response = menu()
        if response == "1":
            plain = raw_input("text to be encoded: ")
            print "Encoded String: " + encode(plain, key)
        elif response == "2":
            coded = raw_input("code to be decyphered: ")
            print "Decoded String: " + decode(coded, key)
        elif response == "3":
            key = randomKey()
            print "Generating a New Key... done"
        elif response == "0":
            print "Thanks for doing secret spy stuff with me."
            keepGoing = False
        else:
            print "I don't know what you want to do..."

# main menu
# receive user input on what to do
def menu():
    print
    print
    print "   SECRET DECODER MENU"
    print "   0) Quit"
    print "   1) Encode"
    print "   2) Decode"
    print "   3) Generate a New Key"
    choice = raw_input("What do you want to do? ")
    print
    return choice

# encoder
def encode(plain, key):
    plain = plain.upper()
    coded = ""
    for i in range(0, len(plain)):
        at = alpha.find(plain[i])
        if (at == -1):
            coded += plain[i]
        else:
            coded += key[at]
    return coded

# decoder
def decode(coded, key):
    coded = coded.upper()
    plain = ""
    for i in range(0, len(coded)):
        at = key.find(coded[i])
        if (at == -1):
            plain += coded[i]
        else:
            plain += alpha[at]
    return plain


if (__name__ == "__main__"):
    main()