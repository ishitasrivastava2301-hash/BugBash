import java.util.*;

public class soliution {

    public static class Driver{
        int id;
        boolean isExperienced;
        int lateTrips;
        int totalTrips;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of drivers:");
        int n = sc.nextInt();
        Driver[] driver = new Driver[n];
        for(int i=0; i<n; i++){
            driver[i] = new Driver();
            System.out.println("Enter the driver id:");
            driver[i].id = sc.nextInt();
            System.out.println("Enter whether the driver is experienced or not?(true/false):");
            driver[i].isExperienced = sc.nextBoolean();
            System.out.println("Enter the no of late trips:");
            driver[i].lateTrips = sc.nextInt();
            System.out.println("Enter the no of total trips:");
            driver[i].totalTrips = sc.nextInt();
        }

        for(Driver d: driver){
            float percentage = ((float)d.lateTrips/d.totalTrips)*100;
            System.out.print("for driver having id"+d.id+": ");
            if(percentage > 50){
                if(d.isExperienced){
                    System.out.println("Discplinary action");
                }
                else{
                    System.out.println("Training session");
                }
            }
            else{
                System.out.println("No action needed");
            }
        }
        sc.close();
    }
}
