import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Database dbMaster = new Database();
        Customer customer = new Customer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int amaconRevenue=0;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select your Role:");
            System.out.println("1. Login as Administrator");
            System.out.println("2. Login as Customer");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            int ex = 0;


            switch (choice) {
                case 1:

                    while (true) {
                        System.out.println("Select option:");
                        System.out.println("1. Insert product/category:");
                        System.out.println("2. Delete product/category:");
                        System.out.println("3. Search product:");
                        System.out.println("4. Modify product");
                        System.out.println("5. Exit as Administrator");

                        int choice1 = scanner.nextInt();
                        int exitOption=0;
                        switch(choice1){
                            case 1:
                                System.out.println("Category?");
                                String cName = bufferedReader.readLine();
                                System.out.println("Product?");
                                String pName = bufferedReader.readLine();
                                try {
                                    dbMaster.insert(cName, pName);
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                            case 2:
                                System.out.println("Enter path or product you want to delete?");
                                String path = bufferedReader.readLine();
                                try {
                                    dbMaster.delete(path);
                                } catch (Exception e) {
                                    System.out.println(e);

                                }
                                break;

                            case 3:
                                System.out.println("Item you want to search?");
                                String productName = bufferedReader.readLine();

                                try {
                                    Product product = dbMaster.search(productName);
                                    System.out.println("No. of units available: " + product.getUnits());
                                    System.out.println("Price of each unit: " + product.getPrice());
                                } catch (Exception e) {
                                    System.out.println(e);
                                }

                                break;

                            case 4:
                                System.out.println("Enter the name of the product?");
                                String ProductName = bufferedReader.readLine();
                                System.out.println("Updated units?");
                                int units = scanner.nextInt();
                                System.out.println("Updated price?");
                                int price = scanner.nextInt();
                                Product ref;
                                try {
                                    ref = dbMaster.search(ProductName);
                                    ref.update(units, price);
                                } catch (Exception e) {
                                    System.out.println(e);
                                }

                                break;

                            case 5:
                                //exiting
                                exitOption = choice1;
                                break;
                            default:
                                System.out.println("Please press appropriate option");
                                break;
                        }
                        if(exitOption==5){
                            break;
                        }
                    }

                    break;
                case 2:
                    while (true) {
                        // customer functionalities
                        System.out.println("Select option:");
                        System.out.println("1. Add funds:");
                        System.out.println("2. Add products to the cart:");
                        System.out.println("3. Check-out cart:");
                        System.out.println("4. Exit as Customer");

                        int choice2 = scanner.nextInt();
                        int exitcase = 0;
                        switch(choice2){
                            case 1:
                                System.out.println("How much funds would you like to add?");
                                int funds = scanner.nextInt();
                                customer.addFunds(funds);

                                break;
                            case 2:
                                System.out.println("Product you would like to add in your cart?");
                                String pName = bufferedReader.readLine();
                                System.out.println("Quantity?");
                                int quantity = scanner.nextInt();
                                Product temp;
                                try {
                                    temp = dbMaster.search(pName);
                                    customer.addProductToCart(temp, quantity);
                                } catch (Exception e) {
                                    System.out.println(e);
                                }

                                break;

                            case 3:
                                int total_revenue = customer.checkOutCart(dbMaster);
                                amaconRevenue = amaconRevenue+total_revenue;
                                break;

                            case 4:
                                exitcase = choice2;
                                break;

                            default:
                                System.out.println("You have pressed something else");
                                break;

                        }
                        if(exitcase == 4){
                            customer.eraseCartItems();
                            break;
                        }
                    }
                    break;

                case 3:
                    ex = choice;
                    break;
                 default:
                     System.out.println("You pressed something else");
                     break;
            }

if(ex==3){

    System.out.println("Total revenue generated by amacon: "+amaconRevenue);
    break;
}
        }


    }


}



