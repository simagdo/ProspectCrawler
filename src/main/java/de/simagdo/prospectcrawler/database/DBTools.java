package de.simagdo.prospectcrawler.database;

import de.simagdo.prospectcrawler.utils.Product;
import de.simagdo.prospectcrawler.utils.Store;
import de.simagdo.prospectcrawler.utils.Utils;
import org.mariadb.jdbc.internal.com.read.dao.Results;

import javax.xml.transform.Result;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;

public class DBTools {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public String driver = "org.mariadb.jdbc.Driver";
    public String url = "jdbc:mariadb://localhost:3306";
    public String user = "root";
    public String password = "";
    private int shopID;

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

        if (this.shopID != 0) this.shopID = this.getID("Shops", "ShopID", "ShopName", store.getStore());

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

        if (this.shopID != 0) this.shopID = this.getID("Shops", "ShopID", "ShopName", store.getStore());

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

    //public void addProduct(Store store, Product product, InputStream imageInputStream) {
    public void addProduct(Store store, Product product) {

        try {

            if (this.shopID == 0) {
                this.shopID = this.getID("Shops", "ShopID", "ShopName", store.getStore());
                this.statement = this.getConnection().createStatement();
            }

            //Insert Product
            /*this.insertProduct(product, shopID, this.statement);

            //Insert Image
            //this.insertImage(imageInputStream, product.getProductName(), this.statement);

            //Insert Offer
            this.insertOffer(product, shopID, this.statement);*/

            product.setImagePath(product.getImagePath().replace("\\", "\\\\"));
            //product.setRelativeImagePath(product.getRelativeImagePath().replace("/", "//"));

            String insertProduct = "INSERT INTO Products(ProductName, ImagePath, RelativeImagePath)\n" +
                    "SELECT *\n" +
                    "FROM (SELECT '" + product.getProductName() + "',\n" +
                    "             '" + product.getImagePath() + "',\n" +
                    "'" + product.getRelativeImagePath() + "') AS temp\n" +
                    "WHERE NOT EXISTS(\n" +
                    "        SELECT ProductName FROM Products WHERE ProductName = '" + product.getProductName() + "');";
            this.statement.executeQuery(insertProduct);

            String insertOffer = "INSERT INTO AvailableOffers(ShopID, ValidityID, ProductID, OldPrice, NewPrice, Link, Amount, PerAmount, Unit)\n" +
                    "SELECT *\n" +
                    "FROM (SELECT " + shopID + "                                                                                             AS ShopID,\n" +
                    "             (SELECT ValidityID\n" +
                    "              FROM ValidityDate\n" +
                    "              WHERE FromDate = '" + product.getFromDate() + "'\n" +
                    "                AND ToDate = '" + product.getToDate() + "')                                                                 AS ValidityID,\n" +
                    "             (SELECT ProductID FROM Products WHERE ProductName = '" + product.getProductName() + "') AS ProductID,\n" +
                    "             " + product.getOldPrice() + ",\n" +
                    "             " + product.getNewPrice() + ",\n" +
                    "             '" + product.getLinkToProduct() + "',\n" +
                    "             '" + product.getAmount() + "' AS Amount,\n" +
                    "             '" + product.getPerAmount() + "' AS PerAmount,\n" +
                    "             '" + product.getUnit() + "') AS temp;";
            this.statement.executeQuery(insertOffer);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert a {@link Product} into the corresponding Table
     *
     * @param product   which will be inserted
     * @param shopID    of the corresponding Shop
     * @param statement which will be used to import the Product
     */
    private void insertProduct(Product product, int shopID, Statement statement) {
        try {

            String insertProduct = "INSERT INTO Products(ProductName, ImagePath, RelativeImagePath)\n" +
                    "SELECT *\n" +
                    "FROM (SELECT '" + product.getProductName() + "',\n" +
                    "             '" + product.getImagePath() + "',\n" +
                    "'" + product.getRelativeImagePath() + "') AS temp\n" +
                    "WHERE NOT EXISTS(\n" +
                    "        SELECT ProductName FROM Products WHERE ProductName = '" + product.getProductName() + "');";
            statement.executeQuery(insertProduct);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert an Image into the corresponding Table
     *
     * @param imageInputStream which will be inserted
     * @param productName      of the corresponding Product
     * @param statement        which will be used to import the Product
     */
    private void insertImage(InputStream imageInputStream, String productName, Statement statement) {
        try {
            /*String insertImage = "INSERT INTO ProductPictures(ProductID, ImageData)\n" +
                    "SELECT (SELECT ProductID FROM Products WHERE ProductName = '" + productName + "') AS ProductID, '" + imageInputStream + "';";*/
            String insertImage = "INSERT INTO ProductPictures(ProductID, ImageData)\n" +
                    "SELECT (SELECT ProductID FROM Products WHERE ProductName = ?) AS ProductID, ?;";
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(insertImage);
            preparedStatement.setString(1, productName);
            preparedStatement.setBinaryStream(2, (InputStream) imageInputStream);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert an Offer into the corresponding Table
     *
     * @param product   which will be used to insert an Offer
     * @param shopID    of the corresponding Shop
     * @param statement which will be used to import the Offer
     */
    private void insertOffer(Product product, int shopID, Statement statement) {
        try {

            String insertOffer = "INSERT INTO AvailableOffers(ShopID, ValidityID, ProductID, OldPrice, NewPrice, Link, Amount, PerAmount, Unit)\n" +
                    "SELECT *\n" +
                    "FROM (SELECT " + shopID + "                                                                                             AS ShopID,\n" +
                    "             (SELECT ValidityID\n" +
                    "              FROM ValidityDate\n" +
                    "              WHERE FromDate = '" + product.getFromDate() + "'\n" +
                    "                AND ToDate = '" + product.getToDate() + "')                                                                 AS ValidityID,\n" +
                    "             (SELECT ProductID FROM Products WHERE ProductName = '" + product.getProductName() + "') AS ProductID,\n" +
                    "             " + product.getOldPrice() + ",\n" +
                    "             " + product.getNewPrice() + ",\n" +
                    "             '" + product.getLinkToProduct() + "',\n" +
                    "             '" + product.getAmount() + "' AS Amount,\n" +
                    "             '" + product.getPerAmount() + "' AS PerAmount,\n" +
                    "             '" + product.getUnit() + "') AS temp;";
            statement.executeQuery(insertOffer);

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

    public void insert() {
        try {
            Utils utils = new Utils();
            InputStream inputStream = utils.getImageAsBlob("https://cdn.penny.de/.imaging/mte/penny/tile-sm/dam/Angebote_der_Woche/2020-47/food/21439610.png/jcr:content/21439610.png?size=1x");

            System.out.println("Size: " + inputStream.available());

            String insert = "INSERT INTO ProductPictures(ProductID, ImageData) VALUES (?, ?);";
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(insert);
            preparedStatement.setInt(1, 430);
            preparedStatement.setBinaryStream(2, inputStream, inputStream.available());
            preparedStatement.executeUpdate();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        try {
            this.resultSet = this.select("ProductPictures", "ImageData", "WHERE ProductPictureID = (SELECT MAX(ProductPictureID) FROM ProductPictures)", "");

            while (this.resultSet.next()) {
                InputStream inputStream = this.resultSet.getBinaryStream(1);

                System.out.println("Size: " + inputStream.available());

                try (FileOutputStream outputStream = new FileOutputStream("Test.jpg")) {
                    int read;
                    byte[] bytes = new byte[inputStream.available()];

                    while ((read = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }

                }

            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

}
