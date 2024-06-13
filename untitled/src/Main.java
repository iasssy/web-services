import model.Car;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This application tries to read some data from a file name carData and does some actions
 * such as add a car, delete a car ....
 * */
public class Main {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Car> cars = new ArrayList<>();
    static final String DATA_FILE = "carData.txt";
    static final String NO_CAR = "There is no car";

    public static void main(String[] args) {
        // read data from File

        try {
            readDataFromFile();

            while (true) {
                System.out.println("\nEnter the options: \n0. Exit \n1. Add a car \n2. List all the cars \n" +
                        "3. Modify cars \n4. Delete a car \n5. Statistics of cars");
                int userInput = input.nextInt();
                switch (userInput) {
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
                    case 4:
                        deleteCar();
                        break;
                    case 5:
                        statisticsCar();
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }

            }

        } catch (Exception exc) {
            System.out.println("An exception happened " + exc.getMessage());
        }
    }

    private static void statisticsCar() {
        if (cars.isEmpty()) {
            System.out.println(NO_CAR);
            return;
        }
        printOldestCar();
        printBiggestEngineCar();
        printAverageProductionYear();
    }

    private static void printOldestCar() {
        Car oldestCar = cars.get(0);
        for (Car car : cars) {
            if (car.getYear() < oldestCar.getYear()) {
                oldestCar = car;
            }
        }
        System.out.println("\nThe oldest car: " + oldestCar);
    }

    private static void printBiggestEngineCar() {
        Car biggestEngineCar = cars.get(0);
        for (Car car : cars) {
            if (car.getEngineSize() > biggestEngineCar.getEngineSize()) {
                biggestEngineCar = car;
            }
        }
        System.out.println("\nThe biggest engine size " + biggestEngineCar.getEngineSize() + " is in " + biggestEngineCar);
    }

    private static void printAverageProductionYear() {
        int totalProductionYears = 0;
        for (Car car : cars) {
            totalProductionYears += car.getYear();
        }
        int averageProductionYear = (int) Math.round((double) totalProductionYears / cars.size());
        System.out.println("\nThe average production year: " + averageProductionYear);
    }


    private static void deleteCar() {
        if (cars.isEmpty()) {
            System.out.println(NO_CAR);
            return;
        }
        System.out.println("Enter the car # to delete");
        int carNum = input.nextInt();
        input.nextLine();

        Car carToBeDeleted = cars.get(carNum - 1);


        if (!cars.contains(carToBeDeleted)){ // index starts from 0
            System.out.println("The car does not exist");
        } else {
            cars.remove(carToBeDeleted);
            System.out.println("The car #"+ carNum + " was deleted.");
        }
        }



    private static void readDataFromFile() {
        try(Scanner reader = new Scanner(new File(DATA_FILE))){
            while(reader.hasNextLine()){
                String[] dataLine = reader.nextLine().split(";");

                String make = dataLine[0];
                int productionYear = Integer.parseInt(dataLine[1]);
                double engineSize = Double.parseDouble(dataLine[2]);

                cars.add(new Car(make, productionYear, engineSize));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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
            catch(Exception exc) {
                throw new RuntimeException(exc);
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
        input.nextLine();

        Car carToBeModified = cars.get(carNum - 1);

        if (!cars.contains(carToBeModified)){ // index starts from 0
            System.out.println("The car does not exist");
        } else {
            System.out.println("Enter the make");
            String make = input.nextLine();
            System.out.println("Enter production year");
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