package business.control;

import business.model.Order;
import infra.OrderPersintenceFactory;
import infra.OrderPersistence;
import util.InfraException;
import util.OrderNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderController implements Serializable {
    private static OrderController orderController;
    private ArrayList<Order> orders;
    private OrderPersistence orderPersistence;

    private OrderController(OrderPersistence orderPersistence) {
        orders = new ArrayList<>();
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

        //TreeSet<Order> sortedOrders = new TreeSet<Order>(comparator);
        //sortedOrders.addAll(this.orders);

        for(Order order : orders){
            System.out.println(order.getStatus());
        }
    }

    public Order getOrderByStatus(String status) throws OrderNotFoundException, InfraException {
        this.orders = this.orderPersistence.load();
        for (Order order : this.orders) {
            if (order.getStatus().equals(status)) {
                return order;
            }
        }
        throw new OrderNotFoundException();
    }

    public void add(Order toCreateOrder) throws InfraException {
        this.orders.add(toCreateOrder);
        this.orderPersistence.save(orders);
    }

    public void updateStatus(Order order, String status){
        order.setStatus(status);
    }
}
