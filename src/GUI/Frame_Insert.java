package GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import student.*;

class Frame_Insert extends JFrame{   
    private JTextField textID1 = new JTextField();
    private JTextField textID2 = new JTextField();
    private JButton blackWinButton = new JButton("흑 승");
    private JButton whiteWinButton = new JButton("백 승");
    Container c;
    private RatingManager rm;
    private StudentInfoManager SIM;
    private boolean matchresult;
    private int whiteNumber, blackNumber;
    public Frame_Insert(StudentInfoManager ref) {
    	SIM = ref;
        setTitle("대국결과기록 프로그램");
        setSize(300, 200);
        
        setLocation(500, 400);
        c = getContentPane();
        c.setLayout(new GridLayout(2, 2));

        blackWinButton.addActionListener(new BlackWinButton());
        whiteWinButton.addActionListener(new WhiteWinButton());

        c.add(new JLabel("흑 학번"));
        c.add(textID1);
        c.add(blackWinButton);
        

        c.add(new JLabel("백 학번"));
        c.add(textID2);
        c.add(whiteWinButton);
        
        setVisible(true);
    }
    class BlackWinButton implements ActionListener{
 	   public void actionPerformed(ActionEvent e) {
 		   	try {
 			 	matchresult = true;
 		   		blackNumber = Integer.parseInt(textID1.getText());
 		   		whiteNumber = Integer.parseInt(textID2.getText());
 		   		SInfo bInfo = SIM.getSInfo(blackNumber);
 		   		SInfo wInfo = SIM.getSInfo(whiteNumber);
 		   		rm = new RatingManager(bInfo.getRating(), wInfo.getRating(), matchresult);
 		   		rm.calculateRatings();
 		   		bInfo.setRating((int)rm.getNewRatingA());
 		   		wInfo.setRating((int)rm.getNewRatingB());
 		   		System.out.println("전적 입력 : "+blackNumber+"-"+rm.getNewRatingA()+", "+whiteNumber+"-"+rm.getNewRatingB());
 		   		// RecordManager 정보에 대국결과 저장
 		   } catch (NullPointerException e1) {
 			   new E1Frame();
 		   } catch(NumberFormatException e2) {
 			   new E2Frame();
 		   }
 	   }
    }
    
    class WhiteWinButton implements ActionListener{
 	   public void actionPerformed(ActionEvent e) {
 		   try {
 		   matchresult = false;
 		   blackNumber = Integer.parseInt(textID1.getText());
 		   whiteNumber = Integer.parseInt(textID2.getText());
 		   rm = new RatingManager(blackNumber, whiteNumber, matchresult);
 		   rm.calculateRatings();
 		   SIM.getSInfo(blackNumber).setRating((int)rm.getNewRatingA());
 		   SIM.getSInfo(whiteNumber).setRating((int)rm.getNewRatingB());
 		   System.out.println("전적 입력 : "+blackNumber+"-"+rm.getNewRatingA()+", "+whiteNumber+"-"+rm.getNewRatingB());
		   
 		   // RecordManager 정보에 대국결과 저장
 		   } catch (NullPointerException e1) {
 			   new E1Frame();
 		   } catch(NumberFormatException e2) {
 			   new E2Frame();
 		   }
 	   }
    }
    
    
}

class E1Frame extends JFrame{
	E1Frame(){
		setTitle("오류");
		JLabel la = new JLabel("존재하지 않는 학번입니다.");
		JButton ok = new JButton("확인");
		setSize(200, 110);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER));
		c.add(la);
		c.add(ok);
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEnabled(false);
				setVisible(false);
			}
		});
		
		setVisible(true);
	}
}

class E2Frame extends JFrame{
	E2Frame(){
		setTitle("오류");
		JLabel la = new JLabel("숫자를 입력하세요.");
		JButton ok = new JButton("확인");
		setSize(200, 110);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER));
		c.add(la);
		c.add(ok);
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEnabled(false);
				setVisible(false);
			}
		});
		
		setVisible(true);
	}
}

