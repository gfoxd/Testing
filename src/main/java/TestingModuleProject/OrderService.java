package TestingModuleProject;

import java.util.Optional;

public class OrderService {
OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String processOrder(Order order){
        int orderId = orderRepository.saveOrder(order);
        if (orderId > 0) {
            return "Order processed successfully";
        } else {
            return "Order processing failed";
        }
    }

    public double calculateTotal(int id) {
        Optional<Order> order = orderRepository.getOrderById(id);
        if (order.isPresent()) {
            return order.get().getTotalPrice();
        } else {
            return 0.0;
        }
    }

}
