package ru.homework.dao.entity;

import com.opencsv.bean.CsvBindByName;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Класс для хранения данных по сущности "Employees"
 */

@Entity
@Table(name = "customers")
public class Employees {

    @Column
    @Id
    @CsvBindByName
    private Integer id;

    @CsvBindByName
    private String name;

    @CsvBindByName
    private Integer roomNumber;

    @CsvBindByName
    private Integer salary;

    @CsvBindByName
    private String position;

    public Employees() {

    }

    public Employees(Integer id, String name, Integer roomNumber, Integer salary, String position) {
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
        StringBuilder builder = new StringBuilder();
        builder.append("{ id=").append(id).append(", Имя=")
                .append(name).append(", Номер комнаты=").append(roomNumber).append(", Зарплата=")
        .append(salary).append(", Должность=").append(position).append(" }");
        return builder.toString();
    }

}
