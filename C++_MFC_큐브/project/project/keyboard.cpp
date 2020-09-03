#include "StdAfx.h"
#include "keyboard.h"


keyboard::keyboard(void)
{
	count = 0;
	str = NULL;
	pNext=NULL;
}


keyboard::~keyboard(void)
{
}

void keyboard::setCount(int a)
{
	count = a;
}
void keyboard::setStr(char* a)
{
	str = a;
}
void keyboard::setpNext(keyboard *a)
{
	pNext = a;
}
int keyboard::getCount()
{
	return count;
}
char *keyboard::getStr()
{
	return str;
}
keyboard * keyboard:: getpNext()
{
	return pNext;
}
