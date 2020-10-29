package de.simagdo.prospectcrawler.database;

import de.simagdo.prospectcrawler.utils.Product;
import de.simagdo.prospectcrawler.utils.Store;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class DBTools {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public String driver = "org.mariadb.jdbc.Driver";
    public String url = "jdbc:mariadb://localhost:3306";
    public String user = "root";
    public String password = "";

    /**
     * @return Open the connection to the DataBase
     */
    public Connection getConnection() {

        //Load Drivers
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url + "/ProspectCrawler", user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //return Connection
        return connection;
    }

    /**
     * @return Close the connection to the DataBase
     */
    public Connection closeConnection() {
        connection = null;
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Create a ResultSet with specific conditions
     *
     * @param tableName Which table will be used
     * @param column    Which colums will be selected
     * @param condition Which condition will be selected (z.B. WHERE NAME IS NOT NULL)
     * @param optional  Other parameters
     * @return ResultSet
     */
    public ResultSet select(String tableName, String column, String condition, String optional) {

        try {
            this.statement = this.getConnection().createStatement();

            this.resultSet = this.statement.executeQuery("SELECT " + column + " FROM " + tableName + " " + condition + " " + optional + ";");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public void addValidityDate(Store store, LocalDate startDate, LocalDate endDate) {
        int shopID;

        try {
            this.statement = this.getConnection().createStatement();

            //Get the ShopID
            shopID = this.getID("Shops", "ShopID", "ShopName", store.getStore());
            System.out.println("ShopID: " + shopID);

            //Insert Validity Date
            this.statement.executeQuery("INSERT INTO ValidityDate (ShopID, FromDate, ToDate) VALUE (" + shopID + ", '" + startDate + "', '" + endDate + "'" + ")");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCategory(Store store, String category) {
        int shopID;

        try {
            this.statement = this.getConnection().createStatement();

            //Get the ShopID
            shopID = this.getID("Shops", "ShopID", "ShopName", store.getStore());
            System.out.println(shopID);
            String sqlStatement = "INSERT INTO Category(ShopID, Category)\n" +
                    "SELECT *\n" +
                    "FROM (SELECT " + shopID + ", '" + category + "') AS temp\n" +
                    "WHERE NOT EXISTS(\n" +
                    "        SELECT Category FROM Category WHERE Category = '" + category + "'\n" +
                    "    );";
            this.statement.executeQuery(sqlStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Store store, Product product) {
        int shopID;

        try {
            this.statement = this.getConnection().createStatement();

            //Get the ShopID
            shopID = this.getID("Shops", "ShopID", "ShopName", store.getStore());
            System.out.println(shopID);

            String sqlStatement = "INSERT INTO Products(ShopID, ValidityID, CategoryID, ProductName, Link, OldPrice, NewPrice, Amount, PerAmount)\n" +
                    "SELECT *\n" +
                    "FROM (SELECT " + shopID + " AS 'ShopID', (SELECT ValidityID FROM ValidityDate WHERE FromDate = '" + product.getFromDate() + "' AND ToDate = '" + product.getToDate() + "') AS 'ValidityID', " +
                    "(SELECT CategoryID FROM Category WHERE Category = '" + product.getCategory() + "') AS 'CategoryID', '" + product.getProductName() + "', '" + product.getLinkToProduct() + "', " + product.getOldPrice() + ", " + product.getNewPrice() + ", '" + product.getAmount() + "', '" + product.getPerAmount() + "') AS temp\n" +
                    "WHERE NOT EXISTS(SELECT ProductName FROM Products WHERE ProductName = '" + product.getProductName() + "' AND ShopID = " + shopID + ");\n";
            System.out.println(sqlStatement);
            System.out.println(product.toString());
            this.statement.executeQuery(sqlStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getID(String table, String column, String field, String value) {
        try {
            this.resultSet = this.select(table, column, "WHERE " + field + " = '" + value + "'", "");

            while (this.resultSet.next()) return this.resultSet.getInt(column);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}