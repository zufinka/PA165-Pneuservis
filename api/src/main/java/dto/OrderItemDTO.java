package dto;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Martin Zilak, 433372@mail.muni.cz
 */
public class OrderItemDTO {
    private Long id;

    private TireDTO tire;

    private ServiceDTO service;

    @NotNull
    @Min(0)
    private Long quantity;

    public OrderItemDTO(TireDTO tire, ServiceDTO service, Long quantity) {
        this.tire = tire;
        this.service = service;
        this.quantity = quantity;
    }

    public OrderItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TireDTO getTire() {
        return tire;
    }

    public void setTire(TireDTO tire) {
        this.tire = tire;
    }

    public ServiceDTO getService() {
        return service;
    }

    public void setService(ServiceDTO service) {
        this.service = service;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemDTO)) return false;
        OrderItemDTO that = (OrderItemDTO) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(tire, that.tire) &&
                Objects.equal(service, that.service) &&
                Objects.equal(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, tire, service, quantity);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("tire", tire)
                .add("service", service)
                .add("quantity", quantity)
                .toString();
    }
}
