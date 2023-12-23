package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import student.Record_Manager;
import student.SInfo;
import student.StudentInfoManager;



public class Frame_Record extends JFrame{
	private JTable rcdTable; // ��ŷ ����� ���̺�
	private JScrollPane scrlP; // ��ŷ ���̺��� ���� ��ũ����
	private StudentInfoManager SIM;
	private Record_Manager RM = new Record_Manager(SIM);
	private DefaultTableModel model;
	
	
	public Frame_Record(StudentInfoManager ref) {
		SIM = ref;
		setTitle("Ranking");
		setSize(300, 200);
		String[] columnName = {"���", "�̸�", "�а�", "�·�" };
		String[][] rankingData = SIM.getRanking();
		/*
		model = new DefaultTableModel(rankingData, columnName) {
			@Override
			public boolean isCellEditable(int row, int mCol) {
				return false;
			}
		};
		
		rnkTable = new JTable(model);
	
		scrlP = new JScrollPane(rnkTable);
		add(scrlP);
		*/
		setVisible(true);
	}
	
	
}

/*
 * 	private JTable rnkTable; // ��ŷ ����� ���̺�
	private JScrollPane scrlP; // ��ŷ ���̺��� ���� ��ũ����
	private StudentInfoManager SIM;
	private DefaultTableModel model;
	public Frame_Ranking(StudentInfoManager ref){
		SIM = ref;
		setTitle("Ranking");
		setSize(300, 200);
		String[] columnName = {"���", "�̸�", "�а�", "����" };
		String[][] rankingData = SIM.getRanking();
		
		model = new DefaultTableModel(rankingData, columnName) {
			@Override
			public boolean isCellEditable(int row, int mCol) {
				return false;
			}
		};
		
		rnkTable = new JTable(model);
	
		scrlP = new JScrollPane(rnkTable);
		add(scrlP);
		setVisible(true);
	}
	
 * */