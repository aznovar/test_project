package ru.homework.dao.entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Класс для хранения данных по сущности "Customers"
 */

@Entity
@Table(name = "customers")
public class Customers {

    @Column
    @Id
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

    @Getter
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

    @Setter
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

    @Override
    public String toString() {
        return "Сотрудник:" +
                " id=" + id + '\n' +
                " Имя='" + name + '\n' +
                " Номер Комнаты=" + roomNumber + '\n' +
                " Зарплата=" + salary + '\n' +
                " Должность='" + position + '\n';
    }
}
