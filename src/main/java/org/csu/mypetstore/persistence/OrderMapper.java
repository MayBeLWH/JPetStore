package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    void insertOrder(Order order);

    void insertOrderStatus(Order order);

    List<Order> getOrdersByUsername(String username);

    Order getOrder(int orderId);

    List<Order> getAllOrders();

    void updateOrder(Order order);

    void updateOrderStatus(Order order);
}
