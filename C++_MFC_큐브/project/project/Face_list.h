#include "Face.h"
#include <fstream>
#include "keyboard.h"
#include "mouse.h"
#pragma once
class Face_list
{
private:
	Face cube[6];
	Face *pHead;
	keyboard *kHead;
	mouse *mHead;
public:
	Face_list(void);
	~Face_list(void);
	void linkedlist(char name);
	void rotation_garo456(int);
	void rotation_garo101112(int);
	void rotation_sero123(int);
	void rotation_sero789(int);
	void rotation_all(Face *e);
	void rotation_all2(Face *e);
	void show();
	void setpHeadR();
	void setpHeadL();
	void setpHeadU();
	void setpHeadD();
	Face * getHead();
	void keyfile(int a,int);
	void outkey();
	void mousefile(char,char*,int,char*,int);
	void outmouse();
};

