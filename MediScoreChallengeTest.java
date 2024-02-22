import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MediScoreChallengeTest {

    @Test
    void calcMediScoreWithPatientExampleTest(){
        // patient 1 example
        assertEquals(0, MediScoreChallenge.calcMediScore(0, 0, 15, 95, 37.1f));
        // patient 2 example
        assertEquals(4, MediScoreChallenge.calcMediScore(2, 0, 17, 95, 37.1f));
        // patient 3 example
        assertEquals(8, MediScoreChallenge.calcMediScore(2, 1, 23, 88, 38.5f));
    }

    @Test
    void failureTest(){ // Tests for failure
        // airOrOxygen
        assertEquals(-1, MediScoreChallenge.calcMediScore(-1, 1, 23, 88, 38.5f));
        // consciousness
        assertEquals(-1, MediScoreChallenge.calcMediScore(2, -1, 17, 95, 37.1f));
        // respirationRange
        assertEquals(-1, MediScoreChallenge.calcMediScore(2, 0, -1, 95, 37.1f));
        // spO2
        assertEquals(-1, MediScoreChallenge.calcMediScore(2, 0, 17, -1, 37.1f));
    }
}