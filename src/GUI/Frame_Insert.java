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
    private RecordManager RM;
    private StudentInfoManager SIM;

    private boolean matchresult;
    private int whiteID, blackID;

    public Frame_Insert(StudentInfoManager refS, RecordManager refR) {
        SIM = refS;
        RM = refR;
        setTitle("대국 결과 입력");
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
		private SInfo bInfo;
		private SInfo wInfo;
		private int NewRatingB;
		private int NewRatingW;
		
	    public void actionPerformed(ActionEvent e) {
	        try {
	            matchresult = true;
	            blackID = Integer.parseInt(textID1.getText());
	            whiteID = Integer.parseInt(textID2.getText());
	            
	            // 같은 학번 입력 오류 처리
	            if (blackID == whiteID) {
	                throw new IllegalArgumentException("같은 학번을 입력할 수 없습니다.");
	            }
	            
	            bInfo = SIM.getSInfo(blackID);
	            wInfo = SIM.getSInfo(whiteID);
	
	            rm = new RatingManager(bInfo.getRating(), wInfo.getRating(), matchresult);
	            rm.calculateRatings();
	            
	            NewRatingB = (int)rm.getNewRatingB();
	            NewRatingW = (int)rm.getNewRatingW();
	            bInfo.setRating(NewRatingB);
	            wInfo.setRating(NewRatingW);
	            
	            SIM.TargetMembers(blackID, NewRatingB);
	            SIM.TargetMembers(whiteID, NewRatingW);
	            
	            System.out.println("전적 입력 : "+blackID+"-"+rm.getNewRatingB()+", "+whiteID+"-"+rm.getNewRatingW());
	            
	            // 대국 결과에 따른 전적 저장
	            if(matchresult) {
	                RM.addRecord(bInfo.getName(), wInfo.getName(), "흑 승");
	            } else {
	                RM.addRecord(bInfo.getName(), wInfo.getName(), "백 승");
	            }
	            RM.writeRecord();
	            setVisible(false);
	            setEnabled(false);
	        } catch (NullPointerException e1) {
	        	JOptionPane.showMessageDialog(null, "올바른 학번을 입력하세요", "입력 오류", JOptionPane.ERROR_MESSAGE);
	        } catch(NumberFormatException e2) {
	        	JOptionPane.showMessageDialog(null, "올바른 학번을 입력하세요", "입력 오류", JOptionPane.ERROR_MESSAGE);
	        } catch(IllegalArgumentException e3) {
	            JOptionPane.showMessageDialog(null, e3.getMessage(), "입력 오류", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
	
	class WhiteWinButton implements ActionListener{
		private SInfo bInfo;
		private SInfo wInfo;
		private int NewRatingB;
		private int NewRatingW;
		
	    public void actionPerformed(ActionEvent e) {
	        try {
	        	 matchresult = false;
		            blackID = Integer.parseInt(textID1.getText());
		            whiteID = Integer.parseInt(textID2.getText());
		            
		            if (blackID == whiteID) {
		                throw new IllegalArgumentException("같은 학번을 입력할 수 없습니다.");
		            }
		            
		            bInfo = SIM.getSInfo(blackID);
		            wInfo = SIM.getSInfo(whiteID);
		
		            rm = new RatingManager(bInfo.getRating(), wInfo.getRating(), matchresult);
		            rm.calculateRatings();
		            
		            NewRatingB = (int)rm.getNewRatingB();
		            NewRatingW = (int)rm.getNewRatingW();
		            bInfo.setRating(NewRatingB);
		            wInfo.setRating(NewRatingW);
		            
		            SIM.TargetMembers(blackID, NewRatingB);
		            SIM.TargetMembers(whiteID, NewRatingW);
		            
		            System.out.println("전적 입력 : "+blackID+"-"+rm.getNewRatingB()+", "+whiteID+"-"+rm.getNewRatingW());
		            
		            // 대국 결과에 따른 전적 저장
		            if(matchresult) {
		                RM.addRecord(bInfo.getName(), wInfo.getName(), "흑 승");
		            } else {
		                RM.addRecord(bInfo.getName(), wInfo.getName(), "백 승");
		            }
		            RM.writeRecord();
		            setVisible(false);
		            setEnabled(false);
	            } catch (NullPointerException e1) {
	            	JOptionPane.showMessageDialog(null, "올바른 학번을 입력하세요", "입력 오류", JOptionPane.ERROR_MESSAGE);	   
	            } catch(NumberFormatException e2) {
	            	JOptionPane.showMessageDialog(null, "올바른 학번을 입력하세요", "입력 오류", JOptionPane.ERROR_MESSAGE);
	            } catch(IllegalArgumentException e3) {
	                JOptionPane.showMessageDialog(null, e3.getMessage(), "입력 오류", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }
}


