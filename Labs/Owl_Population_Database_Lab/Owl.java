public class Owl {
    private String name;
    private int age;
    private double weight;

    public Owl(String OwlName, int OwlAge, double OwlWeight) {
        name = OwlName;
        age = OwlAge;
        weight = OwlWeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String OwlName) {
        name = OwlName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int OwlAge) {
        age = OwlAge;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double OwlWeight) {
        weight = OwlWeight;
    }

    public boolean equals(Owl other) {
        if (!(name.equals(other.name))) {
            return false;
        }
        if (age != other.age) {
            return false;
        }
        if (weight != other.weight)
            return false;
        return true;
    }


    public static void main(String[] args) {
        Owl owl1 = new Owl("owl1", 5, 12.0);
        Owl owl2 = new Owl("owl2", 5, 12.0);
        Owl owl3 = new Owl("owl1", 5, 12.0);
        System.out.println(owl1.equals(owl2));
        System.out.println(owl1.equals(owl3));

    }

}