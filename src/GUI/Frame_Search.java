package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import student.StudentInfoManager;



public class Frame_Search extends JFrame{
  
    JTextField sIDField;
    JLabel ID;
    JPanel topPnl;
    JButton confirm;
    JTable infoTbl;
    // 학생 정보를 저장할 2차원 문자열 배열을 선언
    String[] columnName = {"등수", "이름", "학과", "점수" };
    // 테이블 모델을 선언
    String[][] info = new String[0][4];
    // 학생 정보를 관리하는 객체를 선언
    DefaultTableModel dtm;
    private StudentInfoManager SIM;
    
    public Frame_Search(StudentInfoManager ref) {
        SIM = ref;
        SIM.updateRanking();
        setTitle("검색");
        setSize(400, 200);
        setLocation(500, 400);
	    
        topPnl = new JPanel(new BorderLayout());
        ID = new JLabel("학번");
        sIDField = new JTextField();

        confirm = new JButton("확인");
        confirm.addActionListener(new Btn_confirm());

        topPnl.add(ID, BorderLayout.WEST);
        topPnl.add(sIDField, BorderLayout.CENTER);
        topPnl.add(confirm, BorderLayout.EAST);

        dtm = new DefaultTableModel(info, columnName);
        dtm.addRow(columnName);
        infoTbl = new JTable(dtm);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(topPnl, BorderLayout.NORTH);
        c.add(infoTbl, BorderLayout.CENTER);

        setVisible(true);
    }

    class Btn_confirm implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            try {
                // 테이블에 행이 있을 경우 첫 번째 행을 삭제
                if (dtm.getRowCount() > 0) {
                    dtm.removeRow(0);
                }
                // 텍스트 필드에서 입력받은 학번을 정수로 변환
                int id = Integer.parseInt(sIDField.getText());
                // 학번으로 학생 정보를 검색
                Object[] member = SIM.searchMember(id);
                // 검색된 학생 정보가 없을 경우 에러 창
                if (member == null) {
                    throw new NullPointerException();
                } else {
                    // 검색된 학생 정보를 테이블에 추가
                    dtm.addRow(member);
                }
            } catch(NumberFormatException n) {
                // 텍스트 필드에서 입력받은 학번이 정수가 아닐 경우 에러 창
                JOptionPane.showMessageDialog(null, "올바른 학번을 입력하세요", "입력 오류", JOptionPane.ERROR_MESSAGE);
            } catch(NullPointerException n) {
                // 검색된 학생 정보가 없을 경우 에러 창
                JOptionPane.showMessageDialog(null, "존재하지 않는 학번입니다.", "에러", JOptionPane.ERROR_MESSAGE);
            } finally {
                // 에러가 발생하더라도 다이얼로그를 닫음
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof Dialog) {
                        Dialog dialog = (Dialog) window;
                        if (dialog.isModal()) {
                            dialog.dispose();
                        }
                    }
                }
            }
        }
    }




}
