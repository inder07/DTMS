package kemar.port.dtms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kemar.port.dtms.databinding.ActivityQueryBinding;

public class QueryActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityQueryBinding binding;

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_query);
        binding.setListener(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        binding.et2.setFocusable(false);
        binding.et3.setFocusable(false);
        binding.et4.setFocusable(false);
        binding.et5.setFocusable(false);
        binding.et6.setFocusable(false);
        binding.et7.setFocusable(false);
        binding.et8.setFocusable(false);
        binding.et9.setFocusable(false);
        binding.et10.setFocusable(false);
        binding.et11.setFocusable(false);
        binding.et12.setFocusable(false);
        binding.et13.setFocusable(false);

        String manufacturer = android.os.Build.MANUFACTURER;
        if (manufacturer.equals("Honeywell")) {
            binding.btnRtv.setVisibility(View.GONE);
            binding.btnNew.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                binding.et1.setShowSoftInputOnFocus(false);
            } else {
                try {
                    final Method method = EditText.class.getMethod(
                            "setShowSoftInputOnFocus"
                            , new Class[]{boolean.class});
                    method.setAccessible(true);
                    method.invoke(binding.et1, false);
                } catch (Exception e) {
                    // ignore
                }
            }
        } else {
            binding.btnRtv.setVisibility(View.VISIBLE);
            binding.btnNew.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnRtv:
                binding.et1.requestFocus();
                try {
                    ResultSet rs = getCntrDtls(binding.et1.getText().toString().trim());
                    binding.et2.setText(rs.getString("DACCNTRTYPE"));
                    binding.et3.setText(rs.getString("DAVTRMNCODE"));
                    binding.et4.setText(rs.getString("DACLEFLAG"));
                    //binding.et5.setText(rs.getString("DACCNTRTYPE"));
                    binding.et6.setText(rs.getString("DACSFCODE"));
                    binding.et7.setText(rs.getString("DACCRNTSTTS"));
                    binding.et8.setText(rs.getString("LOC"));
                    binding.et9.setText(rs.getString("DADCRNTSTTSTIME"));
                    binding.et10.setText(rs.getString("DAVSTTNFROM"));
                    binding.et11.setText(rs.getString("DAVSTTNTO"));
                    //binding.et12.setText(rs.getString("DACCNTRTYPE"));
                    //binding.et13.setText(rs.getString("DACCNTRTYPE"));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;

            case R.id.btnNew:
                binding.et1.setText("");
                binding.et2.setText("");
                binding.et3.setText("");
                binding.et4.setText("");
                binding.et5.setText("");
                binding.et6.setText("");
                binding.et7.setText("");
                binding.et8.setText("");
                binding.et9.setText("");
                binding.et10.setText("");
                binding.et11.setText("");
                binding.et12.setText("");
                binding.et13.setText("");
                binding.et1.requestFocus();
                break;
        }
    }

    /*public void initSpinner() {
        List<String> clothList = Arrays.asList(getResources().getStringArray(R.array.cloth));
        cloth = new ArrayList();
        cloth.clear();
        cloth.add("Select Shift");
        cloth.addAll(clothList);
        spinnerClothAdapter = new ArrayAdapter<CharSequence>(StackReportingActivity.this, R.layout.spinner_item, cloth);
        spinnerClothAdapter.setDropDownViewResource(R.layout.spinner_item);
        binding.spinnerCloth.setAdapter(spinnerClothAdapter);
    }*/

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //hideKeyboard(this);
        //Toast.makeText(this, "keyCode ==> "+keyCode, Toast.LENGTH_SHORT).show();
        if (keyCode == KeyEvent.KEYCODE_F1) {
            binding.et1.requestFocus();
            try {
                ResultSet rs = getCntrDtls(binding.et1.getText().toString().trim());
                binding.et2.setText(rs.getString("DACCNTRTYPE"));
                binding.et3.setText(rs.getString("DAVTRMNCODE"));
                binding.et4.setText(rs.getString("DACLEFLAG"));
                //binding.et5.setText(rs.getString("DACCNTRTYPE"));
                binding.et6.setText(rs.getString("DACSFCODE"));
                binding.et7.setText(rs.getString("DACCRNTSTTS"));
                binding.et8.setText(rs.getString("LOC"));
                binding.et9.setText(rs.getString("DADCRNTSTTSTIME"));
                binding.et10.setText(rs.getString("DAVSTTNFROM"));
                binding.et11.setText(rs.getString("DAVSTTNTO"));
                //binding.et12.setText(rs.getString("DACCNTRTYPE"));
                //binding.et13.setText(rs.getString("DACCNTRTYPE"));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            /*if (!binding.et1.getText().toString().equals(""))
                binding.et2.requestFocus();
            if (!binding.et2.getText().toString().equals(""))
                binding.et3.requestFocus();
            if (!binding.et3.getText().toString().equals(""))
                binding.et4.requestFocus();
            if (!binding.et4.getText().toString().equals(""))
                binding.et5.requestFocus();
            if (!binding.et5.getText().toString().equals(""))
                binding.et6.requestFocus();
            if (!binding.et6.getText().toString().equals(""))
                binding.et7.requestFocus();
            if (!binding.et7.getText().toString().equals(""))
                binding.et8.requestFocus();
            if (!binding.et8.getText().toString().equals(""))
                binding.et9.requestFocus();
            if (!binding.et9.getText().toString().equals(""))
                binding.et10.requestFocus();
            if (!binding.et10.getText().toString().equals(""))
                binding.et11.requestFocus();
            if (!binding.et11.getText().toString().equals(""))
                binding.et12.requestFocus();
            if (!binding.et12.getText().toString().equals(""))
                binding.et13.requestFocus();*/
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_F3) {
            binding.et1.setText("");
            binding.et2.setText("");
            binding.et3.setText("");
            binding.et4.setText("");
            binding.et5.setText("");
            binding.et6.setText("");
            binding.et7.setText("");
            binding.et8.setText("");
            binding.et9.setText("");
            binding.et10.setText("");
            binding.et11.setText("");
            binding.et12.setText("");
            binding.et13.setText("");
            binding.et1.requestFocus();
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_F4) {
            finish();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    private ResultSet getCntrDtls(String cntrNumb) throws SQLException, ClassNotFoundException{
        connection = ConnectionHelper.createConnection();
        if (connection.isClosed() || connection == null) {
            connection = ConnectionHelper.createConnection();
        } else {
            statement = connection.createStatement();
        }
        resultSet = statement.executeQuery("select tb1.*,tb2.DAVTRMNCODE,tb2.DACSTCKLOCN as LOC,tb2.DACCRNTSTTS,tb2.DADCRNTSTTSTIME  from dexcntrinfo tb1\n" +
                "JOIN demcntrinvt tb2 on tb1.daccntrnumb= tb2.daccntrnumb  where tb1.daccntrnumb = '"+cntrNumb+"'");
        int count = 0;
        while (resultSet.next()) {
            Log.e("Result::", "getCntrDtls: "+ resultSet.getString("LOC"));
            count = count + 1;
            return resultSet;
        }
        return resultSet;
    }

}