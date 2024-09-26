import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;

public class OwlPopulation {
    private String fileName;
    private Owl[] data;
    public int populateData() throws FileNotFoundException {
        File f = new File(fileName);
        Scanner scanner = new Scanner(f);

        int numLines = 0;
        while(scanner.hasNextLine()){
            numLines++;
            String s = scanner.nextLine();
        }
        scanner.close();

        data = new Owl[numLines];   //data is is allocated the exact amount of space it needs
        scanner = new Scanner(f);

        int i = 0;
        while(scanner.hasNextLine()) {
            String owls = scanner.nextLine();
            String[] owls1 = owls.split(",");
            int owlInt = Integer.parseInt(owls1[1]);
            double olwDouble = Double.parseDouble(owls1[2]);
            Owl owl1 = new Owl(owls1[0], owlInt, olwDouble);
            data[i] = owl1;
            i++;
            //TODO: Populate the data with owls constructed from the lines of the file given
        }
        return numLines;
    }

    public OwlPopulation(String fileName) throws FileNotFoundException {
            this.fileName = fileName;
            populateData();
    }

    public double averageAge(){
        int totalAge = 0;
        int i = 0;
        for(i = 0; i < popSize(); i++){
            int age = data[i].getAge();
            totalAge += age;
        }
        return ((double)totalAge)/data.length;
    }

    public Owl getYoungest(){
        int min = 0;
            for (int i = 0; i < data.length -1; i++){
                if (data[i].getAge() < data[min].getAge()) {
                   min = i;
                }
            }
        return data[min];
    }

    public Owl getHeaviest(){
        int heaviest = 0;
        for(int i = 0; i < data.length - 1; i++){
                if (data[i].getWeight() > data[heaviest].getAge()) {
                    heaviest = i;
                }
        }
        return data[heaviest];
    }


    /*public String toString(){
        System.out.println("The youngest owl is " + getYoungest().getName() + ", which is " + getYoungest().getAge());
        System.out.println("The heaviest owl is " + getHeaviest().getName() + ", which weighs " + getHeaviest().getWeight());
        System.out.println("The average age of the population is " + averageAge());

        return null;
    }*/

    public boolean containsOwl(Owl other){

        //TODO: method you can implement as a helper function to your merge method
        return false;
    }
	
    public void merge(OwlPopulation other){
        //TODO: a brief overview of what you can do to implement this method is given below:

    }

    public int popSize(){
        return data.length;
    }

    public String toString(){
        String owlString = "";
        for (int i = 0; i < data.length; i++){
            owlString = (data[i].getName() + " is " + data[i].getAge() + " years old, and weighs " + data[i].getWeight());
        }
        return owlString;
    }

    public static void main(String[] args) {
        OwlPopulation pop1 = null;
        try {

            //The following should run when you are complete. Feel free to comment out as you see fit while you work.
            pop1 = new OwlPopulation("owlPopulation1.csv");
            System.out.println(pop1);
            System.out.println(pop1.popSize());

            OwlPopulation pop2 = new OwlPopulation("owlPopulation2.csv");
            System.out.println(pop2);
            System.out.println(pop2.popSize());

            pop1.merge(pop2);
            System.out.println(pop1);
            System.out.println(pop1.popSize());
        } catch (FileNotFoundException f) {
            System.out.println("File not found.");
        }
        System.out.println(pop1);
    }
}
