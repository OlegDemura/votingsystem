package ru.votingsystem.to;

public class RestaurantTo {
    private final Integer id;

    private final String name;

    private final String address;

    private final boolean voted;

    public RestaurantTo(Integer id, String name, String address, boolean voted) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.voted = voted;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public boolean isVoted() {
        return voted;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", voted=" + voted +
                '}';
    }
}
