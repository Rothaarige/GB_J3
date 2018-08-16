package Lesson_2;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static Connection connection;
    public static Statement statement;
    public static PreparedStatement preparedStatement;


    public static void main(String[] args) {
        //createTable();
        //dropTable();
        //deleteTable();
        //addProduct();
        //showPrice("товар12");
        //changePrice("товар12", 120);
        //showPrice("товар12");
        //showProduct(100,200);


        Scanner sc = new Scanner(System.in);

        String userDate = sc.nextLine();

        String[] arr = userDate.split(" ");
        if (userDate.startsWith("/цена")) {
            showPrice(arr[1]);
        } else if (userDate.startsWith("/сменитьцену")) {
            changePrice(arr[1], Integer.parseInt(arr[2]));
        } else if (userDate.startsWith("/товарыпоцене")) {
            showProduct(Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
        } else {
            System.out.println("Неверный запрос");
        }
    }


    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:Java3_les2_BD.db");
        statement = connection.createStatement();

    }

    public static void disconnect() throws SQLException {
        connection.close();
    }

    public static void createTable() {
        try {
            connect();

            statement.executeUpdate("CREATE TABLE product (\n" +
                    "    id     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    prodid INTEGER UNIQUE,\n" +
                    "    title  STRING,\n" +
                    "    cost   INTEGER\n" +
                    ");");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void dropTable() {
        try {
            connect();
            statement.executeUpdate("drop table product;");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteTable() {
        try {
            connect();
            statement.executeUpdate("delete from product;");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addProduct() {
        try {
            connect();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement("INSERT INTO product (prodid, title, cost)\n" +
                    "VALUES (?,?,?);");
            for (int i = 1; i < 1001; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, "товар" + i);
                preparedStatement.setInt(3, i * 10);
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            connection.setAutoCommit(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void showPrice(String name) {
        try {
            connect();

            ResultSet rs = statement.executeQuery("select cost from product where title = '" + name + "';");

            if (rs.next()) {
                int cost = rs.getInt(1);
                System.out.println("Цена товара: '" + name + "' составляет: " + cost);
            } else {
                System.out.println("Такого товара нет");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void changePrice(String name, int newCost) {
        try {
            connect();
            statement.executeUpdate("update product set cost=" + newCost + " where title ='" + name + "';");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void showProduct(int from, int till) {
        try {
            connect();
            ResultSet rs = statement.executeQuery("select title, cost from product\n" +
                    "where cost between " + from + " and " + till + ";");
            while (rs.next()) {
                String name = rs.getString(1);
                int cost = rs.getInt(2);
                System.out.println(name + " " + cost);
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
