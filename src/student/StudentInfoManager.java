package student;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

import student.SInfo;

public class StudentInfoManager{
	static Scanner scanner = new Scanner(System.in);
    public StudentInfoManager() {
    	readMembers();
	}
	private static final String FILE_NAME = "기우회재학생명부.txt";
    private HashMap<Integer, SInfo> Members = new HashMap<Integer, SInfo>();
    private HashMap<String, Integer> SIDs = new HashMap<String, Integer>(); 
    private String[][] ranking;
    
    String userHome = System.getProperty("user.home");
    Path desktopPath = Paths.get(userHome, "Desktop");
    String filePath = desktopPath.resolve(FILE_NAME).toString();
    
    // 이름으로 학번 찾기
    
    public void readMembers() { // 파일에서 멤버 목록을 가져와 HashMap에 저장


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 파일에서 한 줄을 읽어와서 Member 객체로 변환
            	parseMember(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseMember(String line) { // 파일에서 Member 가져오기 위함
        // 파일에서 읽어온 한 줄을 파싱하여 Member 객체로 변환
        SInfo tmp;
        int SID, rating;
        String name, department;
        
        String[] parts = line.split(","); // ","을 기준으로 문자열 분리 
        
        if (parts.length == 4) {
        	// 분리된 문자열이 4개라면 (학번, 이름, 학과, 레이팅)
            name = parts[0].trim();
            SID = Integer.parseInt(parts[1].trim());
            department = parts[2].trim();
            rating = Integer.parseInt(parts[3].trim());
            tmp = new SInfo(SID, name, department, rating);
            Members.put(SID, tmp);
            SIDs.put(name, SID);
        } else if (parts.length == 3){
        	// 분리된 문자열이 3개라면 (학번, 이름, 학과) 레이팅 정보 추가 
        	name = parts[0].trim();
        	SID = Integer.parseInt(parts[1].trim());
        	department = parts[2].trim();
        	tmp = new SInfo(SID, name, department, 1000);
        	Members.put(SID, tmp);
        	SIDs.put(name, SID);
        } else {
            System.out.println("잘못된 형식의 라인: " + line);
            return;
        }	
        System.out.println("Read Member : "+name);
    }

    public void writeMembers() { // 현재 멤버 HashMap을 파일에 저장
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // 부원 목록을 파일에 저장
     	   Iterator<Integer> keys = Members.keySet().iterator();
            while(keys.hasNext()) {
         	   int SID = keys.next();
         	   SInfo info = Members.get(SID);
                bw.write(info.getName() + "," + SID + ","
                        + info.getDepartment() + "," + info.getRating());
                bw.newLine();
                System.out.println("Write Members : "+info.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    public void updateRanking() {
    	// 현재 Member 해시맵을 바탕으로 랭킹 계산
    	// 2차원 배열의 행은 (등수, 이름, 학과, 점수)
    	List<SInfo> list = new ArrayList<SInfo>(Members.values());
    	Collections.sort(list, new Comparator<SInfo>() {
    		public int compare(SInfo s1, SInfo s2) {
    			return Integer.compare(s2.getRating(), s1.getRating());
    		}
		});
    	ranking = new String[list.size()][4];
		int rank = 0;
    	for(int i = 0; i< list.size(); i++) {
    		ranking[i][0] = Integer.toString(++rank);
    		System.out.println("Added Ranking : "+rank);
    		if(list.get(i).getRating() == 1000) {
    			ranking[i][3] = "전적기록 없음";
    		} else {
    			ranking[i][3] = Integer.toString(list.get(i).getRating());
    		}
    		ranking[i][1] = list.get(i).getName();
    		ranking[i][2] = list.get(i).getDepartment();
    		
    	}
    }
    
    public String[][] getRanking() {
    	return ranking;
    }
    
    public String[] searchMember(int id) {
    	// 학번 정보 받아서 해당하는 랭킹 행 출력
    	SInfo info = Members.get((Integer)id);
    	int i;
    	for(i = 0; i < ranking.length; i++) {
    		if(info.getName().equals(ranking[i][1]))
    			return ranking[i];
    	}
    	return null;
    }
    
    
   
   
    
    public void resetRating() { 
    	// 모든 사용자의 레이팅 1000으로 초기화(
    	Map<Integer, SInfo> tmpMap = Members;
    	for(SInfo value : tmpMap.values()) {
    		System.out.println(value.getName()+"레이팅 초기화 : 1000");
    		value.setRating(1000);
    	}
    }
    
    public void updateRating(String bName, String wName, boolean matchresult) {
    	// 하나의 전적에 대하여 레이팅 업데이트
    	int bID = SIDs.get(bName);
    	int wID = SIDs.get(wName);
    	SInfo bInfo = Members.get(bID);
    	SInfo wInfo = Members.get(wID);
    	int tmpRatingB = bInfo.getRating();
    	int tmpRatingW = wInfo.getRating();
    	RatingManager rm = new RatingManager((double)bInfo.getRating(), (double)wInfo.getRating(), matchresult);
    	rm.calculateRatings();
    	bInfo.setRating((int)rm.getNewRatingA());
    	wInfo.setRating((int)rm.getNewRatingB());
    	Members.put(bID, bInfo);
    	Members.put(wID, wInfo);
    	System.out.println("레이팅 업데이트 : " + bInfo.getName()+tmpRatingB+"->"+bInfo.getRating() + ", " + wInfo.getName()+tmpRatingW+"->"+wInfo.getRating());
    }
    
    public boolean findMembers(String newID, String newPassword) { // 파일에서 멤버 목록을 가져와 HashMap에 저장
    	boolean isLoginSuccess = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 파일에서 한 줄을 읽어와서 Member 객체로 변환
                isLoginSuccess = compareMember(line, newID, newPassword);
                if (isLoginSuccess) {
                    System.out.println("로그인 성공");
                    break; // 로그인 성공했으므로 반복문 종료
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return isLoginSuccess;
    }
      
    private boolean compareMember(String line, String newID, String newPassword) { // 파일에서 Member 가져오기 위함
        // 파일에서 읽어온 한 줄을 파싱하여 Member 객체로 변환
    	boolean isLoginSuccess = false;
        String[] parts = line.split(","); 
        SInfo tmp;
        String name, department;
        int sID, rating;
        if (parts.length == 4) {
            name = parts[0].trim();
            sID = Integer.parseInt(parts[1].trim());
            department = parts[2].trim();
            rating = Integer.parseInt(parts[3].trim());
            tmp = new SInfo(sID, name, department, rating);
            //값 비교
            isLoginSuccess = String.valueOf(sID).equals(newID) && String.valueOf(sID).equals(newPassword);
        } else if (parts.length == 3){
        	name = parts[0].trim();
        	sID = Integer.parseInt(parts[1].trim());
        	department = parts[2].trim();
        	tmp = new SInfo(sID, name, department, 1000);
        	isLoginSuccess = String.valueOf(sID).equals(newID) && String.valueOf(sID).equals(newPassword);
        } else {
            System.out.println("잘못된 형식의 라인: " + line);
        }
		return isLoginSuccess;	
        
    }
    

    public void registerMembers(String newUsername, String newMajor, String newID, String newPassword) { // 파일에서 멤버 목록을 가져와 HashMap에 저장
    	System.out.println(filePath);
        try {
        	FileWriter fileWriter = new FileWriter(filePath, true);
        	String newUserInfo = newUsername+ "," + newID + ","+ newMajor + "," + 1000 + "\n";
            fileWriter.write(newUserInfo);
            fileWriter.close();
            System.out.println("회원가입이 완료되었습니다.");

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public SInfo getSInfo(int id) {
    	return Members.get(id);
    }
    
}