
// projectView.cpp : CprojectView 클래스의 구현
//

#include "stdafx.h"
// SHARED_HANDLERS는 미리 보기, 축소판 그림 및 검색 필터 처리기를 구현하는 ATL 프로젝트에서 정의할 수 있으며
// 해당 프로젝트와 문서 코드를 공유하도록 해 줍니다.
#ifndef SHARED_HANDLERS
#include "project.h"
#endif

#include "projectDoc.h"
#include "projectView.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CprojectView

IMPLEMENT_DYNCREATE(CprojectView, CView)

BEGIN_MESSAGE_MAP(CprojectView, CView)
	// 표준 인쇄 명령입니다.
	ON_COMMAND(ID_FILE_PRINT, &CView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_DIRECT, &CView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_PREVIEW, &CprojectView::OnFilePrintPreview)
	ON_WM_CONTEXTMENU()
	ON_WM_RBUTTONUP()
	ON_WM_LBUTTONUP()
	ON_WM_LBUTTONDOWN()
	ON_WM_KEYDOWN()
	ON_COMMAND(ID_32771, &CprojectView::On32771)
	ON_COMMAND(ID_32772, &CprojectView::On32772)
	ON_COMMAND(ID_32774, &CprojectView::On32774)
	ON_COMMAND(ID_32773, &CprojectView::On32773)
END_MESSAGE_MAP()

// CprojectView 생성/소멸

CprojectView::CprojectView()
{
	// TODO: 여기에 생성 코드를 추가합니다.
	i=0;
	counter=0;
	ptChild.x=300;
	ptChild.y=300;
}

CprojectView::~CprojectView()
{
}

BOOL CprojectView::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: CREATESTRUCT cs를 수정하여 여기에서
	//  Window 클래스 또는 스타일을 수정합니다.

	return CView::PreCreateWindow(cs);
}

// CprojectView 그리기

