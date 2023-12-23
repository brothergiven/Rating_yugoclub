package student;

public class RatingManager { // 하나의 전적에 대하여 점수 계산하는 클래스
	// 초기 점수
	private double initialRatingA;
	private double initialRatingB;
	// 대국 결과
	private boolean matchResult;
	// 초기 점수에 따른 승률
	private double expectedWinProbabilityA;
	private double expectedWinProbabilityB;
	// 계산 후 레이팅
	private double newRatingA;
	private double newRatingB;
	// 계산을 위한 K-팩터 상수
	private final int kFactor = 32;
	
	// 생성자 : 초기 레이팅과 대국결과 필요
	public RatingManager(double initialRatingA, double initialRatingB, boolean matchResult) {
		this.initialRatingA = initialRatingA;
		this.initialRatingB = initialRatingB;
		this.matchResult = matchResult;

	}
	
	// 생성자 정보를 바탕으로 레이팅 계산
	public void calculateRatings() {
        this.expectedWinProbabilityA = 1.0 / (1.0 + Math.pow(10.0, (double) (initialRatingB - initialRatingA) / 400.0));
        this.expectedWinProbabilityB = 1.0 / (1.0 + Math.pow(10.0, (double) (initialRatingA - initialRatingB) / 400.0));
		if (matchResult) {
            this.newRatingA = (int) (initialRatingA + kFactor * (1 - expectedWinProbabilityA));
            this.newRatingB = (int) (initialRatingB + kFactor * (0 - expectedWinProbabilityB));
        } else {
            this.newRatingA = (int) (initialRatingA + kFactor * (0 - expectedWinProbabilityA));
            this.newRatingB = (int) (initialRatingB + kFactor * (1 - expectedWinProbabilityB));
        }
		
	}
	
	// 계산 후 레이팅 반환
	public double getNewRatingA() {
		return newRatingA;
	}
	
	public double getNewRatingB() {
		return newRatingB;
	}
	
}
