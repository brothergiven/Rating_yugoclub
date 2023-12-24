package GUI;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;

import GUI.LoginFrame.Btn_Success;
import GUI.LoginFrame.Join_Memebership;
import student.StudentInfoManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class administrate_Console extends JFrame {
		//��ü ������
		JPanel mainPanel;
		
		//ȭ�鿡 ���̴� �͵�
		JButton successBtn;
		JButton joinMembership;
		JLabel passwordLabel;
		JLabel userIDLabel;
		
		private JTextField userIDField;
		private JPasswordField passwordField;

		administrate_Console(){
			setTitle("Login");
			setSize(300, 200);
			setLocation(500, 400);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			
			//��ü ������ ����
			mainPanel = new JPanel();
			mainPanel.setBackground(Color.decode("#FFFFFF"));
			mainPanel.setLayout(new GridLayout(3, 1));
			
	        
	        //��й�ȣ �Է¶�
	        passwordLabel = new JLabel("������ �̿� ���Ա��� :");
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

	        //������°� ȭ�鿡 �� ����
	        mainPanel.add(passwordLabel);
	        mainPanel.add(passwordField);
	        mainPanel.add(successBtn);
	        add(mainPanel);
	        setVisible(true);

	}
		
		class Btn_Success implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				//�α��� ��ȿ�� �˻�
	        	String newPassword = new String(passwordField.getPassword());
				StudentInfoManager SIM = new StudentInfoManager();
	        	
				if (newPassword.equals("135789")) new MainFrame();
				else {JOptionPane.showMessageDialog(null, "�α��� ����", "������ �̿ܿ� ���� ����!", JOptionPane.ERROR_MESSAGE);}
				setVisible(false);
			}
		}
}

