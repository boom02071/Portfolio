#include<iostream>
using namespace std;
#pragma once
class keyboard
{
private:
	int count;
	char *str;
	keyboard *pNext;
public:
	keyboard(void);
	~keyboard(void);
	void setCount(int);
	void setStr(char*);
	void setpNext(keyboard*);
	int getCount();
	char *getStr();
	keyboard* getpNext();
};

