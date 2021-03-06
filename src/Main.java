import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        VehicleType[] types = new VehicleType[]{new VehicleType("Bus", 1.2d),
                new VehicleType("Car", 1.0d), new VehicleType("Rink", 1.5d),
                new VehicleType("Tractor", 1.2d)};

        types[3].setCoefficient(1.3d);

        double maxCoef = types[0].getCoefficient();
        double sumCoef = 0.0d;
        double avrCoef;

        for (VehicleType type : types) {
            type.display();
            if (type.getCoefficient() > maxCoef) {
                maxCoef = type.getCoefficient();
            }
            sumCoef += type.getCoefficient();
        }

        avrCoef = sumCoef / types.length;
        System.out.println(maxCoef + " " + avrCoef);


        Vehicle[] vehicles = new Vehicle[]{
                new Vehicle(types[0], "Volkswagen Crafter", "5427 AX-7", 2022,
                        2015, 376000, Color.Blue,
                        new GasolineEngine(2, 8.1, 75)),
                new Vehicle(types[0], "Volkswagen Crafter", "6427 AA-7", 2500,
                        2014, 227010, Color.White,
                        new GasolineEngine(2.18, 8.5, 75)),
                new Vehicle(types[0], "Electric Bus E321", "6785 BA-7", 12080,
                        2019, 20451, Color.Green,
                        new ElectricalEngine(50, 150)),
                new Vehicle(types[1], "Golf 5", "8682 AX-7", 1200,
                        2006, 230451, Color.Gray,
                        new DieselEngine(1.6, 7.2, 55)),
                new Vehicle(types[1], "Tesla Model S 70D", "0001 AA-7", 2200,
                        2019, 10454, Color.White,
                        new ElectricalEngine(25, 70)),
                new Vehicle(types[2], "Hamm HD 12VV", null,
                        3000, 2016, 122, Color.Yellow,
                        new DieselEngine(3.2, 25, 20)),
                new Vehicle(types[3], "МТЗ Беларус-1025.4", "1145 AB-7", 1200,
                        2020, 109, Color.Red,
                        new DieselEngine(4.75, 20.1, 135))
        };

        System.out.println();
        Display.showArray(vehicles);
/*
        Vehicle copy;

        for (int i = 0; i < vehicles.length; i++) {
            for (int j = 0; j < vehicles.length - 1; j++) {
                if (vehicles[j].compareTo(vehicles[j + 1]) > 0) {
                    copy = vehicles[j + 1];
                    vehicles[j + 1] = vehicles[j];
                    vehicles[j] = copy;
                }
            }
        }

        System.out.println();

        Display.showArray(vehicles);

        Vehicle maxMileage = vehicles[0];
        Vehicle minMileage = vehicles[0];
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getMileage() > maxMileage.getMileage()) {
                maxMileage = vehicle;
            }
            if (vehicle.getMileage() < minMileage.getMileage()) {
                minMileage = vehicle;
            }
        }

        System.out.println("Car with max mileage: " + maxMileage);
        System.out.println("Car with min mileage: " + minMileage);
*/

        for (int i = 0; i < vehicles.length; i++) {
            for (int j = i; j < vehicles.length - 1; j++) {
                if (vehicles[j].equals(vehicles[j + 1])) {
                    System.out.println("Одинаковые машины:");
                    System.out.println(vehicles[j].toString());
                    System.out.println(vehicles[j + 1].toString());
                }
            }
        }

        Vehicle maxRange = vehicles[0];

        for (Vehicle vehicle : vehicles) {
            if (vehicle.engine.getMaxKilometers() > maxRange.engine.getMaxKilometers()) {
                maxRange = vehicle;
            }
        }
        System.out.println("машина которая проедет максимальное расстояние");
        System.out.println(maxRange.toString());

    }

    static class Display {
        public static void showArray(Vehicle[] vehicles) {
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle.toString());
            }
        }
    }
}







