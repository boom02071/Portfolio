
// projectView.h : CprojectView Ŭ������ �������̽�
//

#pragma once


class CprojectView : public CView
{
protected: // serialization������ ��������ϴ�.
	CprojectView();
	DECLARE_DYNCREATE(CprojectView)

// Ư���Դϴ�.
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
// �۾��Դϴ�.
public:
	void slice_rotation(CPoint, CPoint);
// �������Դϴ�.
private:
	int abc;
	int i;
	int choice;
	int counter;
public:
	virtual void OnDraw(CDC* pDC);  // �� �並 �׸��� ���� �����ǵǾ����ϴ�.
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
protected:
	virtual BOOL OnPreparePrinting(CPrintInfo* pInfo);
	virtual void OnBeginPrinting(CDC* pDC, CPrintInfo* pInfo);
	virtual void OnEndPrinting(CDC* pDC, CPrintInfo* pInfo);

// �����Դϴ�.
public:
	virtual ~CprojectView();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// ������ �޽��� �� �Լ�
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

#ifndef _DEBUG  // projectView.cpp�� ����� ����
inline CprojectDoc* CprojectView::GetDocument() const
   { return reinterpret_cast<CprojectDoc*>(m_pDocument); }
#endif

