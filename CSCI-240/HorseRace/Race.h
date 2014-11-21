//Race.h

#ifndef RACE_H_EXISTS
#define RACE_H_EXISTS

#include "Horse.h"

class Race{
    private:
        int horses;
        Horse h[10];
    public:
        int length;
        Race();
        Race(int length);
        void printLane(int horseNum);
        void start();
        void getLength();
        void getHorses();
        void getMostFavorite();
        void getLeastFavorite();
};

#endif
