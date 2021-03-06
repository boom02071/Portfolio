
// projectView.h : CprojectView 클래스의 인터페이스
//

#pragma once


class CprojectView : public CView
{
protected: // serialization에서만 만들어집니다.
	CprojectView();
	DECLARE_DYNCREATE(CprojectView)

// 특성입니다.
public:
	CprojectDoc* GetDocument() const;
	int m_x;
	int m_y;
	CPoint ptChild;
	CPoint start_point;
	CPoint end_point;
	bool m_bDragFlag;
	COLORREF color_mapping(int a);
	CWnd m_wndChild;
// 작업입니다.
public:
	void slice_rotation(CPoint, CPoint);
// 재정의입니다.
private:
	int abc;
	int i;
	int choice;
	int counter;
public:
	virtual void OnDraw(CDC* pDC);  // 이 뷰를 그리기 위해 재정의되었습니다.
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
protected:
	virtual BOOL OnPreparePrinting(CPrintInfo* pInfo);
	virtual void OnBeginPrinting(CDC* pDC, CPrintInfo* pInfo);
	virtual void OnEndPrinting(CDC* pDC, CPrintInfo* pInfo);

// 구현입니다.
public:
	virtual ~CprojectView();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// 생성된 메시지 맵 함수
protected:
	afx_msg void OnFilePrintPreview();
	afx_msg void OnRButtonUp(UINT nFlags, CPoint point);
	afx_msg void OnContextMenu(CWnd* pWnd, CPoint point);
	DECLARE_MESSAGE_MAP()
public:
	afx_msg void OnLButtonUp(UINT nFlags, CPoint point);
	afx_msg void OnLButtonDown(UINT nFlags, CPoint point);
	afx_msg void OnKeyDown(UINT nChar, UINT nRepCnt, UINT nFlags);
	afx_msg void On32771();
	afx_msg void On32772();
	afx_msg void On32774();
	afx_msg void On32773();
};

#ifndef _DEBUG  // projectView.cpp의 디버그 버전
inline CprojectDoc* CprojectView::GetDocument() const
   { return reinterpret_cast<CprojectDoc*>(m_pDocument); }
#endif

