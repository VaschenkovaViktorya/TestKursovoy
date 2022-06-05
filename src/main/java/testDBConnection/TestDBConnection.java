package testDBConnection;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TestDBConnection {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        Connection connection = null;
        try{
            connection = getConnection();
            Statement st=  connection.createStatement();
            addColumnSQL(st);
            insertToTable(st);
        } finally{
            if (connection!=null)
            connection.close();

        }

    }

    private static void addColumnSQL(Statement st) throws SQLException {
        st.execute("ALTER TABLE product\n" +
                "ADD  quantity varchar(255)");
    }

    private static void insertToTable(Statement st) throws SQLException {
        st.executeUpdate("INSERT product \nVALUES\n(6,\"bread\", 50)");
    }

    private static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Properties property = getProperties();
            String url = property.getProperty("url");
            String username = property.getProperty("username");
            String password = property.getProperty("password");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful");
            return connection;
        } catch (Exception e) {
            System.out.println("connection failed...");
            System.out.println(e);
        }
        return null;

    }

    private static Properties getProperties() {
        Properties property = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("src/resourses/database.properties"))) {
            property.load(in);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return property;
    }
}
