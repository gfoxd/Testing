package TestingModuleProject;

public class Order {
    private String productName;
    private int id;
    private int quantity;
    private double unitPrice;

    public Order(String productName, int id, int quantity, double unitPrice) {
        this.productName = productName;
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        double totalPrice = quantity * unitPrice;
        return totalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}
