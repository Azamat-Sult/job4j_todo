package ru.job4j.hibernate.cars2;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "engines")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    private int serialNum;
    @OneToOne(
            mappedBy = "engine",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Car car;

    public static Engine of(String model, int serialNum) {
        Engine engine = new Engine();
        engine.model = model;
        engine.serialNum = serialNum;
        return engine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Engine engine = (Engine) o;
        return id == engine.id && serialNum == engine.serialNum
                && Objects.equals(model, engine.model)
                && Objects.equals(car, engine.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, serialNum, car);
    }

    @Override
    public String toString() {
        return "Engine { " + "id=" + id + ", model='" + model + "', serialNum=" + serialNum
                + ", Car=" + car + " }";
    }
}
