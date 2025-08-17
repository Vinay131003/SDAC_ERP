package utils; // Adjust the package name as needed

import Pojo.Orders;

public class OrderUtils {
    public static String getProgressWidth(Orders order) {
        int width = 0;
        if (order.isDelivered()) {
            width = 100; // Fully delivered
        } else if (order.isOutForDelivery()) {
            width = 75; // Out for delivery
        } else if (order.isShipped()) {
            width = 50; // Shipped
        } else if (order.isOrderPlaced()) {
            width = 25; // Order placed
        }
        return width + "%";
    }
}
