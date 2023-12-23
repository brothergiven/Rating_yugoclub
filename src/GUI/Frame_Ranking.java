package GUI;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import student.*;

public class Frame_Ranking extends JFrame{
	private JTable rnkTable; // ��ŷ ����� ���̺�
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
	
}