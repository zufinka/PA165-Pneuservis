package cz.muni.fi.pa165.pneuservis.backend.entity;

import cz.muni.fi.pa165.pneuservis.backend.enums.VehicleTypeEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author Zuzana Žufanová, zufinka@mail.muni.cz
 */

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    @Size (min = 9)
    private Integer phoneNumber;

    @OneToMany
    private Set<VehicleTypeEnum> typesOfCar;

    @OneToMany
    private Set<Order> orders;

    public Customer(){}

    public Customer(String name, String address, Integer phoneNumber){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<VehicleTypeEnum> getTypesOfCar() {
        return typesOfCar;
    }

    public void setTypeOfCar(VehicleTypeEnum typeOfCar) {
        typesOfCar.add(typeOfCar);
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrder(Order order) {
        orders.add(order);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        if (!getName().equals(customer.getName())) return false;
        if (!getAddress().equals(customer.getAddress())) return false;
        return getPhoneNumber().equals(customer.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getPhoneNumber().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", typesOfCar=" + typesOfCar +
                '}';
    }
}
