#include<iostream>
using namespace std;
#pragma once
class Face
{
private:
	char Face_Name;
	int Element[3][3];
	Face *pNext;
	Face *pPre;
	Face *pUp;
	Face *pDown;
public:
	Face(void);
	~Face(void);
	void insert(char str,int start);
	void setpNext(Face *a);
	void setpUp(Face *a);
	void setElement(int x, int y, int num);
	void setpPre(Face *a);
	void setpDown(Face *a);
	Face *getpNext();
	Face *getpUp();
	char getName();
	int getElement(int x, int y);
	Face *getpPre();
	Face *getpDown();
};
