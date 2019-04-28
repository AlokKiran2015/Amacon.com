import java.util.Vector;

public class Category {
    private String categoryName;
    private Vector<SubCategory> subCategories;

    public Category(String categoryName) {
        this.categoryName = categoryName;
        this.subCategories = new Vector<>();
    }

    public void addSub(String subCategoryName)  // Method for adding subcategory
    {
        SubCategory subCategory = new SubCategory(subCategoryName);
        subCategories.add(subCategory);
    }
    public String getName()
    {
        return this.categoryName;
    }
    public void addToSub(String pName, int units, int price, String cName, String sName)
    {
        for(int i = 0; i< subCategories.size(); i++)
        {

            if(sName.equals(subCategories.get(i).getName()))
            {
                subCategories.get(i).addProduct(pName, units, price, cName, sName);
                break;
            }
        }
    }
    public Product findProductInCat(String productName) // method to search inside subcategory
    {
        for(int i = 0; i < subCategories.size(); i++)
        {
            Product temp = subCategories.get(i).findProduct(productName);
            if(temp != null && productName.equals(temp.getName()))
            {
                return subCategories.get(i).findProduct(productName);
            }
        }
        return null;
    }
    public SubCategory findSub(String sName)
    {
        for(int i = 0; i < subCategories.size(); i++)
        {
            SubCategory temp = subCategories.get(i);
            if(temp !=  null && sName.equals(temp.getName()))
            {
                return temp;
            }
        }
        return null;
    }
    public void removeSub(String sName)
    {
        for(int i = subCategories.size()-1; i >=0 ; i--)
        {
            SubCategory temp = subCategories.get(i);
            if(temp !=  null && sName.equals(temp.getName()))
            {

                subCategories.remove(i);
            }
        }

    }
    public int removeProductInCat(String subCategoryName, String productName)
    {
        for(int i = 0; i<subCategories.size(); i++)
        {
            if(subCategoryName.equals(subCategories.get(i).getName()))
            {
                if(subCategories.get(i).removeProduct(productName) == 1)
                {
                    return 1;
                }
            }
        }
        return 0;
    }

}