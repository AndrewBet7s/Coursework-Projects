"""
    bmiCalculator.py
    Author: Naeem Tai
    Date: 11/12/2012
    Program Overview: GUI to calculate Body Mass Index.
"""

from Tkinter import *

class BMI(Tk):
    def __init__(self):
        Tk.__init__(self)
        self.headerFont = ("Times New Roman", "16", "bold")
        self.titleFont = ("Times New Roman", "12", " bold italic")
        self.normalFont = ("Times New Roman", "12")
        self.title("BMI Calculator")
        self["bg"] = "purple"
        Label(self, text = "Body Mass Index (BMI) Calculator", font = self.headerFont, fg = "white", bg = "brown").grid(columnspan = 3)
        self.addInputs()
        self.addOutput()

    def addInputs(self):
        Label(self, text = "Input Information", font = self.headerFont, bg = "purple").grid(row = 1, columnspan = 3)
        Label(self, text = "Height", font = self.titleFont, bg = "purple").grid(row = 2, column = 0)
        Label(self, text = "Feet", font = self.normalFont, bg = "purple").grid(row = 2, column = 1)
        self.txtHeightFeet = Entry(self, bd = 3)
        self.txtHeightFeet.grid(row = 2, column = 2)
        Label(self, text = "Inches", font = self.normalFont, bg = "purple").grid(row = 3, column = 1)
        self.txtHeightInches = Entry(self, bd = 3)
        self.txtHeightInches.grid(row = 3, column = 2)
        Label(self, text = "Weight", font = self.titleFont, bg = "purple").grid(row = 4, column = 0)
        Label(self, text = "lbs", font = self.normalFont, bg = "purple").grid(row = 4, column = 1)
        self.txtWeight = Entry(self, bd = 3)
        self.txtWeight.grid(row = 4, column = 2)

    def addOutput(self):
        self.btnCalc = Button(self, text = "Calculate My BMI", font = self.normalFont, bg = "black", fg = "white")
        self.btnCalc.grid(row = 5, column = 1)
        self.btnCalc["command"] = self.calculate
        self.warning = Label(self, text = "", font = self.normalFont, fg = "red", bg = "purple")
        self.warning.grid(row = 6, columnspan = 3)
        Label(self, text = "Output Information", font = self.headerFont, bg = "purple").grid(row = 7, columnspan = 3)
        Label(self, text = "Results", font = self.titleFont, bg = "purple").grid(row = 8, column = 0)
        Label(self, text = "BMI", font = self.normalFont, bg = "purple").grid(row = 8, column = 1)
        self.bmiVal = Label(self, relief = "groove", bg = "gray")
        self.bmiVal.grid(row = 8, column = 2, sticky = "we")
        Label(self, text = "Status", font = self.normalFont, bg = "purple").grid(row = 9, column = 1)
        self.status = Label(self, relief = "groove", bg = "gray")
        self.status.grid(row = 9, column = 2, sticky = "we")

    def calculate(self):
        invalid = False
        self.warning["text"] = ""
        self.bmiVal["text"] = ""
        self.bmiVal["bg"] = "gray"
        self.status["text"] = ""
        self.status["bg"] = "gray"
        try:
            height = int(self.txtHeightFeet.get()) * 12 + int(self.txtHeightInches.get())
            weight = float(self.txtWeight.get())
            weight = round(weight, 2)
            bmi = weight * 703 /  (height * height)
            bmi = round(bmi, 2)
            self.bmiVal["text"] = bmi
            if bmi <= 18.5:
                self.bmiVal["bg"] = "white"
                self.status["text"] = "Underweight"
                self.status["bg"] = "white"
            elif bmi > 18.5 and bmi < 25:
                self.bmiVal["bg"] = "green"
                self.status["text"] = "Normal"
                self.status["bg"] = "green"
            elif bmi >= 25 and bmi < 30:
                self.bmiVal["bg"] = "yellow"
                self.status["text"] = "Overweight"
                self.status["bg"] = "yellow"
            elif bmi >= 30:
                self.bmiVal["bg"] = "red"
                self.status["text"] = "Obese"
                self.status["bg"] = "red"
        except:
            self.warning["text"] = "WARNING! Invalid Input."

def main():
    app = BMI()
    app.mainloop()

if __name__ == "__main__":
    main()