void CprojectView::OnDraw(CDC* pDC/*pDC*/)
{
	CprojectDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;

	// TODO: 여기에 원시 데이터에 대한 그리기 코드를 추가합니다.
	int i,j;
	
	Face * f=(pDoc->a).getHead();

	//앞면
	j=1,i=1;
	CBrush rectangle_color1(color_mapping(f->getElement(0,0)));
	pDC->SelectObject(&rectangle_color1);
	pDC->Rectangle(ptChild.x+64*(i-1),ptChild.y+64*(j-1),ptChild.x+64*i,ptChild.y+64*j);
	


	j=1;i=2;
	CBrush rectangle_color2(color_mapping(f->getElement(0,1)));
	pDC->SelectObject(&rectangle_color2);
	pDC->Rectangle(ptChild.x+64*(i-1),ptChild.y+64*(j-1),ptChild.x+64*i,ptChild.y+64*j);

	j=1;i=3;
	CBrush rectangle_color3(color_mapping(f->getElement(0,2)));
	pDC->SelectObject(&rectangle_color3);
	pDC->Rectangle(ptChild.x+64*(i-1),ptChild.y+64*(j-1),ptChild.x+64*i,ptChild.y+64*j);

	j=2;i=1;
	CBrush rectangle_color4(color_mapping(f->getElement(1,0)));
	pDC->SelectObject(&rectangle_color4);
	pDC->Rectangle(ptChild.x+64*(i-1),ptChild.y+64*(j-1),ptChild.x+64*i,ptChild.y+64*j);

	j=2;i=2;
	CBrush rectangle_color5(color_mapping(f->getElement(1,1)));
	pDC->SelectObject(&rectangle_color5);
	pDC->Rectangle(ptChild.x+64*(i-1),ptChild.y+64*(j-1),ptChild.x+64*i,ptChild.y+64*j);
	
	j=2;i=3;
	CBrush rectangle_color6(color_mapping(f->getElement(1,2)));
	pDC->SelectObject(&rectangle_color6);
	pDC->Rectangle(ptChild.x+64*(i-1),ptChild.y+64*(j-1),ptChild.x+64*i,ptChild.y+64*j);

	j=3;i=1;
	CBrush rectangle_color7(color_mapping(f->getElement(2,0)));
	pDC->SelectObject(&rectangle_color7);
	pDC->Rectangle(ptChild.x+64*(i-1),ptChild.y+64*(j-1),ptChild.x+64*i,ptChild.y+64*j);

	j=3;i=2;
	CBrush rectangle_color8(color_mapping(f->getElement(2,1)));
	pDC->SelectObject(&rectangle_color8);
	pDC->Rectangle(ptChild.x+64*(i-1),ptChild.y+64*(j-1),ptChild.x+64*i,ptChild.y+64*j);

	j=3;i=3;
	CBrush rectangle_color9(color_mapping(f->getElement(2,2)));
	pDC->SelectObject(&rectangle_color9);
	pDC->Rectangle(ptChild.x+64*(i-1),ptChild.y+64*(j-1),ptChild.x+64*i,ptChild.y+64*j);
	
	//윗면
	CPoint pts[4];

	
	j=1,i=1;
	CBrush polygon_color1(color_mapping((f->getpUp())->getElement(2,0)));
	pDC->SelectObject(&polygon_color1);
	pts[0].x=ptChild.x+30*j+64*(i-1);
	pts[0].y=ptChild.y-30*j;
	pts[1].x=ptChild.x+30*j+64*i;
	pts[1].y=ptChild.y-30*j;
	pts[2].x=ptChild.x+64*i+30*(j-1);
	pts[2].y=ptChild.y-30*(j-1);
	pts[3].x=ptChild.x+64*(i-1)+30*(j-1);
	pts[3].y=ptChild.y-30*(j-1);
	pDC->Polygon(pts,4);

	j=1,i=2;
	CBrush polygon_color2(color_mapping((f->getpUp())->getElement(2,1)));
	pDC->SelectObject(&polygon_color2);
	pts[0].x=ptChild.x+30*j+64*(i-1);
	pts[0].y=ptChild.y-30*j;
	pts[1].x=ptChild.x+30*j+64*i;
	pts[1].y=ptChild.y-30*j;
	pts[2].x=ptChild.x+64*i+30*(j-1);
	pts[2].y=ptChild.y-30*(j-1);
	pts[3].x=ptChild.x+64*(i-1)+30*(j-1);
	pts[3].y=ptChild.y-30*(j-1);
	pDC->Polygon(pts,4);

	j=1,i=3;
	CBrush polygon_color3(color_mapping((f->getpUp())->getElement(2,2)));
	pDC->SelectObject(&polygon_color3);
	pts[0].x=ptChild.x+30*j+64*(i-1);
	pts[0].y=ptChild.y-30*j;
	pts[1].x=ptChild.x+30*j+64*i;
	pts[1].y=ptChild.y-30*j;
	pts[2].x=ptChild.x+64*i+30*(j-1);
	pts[2].y=ptChild.y-30*(j-1);
	pts[3].x=ptChild.x+64*(i-1)+30*(j-1);
	pts[3].y=ptChild.y-30*(j-1);
	pDC->Polygon(pts,4);

	j=2,i=1;
	CBrush polygon_color4(color_mapping((f->getpUp())->getElement(1,0)));
	pDC->SelectObject(&polygon_color4);
	pts[0].x=ptChild.x+30*j+64*(i-1);
	pts[0].y=ptChild.y-30*j;
	pts[1].x=ptChild.x+30*j+64*i;
	pts[1].y=ptChild.y-30*j;
	pts[2].x=ptChild.x+64*i+30*(j-1);
	pts[2].y=ptChild.y-30*(j-1);
	pts[3].x=ptChild.x+64*(i-1)+30*(j-1);
	pts[3].y=ptChild.y-30*(j-1);
	pDC->Polygon(pts,4);
		

	j=2,i=2;
	CBrush polygon_color5(color_mapping((f->getpUp())->getElement(1,1)));
	pDC->SelectObject(&polygon_color5);
	pts[0].x=ptChild.x+30*j+64*(i-1);
	pts[0].y=ptChild.y-30*j;
	pts[1].x=ptChild.x+30*j+64*i;
	pts[1].y=ptChild.y-30*j;
	pts[2].x=ptChild.x+64*i+30*(j-1);
	pts[2].y=ptChild.y-30*(j-1);
	pts[3].x=ptChild.x+64*(i-1)+30*(j-1);
	pts[3].y=ptChild.y-30*(j-1);
	pDC->Polygon(pts,4);


	j=2,i=3;
	CBrush polygon_color6(color_mapping((f->getpUp())->getElement(1,2)));
	pDC->SelectObject(&polygon_color6);
	pts[0].x=ptChild.x+30*j+64*(i-1);
	pts[0].y=ptChild.y-30*j;
	pts[1].x=ptChild.x+30*j+64*i;
	pts[1].y=ptChild.y-30*j;
	pts[2].x=ptChild.x+64*i+30*(j-1);
	pts[2].y=ptChild.y-30*(j-1);
	pts[3].x=ptChild.x+64*(i-1)+30*(j-1);
	pts[3].y=ptChild.y-30*(j-1);
	pDC->Polygon(pts,4);


	j=3,i=1;
	CBrush polygon_color7(color_mapping((f->getpUp())->getElement(0,0)));
	pDC->SelectObject(&polygon_color7);
	pts[0].x=ptChild.x+30*j+64*(i-1);
	pts[0].y=ptChild.y-30*j;
	pts[1].x=ptChild.x+30*j+64*i;
	pts[1].y=ptChild.y-30*j;
	pts[2].x=ptChild.x+64*i+30*(j-1);
	pts[2].y=ptChild.y-30*(j-1);
	pts[3].x=ptChild.x+64*(i-1)+30*(j-1);
	pts[3].y=ptChild.y-30*(j-1);
	pDC->Polygon(pts,4);


	j=3,i=2;
	CBrush polygon_color8(color_mapping((f->getpUp())->getElement(0,1)));
	pDC->SelectObject(&polygon_color8);
	pts[0].x=ptChild.x+30*j+64*(i-1);
	pts[0].y=ptChild.y-30*j;
	pts[1].x=ptChild.x+30*j+64*i;
	pts[1].y=ptChild.y-30*j;
	pts[2].x=ptChild.x+64*i+30*(j-1);
	pts[2].y=ptChild.y-30*(j-1);
	pts[3].x=ptChild.x+64*(i-1)+30*(j-1);
	pts[3].y=ptChild.y-30*(j-1);
	pDC->Polygon(pts,4);


	j=3,i=3;
	CBrush polygon_color9(color_mapping((f->getpUp())->getElement(0,2)));
	pDC->SelectObject(&polygon_color9);
	pts[0].x=ptChild.x+30*j+64*(i-1);
	pts[0].y=ptChild.y-30*j;
	pts[1].x=ptChild.x+30*j+64*i;
	pts[1].y=ptChild.y-30*j;
	pts[2].x=ptChild.x+64*i+30*(j-1);
	pts[2].y=ptChild.y-30*(j-1);
	pts[3].x=ptChild.x+64*(i-1)+30*(j-1);
	pts[3].y=ptChild.y-30*(j-1);
	pDC->Polygon(pts,4);


	


	
	//옆면
	j=1,i=1;
	CBrush side_color1(color_mapping((f->getpNext())->getElement(0,0)));
	pDC->SelectObject(&side_color1);
	pts[0].x=ptChild.x+64*3+30*(j-1);
	pts[0].y=ptChild.y+64*(i-1)-30*(j-1);
	pts[1].x=ptChild.x+64*3+30*j;
	pts[1].y=ptChild.y-30*j+64*(i-1);
	pts[2].x=ptChild.x+64*3+30*j;
	pts[2].y=ptChild.y+64*i-30*j;
	pts[3].x=ptChild.x+64*3+30*(j-1);
	pts[3].y=ptChild.y+64*i-30*(j-1);
	pDC->Polygon(pts,4);


	j=1,i=2;
	CBrush side_color2(color_mapping((f->getpNext())->getElement(1,0)));
	pDC->SelectObject(&side_color2);
	pts[0].x=ptChild.x+64*3+30*(j-1);
	pts[0].y=ptChild.y+64*(i-1)-30*(j-1);
	pts[1].x=ptChild.x+64*3+30*j;
	pts[1].y=ptChild.y-30*j+64*(i-1);
	pts[2].x=ptChild.x+64*3+30*j;
	pts[2].y=ptChild.y+64*i-30*j;
	pts[3].x=ptChild.x+64*3+30*(j-1);
	pts[3].y=ptChild.y+64*i-30*(j-1);
	pDC->Polygon(pts,4);
	
	j=1,i=3;
	CBrush side_color3(color_mapping((f->getpNext())->getElement(2,0)));
	pDC->SelectObject(&side_color3);
	pts[0].x=ptChild.x+64*3+30*(j-1);
	pts[0].y=ptChild.y+64*(i-1)-30*(j-1);
	pts[1].x=ptChild.x+64*3+30*j;
	pts[1].y=ptChild.y-30*j+64*(i-1);
	pts[2].x=ptChild.x+64*3+30*j;
	pts[2].y=ptChild.y+64*i-30*j;
	pts[3].x=ptChild.x+64*3+30*(j-1);
	pts[3].y=ptChild.y+64*i-30*(j-1);
	pDC->Polygon(pts,4);
	

	j=2,i=1;
	CBrush side_color4(color_mapping((f->getpNext())->getElement(0,1)));
	pDC->SelectObject(&side_color4);
	pts[0].x=ptChild.x+64*3+30*(j-1);
	pts[0].y=ptChild.y+64*(i-1)-30*(j-1);
	pts[1].x=ptChild.x+64*3+30*j;
	pts[1].y=ptChild.y-30*j+64*(i-1);
	pts[2].x=ptChild.x+64*3+30*j;
	pts[2].y=ptChild.y+64*i-30*j;
	pts[3].x=ptChild.x+64*3+30*(j-1);
	pts[3].y=ptChild.y+64*i-30*(j-1);
	pDC->Polygon(pts,4);


	j=2,i=2;
	CBrush side_color5(color_mapping((f->getpNext())->getElement(1,1)));
	pDC->SelectObject(&side_color5);
	pts[0].x=ptChild.x+64*3+30*(j-1);
	pts[0].y=ptChild.y+64*(i-1)-30*(j-1);
	pts[1].x=ptChild.x+64*3+30*j;
	pts[1].y=ptChild.y-30*j+64*(i-1);
	pts[2].x=ptChild.x+64*3+30*j;
	pts[2].y=ptChild.y+64*i-30*j;
	pts[3].x=ptChild.x+64*3+30*(j-1);
	pts[3].y=ptChild.y+64*i-30*(j-1);
	pDC->Polygon(pts,4);
	


	j=2,i=3;
	CBrush side_color6(color_mapping((f->getpNext())->getElement(2,1)));
	pDC->SelectObject(&side_color6);
	pts[0].x=ptChild.x+64*3+30*(j-1);
	pts[0].y=ptChild.y+64*(i-1)-30*(j-1);
	pts[1].x=ptChild.x+64*3+30*j;
	pts[1].y=ptChild.y-30*j+64*(i-1);
	pts[2].x=ptChild.x+64*3+30*j;
	pts[2].y=ptChild.y+64*i-30*j;
	pts[3].x=ptChild.x+64*3+30*(j-1);
	pts[3].y=ptChild.y+64*i-30*(j-1);
	pDC->Polygon(pts,4);


	j=3,i=1;
	CBrush side_color7(color_mapping((f->getpNext())->getElement(0,2)));
	pDC->SelectObject(&side_color7);
	pts[0].x=ptChild.x+64*3+30*(j-1);
	pts[0].y=ptChild.y+64*(i-1)-30*(j-1);
	pts[1].x=ptChild.x+64*3+30*j;
	pts[1].y=ptChild.y-30*j+64*(i-1);
	pts[2].x=ptChild.x+64*3+30*j;
	pts[2].y=ptChild.y+64*i-30*j;
	pts[3].x=ptChild.x+64*3+30*(j-1);
	pts[3].y=ptChild.y+64*i-30*(j-1);
	pDC->Polygon(pts,4);

	j=3,i=2;
	CBrush side_color8(color_mapping((f->getpNext())->getElement(1,2)));
	pDC->SelectObject(&side_color8);
	pts[0].x=ptChild.x+64*3+30*(j-1);
	pts[0].y=ptChild.y+64*(i-1)-30*(j-1);
	pts[1].x=ptChild.x+64*3+30*j;
	pts[1].y=ptChild.y-30*j+64*(i-1);
	pts[2].x=ptChild.x+64*3+30*j;
	pts[2].y=ptChild.y+64*i-30*j;
	pts[3].x=ptChild.x+64*3+30*(j-1);
	pts[3].y=ptChild.y+64*i-30*(j-1);
	pDC->Polygon(pts,4);

	j=3,i=3;
	CBrush side_color9(color_mapping((f->getpNext())->getElement(2,2)));
	pDC->SelectObject(&side_color9);
	pts[0].x=ptChild.x+64*3+30*(j-1);
	pts[0].y=ptChild.y+64*(i-1)-30*(j-1);
	pts[1].x=ptChild.x+64*3+30*j;
	pts[1].y=ptChild.y-30*j+64*(i-1);
	pts[2].x=ptChild.x+64*3+30*j;
	pts[2].y=ptChild.y+64*i-30*j;
	pts[3].x=ptChild.x+64*3+30*(j-1);
	pts[3].y=ptChild.y+64*i-30*(j-1);
	pDC->Polygon(pts,4);

	if(abc==0)
	{
	CBrush brush;
	CPen pen;
	CPen * pOldPen;
	CBrush* pOldBrush;
	brush.CreateSolidBrush(RGB(0,0,255));
	
	pOldBrush = pDC->SelectObject(&brush);
	pOldPen = pDC->SelectObject(&pen);
	pDC->SelectObject(pOldBrush);
	pDC->SelectObject(pOldPen);
	CFont font,*pOldfont;
	pOldfont = pDC->SelectObject(&font);
	pDC->SelectObject(pOldfont);
	pDC->SetTextColor(RGB(0, 0, 0));//글자색


	//앞
	COLORREF side_colora1(color_mapping((f->getElement(0,0))));
	pDC->SetBkColor(side_colora1);//글자배경색
	pDC->SelectObject(&side_colora1);
	CString tmp;
	tmp.Format(_T("%d"),f->getElement(0,0));
	pDC->TextOutW(300+64/2,300+64/2,tmp);


	COLORREF side_colora2(color_mapping((f->getElement(0,1))));
	pDC->SetBkColor(side_colora2);//글자배경색
	pDC->SelectObject(&side_colora2);
	tmp.Format(_T("%d"),f->getElement(0,1));
	pDC->TextOutW(300+64*3/2,300+64/2,tmp);


	COLORREF side_colora3(color_mapping((f->getElement(0,2))));
	pDC->SetBkColor(side_colora3);//글자배경색
	pDC->SelectObject(&side_colora3);
	tmp.Format(_T("%d"),f->getElement(0,2));
	pDC->TextOutW(300+64*5/2,300+64/2,tmp);


	COLORREF side_colora4(color_mapping((f->getElement(1,0))));
	pDC->SetBkColor(side_colora4);//글자배경색
	pDC->SelectObject(&side_colora4);
	tmp.Format(_T("%d"),f->getElement(1,0));
	pDC->TextOutW(300+64/2,300+64*3/2,tmp);


	COLORREF side_colora5(color_mapping((f->getElement(1,1))));
	pDC->SetBkColor(side_colora5);//글자배경색
	pDC->SelectObject(&side_colora5);
	tmp.Format(_T("%d"),f->getElement(1,1));
	pDC->TextOutW(300+64*3/2,300+64*3/2,tmp);


	COLORREF side_colora6(color_mapping((f->getElement(1,2))));
	pDC->SetBkColor(side_colora6);//글자배경색
	pDC->SelectObject(&side_colora6);
	tmp.Format(_T("%d"),f->getElement(1,2));
	pDC->TextOutW(300+64*5/2,300+64*3/2,tmp);
	

	COLORREF side_colora7(color_mapping((f->getElement(2,0))));
	pDC->SetBkColor(side_colora7);//글자배경색
	pDC->SelectObject(&side_colora7);
	tmp.Format(_T("%d"),f->getElement(2,0));
	pDC->TextOutW(300+64/2,300+64*5/2,tmp);

	COLORREF side_colora8(color_mapping((f->getElement(2,1))));
	pDC->SetBkColor(side_colora8);//글자배경색
	pDC->SelectObject(&side_colora8);
	tmp.Format(_T("%d"),f->getElement(2,1));
	pDC->TextOutW(300+64*3/2,300+64*5/2,tmp);


	COLORREF side_colora9(color_mapping((f->getElement(2,2))));
	pDC->SetBkColor(side_colora9);//글자배경색
	pDC->SelectObject(&side_colora9);
	tmp.Format(_T("%d"),f->getElement(2,2));
	pDC->TextOutW(300+64*5/2,300+64*5/2,tmp);

	//위
	COLORREF side_colore1(color_mapping((f->getpUp()->getElement(2,0))));
	pDC->SetBkColor(side_colore1);
	pDC->SelectObject(&side_colore1);
	tmp.Format(_T("%d"),(f->getpUp()->getElement(2,0)));
	pDC->TextOutW(300+64/2,300-20,tmp);


	COLORREF side_colore2(color_mapping((f->getpUp()->getElement(2,1))));
	pDC->SetBkColor(side_colore2);
	pDC->SelectObject(&side_colore2);
	tmp.Format(_T("%d"),(f->getpUp()->getElement(2,1)));
	pDC->TextOutW(300+64*3/2,300-20,tmp);


	COLORREF side_colore3(color_mapping((f->getpUp()->getElement(2,2))));
	pDC->SetBkColor(side_colore3);
	pDC->SelectObject(&side_colore3);
	tmp.Format(_T("%d"),(f->getpUp()->getElement(2,2)));
	pDC->TextOutW(300+64*5/2,300-20,tmp);


	COLORREF side_colore4(color_mapping((f->getpUp()->getElement(1,0))));
	pDC->SetBkColor(side_colore4);
	pDC->SelectObject(&side_colore4);
	tmp.Format(_T("%d"),(f->getpUp()->getElement(1,0)));
	pDC->TextOutW(300+64/2+30,300-20-30,tmp);


	COLORREF side_colore5(color_mapping((f->getpUp()->getElement(1,1))));
	pDC->SetBkColor(side_colore5);
	pDC->SelectObject(&side_colore5);
	tmp.Format(_T("%d"),(f->getpUp()->getElement(1,1)));
	pDC->TextOutW(300+64*3/2+30,300-20-30,tmp);


	COLORREF side_colore6(color_mapping((f->getpUp()->getElement(1,2))));
	pDC->SetBkColor(side_colore6);
	pDC->SelectObject(&side_colore6);
	tmp.Format(_T("%d"),(f->getpUp()->getElement(1,2)));
	pDC->TextOutW(300+64*5/2+30,300-20-30,tmp);


	COLORREF side_colore7(color_mapping((f->getpUp()->getElement(0,0))));
	pDC->SetBkColor(side_colore7);
	pDC->SelectObject(&side_colore7);
	tmp.Format(_T("%d"),(f->getpUp()->getElement(0,0)));
	pDC->TextOutW(300+64/2+30*2,300-20-30*2,tmp);


	COLORREF side_colore8(color_mapping((f->getpUp()->getElement(0,1))));
	pDC->SetBkColor(side_colore8);
	pDC->SelectObject(&side_colore8);
	tmp.Format(_T("%d"),(f->getpUp()->getElement(0,1)));
	pDC->TextOutW(300+64*3/2+30*2,300-20-30*2,tmp);


	COLORREF side_colore9(color_mapping((f->getpUp()->getElement(0,2))));
	pDC->SetBkColor(side_colore9);
	pDC->SelectObject(&side_colore9);
	tmp.Format(_T("%d"),(f->getpUp()->getElement(0,2)));
	pDC->TextOutW(300+64*5/2+30*2,300-20-30*2,tmp);


	//옆
	COLORREF side_colorb1(color_mapping((f->getpNext()->getElement(0,0))));
	pDC->SetBkColor(side_colorb1);//글자배경색
	pDC->SelectObject(&side_colorb1);
	tmp.Format(_T("%d"),f->getpNext()->getElement(0,0));
	pDC->TextOutW(300+66*6/2,300+30/2,tmp);


	COLORREF side_colorb2(color_mapping((f->getpNext()->getElement(0,1))));
	pDC->SetBkColor(side_colorb2);//글자배경색
	pDC->SelectObject(&side_colorb2);
	tmp.Format(_T("%d"),f->getpNext()->getElement(0,1));
	pDC->TextOutW(300+66*7/2,300+30/2-30,tmp);


	COLORREF side_colorb3(color_mapping((f->getpNext()->getElement(0,2))));
	pDC->SetBkColor(side_colorb3);//글자배경색
	pDC->SelectObject(&side_colorb3);
	tmp.Format(_T("%d"),f->getpNext()->getElement(0,2));
	pDC->TextOutW(300+66*8/2,300+30/2-30*2,tmp);


	COLORREF side_colorb4(color_mapping((f->getpNext()->getElement(1,0))));
	pDC->SetBkColor(side_colorb4);//글자배경색
	pDC->SelectObject(&side_colorb4);
	tmp.Format(_T("%d"),f->getpNext()->getElement(1,0));
	pDC->TextOutW(300+66*6/2,300+30/2+64,tmp);


	COLORREF side_colorb5(color_mapping((f->getpNext()->getElement(1,1))));
	pDC->SetBkColor(side_colorb5);//글자배경색
	pDC->SelectObject(&side_colorb5);
	tmp.Format(_T("%d"),f->getpNext()->getElement(1,1));
	pDC->TextOutW(300+66*7/2,300+30/2-30+64,tmp);


	COLORREF side_colorb6(color_mapping((f->getpNext()->getElement(1,2))));
	pDC->SetBkColor(side_colorb6);//글자배경색
	pDC->SelectObject(&side_colorb6);
	tmp.Format(_T("%d"),f->getpNext()->getElement(1,2));
	pDC->TextOutW(300+66*8/2,300+30/2-30*2+64,tmp);


	COLORREF side_colorb7(color_mapping((f->getpNext()->getElement(2,0))));
	pDC->SetBkColor(side_colorb7);//글자배경색
	pDC->SelectObject(&side_colorb7);
	tmp.Format(_T("%d"),f->getpNext()->getElement(2,0));
	pDC->TextOutW(300+66*6/2,300+30/2+64*2,tmp);


	COLORREF side_colorb8(color_mapping((f->getpNext()->getElement(2,1))));
	pDC->SetBkColor(side_colorb8);//글자배경색
	pDC->SelectObject(&side_colorb8);
	tmp.Format(_T("%d"),f->getpNext()->getElement(2,1));
	pDC->TextOutW(300+66*7/2,300+30/2-30+64*2,tmp);


	COLORREF side_colorb9(color_mapping((f->getpNext()->getElement(2,2))));
	pDC->SetBkColor(side_colorb9);//글자배경색
	pDC->SelectObject(&side_colorb9);
	tmp.Format(_T("%d"),f->getpNext()->getElement(2,2));
	pDC->TextOutW(300+66*8/2,300+30/2-30*2+64*2,tmp);

	}
	
}

