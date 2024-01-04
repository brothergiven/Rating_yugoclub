package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import student.*;



public class Frame_Record extends JFrame{
	JButton update;
	private RecordManager RM;
	private DefaultTableModel model;
	private JTable recordTable;
	private JScrollPane recordScrlp;
	public Frame_Record(RecordManager refR) {
		// SIM과 RM 객체를 각각 refS와 refR로 초기화
		RM = refR;

		// 현재 창의 제목을 "전적 기록"으로 설정
		setTitle("전적 기록");

		// 현재 창의 크기를 너비 300, 높이 200으로 설정
		setSize(300, 200);
		setLocation(500, 400);

		// 테이블의 컬럼 이름을 정의하는 벡터를 생성
		// 컬럼은 "흑 이름", "백 이름", "대국 결과"의 3가지로 구성
		Vector<String> column = new Vector<String>();
		column.add("흑 이름"); column.add("백 이름"); column.add("대국 결과");

		// RM 객체의 getRecord 메소드를 호출하여 전적 정보를 벡터의 벡터로 가져옴
		// 이 벡터의 벡터는 테이블의 각 행을 구성할 데이터를 담고 있음
		Vector<Vector<String>> recordData = RM.getRecord();

		// DefaultTableModel 객체를 생성하여 테이블 모델을 설정
		// 테이블 모델에는 전적 데이터와 컬럼 이름이 포함됨
		// 또한, isCellEditable 메소드를 재정의(Override)하여 모든 셀을 편집 불가능하게 설정
		model = new DefaultTableModel(recordData, column) {
		    @Override
		    public boolean isCellEditable(int row, int mCol) {
		        // 모든 셀을 편집 불가능하게 설정
		        return false;
		    }
		};

		// DefaultTableModel로부터 JTable을 생성
		// JTable은 GUI 상에서 실제로 테이블을 표현함
		recordTable = new JTable(model);

		  // recordTable에 렌더러 추가하기
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c= super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                c.setBackground(Color.decode("#EEEEEE"));
                String gameResult = (String) table.getValueAt(row, 2);

                
                if (column == 0) {
                    c.setBackground(Color.BLACK); // 특정 열의 배경색 설정
                    c.setForeground(Color.WHITE); // 특정 열의 폰트 색상 설정
                }
                else if (column == 1) {
                    c.setBackground(Color.WHITE); // 특정 열의 배경색 설정
                     c.setForeground(Color.BLACK);
                }
                else if (column == 2) {
                     // 조건에 따라 배경색 변경
                    if (gameResult.equals("백 승")) {
                       // c.setBackground(Color.decode("#FFFFCC"));
                       c.setBackground(Color.decode("#FFFFFF"));
                        gameResult = "흑 승";
                    } else if (gameResult.equals("흑 승")) {
                        //c.setBackground(Color.decode("#FFCC99"));
                       c.setBackground(Color.decode("#000000"));
                       c.setForeground(Color.WHITE);
                        gameResult = "백 승";
                    } else if (gameResult.equals("무승부")) {
                        //c.setBackground(Color.decode("#FFFF99"));
                       c.setBackground(Color.decode("#888888"));
                        gameResult = "무승부";
                    }
                }
                
                return c;
            }
        };

        // 특정 열의 셀 렌더러 설정 (대국 결과 열에 해당하는 인덱스는 2)
        recordTable.getColumnModel().getColumn(2).setCellRenderer(renderer);
        recordTable.getColumnModel().getColumn(1).setCellRenderer(renderer);
        recordTable.getColumnModel().getColumn(0).setCellRenderer(renderer);
        recordTable.setBackground(Color.decode("#000000"));
		
		
		// JTable을 JScrollPane에 추가
		// JScrollPane은 테이블의 스크롤 기능을 관리함
		recordScrlp = new JScrollPane(recordTable);

		// JScrollPane을 창에 추가
		getContentPane().add(recordScrlp);

		// 창을 표시함
		setVisible(true);

	}
}

