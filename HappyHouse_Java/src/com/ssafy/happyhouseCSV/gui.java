package com.ssafy.happyhouseCSV;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class gui {
	JFrame f;
	JButton b;
	JList li;
	// JTextArea ta;
	// WeatherDAO dao;

	public gui(List<String> list,String str) {
		// dao = new NewsDAODOMImpl();
		// dao = WeatherDAO.getDao();
		createGUI(str);
		addEvent();
		showList(list);
	}

	public void createGUI(String str) {
		f = new JFrame(str);
		JPanel p = new JPanel();
		// b = new JButton("News List 호출");
		li = new JList();
		// ta = new JTextArea("");
		// ta.setLineWrap(true);
		p.setLayout(new BorderLayout());
		// p.add(b,"North");
		p.add(li);
		// f.setLayout(new GridLayout(2,1,5,5));
		f.add(p);
		// f.add(ta);
		f.setSize(1500, 500);
		f.setVisible(true);
	}

	public void addEvent() {
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		li.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				// Weather n = (Weather)li.getSelectedValue();
				// ta.setText(n.hour+"시"+"<온도 : "+n.temp+"> <날씨 : "+n.wfKor+"> <강수 확률 :
				// "+n.reh+">");

			}
		});
	}

public void showList(List<String> list) {
   // dao.connectXML("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153056000");
    //java.util.List<Weather> datas = dao.getWeatherList();
    li.removeAll();
    //li.setListData(datas.toArray());
    li.setListData(list.toArray());
}
	/*
	 * public static void main(String[] args) { new gui(); }
	 */
}