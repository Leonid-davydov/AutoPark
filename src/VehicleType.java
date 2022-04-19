public class VehicleType {
    private String name;
    private double coefficient;

    VehicleType() {}

    VehicleType(String name, double coefficient) {
        this.name = name;
        this.coefficient = coefficient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public void display() {
        System.out.println("typeName = " + name);
        System.out.println("taxCoefficient = " + coefficient);
    }

    public String getString() {
        return (name + ", \"" + coefficient + "\"");
    }
}
