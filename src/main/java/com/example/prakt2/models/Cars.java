package com.example.prakt2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String mark, vladelec;
    public boolean Polom;
    private Integer age, probeg;

    public Cars() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getVladelec() {
        return vladelec;
    }

    public void setVladelec(String vladelec) {
        this.vladelec = vladelec;
    }

    public boolean isPolom() {
        return Polom;
    }

    public void setPolom(boolean polom) {
        Polom = polom;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getProbeg() {
        return probeg;
    }

    public void setProbeg(Integer probeg) {
        this.probeg = probeg;
    }

    public Cars(String mark, String vladelec, boolean polom, Integer age, Integer probeg) {
        this.mark = mark;
        this.vladelec = vladelec;
        Polom = polom;
        this.age = age;
        this.probeg = probeg;
    }
}
