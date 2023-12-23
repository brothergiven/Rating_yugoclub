package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import student.*;



public class Frame_Record extends JFrame{
	JButton update;
	private StudentInfoManager SIM;
	private RecordManager RM;
	private DefaultTableModel model;
	private JTable recordTable;
	private JScrollPane recordScrlp;
	public Frame_Record(StudentInfoManager ref) {
		SIM = ref;
		RM = new RecordManager(SIM);
		setTitle("전적 기록");
		setSize(300, 200);
		Vector<String> column = new Vector<String>();
		column.add("흑 이름"); column.add("백 이름"); column.add("대국 결과");
		Vector<Vector<String>> recordData = RM.getRecord();
		model = new DefaultTableModel(recordData, column) {
			@Override
			public boolean isCellEditable(int row, int mCol) {
				return false;
			}
		};
		recordTable = new JTable(model);
		recordScrlp = new JScrollPane(recordTable);
		getContentPane().add(recordScrlp);
		setVisible(true);
	}
	
	
}

