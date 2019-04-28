/*
This class describe the set operations to be performed by the customer.

A customer can add funds/balance to his/her account
A customer can add/purchase items from Amacon and then if want then will proceed towards checkout.

Before adding a product to the cart,that particular product will be searched first if not found then appropriate exception will
arise.

 */

import java.util.Vector;

public class Customer {
    private int funds;
    private Vector<Product> cart;
    private Vector<Integer> quantity;

    public Customer() {
        funds = 0;
        cart = new Vector<>();
        quantity = new Vector<>();
    }

    public void addFunds(int amountToAdd) {
        this.funds = this.funds + amountToAdd;
    }

    public void addProductToCart(Product product, int quantityToAdd) {
        cart.add(product);
        quantity.add(quantityToAdd);
    }

    public int checkOutCart(Database dbMaster) {
        int f = 0;
        int totalCost = 0;
        for (int i = cart.size() - 1; i >= 0; i--) {
            totalCost = totalCost + cart.get(i).getPrice() * quantity.get(i);
            try {
                dbMaster.sale(cart.get(i).getName(), quantity.get(i), funds);
            } catch (Exception e) {
                System.out.println(e);
                f = 1;
                break;
            }

        }
        if (f == 0) {
            this.funds = this.funds - totalCost;
            System.out.println("Checked out. Total purchasing cost: " + totalCost);
            cart.clear();
            quantity.clear();
            System.out.println("available funds : "+this.funds);
            return totalCost;
        }
        return 0;
    }


    public void eraseCartItems(){
        cart.clear();
        quantity.clear();
    }
}