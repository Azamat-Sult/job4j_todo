package ru.job4j.hibernate.cars2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "engine_id")
    private Engine engine;
    @ManyToMany
    @JoinTable (name = "history_owner",
            joinColumns = @JoinColumn (name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id"))
    private List<Driver> drivers  = new ArrayList<>();

    public void addDriver(Driver driver) {
        this.drivers.add(driver);
    }

    public static Car of(String model) {
        Car car = new Car();
        car.model = model;
        return car;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id && Objects.equals(model, car.model)
                && Objects.equals(engine, car.engine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, engine);
    }

    @Override
    public String toString() {
        return "Car { " + "id=" + id + ", model='" + model + "', engine=" + engine + " }";
    }
}
