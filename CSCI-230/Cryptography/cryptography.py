"""
    cryptography.py
    Author: Naeem Tai
    Date: 10/23/2012
    Encode or decode a string implementing a simple substitution cypher.
    Only alphabets are encoded/decoded; numbers and special characters
    are left as they are.
"""

alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
key =   "XPMGTDHLYONZBWEARKJUFSCIQV"

# structure of how program runs by responding to user inputs
def main():
    keepGoing = True
    while keepGoing:
        response = menu()
        if response == "1":
            #use isalpha to check that it contains nothing but letters (extra)
            plain = raw_input("text to be encoded: ")
            print "Encoded String: " + encode(plain)
        elif response == "2":
            #use isalpha to check that it contains nothing but letters (extra)
            coded = raw_input("code to be decyphered: ")
            print "Decoded String: " + decode(coded)
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
    choice = raw_input("What do you want to do? ")
    print
    return choice

# encoder
def encode(plain):
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
def decode(coded):
    coded = coded.upper()
    plain = ""
    for i in range(0, len(coded)):
        at = key.find(coded[i])
        if (at == -1):
            plain += coded[i]
        else:
            plain += alpha[at]
    return plain

main()