package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import main.Main;

public class RecordFrame extends JFrame {
	private DefaultTableModel model;
	private JTable recordTable;
	private JScrollPane scrlp;

	public RecordFrame() {
		setTitle("전적 기록");
		setSize(600, 600);		
		setLocation(660, 240);
		
		String[] columnName = { "순번", "흑", "백", "결과", "일시" };
		String[][] recordData = Main.recordRef.recordData();

		model = new DefaultTableModel(recordData, columnName);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				c.setBackground(Color.decode("#EEEEEE"));
				String gameResult = (String) table.getValueAt(row, 3);

				if (column == 1) {
					c.setBackground(Color.BLACK); // 특정 열의 배경색 설정
					c.setForeground(Color.WHITE); // 특정 열의 폰트 색상 설정
				} else if (column == 2) {
					c.setBackground(Color.WHITE); // 특정 열의 배경색 설정
					c.setForeground(Color.BLACK);
				} else if (column == 3) {
					// 조건에 따라 배경색 변경
					if (gameResult.equals("백 승")) {
						c.setBackground(Color.decode("#FFFFFF"));
						c.setForeground(Color.black);
					} else if (gameResult.equals("흑 승")) {
						c.setBackground(Color.decode("#000000"));
						c.setForeground(Color.WHITE);
					} else if (gameResult.equals("무승부")) {
						c.setBackground(Color.decode("#888888"));
					}
				}

				return c;
			}
		};

		recordTable = new JTable(model);
		
		recordTable.getColumnModel().getColumn(2).setCellRenderer(renderer);
		recordTable.getColumnModel().getColumn(1).setCellRenderer(renderer);
		recordTable.getColumnModel().getColumn(3).setCellRenderer(renderer);

		scrlp = new JScrollPane(recordTable);

		add(scrlp);
		setVisible(true);

	}
}
