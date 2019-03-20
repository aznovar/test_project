package ru.homework.dao.params;


/**
 * Данный класс содержит в себе основные запросы к бд
 */
public class SelectorsConst {

    public static final String INSERT_CUST =
            "INSERT INTO mydb.customers(Idcust,Name,Room_number, Salary,Position) VALUES(?,?,?,?,?)";

    public static final String SELECT_ALL =
            "SELECT * FROM mydb.customers";

    public static final String SELECT_BY_ID =
            "SELECT * FROM mydb.customers WHERE Idcust=?";

    public static final String SELECT_BY_NAME =
            "SELECT * FROM mydb.customers WHERE name=?";

    public static final String DELETE_CUST =
            "DELETE FROM customers WHERE Idcust=?";

    public static final String COUNT_CUST_IN_ROOM =
            "SELECT COUNT(Idcust)AS count FROM mydb.customers WHERE Room_number=?";

    public SelectorsConst() {

    }

}
