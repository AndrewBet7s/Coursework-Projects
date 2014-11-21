# makefile

HeapOfStudents: main.o Student.o Address.o Date.o
	g++ main.o Student.o Address.o Date.o -o HeapOfStudents

main.o: main.cpp Student.h Address.h Date.h
	g++ -c main.cpp
	
Student.o: Student.cpp Student.h Address.h Date.h
	g++ -c Student.cpp
	
Address.o: Address.cpp Address.h
	g++ -c Address.cpp

Date.o: Date.cpp Date.h
	g++ -c Date.cpp
	
clean:
	rm -f *.o
	rm HeapOfStudents
	