#include "StdAfx.h"
#include "mouse.h"


mouse::mouse(void)
{
	name = 0;
	num = 0;
	RC = NULL;
	pNext = NULL;
	direction = NULL;
	count = 0;
}


mouse::~mouse(void)
{
}
void mouse::setName(char a)
{
	name = a;
}
void mouse::setRC(char* a)
{
	RC = a;
}
void mouse::setNum(int a )
{
	num = a;
}
void mouse::setDirec(char* a)
{
	direction = a;
}
void mouse::setpNext(mouse *a)
{
	pNext = a;
}
char mouse::getName()
{
	return name;
}
char *mouse::getRC()
{
	return RC;
}
int mouse::getNum()
{
	return num;
}
char *mouse::getDirec()
{
	return direction;
}
mouse *mouse::getpNext()
{
	return pNext;
}
void mouse::setCount(int a)
{
	count = a;
}
int mouse::getCount()
{
	return count;
}