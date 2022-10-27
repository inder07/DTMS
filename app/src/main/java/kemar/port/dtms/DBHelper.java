package kemar.port.dtms;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBHelper {

    Connection connection = ConnectionHelper.connectionClass();
    Statement statement;
    ResultSet resultSet;

}
