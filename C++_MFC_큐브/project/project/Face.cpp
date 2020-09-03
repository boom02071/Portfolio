#include "StdAfx.h"
#include "Face.h"


Face::Face(void)
{
	Face_Name=0;
	pPre=NULL;
	pNext=NULL;
	pUp=NULL;
	pDown = NULL;
}
Face::~Face(void)
{

}
void Face::insert(char str,int start)
{
	Face_Name = str;
	int i,j;
	for(i=0;i<3;i++)
	{
		for(j=0;j<3;j++,start++)
		{
			Element[i][j]=start;
		}
	}

}
void Face::setpNext(Face *a)
{
	pNext = a;
}
void Face::setpUp(Face *a)
{
	pUp = a;
}
void Face::setElement(int x, int y, int num)
{
	Element[x][y]=num;
}
void Face::setpPre(Face *a)
{
	pPre = a;
}
Face *Face::getpNext()
{
	return pNext;
}
Face *Face::getpUp()
{
	return pUp;
}
char Face::getName()
{
	return Face_Name;
}
int Face::getElement(int x, int y)
{
	return Element[x][y];
}
Face *Face::getpPre()
{
	return pPre;
}
void Face::setpDown(Face *a)
{
	pDown = a;
}
Face *Face::getpDown()
{
	return pDown;
}