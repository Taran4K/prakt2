package com.example.prakt2.models;

import javax.persistence.*;

@Entity
public class PTS {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String Vin;
    private String number;
    @OneToOne(optional = true, mappedBy = "pasport")
    private Cars car;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVin() {
        return Vin;
    }

    public void setVin(String Vin) {
        this.Vin = Vin;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Cars getCar() {
        return car;
    }

    public void setCar(Cars car) {
        this.car = car;
    }
}