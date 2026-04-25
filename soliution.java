import java.util.*;

public class soliution {

    static final double LATE_THRESHOLD = 50.0;
    static final int PENALTY_EXPERIENCED = 5;
    static final int PENALTY_FRESHER = 10;
    static final int BONUS_RELIABLE = 3;

    public static class Driver {
        int id;
        String name;
        boolean isExperienced;
        int lateTrips;
        int totalTrips;
        double latePercent;
        int penaltyScore;
        String action;
    }

    // Computes late trip percentage for a driver
    public static double computeLatePercent(int lateTrips, int totalTrips) {
        return ((double) lateTrips / totalTrips) * 100; // correct
    }

    // Assigns penalty score based on performance and experience
    public static int computePenalty(Driver d) {
        if (d.latePercent > LATE_THRESHOLD) {
            if (d.isExperienced) {
                return PENALTY_EXPERIENCED;
            } else {
                return PENALTY_FRESHER;
            }
        } else {
            return -BONUS_RELIABLE; // reward for good performance
        }
    }

    // Determines action string
    public static String determineAction(Driver d) {
        if (d.latePercent > LATE_THRESHOLD) {
            if (d.isExperienced) {
                return "Disciplinary Action";
            } else {
                return "Training Session";
            }
        }
        return "No Action Needed";
    }

    // Sorts drivers by penalty score descending (highest penalty first)
    public static void sortByPenalty(Driver[] drivers) {
        Arrays.sort(drivers, (a, b) -> b.penaltyScore - a.penaltyScore);
    }

    // Computes fleet-wide average late percentage
    public static double fleetAverage(Driver[] drivers) {
        double sum = 0;
        for (Driver d : drivers) {
            sum += d.latePercent;
        }
        return sum / drivers.length;
    }

    // Returns count of drivers needing intervention
    public static int countFlagged(Driver[] drivers) {
        int count = 0;
        for (Driver d : drivers) {
            if (!d.action.equals("No Action Needed")) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Driver[] drivers = new Driver[n];

        for (int i = 0; i < n; i++) {
            drivers[i] = new Driver();
            drivers[i].id = sc.nextInt();
            drivers[i].name = sc.next();
            drivers[i].isExperienced = sc.nextBoolean();
            drivers[i].lateTrips = sc.nextInt();
            drivers[i].totalTrips = sc.nextInt();
        }

        // Process each driver
        for (Driver d : drivers) {
            d.latePercent = computeLatePercent(d.lateTrips, d.totalTrips);
            d.penaltyScore = computePenalty(d);
            d.action = determineAction(d);
        }

        // Sort drivers by penalty (worst first)
        sortByPenalty(drivers);

        // Print results
        System.out.println("--- Driver Performance Report ---");
        for (Driver d : drivers) {
            System.out.printf("ID: %d | Name: %-12s | Late%%: %5.1f%% | Penalty: %3d | Action: %s%n",
                    d.id, d.name, d.latePercent, d.penaltyScore, d.action);
        }

        System.out.println("---------------------------------");
        System.out.printf("Fleet Late Average : %.1f%%%n", fleetAverage(drivers));
        System.out.printf("Drivers Flagged    : %d%n", countFlagged(drivers));
        sc.close();
    
        sc.close();
    }
}
