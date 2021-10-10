package ru.job4j.hibernate.lazy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "lazycarmodels")
public class LazyCarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "lazycarbrand_id")
    private LazyCarBrand lazyCarBrand;

    public static LazyCarModel of(String name, LazyCarBrand lazyCarBrand) {
        LazyCarModel lazyCarModel = new LazyCarModel();
        lazyCarModel.name = name;
        lazyCarModel.lazyCarBrand = lazyCarBrand;
        return lazyCarModel;
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

    public LazyCarBrand getLazyCarBrand() {
        return lazyCarBrand;
    }

    public void setLazyCarBrand(LazyCarBrand lazyCarBrand) {
        this.lazyCarBrand = lazyCarBrand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LazyCarModel that = (LazyCarModel) o;
        return id == that.id && Objects.equals(name, that.name)
                && Objects.equals(lazyCarBrand, that.lazyCarBrand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lazyCarBrand);
    }

    @Override
    public String toString() {
        return "CarModel{ " + "id=" + id + ", name='" + name + "', lazyCarBrand=" + lazyCarBrand + " }";
    }
}
