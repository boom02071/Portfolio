#include "StdAfx.h"
#include "Face_list.h"
#include "keyboard.h"


Face_list::Face_list(void)
{
	cube[0].insert('a',1);
	cube[1].insert('b',10);
	cube[2].insert('c',19);
	cube[3].insert('d',28);
	cube[4].insert('e',37);
	cube[5].insert('f',46);
	pHead = NULL;
	kHead = NULL;
	mHead = NULL;
}
Face_list::~Face_list(void)
{
	
	
	keyboard *pA=kHead;
	while(pA!=NULL)
	{
		kHead = pA->getpNext();
		delete(pA);
		pA=kHead;

	}

	mouse *pB = mHead;
	while(pB!=NULL)
	{
		mHead = pB->getpNext();
		delete(pB);
		pB = mHead;
	}
}
void Face_list::linkedlist(char name)//ó�� �ʱ�ȭ�� ���
{
	
	Face *a,*b;
	int i,j;
	j=1;
	i=0;

	//a->b->c->d
	while(1)
	{
		
		Face *pCur;
		for(a=b=pHead;b!=NULL;a=b,b=b->getpNext())
		{}
		if(a==b)
		{
			
			pCur = &cube[i];
			pHead = pCur;
			
		}
		else
		{
			pCur=&cube[i];
			a->setpNext(pCur);
			pCur->setpPre(a);
			j++;
		}
		if(j==4)
		{
			pCur->setpNext(pHead);
			pHead->setpPre(pCur);
			break;
		}
		else
			pCur->setpNext(b);
		
		i++;
		

	}

	//a->e
	Face *pCur = new Face();
	pCur=&cube[4];
	pHead->setpUp(pCur);
	pCur->setpDown(pHead);
	
	//e->c
	for(a=pHead,i=1;i!=3;i++)
	{
		a=a->getpNext();
	}
	pCur->setpUp(a);
	a->setpDown(pCur);

	//c->f
	Face *pF = new Face();
	pF=&cube[5];
	a->setpUp(pF);
	pF->setpDown(a);

	//f->a
	pF->setpUp(pHead);
	pHead->setpDown(pF);
	
	
}
void Face_list::rotation_garo456(int num)//row 1,2,3 ���������� ȸ��
{
	Face *pCur=pHead;
	int a,b,c;
	int tempa, tempb, tempc;

	//�������� �̵�
	a = pCur->getElement(num-4,0);
	b = pCur->getElement(num-4,1);
	c = pCur->getElement(num-4,2);
	pCur=pCur->getpNext();

	while(1)
	{
		tempa = pCur->getElement(num-4,0);
		pCur->setElement(num-4,0,a);
		a = tempa;
		tempb = pCur->getElement(num-4,1);
		pCur->setElement(num-4,1,b);
		b = tempb;
		tempc = pCur->getElement(num-4,2);
		pCur->setElement(num-4,2,c);
		c = tempc;

		pCur=pCur->getpNext();
		if(pCur==pHead->getpNext())
			break;
	}

	//���� ������
	if(num==4)
	{
		pCur=pHead->getpUp();
		rotation_all(pCur);
	}
	//�Ʒ��� ������
	if(num==6)
	{
		pCur=pHead->getpDown();
		rotation_all2(pCur);
	}
}
void Face_list::rotation_garo101112(int num)//row 1,2,3 �������� ȸ��
{
	Face *pCur=pHead;
	int a,b,c;
	int tempa, tempb, tempc;
	int psudonum;

	if(num==12)
		psudonum=4;
	else if(num==11)
		psudonum=5;
	else 
		psudonum=6;

	//ȸ��
	a = pCur->getElement(psudonum-4,0);
	b = pCur->getElement(psudonum-4,1);
	c = pCur->getElement(psudonum-4,2);
	pCur=pCur->getpPre();

	while(1)
	{
		tempa = pCur->getElement(psudonum-4,0);
		pCur->setElement(psudonum-4,0,a);
		a = tempa;
		tempb = pCur->getElement(psudonum-4,1);
		pCur->setElement(psudonum-4,1,b);
		b = tempb;
		tempc = pCur->getElement(psudonum-4,2);
		pCur->setElement(psudonum-4,2,c);
		c = tempc;

		pCur=pCur->getpPre();
		if(pCur==pHead->getpPre())
			break;
	}
	if(num==10)//���� ȸ��
	{
		pCur=pHead->getpDown();
	
			rotation_all(pCur);
	}
	if(num==12)//�Ʒ��� ȸ��
	{
		pCur=pHead->getpUp();
		rotation_all2(pCur);
	}
}
void Face_list::rotation_all(Face *e)//���� ȸ��
{
	int copy[3][3];
	int i,j,k,l;

	//���� ����
	for(i=0;i<3;i++)
		for(j=0;j<3;j++)
			copy[i][j]=e->getElement(i,j);

	//���ġ
	for(i=0,k=2;i<3;i++,k--)
		for(j=0;j<3;j++)
			e->setElement(i,j,copy[j][k]);

}
void Face_list::rotation_all2(Face *e)//�Ʒ��� ȸ��
{
	int copy[3][3];
	int i,j,k,l;

	//�Ʒ��� ����
	for(i=0;i<3;i++)
		for(j=0;j<3;j++)
			copy[i][j]=e->getElement(i,j);

	//���ġ
	for(i=0;i<3;i++)
		for(j=0,k=2;j<3;j++,k--)
			e->setElement(i,j,copy[k][i]);
}
void Face_list::rotation_sero123(int num)//col 1,2,3 ���� ȸ��
{
	Face *pCur=pHead;
	int a,b,c;
	int tempa, tempb, tempc;

	int psudo;

	if(num==1)
		psudo=3;
	if(num==2)
		psudo=2;
	if(num==3)
		psudo=1;

	//ȸ��
	a = pCur->getElement(0,num-1);
	b = pCur->getElement(1,num-1);
	c = pCur->getElement(2,num-1);
	pCur=pCur->getpUp();

	//1
	tempa = pCur->getElement(0,num-1);
	pCur->setElement(0,num-1,a);
	a = tempa;

	tempb = pCur->getElement(1,num-1);
	pCur->setElement(1,num-1,b);
	b = tempb;

	tempc = pCur->getElement(2,num-1);
	pCur->setElement(2,num-1,c);
	c = tempc;

	//2
	pCur=pCur->getpUp();
	
	tempa = pCur->getElement(0,psudo-1);
	tempb = pCur->getElement(1,psudo-1);
	tempc = pCur->getElement(2,psudo-1);
	pCur->setElement(2,psudo-1,a);
	pCur->setElement(1,psudo-1,b);
	pCur->setElement(0,psudo-1,c);
	a = tempa;
	b = tempb;
	c = tempc;


	//3
	pCur=pCur->getpUp();
	tempa = pCur->getElement(0,num-1);
	tempb = pCur->getElement(1,num-1);
	tempc = pCur->getElement(2,num-1);
	pCur->setElement(2,num-1,a);
	pCur->setElement(1,num-1,b);
	pCur->setElement(0,num-1,c);
	a = tempa;
	b = tempb;
	c = tempc;

	//4
	pCur=pCur->getpUp();
	tempa = pCur->getElement(0,num-1);
	tempb = pCur->getElement(1,num-1);
	tempc = pCur->getElement(2,num-1);
	pCur->setElement(0,num-1,a);
	pCur->setElement(1,num-1,b);
	pCur->setElement(2,num-1,c);
	a = tempa;
	b = tempb;
	c = tempc;

	//�����ʸ�
	if(num==3)
	{
		pCur=pHead;
		pCur=pCur->getpNext();
		rotation_all2(pCur);
	}
	if(num==1)//���ʸ�
	{
		pCur=pHead;
		pCur=pCur->getpPre();
		rotation_all(pCur);
	}
}
void Face_list::rotation_sero789(int num)//col 1,2,3 �Ʒ��� ȸ��
{
	Face *pCur=pHead;
	int a,b,c;
	int tempa, tempb, tempc;


	//ȸ��
	int psudo;
	if(num==7)
		psudo=9;
	if(num==8)
		psudo=8;
	if(num==9)
		psudo=7;
	a = pCur->getElement(0,num-7);
	b = pCur->getElement(1,num-7);
	c = pCur->getElement(2,num-7);
	pCur=pCur->getpDown();

	//1
	tempa = pCur->getElement(0,num-7);
	pCur->setElement(0,num-7,a);
	a = tempa;

	tempb = pCur->getElement(1,num-7);
	pCur->setElement(1,num-7,b);
	b = tempb;

	tempc = pCur->getElement(2,num-7);
	pCur->setElement(2,num-7,c);
	c = tempc;

	//2
	pCur=pCur->getpDown();
	
	tempa = pCur->getElement(0,psudo-7);
	tempb = pCur->getElement(1,psudo-7);
	tempc = pCur->getElement(2,psudo-7);
	pCur->setElement(2,psudo-7,a);
	pCur->setElement(1,psudo-7,b);
	pCur->setElement(0,psudo-7,c);
	a = tempa;
	b = tempb;
	c = tempc;


	//3
	pCur=pCur->getpDown();
	tempa = pCur->getElement(0,num-7);
	tempb = pCur->getElement(1,num-7);
	tempc = pCur->getElement(2,num-7);
	pCur->setElement(2,num-7,a);
	pCur->setElement(1,num-7,b);
	pCur->setElement(0,num-7,c);
	a = tempa;
	b = tempb;
	c = tempc;

	//4
	pCur=pCur->getpDown();
	tempa = pCur->getElement(0,num-7);
	tempb = pCur->getElement(1,num-7);
	tempc = pCur->getElement(2,num-7);
	pCur->setElement(0,num-7,a);
	pCur->setElement(1,num-7,b);
	pCur->setElement(2,num-7,c);
	a = tempa;
	b = tempb;
	c = tempc;


	if(num==7)//���ʸ� ȸ��
	{
		pCur=pHead;
		pCur=pCur->getpPre();
		rotation_all2(pCur);
	}
	if(num==9)//�����ʸ� ȸ��
	{
		pCur=pHead;
		pCur=pCur->getpNext();
		rotation_all(pCur);
	}
}
Face * Face_list::getHead()
{
	return pHead;
}
void Face_list::setpHeadR()//Ű���� ����
{
	Face *a,*b,*c,*d;

	//a�� pHead
	//b�� ����
	//c�� ����
	//d�� �Ʒ���
	a=b=pHead;
	b=b->getpNext();
	c=pHead->getpUp();
	d=pHead->getpDown();
	

	//����� ���� ����
	b->setpUp(c);
	c->setpDown(b);
	b->setpDown(d);
	d->setpUp(b);
	
	//pHead�� ���Ʒ� ������ ���� �� ������ pHead�� ����
	a->setpUp(NULL);
	a->setpDown(NULL);
	pHead = b;

	//a�� ���� pHead�� ������
	a=b->getpNext()->getpNext();

	//����� ����
	c->setpUp(a);
	a->setpDown(c);

	//�Ʒ���� ����
	a->setpUp(d);
	d->setpDown(a);


	rotation_all(d);//�Ʒ���ȸ��
	rotation_all2(c);//����ȸ��

}
void Face_list::setpHeadL()//Ű���� ������
{
	Face *a,*b,*c,*d;

	//a�� pHead
	//b�� ����(����)
	//c�� ����
	//d�� �Ʒ���
	a=b=pHead;
	b=b->getpPre();
	c=pHead->getpUp();
	d=pHead->getpDown();
	

	//����� ���� ����
	b->setpUp(c);
	c->setpDown(b);
	b->setpDown(d);
	d->setpUp(b);
	
	
	//pHead�� ���Ʒ� ������ ���� �� ������ pHead�� ����
	a->setpUp(NULL);
	a->setpDown(NULL);
	pHead = b;

	//a�� ���� pHead�� ����(������)
	a=b->getpNext()->getpNext();

	//����� ����
	c->setpUp(a);
	a->setpDown(c);

	//�Ʒ���� ����
	a->setpUp(d);
	d->setpDown(a);

	rotation_all(c);//�Ʒ���ȸ��
	rotation_all2(d);//����ȸ��
}
void Face_list::setpHeadU()//Ű���� �Ʒ���ư
{
	Face *a,*e,*b,*f,*d,*c;

	//a�� pHead
	//b�� ����(������)
	//c�� ������(������)
	//d�� ����(����)
	//e�� ����
	//f�� �Ʒ���

	a=pHead;
	e=pHead->getpUp();
	b=pHead->getpNext();
	f=pHead->getpDown();
	d=b->getpNext()->getpNext();
	c=b->getpNext();

	//pHead�� �������� �ٲ�
	pHead = e;


	//pHead�� next���� ����
	a->setpNext(NULL);
	a->setpPre(NULL);

	//pHead�� b�� ����
	e->setpNext(b);
	b->setpPre(e);

	//b�� f�� ����
	b->setpNext(f);
	f->setpPre(b);

	//c���� ����
	c->setpNext(NULL);
	c->setpPre(NULL);

	//f�� next�� d ����
	f->setpNext(d);
	d->setpPre(f);
	//d�� pHead ����
	d->setpNext(e);
	e->setpPre(d);

	//�� ȸ��
	rotation_all(b);
	rotation_all(c);
	rotation_all(c);
	rotation_all(f);
	rotation_all(f);
	rotation_all2(d);


}
void Face_list::setpHeadD()
{
	Face *a,*e,*b,*f,*d,*c;


	//a�� pHead
	//b�� ����(������)
	//c�� ������(������)
	//d�� ����(����)
	//e�� ����
	//f�� �Ʒ���
	a=pHead;
	e=pHead->getpUp();
	b=pHead->getpNext();
	f=pHead->getpDown();
	d=b->getpNext()->getpNext();
	c=b->getpNext();

	//pHead�� �Ʒ���
	pHead = f;

	//a�� next���� ����
	a->setpNext(NULL);
	a->setpPre(NULL);

	//f�� b����
	f->setpNext(b);
	b->setpPre(f);

	//b�� e ����
	b->setpNext(e);
	e->setpPre(b);

	//c���� ����
	c->setpNext(NULL);
	c->setpPre(NULL);

	//e�� d ����
	e->setpNext(d);
	d->setpPre(e);

	//d��f����
	d->setpNext(f);
	f ->setpPre(d);

	//��ȸ��
	rotation_all2(b);
	rotation_all(e);
	rotation_all(e);
	rotation_all(d);
	rotation_all(c);
	rotation_all(c);


}
void Face_list::keyfile(int a,int count)//key���� �α� ��ũ�� ����Ʈ�� �����ϱ�
{
	keyboard *pCur = new keyboard();
	keyboard *b,*c;
	
	for(b=c=kHead;c!=NULL;b=c,c=c->getpNext())
	{}
	if(b==c)
		kHead = pCur;
	else
		b->setpNext(pCur);

	pCur->setpNext(c);
	if(a==1)
	{
		pCur->setCount(count);
		pCur->setStr("Key_Left");
	}
	if(a==2)
	{
		pCur->setCount(count);
		pCur->setStr("Key_Right");
	}
	if(a==3)
	{
		pCur->setCount(count);
		pCur->setStr("Key_Up");
	}
	if(a==4)
	{
		pCur->setCount(count);
		pCur->setStr("Key_Down");
	}

	
}
void Face_list::outkey()//�α� ���Ϸ� ��������
{
	ofstream key("keyboard_log.txt");
	keyboard *pCur=kHead;

	while(pCur!=NULL)
	{
		key<<pCur->getCount()<<".	"<<pCur->getStr()<<endl;
		pCur=pCur->getpNext();
	}
	key.close();
}
void Face_list::mousefile(char name,char *RC,int number,char* direc,int count)//���콺 �α� ��ũ�� ����Ʈ�� �����ϱ�
{
	mouse *pCur= new mouse();
	mouse *a, *b;

	for(a=b=mHead;b!=NULL;a=b,b=b->getpNext())
	{}
	if(a==b)
		mHead = pCur;
	else
		a->setpNext(pCur);

	pCur->setpNext(b);
	pCur->setName(name);//face �̸�
	pCur->setRC(RC);//row ���� col����
	pCur->setNum(number);//ȸ���ϴ� ��
	pCur->setDirec(direc);//����
	pCur->setCount(count);//���° ����������

}
void Face_list::outmouse()//Ű���� �α� ���Ϸ� ���
{
	ofstream mou("mouse_log.txt");
	mouse *pCur = mHead;
	
	while(pCur!=NULL)
	{
		mou<<pCur->getCount()<<".	Face : "<<pCur->getName()<<" / "<<pCur->getRC()<<" : "
			<<pCur->getNum()<<" / "<<pCur->getDirec()<<endl;
		pCur=pCur->getpNext();
	}
	mou.close();
}