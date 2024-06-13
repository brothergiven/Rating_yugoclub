package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.Main;
import member.Member;

public class GameFrame extends JFrame {
	// 상대, 급수, 점수 테이블 출력
	// 테이블에서 처리는 어떻게?
	// 신청이 들어오고 신청을 받아주면.
	private final String FILE_NAME = "GameRequest.txt";
	private int callerID;
	ArrayList<int[]> others = new ArrayList<int[]>();
	ArrayList<int[]> callers = new ArrayList<int[]>();
	private DefaultTableModel model;
	private JTable rnkTable;
	private JScrollPane scrlP;
	private JButton confirm = new JButton("확인");
	public GameFrame(int id) {
		callerID = id;
		setSize(600, 600);
		setLocation(660, 240);
		setLayout(new BorderLayout());
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME), "utf-8"))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String parts[] = line.split(":");
				if(parts.length != 2) continue;
				int reqCaller = Integer.parseInt(parts[1]);
				int[] inst = new int[] {Integer.parseInt(parts[0]), reqCaller};
				if(reqCaller == callerID) {
					callers.add(inst);
				} else 
					others.add(inst);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] columnName = { "순번", "이름", "학과", "점수" };
		String[][] rankingData = new String[callers.size()][]; 
		
		for(int i = 0; i < callers.size(); i++) {
			Member tmp = Main.memberRef.members.get(callers.get(i)[0]);
			rankingData[i] = tmp.toOutputData();
			rankingData[i][0] = Integer.toString(i+1);
		}
		
		
		
		
		model = new DefaultTableModel(rankingData, columnName) {
			@Override
			public boolean isCellEditable(int row, int mCol) {
				return false;
			}
		};

		rnkTable = new JTable(model);

		scrlP = new JScrollPane(rnkTable);
		
		add(scrlP, BorderLayout.CENTER);
		add(confirm, BorderLayout.SOUTH);
		
		
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_NAME), "utf-8"))){
					for(int[] arr : others) {
						String line = String.format("%d:%d", arr[0], arr[1]);
						bw.write(line);
						bw.newLine();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "대국 신청이 확인되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
