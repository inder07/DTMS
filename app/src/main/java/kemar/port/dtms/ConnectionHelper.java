package kemar.port.dtms;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    static String url,user,pass;
    private static final String DEFAULT_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DEFAULT_URL = "jdbc:oracle:thin:@10.10.40.13:1725:dtmstest";
    private static final String DEFAULT_USERNAME = "concor";
    private static final String DEFAULT_PASSWORD = "dtms9006";

    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private  static final String URL = "jdbc:oracle:thin:@10.10.40.13:1725:dtmstest";
    private static final String USERNAME = "concor";
    private static final String PASSWORD = "dtms9006";

    @SuppressLint("NewApi")
    public static Connection connectionClass()
    {

        url = "jdbc:oracle:thin:@10.10.40.13:1725:dtmstest";
        user = "concor";
        pass = "dtms9006";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            connection = DriverManager.getConnection(url + "&user=" + user + "&password=" + pass + "");
        }
        catch (SQLException se)
        {
            Log.e("SQLException : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("ClassNotFound : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("Exception : ", e.getMessage());
        }
        return connection;
    }

    public static Connection createConnection(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }

    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        return createConnection(DEFAULT_DRIVER, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }
}