COLORREF CprojectView::color_mapping(int a)
{
	if(a>=1&&a<=9)
		return RGB(255,0,0);
	else if(a>=10&&a<=18)
		return RGB(0,255,0);
	else if(a>=19&&a<=27)
		return RGB(255,127,0);
	else if(a>=28&&a<=36)
		return RGB(0,0,225);
	else if(a>=37&&a<=45)
		return RGB(255,255,0);
	else if(a>=46&&a<=54)
		return RGB(255,255,255);
	
	
}
// CprojectView 인쇄


void CprojectView::OnFilePrintPreview()
{
#ifndef SHARED_HANDLERS
	AFXPrintPreview(this);
#endif
}

BOOL CprojectView::OnPreparePrinting(CPrintInfo* pInfo)
{
	// 기본적인 준비
	return DoPreparePrinting(pInfo);
}

void CprojectView::OnBeginPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: 인쇄하기 전에 추가 초기화 작업을 추가합니다.
}

void CprojectView::OnEndPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: 인쇄 후 정리 작업을 추가합니다.
}

void CprojectView::OnRButtonUp(UINT /* nFlags */, CPoint point)
{
	ClientToScreen(&point);
	OnContextMenu(this, point);
}

void CprojectView::OnContextMenu(CWnd* /* pWnd */, CPoint point)
{
#ifndef SHARED_HANDLERS
	theApp.GetContextMenuManager()->ShowPopupMenu(IDR_POPUP_EDIT, point.x, point.y, this, TRUE);
#endif
}


