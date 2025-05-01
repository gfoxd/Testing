package TestingModuleProject;

import java.util.Optional;

public interface OrderRepository {

    /**
     * @return id сохраненного заказа
     * @throw RuntimeException
     */
    int saveOrder(Order order);

    /**
     * @return Optional Order
     */
    Optional<Order> getOrderById(int id);

}
