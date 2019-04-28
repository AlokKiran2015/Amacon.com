
/*

This class implements the methods for describing a product and the operations like update , printing product path.


 */

public class Product {
    private String productName;
    private int units;
    private int price;
    private String productCategoryName;
    private String productSubCategoryName;

    public Product(String productName, int units, int price, String productCategoryName, String productSubCategoryName)
    {
        // Constructor
        this.productName = productName;
        this.units = units;
        this.price = price;
        this.productCategoryName = productCategoryName;
        this.productSubCategoryName = productSubCategoryName;

    }
    public void update(int units, int price)
    {
        this.units = units;
        this.price = price;
    }
    public String getName()
    {

        return this.productName;
    }
    public int getUnits()
    {

        return this.units;
    }
    public int getPrice()
    {
        return this.price;
    }
    public String getSubName()
    {

        return this.productSubCategoryName;
    }
    public void printProductPath()
    {
        System.out.println("Product Path:" + productCategoryName + " > " + productSubCategoryName + " > " + this.productName);
    }
}