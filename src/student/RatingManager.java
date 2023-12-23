package student;

public class RatingManager { // �ϳ��� ������ ���Ͽ� ���� ����ϴ� Ŭ����
	// �ʱ� ����
	private double initialRatingA;
	private double initialRatingB;
	// �뱹 ���
	private boolean matchResult;
	// �ʱ� ������ ���� �·�
	private double expectedWinProbabilityA;
	private double expectedWinProbabilityB;
	// ��� �� ������
	private double newRatingA;
	private double newRatingB;
	// ����� ���� K-���� ���
	private final int kFactor = 32;
	
	// ������ : �ʱ� �����ð� �뱹��� �ʿ�
	public RatingManager(double initialRatingA, double initialRatingB, boolean matchResult) {
		this.initialRatingA = initialRatingA;
		this.initialRatingB = initialRatingB;
		this.matchResult = matchResult;

	}
	
	// ������ ������ �������� ������ ���
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
	
	// ��� �� ������ ��ȯ
	public double getNewRatingA() {
		return newRatingA;
	}
	
	public double getNewRatingB() {
		return newRatingB;
	}
	
}