// CprojectView 진단

#ifdef _DEBUG
void CprojectView::AssertValid() const
{
	CView::AssertValid();
}

void CprojectView::Dump(CDumpContext& dc) const
{
	CView::Dump(dc);
}

CprojectDoc* CprojectView::GetDocument() const // 디버그되지 않은 버전은 인라인으로 지정됩니다.
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CprojectDoc)));
	return (CprojectDoc*)m_pDocument;
}
#endif //_DEBUG


// CprojectView 메시지 처리기


void CprojectView::OnLButtonUp(UINT nFlags, CPoint point)
{
	// TODO: 여기에 메시지 처리기 코드를 추가 및/또는 기본값을 호출합니다.
	
	end_point = point;

	slice_rotation(start_point, end_point);
	RedrawWindow();
	ReleaseCapture();
	CView::OnLButtonUp(nFlags, point);
}


void CprojectView::OnLButtonDown(UINT nFlags, CPoint point)
{
	// TODO: 여기에 메시지 처리기 코드를 추가 및/또는 기본값을 호출합니다.
	start_point = point;
	SetCapture();
	m_bDragFlag = true;
	RedrawWindow();
	CView::OnLButtonDown(nFlags, point);
}
void CprojectView::slice_rotation(CPoint start, CPoint end)
{

	CprojectDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;

	
	int sx= start.x;
	int sy = start.y;
	int ex= end.x;
	int ey = end.y;


	//1->3
	if(sx>=300&&sx<=364&&sy>=300&&sy<=364)
		if(ex>=300+64*2&&ex<=300+64*3&&ey>=300&&ey<=300+64)
		{
			(pDoc->a).rotation_garo456(4);
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getName(),"Row",1,"Right",counter);
		}

	//4->6
	if(sx>=300&&sx<=364&&sy>=300+64&&sy<=300+64*2)
		if(ex>=300+64*2&&ex<=300+64*3&&ey>=300+64&&ey<=300+64*2)
		{
			(pDoc->a).rotation_garo456(5);
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getName(),"Row",2,"Right",counter);
		}

	//7->9
	if(sx>=300&&sx<=364&&sy>=300+64*2&&sy<=300+64*3)
		if(ex>=300+64*2&&ex<=300+64*3&&ey>=300+64*2&&ey<=300+64*3)
		{
			(pDoc->a).rotation_garo456(6);
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getName(),"Row",3,"Right",counter);
		}
	

	//3->1
	if(sx>=300+64*2&&sx<=300+64*3&&sy>=300&&sy<=300+64)
		if(ex>=300&&ex<=364&&ey>=300&&ey<=364)
		{
			(pDoc->a).rotation_garo101112(12);
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getName(),"Row",1,"Left",counter);
		}

	//6->4
	if(sx>=300+64*2&&sx<=300+64*3&&sy>=300+64&&sy<=300+64*2)
		if(ex>=300&&ex<=364&&ey>=300+64&&ey<=300+64*2)
		{
			(pDoc->a).rotation_garo101112(11);
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getName(),"Row",2,"Left",counter);
		}

	//9->7
	if(sx>=300+64*2&&sx<=300+64*3&&sy>=300+64*2&&sy<=300+64*3)
		if(ex>=300&&ex<=364&&ey>=300+64*2&&ey<=300+64*3)
		{
			(pDoc->a).rotation_garo101112(10);
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getName(),"Row",3,"Left",counter);
		}

	//7->1
	if(sx>=300&&sx<=364&&sy>=300+64*2&&sy<=300+64*3)
		if(ex>=300&&ex<=364&&ey>=300&&ey<=364)
		{
			(pDoc->a).rotation_sero123(1);
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getName(),"Col",1,"Up",counter);
		}

	//8->2
	if(sx>=300+64&&sx<=300+64*2&&sy>=300+64*2&&sy<=300+64*3)
		if(ex>=300+64&&ex<=300+64*2&&ey>=300&&ey<=364)
		{
			(pDoc->a).rotation_sero123(2);
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getName(),"Col",2,"Up",counter);
		}

	//9->3
	if(sx>=300+64*2&&sx<=300+64*3&&sy>=300+64*2&&sy<=300+64*3)
		if(ex>=300+64*2&&ex<=300+64*3&&ey>=300&&ey<=364)
		{
			(pDoc->a).rotation_sero123(3);
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getName(),"Col",3,"Up",counter);
		}


	//1->7
	if(sx>=300&&sx<=364&&sy>=300&sy<=364)
		if(ex>=300&&ex<=364&&ey>=300+64*2&&ey<=300+64*3)
		{
			(pDoc->a).rotation_sero789(7);
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getName(),"Col",1,"Down",counter);
		}

	//2->8
	if(sx>=300+64&&sx<=300+64*2&&sy>=300&&sy<=364)
		if(ex>=300+64&&ex<=300+64*2&&ey>=300+64*2&&ey<=300+64*3)
		{
			(pDoc->a).rotation_sero789(8);
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getName(),"Col",2,"Down",counter);
		}

	//3->9
	if(sx>=300+64*2&&sx<=300+64*3&&sy>=300&&sy<=364)
		if(ex>=300+64*2&&ex<=300+64*3&&ey>=300+64*2&&ey<=300+64*3)
		{
			(pDoc->a).rotation_sero789(9);
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getName(),"Col",3,"Down",counter);
		}


	//면e
	//37->39
	if(sx>=300+30*3&&sx<=300+30*3+32&&sy>=300-30*3&&sy<=300-30*2)
		if(ex>=300+30*3+64*2&&ex<=300+30*3+32+64*2&&ey>=300-30*3&&ey<=300-30*2)
		{
			(pDoc->a).setpHeadU();
			pDoc->a.rotation_garo456(4);
			pDoc->a.setpHeadD();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpUp()->getName(),"Row",1,"Right",counter);
		}


	//40->42
	if(sx>=300+30*2&&sx<=300+30*2+32&&sy>=300-30*2&&sy<=300-30)
		if(ex>=300+30*2+64*2&&ex<=300+30*2+32+64*2&&ey>=300-30*2&&ey<=300-30)
		{
			(pDoc->a).setpHeadU();
			pDoc->a.rotation_garo456(5);
			pDoc->a.setpHeadD();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpUp()->getName(),"Row",2,"Right",counter);
		}


	//43->45
	if(sx>=330&&sx<=330+32&&sy>=300-30&&sy<=300)
		if(ex>=330+64*2&&ex<=330+32+64*2&&ey>=300-30&&ey<=300)
		{
			(pDoc->a).setpHeadU();
			pDoc->a.rotation_garo456(6);
			pDoc->a.setpHeadD();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpUp()->getName(),"Row",3,"Right",counter);
		}


	//37<-39
	if(sx>=300+30*3+64*2&&sx<=300+30*3+32+64*2&&sy>=300-30*3&&sy<=300-30*2)
		if(ex>=300+30*3&&ex<=300+30*3+32&&ey>=300-30*3&&ey<=300-30*2)
		{
			(pDoc->a).setpHeadU();
			pDoc->a.rotation_garo101112(12);
			pDoc->a.setpHeadD();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpUp()->getName(),"Row",1,"Left",counter);
		}


	//40<-42
	if(ex>=300+30*2&&ex<=300+30*2+32&&ey>=300-30*2&&ey<=300-30)
		if(sx>=300+30*2+64*2&&sx<=300+30*2+32+64*2&&sy>=300-30*2&&sy<=300-30)
		{
			(pDoc->a).setpHeadU();
			pDoc->a.rotation_garo101112(11);
			pDoc->a.setpHeadD();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpUp()->getName(),"Row",2,"Left",counter);
		}


	//43<-45
	if(ex>=300+30&&ex<=300+30+32&&ey>=300-30&&ey<=300)
		if(sx>=300+30+64*2&&sx<=300+30+32+64*2&&sy>=300-30&&sy<=300)
		{
			(pDoc->a).setpHeadU();
			pDoc->a.rotation_garo101112(10);
			pDoc->a.setpHeadD();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpUp()->getName(),"Row",3,"Left",counter);
		}


	//37->43
	if(sx>=300+30*3&&sx<=300+30*3+32&&sy>=300-30*3&&sy<=300-30*2)
		if(ex>=300+30&&ex<=300+30+32&&ey>=300-30&&ey<=300)
		{
			(pDoc->a).setpHeadU();
			pDoc->a.rotation_sero789(7);
			pDoc->a.setpHeadD();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpUp()->getName(),"Col",1,"Down",counter);
		}

	//38->44
	if(sx>=300+30*3+64&&sx<=300+30*3+32+64&&sy>=300-30*3&&sy<=300-30*2)
		if(ex>=300+30+64&&ex<=300+30+32+64&&ey>=300-30&&ey<=300)
		{
			(pDoc->a).setpHeadU();
			pDoc->a.rotation_sero789(8);
			pDoc->a.setpHeadD();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpUp()->getName(),"Col",2,"Down",counter);
		}

	//39->45
	if(sx>=300+30*3+64*2&&sx<=300+30*3+32+64*2&&sy>=300-30*3&&sy<=300-30*2)
		if(ex>=300+30+64*2&&ex<=300+30+32+64*2&&ey>=300-30&&ey<=300)
		{
			(pDoc->a).setpHeadU();
			pDoc->a.rotation_sero789(9);
			pDoc->a.setpHeadD();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpUp()->getName(),"Col",3,"Down",counter);
		}

	


	//37<-43
	if(ex>=300+30*3&&ex<=300+30*3+32&&ey>=300-30*3&&ey<=300-30*2)
		if(sx>=300+30&&sx<=300+30+32&&sy>=300-30&&sy<=300)
		{
			(pDoc->a).setpHeadU();
			pDoc->a.rotation_sero123(1);
			pDoc->a.setpHeadD();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpUp()->getName(),"Col",1,"Up",counter);
		}

	//38<-44
	if(ex>=300+30*3+64&&ex<=300+30*3+32+64&&ey>=300-30*3&&ey<=300-30*2)
		if(sx>=300+30+64&sx<=300+30+32+64&&sy>=300-30&&sy<=300)
		{
			(pDoc->a).setpHeadU();
			pDoc->a.rotation_sero123(2);
			pDoc->a.setpHeadD();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpUp()->getName(),"Col",2,"Up",counter);
		}

	//39<-45
	if(ex>=300+30*3+64*2&&ex<=300+30*3+32+64*2&&ey>=300-30*3&&ey<=300-30*2)
		if(sx>=300+30+64*2&&sx<=300+30+32+64*2&&sy>=300-30&&sy<=300)
		{
			(pDoc->a).setpHeadU();
			pDoc->a.rotation_sero123(3);
			pDoc->a.setpHeadD();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpUp()->getName(),"Col",3,"Up",counter);
		}

	//옆면
	//10->12
	if(sx>=300+64*3&&sx<=300+64*3+30&&sy>=300&&sy<=300+32)
		if(ex>=300+64*3+30*2&&ex<=300+64*3+30*3&&ey>=300-30*2&&ey<=300-30*2+32)
		{
			(pDoc->a).setpHeadL();
			pDoc->a.rotation_garo456(4);
			pDoc->a.setpHeadR();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpNext()->getName(),"Row",1,"Right",counter);
		}



	//13->15
	if(sx>=300+64*3&&sx<=300+64*3+30&&sy>=300+64&&sy<=300+32+64)
		if(ex>=300+64*3+30*2&&ex<=300+64*3+30*3&&ey>=300-30*2+64&&ey<=300-30*2+32+64)
		{
			(pDoc->a).setpHeadL();
			pDoc->a.rotation_garo456(5);
			pDoc->a.setpHeadR();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpNext()->getName(),"Row",2,"Right",counter);
		}


	//16->18
	if(sx>=300+64*3&&sx<=300+64*3+30&&sy>=300+64*2&&sy<=300+32+64*2)
		if(ex>=300+64*3+30*2&&ex<=300+64*3+30*3&&ey>=300-30*2+64*2&&ey<=300-30*2+32+64*2)
		{
			(pDoc->a).setpHeadL();
			pDoc->a.rotation_garo456(6);
			pDoc->a.setpHeadR();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpNext()->getName(),"Row",3,"Right",counter);
		}



	//10<-12
	if(ex>=300+64*3&&ex<=300+64*3+30&&ey>=300&&ey<=300+32)
		if(sx>=300+64*3+30*2&&sx<=300+64*3+30*3&&sy>=300-30*2&&sy<=300-30*2+32)
		{
			(pDoc->a).setpHeadL();
			pDoc->a.rotation_garo101112(12);
			pDoc->a.setpHeadR();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpNext()->getName(),"Row",1,"Left",counter);
		}



	//13<-15
	if(ex>=300+64*3&&ex<=300+64*3+30&&ey>=300+64&&ey<=300+32+64)
		if(sx>=300+64*3+30*2&&sx<=300+64*3+30*3&&sy>=300-30*2+64&&sy<=300-30*2+32+64)
		{
			(pDoc->a).setpHeadL();
			pDoc->a.rotation_garo101112(11);
			pDoc->a.setpHeadR();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpNext()->getName(),"Row",2,"Left",counter);
		}


	//16<-18
	if(ex>=300+64*3&&ex<=300+64*3+30&&ey>=300+64*2&&ey<=300+32+64*2)
		if(sx>=300+64*3+30*2&&sx<=300+64*3+30*3&&sy>=300-30*2+64*2&&sy<=300-30*2+32+64*2)
		{
			(pDoc->a).setpHeadL();
			pDoc->a.rotation_garo101112(10);
			pDoc->a.setpHeadR();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpNext()->getName(),"Row",3,"Left",counter);
		}

	

	//10->16
	if(sx>=300+64*3&&sx<=300+64*3+30&&sy>=300&&sy<=300+32)
		if(ex>=300+64*3&&ex<=300+64*3+30&&ey>=300+64*2&&ey<=300+32+64*2)
		{
			(pDoc->a).setpHeadL();
			pDoc->a.rotation_sero123(3);
			pDoc->a.setpHeadR();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpNext()->getName(),"Col",1,"Down",counter);
		}

	//11->17
	if(sx>=300+64*3+30&&sx<=300+64*3+30*2&&sy>=300-30&&sy<=300+32-30)
		if(ex>=300+64*3+30&&ex<=300+64*3+30*2&&ey>=300+64*2-30&&ey<=300+32+64*2-30)
		{
			(pDoc->a).setpHeadL();
			pDoc->a.rotation_sero123(2);
			pDoc->a.setpHeadR();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpNext()->getName(),"Col",2,"Down",counter);
		}

	//12->18
	if(sx>=300+64*3+30*2&&sx<=300+64*3+30*3&&sy>=300-30*2&&sy<=300-30*2+32)
		if(ex>=300+64*3+30*2&&ex<=300+64*3+30*3&&ey>=300-30*2+64*2&&ey<=300-30*2+32+64*2)
		{
			(pDoc->a).setpHeadL();
			pDoc->a.rotation_sero123(1);
			pDoc->a.setpHeadR();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpNext()->getName(),"Col",3,"Down",counter);
		}


	//10<-16
	if(ex>=300+64*3&&ex<=300+64*3+30&&ey>=300&&ey<=300+32)
		if(sx>=300+64*3&&sx<=300+64*3+30&&sy>=300+64*2&&sy<=300+32+64*2)
		{
			(pDoc->a).setpHeadL();
			pDoc->a.rotation_sero789(9);
			pDoc->a.setpHeadR();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpNext()->getName(),"Col",1,"Up",counter);
		}

	//11<-17
	if(ex>=300+64*3+30&&ex<=300+64*3+30*2&&ey>=300-30&&ey<=300+32-30)
		if(sx>=300+64*3+30&&sx<=300+64*3+30*2&&sy>=300+64*2-30&&sy<=300+32+64*2-30)
		{
			(pDoc->a).setpHeadL();
			pDoc->a.rotation_sero789(8);
			pDoc->a.setpHeadR();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpNext()->getName(),"Col",2,"Up",counter);
		}

	//12<-18
	if(ex>=300+64*3+30*2&&ex<=300+64*3+30*3&&ey>=300-30*2&&ey<=300-30*2+32)
		if(sx>=300+64*3+30*2&&sx<=300+64*3+30*3&&sy>=300-30*2+64*2&&sy<=300-30*2+32+64*2)
		{
			(pDoc->a).setpHeadL();
			pDoc->a.rotation_sero789(7);
			pDoc->a.setpHeadR();
			counter++;
			pDoc->a.mousefile(pDoc->a.getHead()->getpNext()->getName(),"Col",3,"Up",counter);
		}
}

