package business.control;

import business.model.IUser;
import business.model.Order;
import infra.OrderPersistence;
import util.InfraException;
import util.OrderNotFoundException;
import util.UserNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class OrderController implements Serializable {
    private ArrayList<Order> orders;
    private OrderPersistence orderPersistence;

    public OrderController(OrderPersistence orderPersistence) {
        orders = new ArrayList<>();
        this.orderPersistence = orderPersistence;
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
