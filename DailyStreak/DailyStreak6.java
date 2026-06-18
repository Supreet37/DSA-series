
/* 

1344. Angle Between Hands of a Clock

Algorithm:
 1. Convert time into fractional hours:
       n = hour + minutes/60.0

 2. Find relative position of the two hands:
       diff = (11 * n) % 12

 3. Choose the smaller distance on the clock:
       diff = min(diff, 12 - diff)

 4. Convert clock units to degrees:
       answer = diff * 30

 Time Complexity: O(1)
 Space Complexity: O(1)
*/


public class DailyStreak6 {

    public double angleClock(int hour, int minutes) {
        double n = hour + minutes / 60.0;
        double diff = (11.0 * n) % 12.0;
        return Math.min(diff, 12.0 - diff) * 30.0;
    }

    public static void main(String[] args) {
        DailyStreak6 obj = new DailyStreak6();

        int hour = 3;
        int minutes = 30;

        double angle = obj.angleClock(hour, minutes);

        System.out.println("Angle between clock hands: " + angle);
    }
}