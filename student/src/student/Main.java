package student;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import student.*;
public class Main{
   public static void main(String[] args) {
      new LoginFrame();
   }
}


@SuppressWarnings("serial")
class LoginFrame extends JFrame{
	
	//��ü ������
	JPanel mainPanel;
	
	//ȭ�鿡 ���̴� �͵�
	JButton successBtn;
	JButton joinMembership;
	JLabel passwordLabel;
	JLabel userIDLabel;
	
	private JTextField userIDField;
	private JPasswordField passwordField;

	LoginFrame(){
		setTitle("Login");
		setSize(300, 200);
		setLocation(500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//��ü ������ ����
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.decode("#FFFFFF"));
		mainPanel.setLayout(new GridLayout(3, 2));
		
		//ID �Է¶�
        userIDLabel = new JLabel("�й�:");
        userIDLabel.setForeground(Color.BLACK);
        userIDLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        userIDField = new JTextField();
        userIDField.setBackground(Color.decode("#FFFFFF"));
        userIDField.setForeground(Color.BLACK);
        userIDField.setFont(new Font("Helvetica", Font.BOLD, 13));
        
        //��й�ȣ �Է¶�
        passwordLabel = new JLabel("��й�ȣ:");
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(new Font("Helvetica", Font.BOLD, 15));   
        passwordField = new JPasswordField();
        passwordField.setBackground(Color.decode("#FFFFFF"));
        passwordField.setForeground(Color.BLACK);
        passwordField.setFont(new Font("Helvetica", Font.BOLD, 10));

        //�α��� ��ư
        successBtn = new JButton("�α���");
        successBtn.setBackground(Color.decode("#FFFFFF"));
        successBtn.setForeground(Color.BLACK);
        successBtn.setFont(new Font("Helvetica", Font.BOLD, 15));
        successBtn.addActionListener(new Btn_Success());

        //ȸ������ ��ư
        joinMembership = new JButton("ȸ������");
        joinMembership.setBackground(Color.decode("#FFFFFF"));
        joinMembership.setForeground(Color.BLACK);
        joinMembership.setFont(new Font("Helvetica", Font.BOLD, 15));
        joinMembership.addActionListener(new Join_Memebership());
               
        //������°� ȭ�鿡 �� ����
        mainPanel.add(userIDLabel);
        mainPanel.add(userIDField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(successBtn);
        mainPanel.add(joinMembership);
        add(mainPanel);
        setVisible(true);

	}
	
	class Btn_Success implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//�α��� ��ȿ�� �˻�
        	String newID = userIDField.getText();
        	String newPassword = new String(passwordField.getPassword());
			StudentInfoManager SIM = new StudentInfoManager();
        	SIM.findMembers(newID, newPassword);
			new MainFrame();
			setVisible(false);
		}
	}
	
	class Join_Memebership implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new Join_Membership();
			setVisible(false);
		}
	}
	
}

class Join_Membership extends JFrame{
	//�⺻ ������
	JFrame registerFrame;
	//��ü �г�
	JPanel registerPanel;
	
	//ȭ�鿡 ���̴� �͵�
	JLabel newIDLabel;
	JTextField newIDField;
	
	JLabel newPasswordLabel;
	JPasswordField newPasswordField;
	
	JLabel newMajorLabel;
	JTextField newMajorField;	
	
	JLabel newUsernameLabel;
	JTextField newUsernameField;	
	
	JButton submitButton;
	
	
	Join_Membership(){
        //�⺻ ������ ����
		registerFrame = new JFrame("ȸ������");
        registerFrame.setSize(300, 250);
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setLocationRelativeTo(null);

        //��ü �г� �⺻ ����
        registerPanel = new JPanel();
        registerPanel.setBackground(Color.decode("#FFFFFF"));
        registerPanel.setLayout(new GridLayout(5, 2));
  
        //�̸� �Է¶�
        newUsernameLabel = new JLabel("�̸�");
        newUsernameLabel.setForeground(Color.BLACK);
        newUsernameLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        newUsernameField = new JTextField();
        newUsernameField.setBackground(Color.decode("#FFFFFF"));
        newUsernameField.setForeground(Color.BLACK);
        newUsernameField.setFont(new Font("Helvetica", Font.BOLD, 13));
        
        //�а� �Է¶�
        newMajorLabel = new JLabel("�а�");
        newMajorLabel.setForeground(Color.BLACK);
        newMajorLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        newMajorField = new JTextField();
        newMajorField.setBackground(Color.decode("#FFFFFF"));
        newMajorField.setForeground(Color.BLACK);
        newMajorField.setFont(new Font("Helvetica", Font.BOLD, 13));
        
        //�й� �Է¶�
        newIDLabel = new JLabel("�й� ���̵�:");
        newIDLabel.setForeground(Color.BLACK);
        newIDLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        newIDField = new JTextField();
        newIDField.setBackground(Color.decode("#FFFFFF"));
        newIDField.setForeground(Color.BLACK);
        newIDField.setFont(new Font("Helvetica", Font.BOLD, 13));
        
        //��й�ȣ �Է¶�
        newPasswordLabel = new JLabel("��й�ȣ:");
        newPasswordLabel.setForeground(Color.BLACK);
        newPasswordLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        newPasswordField = new JPasswordField();
        newPasswordField.setBackground(Color.decode("#FFFFFF"));
        newPasswordField.setForeground(Color.BLACK);
        newPasswordField.setFont(new Font("Helvetica", Font.BOLD, 13));
           
        //��� ��ư
        submitButton = new JButton("���");
        submitButton.setBackground(Color.decode("#FFFFFF"));
        submitButton.setForeground(Color.BLACK);
        submitButton.setFont(new Font("Helvetica", Font.BOLD, 15));
        
        //������°� �� ����
        registerPanel.add(newUsernameLabel);
        registerPanel.add(newUsernameField);       
        registerPanel.add(newMajorLabel);
        registerPanel.add(newMajorField);
        registerPanel.add(newIDLabel);
        registerPanel.add(newIDField);        
        registerPanel.add(newPasswordLabel);
        registerPanel.add(newPasswordField);
        registerPanel.add(submitButton);        
        registerFrame.add(registerPanel);
        
        //��� ��ư ���� �� ó������
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//�Էµ� ���� ������ ����
            	String newUsername = newUsernameField.getText();
            	String newMajor = newMajorField.getText();
            	String newID = newIDField.getText();
            	String newPassword = new String(newPasswordField.getPassword());
            	System.out.println("���ο� ����� ��� - �̸�: "+ newUsername + " �а�: " + newMajor + " �й� ���̵�: " + newID + ", ��й�ȣ: " + newPassword);

                StudentInfoManager SIM = new StudentInfoManager();
                SIM.registerMembers(newUsername, newMajor, newID, newPassword);
                	
                registerFrame.dispose(); // ��� â �ݱ�
            }
        });
        registerFrame.setVisible(true);
   }
}

@SuppressWarnings("serial")
class MainFrame extends JFrame{
	
}