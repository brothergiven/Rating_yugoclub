package student;

public class RatingManager { // 하나의 전적에 대하여 점수 계산하는 클래스
	// 초기 점수
	private double initialRatingB;
	private double initialRatingW;
	// 대국 결과
	private boolean matchResult;
	// 초기 점수에 따른 승률
	private double expectedWinProbabilityB;
	private double expectedWinProbabilityW;
	// 계산 후 레이팅
	private double newRatingB;
	private double newRatingW;
	// 계산을 위한 K-팩터 상수
	private final int kFactor = 32;
	
	// 생성자 : 초기 레이팅과 대국결과 필요
	public RatingManager(double initialRatingB, double initialRatingW, boolean matchResult) {
		this.initialRatingB = initialRatingB;
		this.initialRatingW = initialRatingW;
		this.matchResult = matchResult;

	}
	
	// 생성자 정보를 바탕으로 레이팅 계산
	public void calculateRatings() {
        this.expectedWinProbabilityB = 1.0 / (1.0 + Math.pow(10.0, (double) (initialRatingW - initialRatingB) / 400.0));
        this.expectedWinProbabilityW = 1.0 / (1.0 + Math.pow(10.0, (double) (initialRatingB - initialRatingW) / 400.0));
		if (matchResult) {
            this.newRatingB = (int) (initialRatingB + kFactor * (1 - expectedWinProbabilityB));
            this.newRatingW = (int) (initialRatingW + kFactor * (0 - expectedWinProbabilityW));
        } else {
            this.newRatingB = (int) (initialRatingB + kFactor * (0 - expectedWinProbabilityB));
            this.newRatingW = (int) (initialRatingW + kFactor * (1 - expectedWinProbabilityW));
        }
		if(newRatingB == 1000 || newRatingW == 1000) {
			newRatingB += 1;
			newRatingW += 1;
		}
	}
	
	// 계산 후 레이팅 반환
	public double getNewRatingB() {
		return newRatingB;
	}
	
	public double getNewRatingW() {
		return newRatingW;
	}
	
}