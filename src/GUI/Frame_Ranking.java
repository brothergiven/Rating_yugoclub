package GUI;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import student.*;

public class Frame_Ranking extends JFrame{
	private JTable rnkTable; // 랭킹 출력할 테이블
	private JScrollPane scrlP; // 랭킹 테이블을 얹을 스크롤팬
	private StudentInfoManager SIM;
	private DefaultTableModel model;
	public Frame_Ranking(StudentInfoManager ref){
		SIM = ref;
		setTitle("Ranking");
		setSize(300, 200);
		String[] columnName = {"등수", "이름", "학과", "점수" };
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