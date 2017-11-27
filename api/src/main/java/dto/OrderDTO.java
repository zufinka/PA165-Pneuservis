package dto;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Martin Zilak, 433372@mail.muni.cz
 */
public class OrderDTO {
    private Long id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private CustomerDTO customer;

    @NotNull
    private List<OrderItemDTO> orderItems;

    @NotNull
    public OrderDTO(LocalDateTime date, CustomerDTO customer, List<OrderItemDTO> orderItems) {
        this.date = date;
        this.customer = customer;
        this.orderItems = orderItems;
    }

    public OrderDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO)) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equal(id, orderDTO.id) &&
                Objects.equal(date, orderDTO.date) &&
                Objects.equal(customer, orderDTO.customer) &&
                Objects.equal(orderItems, orderDTO.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, date, customer, orderItems);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("date", date)
                .add("customer", customer)
                .add("orderItems", orderItems)
                .toString();
    }
}
