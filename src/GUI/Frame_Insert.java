package GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import student.*;

class Frame_Insert extends JFrame{   
    private double initialRatingA;
    private double initialRatingB;
    private boolean matchResult;
    private double expectedWinProbabilityA;
    private double expectedWinProbabilityB;
    private int newRatingA;
    private int newRatingB;
    private final int kFactor = 32;

    public void setInformation(double initialRatingA, double initialRatingB, boolean matchResult) {
        this.initialRatingA = initialRatingA;
        this.initialRatingB = initialRatingB;
        this.matchResult = matchResult;
    }

    public int getNewRatingA() {
        return newRatingA;
    }

    public int getNewRatingB() {
        return newRatingB;
    }

    private void updateRatings() {
        if (matchResult) {
            this.expectedWinProbabilityA = 1.0 / (1.0 + Math.pow(10.0, (double) (initialRatingA - initialRatingB) / 400.0));
            this.expectedWinProbabilityB = 1 - this.expectedWinProbabilityA;
            this.newRatingA = (int) (initialRatingA + kFactor * (1 - expectedWinProbabilityA));
            this.newRatingB = (int) (initialRatingB + kFactor * (0 - expectedWinProbabilityB));
        } else {
            this.expectedWinProbabilityB = 1.0 / (1.0 + Math.pow(10.0, (double) (initialRatingB - initialRatingA) / 400.0));
            this.expectedWinProbabilityA = 1 - this.expectedWinProbabilityB;
            this.newRatingA = (int) (initialRatingA + kFactor * (0 - expectedWinProbabilityA));
            this.newRatingB = (int) (initialRatingB + kFactor * (1 - expectedWinProbabilityB));
        }
        
        
    }
    
    
    JPanel panel;
    JTextField textID1;
    JTextField textID2;
    JButton blackWinButton;
    JButton whiteWinButton;
    Container contentPane;
    StudentInfoManager SIM;
    public Frame_Insert(StudentInfoManager ref) {
    	SIM = ref;
        setTitle("대국결과기록 프로그램");
        setPreferredSize(new Dimension(300, 200));
        setLocation(500, 400);
        contentPane = getContentPane();
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

//        JTextField text1 = new JTextField();
//        JTextField text2 = new JTextField();
        textID1 = new JTextField();
        textID2 = new JTextField();
        blackWinButton = new JButton("흑 승");
        whiteWinButton = new JButton("백 승");

        blackWinButton.addActionListener(new BlackWinButton());

        whiteWinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matchResult = false;
                updateRatings();
            }
        });

   //     panel.add(new JLabel("이름"));
   //     panel.add(text1);
        panel.add(new JLabel("흑 학번"));
  //      panel.add(text2);
        panel.add(textID1);
        panel.add(blackWinButton);
        
//        panel.add(new JLabel("이름"));

        panel.add(new JLabel("백 학번"));
        panel.add(textID2);
        panel.add(whiteWinButton);
        
        contentPane.add(panel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }
    class BlackWinButton implements ActionListener{
 	   public void actionPerformed(ActionEvent e) {
 		   matchResult = true;

            int id1 = Integer.parseInt(textID1.getText()); // 학번, SInfo 한번에 가져오는 메소드 구현 필요
            int id2 = Integer.parseInt(textID2.getText());
            SInfo info1 = SIM.getSInfo(id1);
            SInfo info2 = SIM.getSInfo(id2);
            initialRatingA = info1.getRating();
            initialRatingB = info2.getRating();
            // 예외 처리 안되어있음
            updateRatings();
            SInfo newInfo1 = new SInfo(info1.getName(), info1.getDepartment(), newRatingA); // 새로운 객체 생성시 메소드 구현 필요
            SIM.Members.put(id1, newInfo1); // newInfo도 함수 내의 지역변수 선언 안해도 됨, Members 접근지정자 private으로 할 수 있도록 메소드 구현
            SInfo newInfo2 = new SInfo(info2.getName(), info2.getDepartment(), newRatingB);
            SIM.Members.put(id2, newInfo2);
            SIM.saveMembers();
            SIM.loadMembers();
            setVisible(false);
 	   }
    }
    
    class WhiteWinButton implements ActionListener{
 	   public void actionPerformed(ActionEvent e) {
 		   matchResult = false;
 		   
 		   int id1 = Integer.parseInt(textID1.getText());
 		   int id2 = Integer.parseInt(textID2.getText());
 		   SInfo info1 = SIM.getSInfo(id1);
 		   SInfo info2 = SIM.getSInfo(id2);
 		   initialRatingA = info1.getRating();
 		   initialRatingB = info2.getRating();
 		   
 		   updateRatings();
 		   SInfo newInfo1 = new SInfo(info1.getName(), info1.getDepartment(), newRatingA);
 		   SInfo newInfo2 = new SInfo(info2.getName(), info2.getDepartment(), newRatingB);
 		   
 		   SIM.Members.put(id1,  newInfo1);
 		   SIM.Members.put(id2, newInfo2);
 		   SIM.saveMembers();
 		   SIM.loadMembers();
 		   setVisible(false);
 	   }
    }
    
    
}