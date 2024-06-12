import model.Car;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This application....
 * */
public class Main {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Car> cars = new ArrayList();
    static final String DATA_FILE = "carData.txt";
    static final String NO_CAR = "There is no car";

    public static void main(String[] args) {
        // read data from File



        while(true) {
            System.out.println("\nEnter the options: \n0. Exit \n1. Add a car \n2. List all the cars \n3. Modify cars");
            int userInput = input.nextInt();
            switch (userInput){
                case 0:
                    saveToFile();
                    return;
                case 1:
                    addCar();
                    break;
                case 2:
                    listCars();
                    break;
                case 3:
                    modifyCars();
                    break;
            }

//            switch (userInput) {
//                case 0 -> {
//                    saveToFile();
//                    return;
//                }
//                case 1 -> addCar();
//                case 2 -> listCars();
//                case 3 -> modifyCars();
//            }
        }
    }

    private static void saveToFile() {
        if(!cars.isEmpty()){
            try(PrintWriter writer = new PrintWriter(DATA_FILE)){
                // the code
                for (Car car: cars){
                    writer.println(car.toDataString());
                }
            }
            catch(Exception exc){
            }
        }
    }

    private static void modifyCars() {
        if (cars.isEmpty()) {
            System.out.println(NO_CAR);
            return;
        }
        System.out.println("Enter the car # to modify");
        int carNum = input.nextInt();
        Car carToBeModified = cars.get(carNum - 1);

        if (!cars.contains(carToBeModified)){ // index starts from 0
            System.out.println("The car is not exists");
        } else {
            System.out.println("Enter the make");
            input.nextLine();
            String make = input.nextLine();
            System.out.println("Enter production");
            int year = input.nextInt();
            input.nextLine();
            System.out.println("Enter the engine size");
            double engineSize = input.nextDouble();
            input.nextLine();

            carToBeModified.setMake(make);
            carToBeModified.setYear(year);
            carToBeModified.setEngineSize(engineSize);
        }
    }


    private static void listCars() {
        if (cars.isEmpty()){
            System.out.println(NO_CAR);
            return;
        }
        for (int i=0; i< cars.size(); i++){
            System.out.printf("#%d %s %n", i+1, cars.get(i));
        }
    }

    private static void addCar() {
        System.out.println("Enter the make");
        input.nextLine();
        String make = input.nextLine();
        System.out.println("Enter production");
        int year = input.nextInt();
        input.nextLine();
        System.out.println("Enter the engine size");
        double engineSize = input.nextDouble();
        Car car = new Car(make, year, engineSize);
        cars.add(car);

    }
}