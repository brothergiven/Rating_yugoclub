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
        setTitle("���� ���");
        setSize(300, 200);
        Vector<String> column = new Vector<String>();
        column.add("�� �̸�"); column.add("�� �̸�"); column.add("�뱹 ���");
        Vector<Vector<String>> recordData = RM.getRecord();
        model = new DefaultTableModel(recordData, column) {
            @Override
            public boolean isCellEditable(int row, int mCol) {
                return false;
            }
        };
        recordTable = new JTable(model);

        // recordTable�� ������ �߰��ϱ�
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c= super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                c.setBackground(Color.decode("#EEEEEE"));
                String gameResult = (String) table.getValueAt(row, 2);

                
                if (column == 0) {
                    c.setBackground(Color.BLACK); // Ư�� ���� ���� ����
                    c.setForeground(Color.WHITE); // Ư�� ���� ��Ʈ ���� ����
                }
                else if (column == 1) {
                	 c.setBackground(Color.WHITE); // Ư�� ���� ���� ����
                     c.setForeground(Color.BLACK);
                }
                // ���ǿ� ���� ���� ����
                else if (gameResult.equals("�� ��")) {
                   // c.setBackground(Color.decode("#FFFFCC"));
                	c.setBackground(Color.decode("#FFFFFF"));
                    gameResult = "�� ��";
                } else if (gameResult.equals("�� ��")) {
                    //c.setBackground(Color.decode("#FFCC99"));
                	c.setBackground(Color.decode("#000000"));
                	c.setForeground(Color.WHITE);
                    gameResult = "�� ��";
                } else if (gameResult.equals("���º�")) {
                    //c.setBackground(Color.decode("#FFFF99"));
                	c.setBackground(Color.decode("#888888"));
                    gameResult = "���º�";
                } else {
                    c.setBackground(table.getBackground());
                }

                return c;
            }
        };

        
        
        // Ư�� ���� �� ������ ���� (�뱹 ��� ���� �ش��ϴ� �ε����� 2)
        recordTable.getColumnModel().getColumn(2).setCellRenderer(renderer);
        recordTable.getColumnModel().getColumn(1).setCellRenderer(renderer);
        recordTable.getColumnModel().getColumn(0).setCellRenderer(renderer);
        recordTable.setBackground(Color.decode("#000000"));
        recordScrlp = new JScrollPane(recordTable);
        getContentPane().add(recordScrlp);
        setVisible(true);
    }
}
