package member;

public class RatingCalculator {

		static int kFactor = 32;
		// 생성자 정보를 바탕으로 레이팅 계산
		public static double[] calculateRatings(double initialRatingB, double initialRatingW, boolean matchResult) {
	        double expectedWinProbabilityB = 1.0 / (1.0 + Math.pow(10.0, (double) (initialRatingW - initialRatingB) / 400.0));
	        double expectedWinProbabilityW = 1.0 / (1.0 + Math.pow(10.0, (double) (initialRatingB - initialRatingW) / 400.0));
			double newRatingB, newRatingW;
	        if (matchResult) {
	            newRatingB = initialRatingB + kFactor * (1 - expectedWinProbabilityB);
	            newRatingW = initialRatingW + kFactor * (0 - expectedWinProbabilityW);
	        } else {
	            newRatingB = initialRatingB + kFactor * (0 - expectedWinProbabilityB);
	            newRatingW = initialRatingW + kFactor * (1 - expectedWinProbabilityW);
	        }
	        
	        
	        return new double[] {newRatingB, newRatingW};
	        
		}
		
}
