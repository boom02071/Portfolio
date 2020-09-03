package com.ssafy.happyhouseCSV;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class csv1 {
	/*public static void main(String[] args) {
		try {
			fun2("혜화");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	public void fun1(String dong) throws IOException {
		List<String> s = new ArrayList<String>();
		String csvFileName = "./서울시 종로구 환경 지도점검 내역 현황.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFileName))) {
		    String line;
		    
		    
		    while ((line = br.readLine()) != null) {
		    	String[] values = line.split(",");
		    	if(!values[12].contains(dong))
		    		continue;
		    
		    	
		    	String str="";
		    	for(int i=0;i<values.length;i++) {
		    		str+=values[i];
		    	}
		    	
		    	s.add(str);
		    }
		    if(s.isEmpty()) s.add("해당 동 자료 없음");
		    gui g = new gui(s,"환경 지도 점검");	
		}
	}
	public static void fun2(String dong) throws FileNotFoundException, IOException {
		List<String> s = new ArrayList<String>();
		String csvFileName ="./상가업소정보.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFileName))) {
		    String line;

		    while ((line = br.readLine()) != null) {
		    	String[] values = line.split("\\|");

		    	if(!values[16].contains(dong))
		    		continue;
		    	
		    	String str="";
		    	for(int i=0;i<20;i++) {
		    		if(i>=3 && i%2==1)
		    			continue;
		    		str+=values[i];
		    		str+="    ";
		    	}
		    	s.add(str);
		    }
		    if(s.isEmpty()) s.add("해당 동 자료 없음");
		    gui g = new gui(s,"주변 상가 정보");	
		    
		}
	}
}
