package com.studentmanagement;

import javax.persistence.*;

@Entity
@Table(name = "StudentsApi")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name= "gender")
    private String gender;

    @Column(name = "course")
    private String course;

    @Column(name = "paid")
    private boolean paid;


    public Students() {
        
    }


    public Students(String firstname, String lastname, String gender, String course, boolean paid) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.course = course;
        this.paid = paid;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCourse() {
        return this.course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public boolean isPaid() {
        return this.paid;
    }

    public boolean getPaid() {
        return this.paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", gender='" + getGender() + "'" +
            ", course='" + getCourse() + "'" +
            ", paid='" + isPaid() + "'" +
            "}";
    }
    


} 