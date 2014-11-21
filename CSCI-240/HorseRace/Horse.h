//Horse.h

#ifndef HORSE_H_EXISTS
#define HORSE_H_EXISTS

class Horse{
    private:
        double chance;
        int position;
        float determine;
    public:
        Horse();
        void advance();
        int getPosition();
        double getChance();
};

#endif
