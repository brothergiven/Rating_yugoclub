package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import student.*;

public class LoginFrame extends JFrame{
	
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