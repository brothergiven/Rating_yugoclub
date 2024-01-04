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
    private HashMap<Integer, SInfo> Members = new HashMap<Integer, SInfo>(); //Key가 integer타입이고, value가 SInfo타입인 객체 Members 생성
    private HashMap<String, Integer> SIDs = new HashMap<String, Integer>(); //Key가 String타입이고, value가 Integer타입인 객체 SIDs 생성
    private HashMap<Integer, String> Login = new HashMap<Integer, String>();
    private String[][] ranking;
    
    String userHome = System.getProperty("user.home");	//System.getProperty("user.home")는 현재 사용자의 홈 디렉토리 경로를 문자열로 반환. 이 값은 시스템 속성 중에서 "user.home"에 해당하는 값을 가져옴.
    Path desktopPath = Paths.get(userHome, "Desktop");	//Paths.get() 메소드는 주어진 경로 문자열을 사용하여 Path 객체를 생성. 사용자의 홈 디렉토리에 "Desktop"을 추가하여 바탕화면의 경로를 생성
    String filePath = desktopPath.resolve(FILE_NAME).toString();	//resolve() 메소드는 주어진 경로를 이 경로에 추가함. FILE_NAME은 여기서 파일 이름을 나타내는 변수이며, 이 변수에 해당하는 파일의 전체 경로를 문자열로 변환하여 filePath에 저장
    
    // 이름으로 학번 찾기
    
    public void readMembers() { // 파일에서 멤버 목록을 가져와 HashMap에 저장
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {//BufferedReader: 문자 입력 스트림에서 문자를 효율적으로 읽어들이는 데 사용
            String line;
            while ((line = br.readLine()) != null) {
                // 파일에서 한 줄을 읽어와서 Member 객체로 변환
            	parseMember(line);
            }
        } catch (IOException e) {
            e.printStackTrace();	//예외가 발생하면 해당 예외 객체는 발생한 예외에 대한 정보를 포함하며, printStackTrace() 메소드는 이 정보를 사용하여 예외가 발생한 시점의 호출 스택을 출력
        }
    }

    private void parseMember(String line) { // 파일에서 Member 가져오기 위함
        // 파일에서 읽어온 한 줄을 파싱하여 Member 객체로 변환
        SInfo tmp;
        int SID, rating;
        String name, department;
        String ID; 
        String password; 
        
        String[] parts = line.split(","); // ","을 기준으로 문자열 분리 
        parts[0] = parts[0].trim();	//trim: 문자열의 앞뒤 공백을 제거하는 역할
        parts[1] = parts[1].trim();
        parts[2] = parts[2].trim();
        parts[3] = parts[3].trim();
       
        if (parts.length == 4) {
        	// 분리된 문자열이 4개라면 (학번, 이름, 학과, 레이팅)
            name = parts[0];
            SID = Integer.parseInt(parts[1]);	//Integer.parseInt: 문자열을 입력으로 받아 해당 문자열을 정수(int)로 변환
            department = parts[2];
            rating = Integer.parseInt(parts[3]);
            ID = parts[1];
            password = "g"+SID;
            tmp = new SInfo(SID, name, department, rating);
            Members.put(SID, tmp);
            SIDs.put(name, SID);
            Login.put(SID, password);
        } 
        
        else if (parts.length == 3){
        	// 분리된 문자열이 3개라면 (학번, 이름, 학과) 레이팅 정보 추가 
        	name = parts[0];
        	SID = Integer.parseInt(parts[1]);
        	department = parts[2];
        	tmp = new SInfo(SID, name, department, 1000);
            ID = parts[1];
        	password = "g"+SID;
        	Members.put(SID, tmp);
        	SIDs.put(name, SID);
        	Login.put(SID, password);
        } 
        
        else {
            System.out.println("잘못된 형식의 라인: " + line);
            return;
        }	
        System.out.println("Read Member : " + name);
    }

    public void writeMembers() { // 현재 멤버 HashMap을 파일에 저장
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // 부원 목록을 파일에 저장
     	   Iterator<Integer> keys = Members.keySet().iterator();
            while(keys.hasNext()) {
         	   int SID = keys.next();
         	   SInfo info = Members.get(SID);
                bw.write(info.getName() + "," + SID + "," + info.getDepartment() + "," + info.getRating());
                bw.newLine();
                System.out.println("Write Members : "+info.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void TargetMembers(int targetSID, int newRating) { 
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
        	Iterator<Integer> keys = Members.keySet().iterator();
            while(keys.hasNext()) {
            	// 특정 부원의 점수를 변경하고 파일에 저장
	           if (Members.containsKey(targetSID)) {
	               SInfo info = Members.get(targetSID);
	               info.setRating(newRating); //새로운 점수 설정
	               String newRatingStr = Integer.toString(info.getRating()); // 점수를 String으로 변환
	               bw.write(info.getName() + "," + targetSID + "," + info.getDepartment() + "," + info.getRating());
	               bw.newLine();
	               System.out.println("Write Members : "+info.getName());
	               targetSID = 0;
	               continue;
	           }
         	   int SID = keys.next();
         	   SInfo info = Members.get(SID);
               bw.write(info.getName() + "," + SID + "," + info.getDepartment() + "," + info.getRating());
               bw.newLine();
               System.out.println("Write Members : "+info.getName());
            }
          
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
    
    
    public void updateRanking() {
    	// 현재 Members 해시맵을 바탕으로 랭킹 계산
    	// 2차원 배열의 행은 (등수, 이름, 학과, 점수)
    	// Members라는 Map의 모든 값들을 가져와서 ArrayList에 저장
    	List<SInfo> list = new ArrayList<SInfo>(Members.values());    	
    	
    	// 원래 리스트를 점수에 따라 내림차순 정렬
    	// Java에서 제공하는 Collections 클래스의 sort 메소드를 이용해 리스트를 정렬하는 부분
    	// 만약 점수가 1000점인 사람이 있다면 그 사람을 뒤로 보내도록 정렬
    	//만약 대국을 여러판 했을 때 1000점이 나올 수가 있으므로 알고리즘에 1000점이 나오지 않도록 1000점이 나왔을때는 보너스 점수 1점 추가
    	
        Collections.sort(list, (s1, s2) -> {
            if (s1.getRating() == 1000 && s2.getRating() == 1000) {
                return 0;
            } 
            else if (s1.getRating() == 1000) {
                return 1;
            } 
            else if (s2.getRating() == 1000) {
                return -1;
            } 
            else {
                return Integer.compare(s2.getRating(), s1.getRating());
            }
        });
        	 
        
    	ranking = new String[list.size()][4];
		int rank = 0;
		for(int i = 0; i< list.size(); i++) {
	        if(list.get(i).getRating() == 1000) {
	            ranking[i][0] = " ";
	            ranking[i][3] = "전적기록 없음";
	        }
	        else {
	            ranking[i][0] = Integer.toString(++rank);
	            ranking[i][3] = Integer.toString(list.get(i).getRating());
	        }
	        ranking[i][1] = list.get(i).getName();
	        ranking[i][2] = list.get(i).getDepartment();
	        System.out.println("Added Ranking : "+rank);
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
    	bInfo.setRating((int)rm.getNewRatingB());
    	wInfo.setRating((int)rm.getNewRatingW());
    	Members.put(bID, bInfo);
    	Members.put(wID, wInfo);
    	System.out.println("레이팅 업데이트 : " + bInfo.getName()+tmpRatingB+"->"+bInfo.getRating() + ", " + wInfo.getName()+tmpRatingW+"->"+wInfo.getRating());
    }
    
    public boolean findMembers(int newID, String newPassword) { // 파일에서 멤버 목록을 가져와 HashMap에 저장
     // ID와 비밀번호를 검증
	    if (Login.containsKey(newID)) {
	        return Login.get(newID).equals(newPassword);
	    } else {
	        return false;
	    }
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