package co.develhope.crud.entities;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private boolean isWorking;

    public Student() { }

    public Student(Long id, String name, String surname, boolean isWorking) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.isWorking = isWorking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

}
