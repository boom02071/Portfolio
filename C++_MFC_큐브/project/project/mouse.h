#include<iostream>
using namespace std;
#pragma once
class mouse
{
private:
	char name;
	char *RC;
	int num;
	char *direction;
	int count;
	mouse *pNext;
public:
	mouse(void);
	~mouse(void);
	void setName(char);
	void setRC(char*);
	void setNum(int);
	void setDirec(char*);
	void setpNext(mouse *);
	char getName();
	char *getRC();
	int getNum();
	char *getDirec();
	mouse *getpNext();
	void setCount(int a);
	int getCount();
};

