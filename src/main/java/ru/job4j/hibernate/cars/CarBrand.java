package ru.job4j.hibernate.cars;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "carbrands")
public class CarBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarModel> carModels = new ArrayList<>();

    public static CarBrand of(String name) {
        CarBrand carBrand = new CarBrand();
        carBrand.name = name;
        return carBrand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCarModel(CarModel carModel) {
        this.carModels.add(carModel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarBrand carBrand = (CarBrand) o;
        return id == carBrand.id && Objects.equals(name, carBrand.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
