package kemar.port.dtms;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.textfield.TextInputEditText;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kemar.port.dtms.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding binding;
    ProgressDialog progress;
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    String url, user, pass;

    private static final String DEFAULT_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DEFAULT_URL = "jdbc:oracle:thin:@10.10.40.13:1725:dtmstest";
    private static final String DEFAULT_USERNAME = "concor";
    private static final String DEFAULT_PASSWORD = "dtms9006";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setListener(this);
        progress = new ProgressDialog(LoginActivity.this);
        progress.setMessage("Please Wait..");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

       /* url = "jdbc:oracle:thin:@10.10.40.13:1725:dtmstest";
        user = "concor";
        pass = "dtms9006";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    url, user, pass);
        } catch (SQLException e) {
            binding.edUsername.setText("SQLException");
            e.printStackTrace();
        }
        if (conn != null) {
            System.out.println("Successfully connected to DB");
            binding.edUsername.setText("Successfully connected to DB");
        } else {
            System.out.println("Failed to connect to DB");
            binding.edUsername.setText("Failed to connect to DB");
        }*/

        /*try {
            int count = getConn();
            if (count > 0) {
                Toast.makeText(this, "Connected Successfully!!", Toast.LENGTH_SHORT).show();
                binding.edUsername.setText("Connected Successfully!!");
            } else {
                Toast.makeText(this, "Connection Failed!!", Toast.LENGTH_SHORT).show();
                binding.edUsername.setText("Connection Failed!!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            binding.edUsername.setText("Error");
        }*/

        /*try {
            this.connection = createConnection();
            Toast.makeText(LoginActivity.this, "Connected",
                    Toast.LENGTH_SHORT).show();
            Statement stmt=connection.createStatement();
            StringBuffer stringBuffer = new StringBuffer();
            ResultSet rs=stmt.executeQuery("select * from user_info");
            while(rs.next()) {
                stringBuffer.append( rs.getString(1)+"\n");
            }
            binding.edUsername.setText(stringBuffer.toString());
            connection.close();
        }
        catch (Exception e) {

            Toast.makeText(LoginActivity.this, ""+e,
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }*/


        String manufacturer = android.os.Build.MANUFACTURER;
        if (manufacturer.equals("Honeywell")) {
            binding.btnLogin.setVisibility(View.GONE);
            binding.btnClear.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                binding.edUsername.setShowSoftInputOnFocus(false);
                binding.edPass.setShowSoftInputOnFocus(false);
                binding.edTerminal.setShowSoftInputOnFocus(false);
            } else {
                try {
                    final Method method = EditText.class.getMethod(
                            "setShowSoftInputOnFocus"
                            , new Class[]{boolean.class});
                    method.setAccessible(true);
                    method.invoke(binding.edUsername, false);
                    method.invoke(binding.edPass, false);
                    method.invoke(binding.edTerminal, false);
                } catch (Exception e) {
                    // ignore
                }
            }
        } else {
            binding.btnLogin.setVisibility(View.VISIBLE);
            binding.btnClear.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                if (!validateEditText(ids)) {
                    String user = binding.edUsername.getText().toString().trim();
                    String pass = binding.edPass.getText().toString().trim();
                    String terminal = binding.edTerminal.getText().toString().trim();
                    try {
                        ResultSet rs = login(user);
                        if (rs == null)
                            Toast.makeText(this, "Invalid Username", Toast.LENGTH_SHORT).show();
                        else {
                            if (!rs.getString("").equals(pass)) {
                                Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                binding.edPass.setError("Wrong Password");
                            } else if (!rs.getString("").equals(terminal)) {
                                Toast.makeText(this, "Wrong Terminal", Toast.LENGTH_SHORT).show();
                                binding.edTerminal.setError("Wrong Terminal");
                            } else {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }


                } else {
                    Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnClear:
                binding.edUsername.setText("");
                binding.edPass.setText("");
                binding.edTerminal.setText("");
                binding.edUsername.requestFocus();
                break;
        }
    }

    private ResultSet login(String user) throws SQLException, ClassNotFoundException {
        connection = ConnectionHelper.createConnection();
        if (connection.isClosed() || connection == null) {
            connection = ConnectionHelper.createConnection();
        } else {
            statement = connection.createStatement();
        }
        resultSet = statement.executeQuery("select * from User_info where user_id ='" + user + "'");
        int count = 0;
        while (resultSet.next()) {
            Log.e("Result::", "getCntrDtls: " + resultSet.getString("LOC"));
            count = count + 1;
            return resultSet;
        }
        return resultSet;
    }

    /*private void login(String username, String password) {
        runOnUiThread(() -> {
            progress.show();
        });
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName", username);
            jsonObject.put("Password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String uri = "http://"+ serverIp + "/service/api/UserManagement/authenticate";
        NetworkTask t = new NetworkTask(LoginActivity.this,this, uri, jsonObject.toString(), Constants.POST, Constants.LOGIN,false);
    }*/

    /*@Override
    public void getResponse(Response res, int reqType) {
        String response = "";
        try {
            if (res.body() != null) {
                response = res.body().string();
                Log.e("UTDashboard", response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (progress.isShowing())
            progress.dismiss();

        String finalResponse = response;
        runOnUiThread(() -> {
            switch (reqType) {
                case Constants.LOGIN:
                    if (res.code() == Constants.HTTP_OK) {
                        try {
                            JSONObject jsonObject = new JSONObject(finalResponse);
                            String jwtToken = jsonObject.get("jwtToken").toString();
                            String userName = jsonObject.get("userName").toString();
                            String role = jsonObject.get("roleName").toString();
                            if (role.equals("Admin"))
                                Utils.setSharedPrefs(LoginActivity.this, Constants.IS_ADMIN, "true");
                            else
                                Utils.setSharedPrefs(LoginActivity.this, Constants.IS_ADMIN, "false");
                            Utils.setSharedPrefs(LoginActivity.this, Constants.USERNAME, userName);
                            Utils.setSharedPrefs(LoginActivity.this, Constants.TOKEN, jwtToken);
                            Utils.setSharedPrefsBoolean(LoginActivity.this, Constants.LOGGEDIN, true);
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } else {
                        Toast.makeText(this, "Error has occured while login", Toast.LENGTH_SHORT).show();
                        binding.edPass.setText("");
                    }
                    break;
            }
        });
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //hideKeyboard(this);
        //Toast.makeText(this, "keyCode ==> "+keyCode, Toast.LENGTH_SHORT).show();
        if (keyCode == KeyEvent.KEYCODE_F2) {
            if (!validateEditText(ids)) {
                String user = binding.edUsername.getText().toString().trim();
                String pass = binding.edPass.getText().toString().trim();
                String terminal = binding.edTerminal.getText().toString().trim();
                try {
                    ResultSet rs = login(user);
                    if (rs == null)
                        Toast.makeText(this, "Invalid Username", Toast.LENGTH_SHORT).show();
                    else {
                        if (!rs.getString("").equals(pass)) {
                            Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            binding.edPass.setError("Wrong Password");
                        } else if (!rs.getString("").equals(terminal)) {
                            Toast.makeText(this, "Wrong Terminal", Toast.LENGTH_SHORT).show();
                            binding.edTerminal.setError("Wrong Terminal");
                        } else {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            } else {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_F3) {
            binding.edUsername.setText("");
            binding.edPass.setText("");
            binding.edTerminal.setText("");
            binding.edUsername.requestFocus();
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_F4) {
            finish();
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            //hideKeyboard(this);
            if (!validateEditText(ids)) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    int[] ids = new int[]
            {
                    R.id.edUsername,
                    R.id.edPass,
                    R.id.edTerminal
            };

    public boolean validateEditText(int[] ids) {
        boolean isEmpty = false;

        for (int id : ids) {
            TextInputEditText et = (TextInputEditText) findViewById(id);

            if (TextUtils.isEmpty(et.getText().toString())) {
                et.setError("Please enter value");
                isEmpty = true;
            }
        }

        return isEmpty;
    }

    public void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public int getConn() throws SQLException, ClassNotFoundException {
        connection = ConnectionHelper.createConnection();
        if (connection.isClosed() || connection == null) {
            connection = ConnectionHelper.createConnection();
        } else {
            statement = connection.createStatement();
        }
        resultSet = statement.executeQuery("select * from User_info");
        int count = 0;
        while (resultSet.next()) {
            count = count + 1;
        }
        return count;
    }

    public Connection createConnection(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException {

        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }

    public Connection createConnection() throws ClassNotFoundException, SQLException {
        return createConnection(DEFAULT_DRIVER, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

}