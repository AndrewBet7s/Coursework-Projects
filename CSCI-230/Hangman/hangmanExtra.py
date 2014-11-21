"""
    hangmanExtra.py
    Author: Naeem Tai
    Date: 12/05/2012
    Program Overview: GUI Hangman Game
"""

from Tkinter import *
from random import *
import tkMessageBox

class Game(Tk):
    def __init__(self):
        Tk.__init__(self)

        self.title("Hangman")
        self.titlefont = ("Rockwell Extra Bold", 20, "bold")
        self.headerfont = ("Times New Roman", 20, "bold")
        self.normalfont = ("Arial", 20, "bold")
        self["bg"] = "gray"

        self.heading = [""] * 7
        self.myword = ""
        self.lives = 6
        self.question = [""] * 15
        self.questionlbls = [""] * 15
        self.answerlbls = [""] * 15
        self.answer = [""] * 15
        self.alphabtns = []
        # set alphabets array
        self.alphabets = [""] * 26
        for i in range (0, 26, 1):
            self.alphabets[i] = chr(i + 65)

        self.setGrid()
        self.setMenu()
        self.setTitle()
        self.setBlanks()
        self.setAlpha()
        self.setEnds()
        self.setStats()
        self.setCanvas()


        # change font size according to change in window size
        def configure(event):
            # get width and height of the window
            high = self.winfo_height()
            wide = self.winfo_width()
            # decide determinant
            if high < 1.5*wide:
                change = high/25
            else:
                change = wide/25
            # change font sizes
            self.titlefont = ("Rockwell Extra Bold", change, "bold")
            self.headerfont = ("Times New Roman", change, "bold")
            self.normalfont = ("Arial", change, "bold")
            # assign font to widgets
            for i in range(0, 26, 1):
                self.alphabtns[i]["font"] = self.headerfont
            for i in range(0, len(self.myword), 1):
                if self.answerlbls[i] != "":
                    self.questionlbls[i]["font"] = self.normalfont
            for i in range(0, len(self.myword), 1):
                if self.answerlbls[i] != "":
                    self.answerlbls[i]["font"] = self.normalfont
            for i in range(0, 7, 1):
                if self.heading[i] != "":
                    self.heading[i]["font"] = self.titlefont
            self.livesLeft["font"] = self.normalfont
            self.status["font"] = self.normalfont
            self.endGameBtn["font"] = self.normalfont

        self.bind("<Configure>", configure)


    # configure grid to adjust widgets in accordance with window size
    def setGrid(self):
        for i in range(0, 21, 1):
            self.columnconfigure(i, weight = 20)
        for i in range(1, 12, 1):
            self.rowconfigure(i, weight = 11)


    # create menu system
    def setMenu(self):
        menubar = Menu(self)
        # file
        filemenu = Menu(menubar, tearoff=0)
        filemenu.add_command(label="New Game", command=self.getNewWord)
        filemenu.add_separator()
        filemenu.add_command(label="Exit", command=self.destroy)
        menubar.add_cascade(label="File", menu=filemenu)
        # edit
        editmenu = Menu(menubar, tearoff=0)
        editmenu.add_command(label="Add New Word...", command=self.addNewWord)
        menubar.add_cascade(label="Edit", menu=editmenu)
        # help
        helpmenu = Menu(menubar, tearoff=0)
        helpmenu.add_command(label="About...", command=self.seeAbout)
        helpmenu.add_command(label="How to Play?", command=self.howToPlay)
        menubar.add_cascade(label="Help", menu=helpmenu)

        self.config(menu=menubar)


    # About the gmae
    def seeAbout(self):
        top = Toplevel()
        top.title("About This Game")
        Label(top, text = "About This Game:", font = ("Times New Roman", 12, "bold")).grid(columnspan = 2)
        Label(top, text = "Game: ", font = ("Times New Roman", 12, "italic")).grid(row = 2, column = 0, sticky = "e")
        Label(top, text = "Hangman", font = ("Times New Roman", 12, "")).grid(row = 2, column = 1, sticky = "w")
        Label(top, text = "Developed By: ", font = ("Times New Roman", 12, "italic")).grid(row = 3, column = 0, sticky = "e")
        Label(top, text = "Naeem Tai", font = ("Times New Roman", 12, "")).grid(row = 3, column = 1, sticky = "w")
        Label(top, text = "Last Update: ", font = ("Times New Roman", 12, "italic")).grid(row = 4, column = 0, sticky = "e")
        Label(top, text = "Wednesday, 12/05/2012", font = ("Times New Roman", 12, "")).grid(row = 4, column = 1, sticky = "w")
        top.mainloop()


    # How To Play
    def howToPlay(self):
        top = Toplevel()
        top.title("How to Play?")
        Label(top, text = "How to Play?", font = ("Times New Roman", 12, "bold")).grid()
        instructions = """
    * Start a new game from File -> New Game
    * Click on a letter to guess the letter.
    * Monitor your progress on the right end of the window.
    * Click "End Game" to end the current game at anytime.
    * To add a new word to the file:
        Edit -> Add New Word.
    * To learn more about the game:
        Help -> About
    * For Instructions:
        Help -> How to Play?
    * To Exit:
        File -> Exit
        """
        Label(top, text = instructions, font = ("Times New Roman", 12, ""), justify = "left").grid()
        top.mainloop()


    # Add New Word
    def addNewWord(self):
        def addNow():
            newWord = wordtxt.get()
            newWord = newWord.upper()
            valid = True
            # check validity of the word
            if len(newWord) == 0: # no word
                valid = False
                tkMessageBox.showerror("Error", "Please enter a word. Retry...")
            elif len(newWord) > 15: # word length
                valid = False
                tkMessageBox.showwarning("Error", "Word longer than 15 characters. Unable to add to file. Retry...")
            else: # contains alphabet
                alphabetical = False
                for i in range(0, len(newWord), 1):
                    if newWord[i].isalpha():
                        alphabetical = True
                if not alphabetical:
                    tkMessageBox.showerror("Error", "Word must contain atleast one alphabet. Retry...")
                    valid = False
            # check for duplication
            if valid:
                myfile = open("words.txt", "r")
                for line in myfile:
                    if newWord == line.strip():
                        tkMessageBox.showwarning("Warning!", "Word already exists in the file. Retry...")
                        valid = False
                myfile.close()
            # success - add to file if valid
            if valid:
                myfile = open("words.txt", "a")
                myfile.write("\n"+newWord)
                myfile.close()
                tkMessageBox.showinfo("Success", "Word successfully added to the file.")

        top = Toplevel()
        top.title("Add New Word...")
        Label(top, text = "Enter a word you wish to add to the file.", font = ("Times New Roman", 12, "italic")).grid(columnspan = 2)
        Label(top, text = "Word (15 characters max.): ", font = ("Times New Roman", 12, "bold")).grid(row = 2, column = 0)
        wordtxt = Entry(top, text = "", bd = 3, font = ("Times New Roman", 12, ""))
        wordtxt.grid(row = 2, column = 1)
        add = Button(top, text = "Add to File", font = ("Times New Roman", 12, ""), fg = "white", bg = "black", relief = "raised")
        add["command"] = addNow
        add.grid(columnspan = 2)
        top.mainloop()


    # create title
    def setTitle(self):
        self.heading = [""] * 7
        self.heading[0] = Label(self, text = "H", font = self.titlefont, bg = "white", fg = "blue", relief = "groove", bd = 5)
        self.heading[0].grid(row = 1, column = 4, sticky = N+E+S+W)
        self.heading[1] = Label(self, text = "A", font = self.titlefont, bg = "white", fg = "blue", relief = "groove", bd = 5)
        self.heading[1].grid(row = 1, column = 5, sticky = N+E+S+W)
        self.heading[2] = Label(self, text = "N", font = self.titlefont, bg = "white", fg = "blue", relief = "groove", bd = 5)
        self.heading[2].grid(row = 1, column = 6, sticky = N+E+S+W)
        self.heading[3] = Label(self, text = "G", font = self.titlefont, bg = "white", fg = "blue", relief = "groove", bd = 5)
        self.heading[3].grid(row = 1, column = 7, sticky = N+E+S+W)
        self.heading[4] = Label(self, text = "M", font = self.titlefont, bg = "white", fg = "blue", relief = "groove", bd = 5)
        self.heading[4].grid(row = 1, column = 8, sticky = N+E+S+W)
        self.heading[5] = Label(self, text = "A", font = self.titlefont, bg = "white", fg = "blue", relief = "groove", bd = 5)
        self.heading[5].grid(row = 1, column = 9, sticky = N+E+S+W)
        self.heading[6] = Label(self, text = "N", font = self.titlefont, bg = "white", fg = "blue", relief = "groove", bd = 5)
        self.heading[6].grid(row = 1, column = 10, sticky = N+E+S+W)
        # place empty line
        self.createEmptyLine(2)


    # create appropriate number of centered blanks based on word, assign non-alphabetical values to question list
    def makeblanks(self, myword):
        self.question = [""] * 15
        self.questionlbls = [""] * 15
        c = 7 - int(len(myword) / 2)
        for i in range(0, len(myword), 1):
            if ord(myword[i]) >= 65 and ord(myword[i]) <= 90:
                self.questionlbls[i] = Label(self, text = "", font = self.normalfont, bg = "white", fg = "green", relief = "groove", bd = 5)
                self.questionlbls[i].grid(row = 3, column = c, sticky = N+E+S+W)
            elif ord(myword[i]) == 32:
                self.question[i] = " "
                self.questionlbls[i] = Label(self, text = " ", font = self.normalfont, bg = "gray", bd = 5)
                self.questionlbls[i].grid(row = 3, column = c, sticky = N+E+S+W)
            else:
                self.question[i] = myword[i]
                self.questionlbls[i] = Label(self, text = myword[i], font = self.normalfont, bg = "white", fg = "green", relief = "groove", bd = 5)
                self.questionlbls[i].grid(row = 3, column = c, sticky = N+E+S+W)
            c += 1
        # set stats section
        self.setStats()


    # choose a random word from the file
    def getNewWord(self):
        # remove previous blanks
        self.setBlanks()
        # count lines in a file
        lines = 0
        myfile = open("words.txt", "r")
        for line in myfile:
            lines +=1
        myfile.close()
        # get a word from a random line
        rndline = randint(1, lines)
        current = 0
        myfile = open("words.txt", "r")
        for line in myfile:
            current += 1
            if current == rndline:
                self.myword = line.strip()
                self.myword = self.myword.upper()
        myfile.close()
        # assign values to answer list
        self.answer = [""] * 15
        for i in range (0, len(self.myword), 1):
            self.answer[i] = self.myword[i]
        # make blanks
        self.makeblanks(self.myword)
        # enable alphabet buttons
        self.enableAlphas()
        # enable end game button
        self.endGameBtn["state"] = "active"
        # show stats section
        self.lives = 6
        self.livesLeft["text"] = "Lives Left: " + str(self.lives)
        # put canvas
        self.setCanvas()


    # create space for blanks and answer
    def setBlanks(self):
        self.createEmptyLine(3)
        self.createEmptyLine(4)


    # create letter board
    def setAlpha(self):
        self.createEmptyLine(5)
        # create buttons for a letter
        colnum = -1
        rownum = 6
        letter = 64
        switch = True
        for i in range (0, 26, 1):
            colnum += 2
            letter += 1
            self.alphabtns.append(Button(self, text = chr(letter), bd = 7, font = self.headerfont, cursor="man"))
            self.alphabtns[i]["command"] = lambda i = i: self.checkLetter(self.alphabets[i])
            self.alphabtns[i].grid(row = rownum, column = colnum, sticky = N+E+S+W)
            if colnum >=12:
                switch = not switch
                if switch:
                    colnum = -1
                else:
                    colnum = 0
                rownum += 1
        # disable alphabet buttons
        self.disableAlphas()


    # check for letter in the word
    def checkLetter(self, letter):
        # put the letter in question blank if it matches
        notFound = True
        for i in range (0, len(self.myword), 1):
            if self.answer[i] == letter:
                notFound = False
                self.questionlbls[i]["text"] = self.answer[i]
                self.question[i] = letter
        # disable clicked button
        index = self.alphabets.index(letter)
        self.alphabtns[index]["state"] = "disabled"
        self.alphabtns[index]["relief"] = "sunken"
        self.alphabtns[index]["bg"] = "white"
        self.alphabtns[index]["fg"] = "black"
        # decrease lives if letter not in the word
        if notFound:
            self.lives -= 1
        # check for win
        if self.question == self.answer:
            self.status["text"] = "You WIN!!!"
            self.disableAlphas()
            self.endGameBtn["state"] = "disabled"
        # update canvas
        if self.lives == 5:
            self.myimage = PhotoImage(file = "hangman1.gif")
            self.hangmanImg.create_image(100, 160, image = self.myimage)
        elif self.lives == 4:
            self.myimage = PhotoImage(file = "hangman2.gif")
            self.hangmanImg.create_image(100, 160, image = self.myimage)
        elif self.lives == 3:
            self.myimage = PhotoImage(file = "hangman3.gif")
            self.hangmanImg.create_image(100, 160, image = self.myimage)
        elif self.lives == 2:
            self.myimage = PhotoImage(file = "hangman4.gif")
            self.hangmanImg.create_image(100, 160, image = self.myimage)
        elif self.lives == 1:
            self.myimage = PhotoImage(file = "hangman5.gif")
            self.hangmanImg.create_image(100, 160, image = self.myimage)
        # check for loss
        elif self.lives <= 0:
            self.myimage = PhotoImage(file = "hangman6.gif")
            self.hangmanImg.create_image(100, 160, image = self.myimage)
            self.status["text"] = "You LOSE!!!"
            self.disableAlphas()
            self.resetGame()
        # update stats section
        self.livesLeft["text"] = "Lives Left: " + str(self.lives)

    # create empty line on a row
    def createEmptyLine(self, rownum):
        for i in range (0, 20, 1):
            Label(self, text = "", font = self.normalfont, bg = "gray", width = 2, bd = 5).grid(row = rownum, column = i, sticky = N+E+S+W)


    # create end game button
    def setEnds(self):
        self.createEmptyLine(10)
        self.endGameBtn = Button(self, text = "End Game", font = ("Algerian", 16, ""), bg = "red", fg = "white")
        self.endGameBtn["command"] = self.askReset
        self.endGameBtn["activebackground"] = "red"
        self.endGameBtn["activeforeground"] = "white"
        self.endGameBtn["state"] = "disabled"
        self.endGameBtn.grid(row = 11, column = 5, columnspan = 5, sticky = N+E+S+W)


    # ask yes/no to end game
    def askReset(self):
        reply = tkMessageBox.askyesno("confirm", "Are you sure you want to end current game? It will be considered as a loss.")
        if reply:
            self.resetGame()


    # reset game when clicking a new game or clicking end game
    def resetGame(self):
        self.answerlbls = [""] * 15
        # update stats
        self.lives = 0
        self.livesLeft["text"] = "Lives Left: " + str(self.lives)
        self.myimage = PhotoImage(file = "hangman6.gif")
        self.hangmanImg.create_image(100, 160, image = self.myimage)
        self.status["text"] = "You LOSE!!!"
        # show the word
        c = 7 - int(len(self.myword) / 2)
        for i in range(0, len(self.myword), 1):
            if ord(self.myword[i]) == 32:
                self.answerlbls[i] = Label(self, text = "", font = self.normalfont, bg = "gray", bd = 5)
                self.answerlbls[i].grid(row = 3, column = c, sticky = N+E+S+W)
            else:
                self.answerlbls[i] = Label(self, text = self.myword[i], font = self.normalfont, bg = "white", fg = "red", relief = "groove", bd = 5)
                self.answerlbls[i].grid(row = 4, column = c, sticky = N+E+S+W)
            c += 1
        # disable alphabet buttons
        self.disableAlphas()
        # disable end game button
        self.endGameBtn["state"] = "disabled"


    # set stats section - temporary label, replace with canvas
    def setStats(self):
        self.livesLeft = Label(self, text = " ", font = self.normalfont, bg = "gray", fg = "black")
        self.livesLeft.grid(row = 2, column = 15, columnspan = 5, sticky = N+E+S+W)
        self.status = Label(self, text = " ", font = self.normalfont, bg = "gray", fg = "black")
        self.status.grid(row = 3, column = 15, columnspan = 5, sticky = N+E+S+W)


    # set canvas to display image
    def setCanvas(self):
        self.hangmanImg = Canvas(self, width = 100, height = 100, bg = "gray", relief = "raised")
        self.hangmanImg.grid(row = 4, column = 15, rowspan = 6, columnspan = 5, sticky = N+E+S+W)


    # disable alphabet buttons
    def disableAlphas(self):
        for i in range(0, 26, 1):
            self.alphabtns[i]["state"] = "disabled"
            self.alphabtns[i]["relief"] = "sunken"
            self.alphabtns[i]["bg"] = "white"
            self.alphabtns[i]["fg"] = "black"


    # enable alphabet buttons
    def enableAlphas(self):
        for i in range(0, 26, 1):
            self.alphabtns[i]["state"] = "active"
            self.alphabtns[i]["relief"] = "raised"
            self.alphabtns[i]["bg"] = "black"
            self.alphabtns[i]["fg"] = "white"
            self.alphabtns[i]["activebackground"] = "black"
            self.alphabtns[i]["activeforeground"] = "white"


def main():
    app = Game()
    app.mainloop()

if __name__ == "__main__":
    main()
