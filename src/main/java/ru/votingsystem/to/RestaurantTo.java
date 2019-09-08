package ru.votingsystem.to;

public class RestaurantTo extends BaseTo {

    private String name;

    private String address;

    private Integer amount;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String name, String address, Integer amount) {
        super(id);
        this.name = name;
        this.address = address;
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
