
// projectDoc.h : CprojectDoc Ŭ������ �������̽�
//

#include "Face_list.h"

#pragma once


class CprojectDoc : public CDocument
{
protected: // serialization������ ��������ϴ�.
	CprojectDoc();
	DECLARE_DYNCREATE(CprojectDoc);

// Ư���Դϴ�.
public:
	
// �۾��Դϴ�.
public:
	Face_list a;

// �������Դϴ�.
public:
	virtual BOOL OnNewDocument();
	virtual void Serialize(CArchive& ar);
#ifdef SHARED_HANDLERS
	virtual void InitializeSearchContent();
	virtual void OnDrawThumbnail(CDC& dc, LPRECT lprcBounds);
#endif // SHARED_HANDLERS

// �����Դϴ�.
public:
	virtual ~CprojectDoc();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// ������ �޽��� �� �Լ�
protected:
	DECLARE_MESSAGE_MAP()

#ifdef SHARED_HANDLERS
	// �˻� ó���⿡ ���� �˻� �������� �����ϴ� ����� �Լ�
	void SetSearchContent(const CString& value);
#endif // SHARED_HANDLERS
public:
	
};
