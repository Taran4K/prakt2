package com.example.prakt2.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min=2, max=30, message = "Размер данного поля должен быть в диапазоне от 2 до 30")
    private String mark, vladelec;
    public boolean Polom;
    @NotNull(message = "Поле не может быть пустым")
    private Integer probeg, age;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="pasport_id")
    private PTS pasport;

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
    public PTS getPasport() {
        return pasport;
    }

    public void setPasport(PTS pasport) {
        this.pasport = pasport;
    }

    public Cars(String mark, String vladelec, boolean polom, Integer age, Integer probeg, PTS pasport) {
        this.mark = mark;
        this.vladelec = vladelec;
        this.Polom = polom;
        this.age = age;
        this.probeg = probeg;
        this.pasport=pasport;
    }

}
