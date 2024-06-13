package member;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MemberList {
	// 멤버 정보 리스트 가지는 클래스.
	public HashMap<Integer, Member> members;
	public HashMap<String, Integer> nametoid;
	private final String FILE_NAME = "MemberList.txt";

	public MemberList() {
		members = new HashMap<Integer, Member>();
		nametoid = new HashMap<String, Integer>();
		readMembers();
	}

	public String[][] getRanking() {
		ArrayList<Member> memberList = new ArrayList<Member>(members.values());

		Collections.sort(memberList, (o1, o2) -> (o2.getRating() > o1.getRating()) ? 1 : -1);

		String[][] rankingData = new String[memberList.size()][];
		for (int i = 0; i < memberList.size(); i++) {
			String[] data = memberList.get(i).toOutputData();
			data[0] = Integer.toString(i + 1);
			rankingData[i] = data;
		}

		return rankingData;
	}

	private void readMembers() { // 파일에서 멤버 목록을 가져와 HashMap에 저장, 최초 1회 실행
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME), "utf-8"))) {
			String line;
			while ((line = br.readLine()) != null) {
				Member m = parseMember(line);
				System.out.println(line);
				if (m != null) {
					members.put(m.getID(), m);
					nametoid.put(m.getName(), m.getID());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Member parseMember(String line) {
		String[] parts = line.split(":");
		if (parts.length != 5)
			return null;
		parts[0] = parts[0].trim(); // id
		parts[1] = parts[1].trim(); // name
		parts[2] = parts[2].trim(); // department
		parts[3] = parts[3].trim(); // level
		int id = Integer.parseInt(parts[0]);
		Member member = new Member(id, parts[1], parts[2], parts[3]);
		return member;
	}



	public void writeMembers() {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_NAME), "utf-8"))) {
			for (Map.Entry entry : members.entrySet()) {
				bw.write(entry.getValue().toString());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void initRating() {
		for(Map.Entry<Integer, Member> entry : members.entrySet()) {
			Member m = entry.getValue();
			m.setRating(1000);
		}
	}
}
