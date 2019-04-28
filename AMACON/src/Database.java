/*

This class is the implementation of Administrator methods.
1. inserting product/category
2. modifying the product
3. deleting the product/category
4. searching a product.

 */


import java.util.Vector;
import java.util.Scanner;

public class Database {
    private Vector<Category> Categories;
    private Vector<Product>myProducts;
    private Vector<SubCategory> subCategories;
    private Scanner scanner = new Scanner(System.in);

    public Database() {
// Constructor
        this.Categories = new Vector<>();
        this.myProducts = new Vector<>();
        this.subCategories = new Vector<>();
    }

    public void insert(String categoryName, String productName) throws Exception {

        String[] str = categoryName.split("[> ]+", 2);
        for (int i = 0; i < myProducts.size(); i++) {  // checking if the product already exists in the database
            if (productName.equals(myProducts.get(i).getName())) {
                throw new Exception("Item already exists!");
            }
        }

        System.out.println("enter the number of units to add?");
        int units = scanner.nextInt();
        System.out.println("set price for each unit?");
        int price = scanner.nextInt();
// If subcategory exists then insert the product in that subcategory
        int fCase = 0;
        for (int i = 0; i < Categories.size(); i++) {
            if (str[0].equals(Categories.get(i).getName())) {
                for (int j = 0; j < subCategories.size(); j++) {
                    if (str[1].equals(subCategories.get(j).getName())) {
                        Categories.get(i).addToSub(productName, units, price, str[0], str[1]);
                        myProducts.add(new Product(productName, units, price, str[0], str[1]));
                        fCase = 1;
                        break;
                    }
                }
                if (fCase == 0) {
// If the category exists but subcategory doesn't exists then create the subcategory and then insert
                    Categories.get(i).addSub(str[1]);
                    Categories.get(i).addToSub(productName, units, price, str[0], str[1]);
                    subCategories.add(new SubCategory(str[1]));
                    myProducts.add(new Product(productName, units, price, str[0], str[1]));
                    fCase = 1;
                    break;
                } else if (fCase == 1) {
                    break;
                }
            }
        }

        if (fCase == 0) {
// If the category doesn't exists then create category + subcategory then insert
            Category tCategory = new Category(str[0]);
            tCategory.addSub(str[1]);
            tCategory.addToSub(productName, units, price, str[0], str[1]);
            Categories.add(tCategory);
            subCategories.add(new SubCategory(str[1]));
            myProducts.add(new Product(productName, units, price, str[0], str[1]));
        }

    }

    public Product search(String productName) throws Exception {
        int flag = 0;
        for (int i = 0; i < myProducts.size(); i++) {
            if (productName.equals((myProducts.get(i)).getName())) { // checking if product exists!
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            throw new Exception("Item not found!");
        }

        for (int i = 0; i < Categories.size(); i++) {
            Product temp = Categories.get(i).findProductInCat(productName);
            if (temp != null && productName.equals(temp.getName())) {
                Categories.get(i).findProductInCat(productName).printProductPath(); // finding inside subcategory if exists in the category

                return Categories.get(i).findProductInCat(productName);
            }
        }
        return null;

    }

    public void delete(String path) throws Exception {
        String[] str = path.split("[ >]+");
        int size = str.length;
        String categoryName = str[0];
        String subCategoryName = str[1];
        if (size == 2) {
            int flag = 0;
            for (int i = 0; i < Categories.size(); i++) {
                if (categoryName.equals(Categories.get(i).getName())) {
                    SubCategory temp = Categories.get(i).findSub(subCategoryName);
                    if (temp == null) {
                        flag = 0;
                    } else {
                        Categories.get(i).removeSub(subCategoryName);  // removing subcategory
                        for (int j = subCategories.size() - 1; j >= 0; j--) {
                            if (subCategoryName.equals(subCategories.get(j).getName())) {
                                flag = 1;
                                subCategories.remove(j); // removing category
                                break;
                            }
                        }
                        for (int j = myProducts.size() - 1; j >= 0; j--) {
                            if (subCategoryName.equals(myProducts.get(j).getSubName())) {
                                myProducts.remove(j); // removing products.
                            }
                        }
                        break;
                    }

                }
            }
            if (flag == 0) {
                throw new Exception("Invalid path!");
            }
        } else if (size == 3) {
            String productName = str[2];
            int flag = 0;
            for (int i = 0; i < Categories.size(); i++) {
                if (categoryName.equals(Categories.get(i).getName())) {
                    flag = Categories.get(i).removeProductInCat(subCategoryName, productName);
                    break;
                }
            }
            if (flag == 1) {
                for (int j = myProducts.size() - 1; j >= 0; j--) {
                    //
                    if (productName.equals(myProducts.get(j).getName())) {
                        myProducts.remove(j);
                    }
                }
            }

            if (flag == 0) {
                throw new Exception("Invalid path!");
            }
        }
    }


/*
Selling interface :

if the funds are greater than or equal to the total purchase cast of the cart then checkout will be successful else
appropriate exception will arise.

 */

    public void sale(String productName, int quantity, int funds) throws Exception
    {
        Product product = search(productName);
        if(quantity > product.getUnits())
        {
            throw new Exception("Stock unavailable!");
        }
        if(funds < quantity*product.getPrice())
        {
            throw new Exception("Not enough funds available! Please Add sufficient funds");
        }
        product.update(product.getUnits() - quantity, product.getPrice());
    }

}