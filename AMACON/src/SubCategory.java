import java.util.Vector;


public class SubCategory {
    private String subCategoryName;
    private Vector<Product> products;

    public SubCategory(String subCategoryName) {
        this.subCategoryName = subCategoryName;
        this.products = new Vector<>();
    }
    public void addProduct(String productName, int units, int price, String categoryName, String subCategoryName)
    {
        Product product = new Product(productName, units, price, categoryName, subCategoryName);
        products.add(product);
    }

    public String getName()
    {
        return this.subCategoryName;
    }
    public Product findProduct(String productName)
    {
        for(int i = 0; i < products.size(); i++)
        {
            Product temp = products.get(i);
            if(temp != null && productName.equals(products.get(i).getName()))
            {

                return products.get(i);
            }
        }
        return null;
    }
    public int removeProduct(String productName)
    {
        for(int i = products.size()-1; i >= 0; i--)
        {
            Product temp = products.get(i);
            if(temp != null && productName.equals(products.get(i).getName()))
            {
                products.remove(i);
                return 1;
            }
        }
        return 0;
    }

}