""" changeMaker.py
    Author: Naeem Tai
    Date: 10/09/2012
    Program Overview: Receive user input for purchase price and cash tendered.
                      Output change due and how many of each value to return.
                      Keep track of transaction number and continue with new 
                      transactions until user wants to exit.
                      Note: All values are rounded to two decimal places.
"""

done = False
tNum = 0
while (not done):    
    print    
    tNum += 1
    print "============ Transaction #%d ============" % tNum
    print

    # get purchase amount of more than 0 and round it to two decimal places
    purchase = 0
    while (purchase <= 0):
        purchase = raw_input("Purchase Amount: $")
        purchase = float(purchase)
    purchase = round (purchase, 2)
    print "    Purchase Amount: $%.2f" % purchase
    print

    # get cash tender amount of more than purchase amount and round it to two decimal places
    cash = 0
    while (cash < purchase):
        cash = raw_input("Cash Tender: $")
        cash = float(cash)
    cash = round (cash, 2)
    print "    Cash Tender: $%.2f" % cash
    print

    # find change due
    change = cash - purchase
    print "Change Due: $%.2f" % change

    # sparate change due between bills and coins
    bills = int(change)
    coins = change - bills

    # calculate numer of bills for each type
    print "    Bills -"
    twenties = 0
    tens = 0
    fives = 0
    ones = 0

    billsLeft = bills

    if (billsLeft >= 20):
        twenties = billsLeft / 20
        billsLeft = billsLeft % 20
    print "        Twenties: %d" % twenties
    if (billsLeft >= 10):
        tens = billsLeft / 10
        billsLeft = billsLeft % 10
    print "        Tens: %d" % tens
    if (billsLeft >= 5):
        fives = billsLeft / 5
        billsLeft = billsLeft % 5
    print "        Fives: %d" % fives
    ones = billsLeft
    print "        Ones: %d" % ones

    # calculate number of coins for each type
    print "    Coins -"
    quaters = 0
    dimes = 0
    nickels = 0
    pennies = 0

    coinsLeft = int(coins * 100 + .5)

    if (coinsLeft >= 25):
        quaters = coinsLeft / 25
        coinsLeft = coinsLeft % 25
    print "        Quaters: %d" % quaters
    if (coinsLeft >= 10):
        dimes = coinsLeft / 10
        coinsLeft = coinsLeft % 10
    print "        Dimes: %d" % dimes
    if (coinsLeft >= 5):
        nickels = coinsLeft / 5
        coinsLeft = coinsLeft % 5
    print "        Nickels: %d" % nickels
    pennies = coinsLeft
    print "        Pennies: %d" % pennies

    print
    print "========================================"
    print
    print
    
    # ask user for another transaction
    response = raw_input("Start new transaction? (y/n): ")
    if (response.lower() == "n"):
        done = True
        
# end
print
print "Thanks for shopping!"