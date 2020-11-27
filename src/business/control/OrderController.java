package business.control;

import business.model.OrderInterface;
import infra.OrderPersintenceFactory;
import infra.OrderPersistence;
import util.InfraException;
import util.OrderNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderController implements Serializable {
    private static OrderController orderController;
    private List<OrderInterface> orders;
    private OrderPersistence orderPersistence;

    private OrderController(OrderPersistence orderPersistence) {
        this.orders = new ArrayList<OrderInterface>();
        this.orderPersistence = orderPersistence;
    }

    public static OrderController getInstance() {
        if(orderController == null) {
            orderController = new OrderController(OrderPersintenceFactory.getPersistence());
        }
        return orderController;
    }

    public void list() throws  InfraException {
        this.orders = this.orderPersistence.load();

        for(OrderInterface order : orders){
            System.out.println(order.getStatus());
        }
    }

    public OrderInterface getOrderByStatus(String status) throws OrderNotFoundException, InfraException {
        this.orders = this.orderPersistence.load();
        for (OrderInterface order : this.orders) {
            if (order.getStatus().equals(status)) {
                return order;
            }
        }
        throw new OrderNotFoundException();
    }

    public void add(OrderInterface toCreateOrder) throws InfraException {
        this.orders.add(toCreateOrder);
        this.orderPersistence.save(orders);
    }

    public void updateStatus(OrderInterface order, String status){
        order.setStatus(status);
    }
}
