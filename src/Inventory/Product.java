package Inventory;

import Inventory.InventoryExceptions.InvalidProductPrice;
import Inventory.InventoryExceptions.InvalidSaleAmount;

public class Product {
    public Product(String name ,String brandName,ProductCategory category,ProductType type,String description
                   ,double price,double saleAmount) throws InvalidProductPrice, InvalidSaleAmount {
        this.ID = newID;
        setName(name);
        setDescription(description);
        setBrandName(brandName);
        this.category = category;
        this.type = type;
        setPrice(price);
        setSaleAmount(saleAmount);
        Product.newID++;
    }
    public String getName() {
        return name;
    }
    public double getCost() {
        return price - (saleAmount/100*price);
    }
    public double getPrice() {
        return price ;
    }
    public double getSaleAmount() {
        return saleAmount;
    }
    public ProductCategory getCategory() {
        return category;
    }
    public ProductType getType() {
        return type;
    }
    public String getBrandName() {
        return brandName;
    }
    public String getDescription() {
        return description;
    }

    public void setPrice(double price) throws InvalidProductPrice {
        if(price <= 0)
            throw new InvalidProductPrice("Price Can't be Less Than 0");
        this.price = price;
    }
    public void setSaleAmount(double saleAmount) throws InvalidSaleAmount {
        if(saleAmount < 0 || saleAmount > 100)
            throw  new InvalidSaleAmount("Sale Percentage Must be Between [0-100]");
        this.saleAmount = saleAmount;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public int getID() {
        return ID;
    }

    private int ID;
    private String name;
    private String brandName;
    private ProductCategory category;
    private String description;
    private double price;
    private double saleAmount;
    private ProductType type;
    private static int newID;
}
