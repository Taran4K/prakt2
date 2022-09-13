package com.example.prakt2.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.Collection;
import java.util.Set;

import static org.hibernate.engine.transaction.internal.jta.JtaStatusHelper.isActive;


@Entity
public class Students{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min=2, max=30, message = "Размер данного поля должен быть в диапазоне от 2 до 30")
    private String name, surname, otchestvo, student_group;
    @NotNull(message = "Поле не может быть пустым")
    private Integer age;

    @ManyToMany
    @JoinTable (name="student_university",
            joinColumns=@JoinColumn (name="student_id"),
            inverseJoinColumns=@JoinColumn(name="university_id"))
    private List<University> universities;

    public Students(String name, String surname, String otchestvo, String student_group, Integer age) {
        this.name = name;
        this.surname = surname;
        this.otchestvo = otchestvo;
        this.student_group = student_group;
        this.age = age;
    }
    public Students() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

    public String getStudent_group() {
        return student_group;
    }

    public void setStudent_group(String student_group) {
        this.student_group = student_group;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<University> getUniversities() {
        return universities;
    }

    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }
}
