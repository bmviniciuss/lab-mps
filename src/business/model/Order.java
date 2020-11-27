package business.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {

    private User user;
    private ArrayList<String> list;
    private String status;
    private Date orderDate;
    private String address;

    public Order(ArrayList<String> list){
        this.list = list;
        this.status = "open";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public Date getOrderDate(){ return orderDate; }

    public void setOrderDate(Date orderDate){ this.orderDate = orderDate; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
