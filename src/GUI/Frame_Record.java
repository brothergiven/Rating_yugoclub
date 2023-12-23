package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import student.*;

public class Frame_Record extends JFrame {
    JButton update;
    private StudentInfoManager SIM;
    private Record_Manager RM;
    private DefaultTableModel model;
    private JTable recordTable;
    private JScrollPane recordScrlp;

    public Frame_Record(StudentInfoManager ref) {
        SIM = ref;
        RM = new Record_Manager(SIM);
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
                // 조건에 따라 배경색 변경
                else if (gameResult.equals("백 승")) {
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
                } else {
                    c.setBackground(table.getBackground());
                }

                return c;
            }
        };

        
        
        // 특정 열의 셀 렌더러 설정 (대국 결과 열에 해당하는 인덱스는 2)
        recordTable.getColumnModel().getColumn(2).setCellRenderer(renderer);
        recordTable.getColumnModel().getColumn(1).setCellRenderer(renderer);
        recordTable.getColumnModel().getColumn(0).setCellRenderer(renderer);
        recordTable.setBackground(Color.decode("#000000"));
        recordScrlp = new JScrollPane(recordTable);
        getContentPane().add(recordScrlp);
        setVisible(true);
    }
}
