package ru.homework.dao.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Класс для хранения данных по сущности "Customers"
 */

@Entity
@Table(name = "customers")
public class Customers {

    private Integer id;

    private String name;

    private Integer roomNumber;

    private Integer salary;

    private String position;

    public Customers() {

    }

    public Customers(Integer id, String name, Integer roomNumber, Integer salary, String position) {
        this.id = id;
        this.name = name;
        this.roomNumber = roomNumber;
        this.salary = salary;
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getPosition() {
        return position;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
