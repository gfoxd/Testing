import TestingModuleProject.Order;

import TestingModuleProject.OrderRepository;
import TestingModuleProject.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private OrderRepository orderRepository;
    private OrderService orderService;

    private Order createOrder() {
        return new Order("Phone", 54321, 3, 100);
    }

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
    }

    @Test
    void testProcessOrderSuccess() {

//        Arrange
        Order order = createOrder();

        when(orderRepository.saveOrder(order)).thenReturn(1);

//      Act
        String result = orderService.processOrder(order);

//      Assert
        assertEquals("Order processed successfully", result);

//        verify
        verify(orderRepository, times(1)).saveOrder(order);
    }

    @Test
    void testProcessOrderFail() {

//        Arrange
        Order order = createOrder();

        when(orderRepository.saveOrder(order)).thenThrow(new RuntimeException("Order processing failed"));

//      Act
        Exception exception = assertThrows(RuntimeException.class, () -> {
           orderService.processOrder(order);
        });

//      Assert
        assertEquals("Order processing failed", exception.getMessage());

//        verify
        verify(orderRepository, times(1)).saveOrder(order);
    }

    @Test
    void testCalculateTotalSuccess() {

//        Arrange
        int id = 54321;
        Order order = createOrder();
        when(orderRepository.getOrderById(id)).thenReturn(Optional.of(order));

//        Act
        double calculateResult = orderService.calculateTotal(id);

//      Assert
        assertEquals(300.0, calculateResult);

//        verify
        verify(orderRepository, times(1)).getOrderById(id);
    }

    @Test
    void testCalculateTotalException() {

//        Arrange
        int id = 54321;
        when(orderRepository.getOrderById(id)).thenReturn(Optional.empty());

//        Act
        double calculateResult = orderService.calculateTotal(id);

//      Assert
        assertEquals(0.0, calculateResult);

//        verify
        verify(orderRepository, times(1)).getOrderById(id);
    }

    @Test
    void testCalculateTotalWithZero() {

//        Arrange
        int id = 54321;
        Order order = new Order("Headphones", 54321, 0, 100.0);
        when(orderRepository.getOrderById(id)).thenReturn(Optional.of(order));

//        Act
        double calculateResult = orderService.calculateTotal(id);

//      Assert
        assertEquals(0.0, calculateResult);

//        verify
        verify(orderRepository, times(1)).getOrderById(id);
    }

}
