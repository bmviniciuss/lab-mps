package business.model;

import java.io.Serializable;
import java.util.List;

public interface OrderInterface extends Serializable {
    public IUser getUser();
    public List<ItemInterface> getItemsList() ;
    public String getStatus();
    public void setStatus(String status);
    public int getId();
}
