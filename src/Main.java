import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ParkingManager parkingManager = new ParkingManager();
        CustomerManager customerManager = new CustomerManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Parking Lot Management System");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Vehicle Exit");
            System.out.println("3. Add Customer");
            System.out.println("4. Get Customer Info");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear buffer

            switch (choice) {
                case 1:
                    System.out.println("Enter license plate:");
                    String licensePlate = scanner.nextLine();
                    parkingManager.addVehicle(licensePlate);
                    break;
                case 2:
                    System.out.println("Enter license plate:");
                    licensePlate = scanner.nextLine();
                    parkingManager.vehicleExit(licensePlate);
                    break;
                case 3:
                    System.out.println("Enter customer name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter phone:");
                    String phone = scanner.nextLine();
                    System.out.println("Enter license plate:");
                    licensePlate = scanner.nextLine();
                    customerManager.addCustomer(name, phone, licensePlate);
                    break;
                case 4:
                    System.out.println("Enter license plate:");
                    licensePlate = scanner.nextLine();
                    customerManager.getCustomerInfo(licensePlate);
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
