package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.Iterator;
import java.util.List;

import student.*;
public class Main{
	public static void main(String[] args) {
		new LoginFrame();
	}
}


class LoginFrame extends JFrame{
	JPanel mainPanel;
	JButton successBtn; // 로그인 성공 버튼
	LoginFrame(){
		setTitle("Login");
		setSize(300, 200);
		setLocation(500, 400);
		mainPanel = new JPanel();
		
		Container c = getContentPane();
		successBtn = new JButton("시작");
		successBtn.addActionListener(new Btn_Success());
		c.add(successBtn);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	class Btn_Success implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new MainFrame();
			setVisible(false);
		}
	}
	
}

class MainFrame extends JFrame{
	JPanel panel;
	JButton btnRanking;
	JButton btnSearch;
	JButton btnRecord;
	JButton btnInsert;
	StudentInfoManager SIM = new StudentInfoManager(); // 생성자 호출 후 파일에 있는 부원 정보 SIM.Members에 저장
	MainFrame(){
		setTitle("Rating Project");
		setPreferredSize(new Dimension(300, 200));
		setLocation(500, 400);
	    
		Container c = getContentPane();
	    
	    panel = new JPanel();
	    panel.setLayout(new GridLayout(4, 1));
	    
	    
	    btnRanking = new JButton("Ranking");
	    btnSearch = new JButton("검색");
	    btnRecord = new JButton("전적 기록");
	    btnInsert = new JButton("전적 입력");
	      
	    btnRanking.addActionListener(new Btn_Ranking());
	    btnSearch.addActionListener(new Btn_Search());
	    btnRecord.addActionListener(new Btn_Record());
	    btnInsert.addActionListener(new Btn_Insert());
	    
	    panel.add(btnRanking);
	    panel.add(btnSearch);
	    panel.add(btnRecord);
	    panel.add(btnInsert);
	    
	    c.add(panel);
	      
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pack();
	    setVisible(true);

	}
	
	class Btn_Ranking implements ActionListener {
	   public void actionPerformed(ActionEvent e) {
		   SIM.loadMembers();
		   new Frame_Ranking();
	   }
	}
	
	class Btn_Search implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			SIM.loadMembers();
			new Frame_Search();
		}
	}

	class Btn_Record implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			SIM.loadMembers();
			new Frame_Record();
		}
	}
	
	class Btn_Insert implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			SIM.loadMembers();
			new Frame_Insert();
		}
	}

	

	class Frame_Ranking extends JFrame{
		JTable table;
		JScrollPane scrlP;
		public Frame_Ranking(){
			setTitle("Ranking");
			setSize(300, 200);
			String[] columnName = {"등수", "이름", "학과", "점수" };
			String[][] rankingData = SIM.getRanking();
			table = new JTable(rankingData, columnName);
			scrlP = new JScrollPane(table);
			add(scrlP);
			setVisible(true);
		}
	}
	
	class Frame_Search extends JFrame{
		JTextField sIDField;
		JLabel ID;
		JPanel topPnl;
		JButton confirm;
		JTable infoTbl;
		String[][] info = new String[0][4];
		DefaultTableModel dtm;
		public Frame_Search() {
			setTitle("검색");
			setSize(300, 200);
			topPnl = new JPanel(new BorderLayout());
			
			ID = new JLabel("학번");
			sIDField = new JTextField();

			
			confirm = new JButton("확인");
			confirm.addActionListener(new Btn_confirm());
			
			topPnl.add(ID, BorderLayout.WEST);
			topPnl.add(sIDField, BorderLayout.CENTER);
			topPnl.add(confirm, BorderLayout.EAST);
			
			String[] columnName = {"등수", "이름", "학과", "점수" };
			dtm = new DefaultTableModel(info, columnName);
			infoTbl = new JTable(dtm);
			
			Container c = getContentPane();
			c.setLayout(new BorderLayout());
			c.add(topPnl, BorderLayout.NORTH);
			c.add(infoTbl, BorderLayout.CENTER);
			
			
			setVisible(true);
		}
		class Btn_confirm implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(sIDField.getText());
				dtm.addRow(SIM.searchMember(id));
			}
		}
		
		
		
	}
	
	class Frame_Record extends JFrame{
		JButton update;
		public Frame_Record() {
			setTitle("전적 기록");
			setSize(300, 200);
			update = new JButton("최신화");
			update.addActionListener(new Btn_update());
			getContentPane().add(update);
			setVisible(true);
		}
		
		class Btn_update implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				SIM.resetRating();
				SIM.updateAllRating();
				SIM.saveMembers();
			}
		}
	}
	
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

	       public Frame_Insert() {
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
	

	
	
}