public class MediScoreChallenge {

    enum AirOrOxygenEnum {
        AIR(0), OXYGEN(2);
        private final int score;
        AirOrOxygenEnum(int score) {
            this.score = score;
        }
        public int getValue() {
            return this.score;
        }
    }

    public static void main(String[] args) {
        System.out.println(calcMediScore(0, 0, 15, 95, 37.1f));
        System.out.println(calcMediScore(2, 0, 17, 95, 37.1f));
        System.out.println(calcMediScore(2, 1, 23, 88, 38.5f));
    }

    public static int calcMediScore(int airOrOxygenObservation, int consciousnessObservation, int respirationRangeObservation, int spO2Observation, float temperatureObservation) {

        int airOrOxygenScore = getAirOrOxygenScore(airOrOxygenObservation);
        int consciousnessScore = getConsciousnessScore(consciousnessObservation);
        int respirationRangeScore = getRespirationRangeScore(respirationRangeObservation);
        int spO2Score = getSpO2Score(spO2Observation, airOrOxygenScore);
        int temperatureScore = getTemperatureScore(temperatureObservation);

        if(airOrOxygenScore == -1 || consciousnessScore == -1 || respirationRangeScore == -1 || spO2Score == -1 || temperatureScore == -1){
            return -1; // invalid observation
        }

        int sumMediScore = 0;
        sumMediScore += airOrOxygenScore + consciousnessScore + respirationRangeScore + spO2Score + temperatureScore;
        if(airOrOxygenScore == 2 && spO2Observation <= 92){
            sumMediScore += 2;
        }
        return sumMediScore;
    }

    private static int getAirOrOxygenScore(int airOrOxygenObservation){
        int airOrOxygenScore;
        if(airOrOxygenObservation == AirOrOxygenEnum.AIR.getValue()) {
            airOrOxygenScore = AirOrOxygenEnum.AIR.getValue();
        }
        else if(airOrOxygenObservation == AirOrOxygenEnum.OXYGEN.getValue()) {
            airOrOxygenScore = AirOrOxygenEnum.OXYGEN.getValue();
        }
        else {
            System.out.println("Error, airOrOxygenObservation can only be 0 or 2");
            return -1;
        }
        return airOrOxygenScore;
    }

    private static int getConsciousnessScore(int consciousnessObservation){
        int consciousnessScore;
        if(consciousnessObservation == 0) {
            consciousnessScore = 0;
        }
        else if(consciousnessObservation >= 0 && consciousnessObservation <= 3) {
            consciousnessScore = consciousnessObservation;
        }
        else {
            System.out.println("Error, consciousnessObservation can only be between 0 and 3 (inclusive)");
            return -1;
        }
        return consciousnessScore;
    }

    private static int getRespirationRangeScore(int respirationRangeObservation){
        int respirationRangeScore;
        if((respirationRangeObservation <= 8 && respirationRangeObservation >= 0) || respirationRangeObservation >= 25) {
            respirationRangeScore = 3;
        }
        else if (respirationRangeObservation >= 21 && respirationRangeObservation <= 24) {
            respirationRangeScore = 2;
        }
        else if (respirationRangeObservation >= 9 && respirationRangeObservation <= 11) {
            respirationRangeScore = 1;
        }
        else if (respirationRangeObservation >= 12 && respirationRangeObservation <= 20) {
            respirationRangeScore = 0;
        }
        else {
            System.out.println("Error, enter a positive value");
            return -1;
        }
        return respirationRangeScore;
    }

    private static int getSpO2Score(int spO2Observation, int airOrOxygenScore){
        int spO2Score;
        if ((spO2Observation <= 83 && spO2Observation >= 0) || (spO2Observation >= 97 && airOrOxygenScore == 2)){
            spO2Score = 3;
        }
        else if ((spO2Observation == 84 || spO2Observation == 85) || ((spO2Observation == 95 || spO2Observation == 96) && airOrOxygenScore == 2)){
            spO2Score = 2;
        }
        else if ((spO2Observation == 86 || spO2Observation == 87) || ((spO2Observation == 93 || spO2Observation == 94) && airOrOxygenScore == 2)) {
            spO2Score = 1;
        }
        else if ((spO2Observation >= 88 && spO2Observation <= 92) || (spO2Observation >= 93 && airOrOxygenScore == 0)){
            spO2Score = 0;
        }
        else {
            System.out.println("Error, enter a positive value");
            return -1;
        }
        return spO2Score;
    }

    private static int getTemperatureScore(float temperatureObservation){
        int temperatureScore;
        if(temperatureObservation <= 35.0){
            temperatureScore = 3;
        }
        else if (temperatureObservation >= 39.1){
            temperatureScore = 2;
        }
        else if ((temperatureObservation >= 35.1 && temperatureObservation <= 36.0) || (temperatureObservation >= 38.1 && temperatureObservation <= 39.0)){
            temperatureScore = 1;
        }
        else if (temperatureObservation >= 36.1 && temperatureObservation <= 38.0){
            temperatureScore = 0;
        }
        else {
            return -1;
        }
        return temperatureScore;
    }
}