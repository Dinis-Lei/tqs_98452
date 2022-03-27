package tqs.carservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    private String maker;

    private String model;

    public Car(){}

    public Car(String maker, String model) {
        this.maker = maker;
        this.model = model;
    }

    public Car(Long carId, String maker, String model) {
        this.carId = carId;
        this.maker = maker;
        this.model = model;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car [carId=" + carId + ", maker=" + maker + ", model=" + model + "]";
    }

    
}
