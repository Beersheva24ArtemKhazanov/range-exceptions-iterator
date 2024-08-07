package telran.range;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BrokenFloorTest {

    private int getMinimalBrokenFloor(BallBrokenFloor bbf) {
        int downFloor = 0;
        int upFloor = bbf.getMinBrokenFloor();
        int mid = (downFloor + upFloor) / 2;
        int res = -1;
        while (downFloor <= upFloor) {
            try {
                bbf.checkFloor(mid);
                downFloor = mid + 1;
            } catch (Exception e) {
                upFloor = mid - 1;
                res = mid;
            }
            mid = (downFloor + upFloor) / 2;
        }
        return res;
    }

    @Test
    void minimalBrokenFloorTest() {
        int[] floors = { 200, 17, 1001, 2000 };
        for (int i = 0; i < floors.length; i++) {
            BallBrokenFloor bbf = new BallBrokenFloor(floors[i]);
            assertEquals(bbf.getMinBrokenFloor(), getMinimalBrokenFloor(bbf));
        }
    }
}
