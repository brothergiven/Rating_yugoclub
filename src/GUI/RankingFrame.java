package gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.Main;

public class RankingFrame extends JFrame {
	private JTable rnkTable; // 랭킹 출력할 테이블
	private JScrollPane scrlP; // 랭킹 테이블을 얹을 스크롤팬
	private DefaultTableModel model;

	public RankingFrame() {
		setTitle("Ranking");
		setSize(600, 600);
		setLocation(660, 240);
		String[] columnName = { "등수", "이름", "학과", "점수" };
		String[][] rankingData = Main.memberRef.getRanking(); // MemberList에서 가져옴
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
