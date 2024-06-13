package member;

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Login {
	final public String ACTUAL_FILE = "LoginInfo.txt";
	final public String CONFIRM_FILE = "ConfirmRegister.txt";
	private HashMap<Integer, String> passwords = null;

	public boolean login(int id, String passwd) {
		if (passwords == null) {
			passwords = new HashMap<Integer, String>();
			readLoginInfo();
		}
		String target = passwords.get(id);
		return target != null && target.equals(passwd);
	}

	private void readLoginInfo() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ACTUAL_FILE), "utf-8"))) {
			String line;
			while ((line = br.readLine()) != null) {
				parseLine(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void parseLine(String line) {
		String[] parts = line.split(":");
		if (parts.length != 2)
			return;
		parts[0] = parts[0].trim();
		parts[1] = parts[1].trim();
		passwords.put(Integer.parseInt(parts[0]), parts[1]);
	}

	public void register(int id, String passwd) {
		passwords.put(id, passwd);
		writeLoginLine(id, passwd);
	}
	
	

	

	public void requestRegister(int id, String name, String major, String pw, String level) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(CONFIRM_FILE, true))) {
			String line = String.format("%d:%s:%s:%s:%s", id, name, major, pw, level);
			bw.write(line);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeLoginLine(int id, String passwd) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ACTUAL_FILE, true))) {
			String line = String.format("%d:%s", id, passwd);
			bw.write(line);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeLoginInfo() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ACTUAL_FILE))) {
			for (Map.Entry entry : passwords.entrySet()) {
				String id = entry.getKey().toString();
				String pw = (String) entry.getValue();
				bw.write(id + ":" + pw);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
