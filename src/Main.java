import java.io.IOException;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        VehicleCollection vc = new VehicleCollection("types", "vehicles", "rents");
        MechanicService mechanicService = new MechanicService();

        for (Vehicle v : vc.VCollection) {
            if (mechanicService.detectAndRepair(v))
                System.out.println(v);
        }

        for (Vehicle v : vc.VCollection) {
            mechanicService.detectBreaking(v);
        }

        int max = mechanicService.findMost();
        System.out.println("Id max = " + max);

        for (Vehicle v : vc.VCollection) {
            if (v.getId() == max){
                System.out.println(v);
                break;
            }
        }
    }

    static class Display {
        public static void showArray(Vehicle[] vehicles) {
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle.toString());
            }
        }
    }
}