void CprojectView::OnKeyDown(UINT nChar, UINT nRepCnt, UINT nFlags)//키보드 이벤트
{
	
	// TODO: 여기에 메시지 처리기 코드를 추가 및/또는 기본값을 호출합니다.
	CprojectDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;
	
	
	switch(nChar)
	{
	case VK_RIGHT:
		i++;
		pDoc->a.setpHeadL();
		pDoc->a.keyfile(2,i);
		break;
	case VK_LEFT:
		i++;
		pDoc->a.setpHeadR();
		pDoc->a.keyfile(1,i);
		break;
	case VK_UP:
		i++;
		pDoc->a.keyfile(3,i);
		pDoc->a.setpHeadD();
		break;
	case VK_DOWN:
		i++;
		pDoc->a.keyfile(4,i);
		pDoc->a.setpHeadU();
		break;
	}

		
		
	Invalidate(true);
	CView::OnKeyDown(nChar, nRepCnt, nFlags);
}
void CprojectView::On32771()//색
{
	abc=0;
	Invalidate(true);
}
void CprojectView::On32772()//색+숫자
{
	// TODO: 여기에 명령 처리기 코드를 추가합니다.
	abc=1;
	Invalidate(true);
}
void CprojectView::On32774()//키보드저장
{
	// TODO: 여기에 명령 처리기 코드를 추가합니다.
	CprojectDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;


	pDoc->a.outkey();
}
void CprojectView::On32773()//마우스저장
{
	// TODO: 여기에 명령 처리기 코드를 추가합니다.
	CprojectDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;

	pDoc->a.outmouse();
}
