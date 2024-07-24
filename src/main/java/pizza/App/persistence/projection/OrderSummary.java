package pizza.App.persistence.projection;

import java.time.LocalDate;

public interface OrderSummary {

    Integer getIdOrder();
    String getCustomerName();
    LocalDate getOrderDate();
    Double getOrderTotal();
    String getPizzaNames();

}
