package Pojo;

import java.sql.Timestamp;

public class Orders {
    private int orderID;
    private int productID;
    private String consumerPortID;
    private int quantity;
    private Timestamp orderDate;
    private boolean orderPlaced;
    private boolean shipped;
    private boolean outForDelivery;
    private boolean delivered;
    private String ProductName;
    public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	// Default constructor
    public Orders() {}

    // Constructor with all parameters
    public Orders(int orderID, int productID, String consumerPortID, int quantity, Timestamp orderDate,
                  boolean orderPlaced, boolean shipped, boolean outForDelivery, boolean delivered) {
        this.orderID = orderID;
        this.productID = productID;
        this.consumerPortID = consumerPortID;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.orderPlaced = orderPlaced;
        this.shipped = shipped;
        this.outForDelivery = outForDelivery;
        this.delivered = delivered;
    }

    // Getters and Setters for all fields
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getConsumerPortID() {
        return consumerPortID;
    }

    public void setConsumerPortID(String consumerPortID) {
        this.consumerPortID = consumerPortID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isOrderPlaced() {
        return orderPlaced;
    }

    public void setOrderPlaced(boolean orderPlaced) {
        this.orderPlaced = orderPlaced;
    }

    public boolean isShipped() {
        return shipped;
    }

    public void setShipped(boolean shipped) {
        this.shipped = shipped;
    }

    public boolean isOutForDelivery() {
        return outForDelivery;
    }

    public void setOutForDelivery(boolean outForDelivery) {
        this.outForDelivery = outForDelivery;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    // Optional: Override toString() for easier debugging
    @Override
    public String toString() {
        return "Orders{" +
                "orderID=" + orderID +
                ", productID=" + productID +
                ", consumerPortID='" + consumerPortID + '\'' +
                ", quantity=" + quantity +
                ", orderDate=" + orderDate +
                ", orderPlaced=" + orderPlaced +
                ", shipped=" + shipped +
                ", outForDelivery=" + outForDelivery +
                ", delivered=" + delivered +
                '}';
    }
}
