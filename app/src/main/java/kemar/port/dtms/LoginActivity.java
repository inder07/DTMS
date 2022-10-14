package kemar.port.dtms;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
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

import kemar.port.dtms.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding binding;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setListener(this);
        progress = new ProgressDialog(LoginActivity.this);
        progress.setMessage("Please Wait..");

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
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                break;

            case R.id.btnClear:
                binding.edUsername.setText("");
                binding.edPass.setText("");
                binding.edTerminal.setText("");
                break;
        }
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
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else {
                Toast.makeText(this, "Please fill all Fields!", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_F3) {
            binding.edUsername.setText("");
            binding.edPass.setText("");
            binding.edTerminal.setText("");
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_F4) {
            finish();
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
                et.setError("Must enter Value");
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

}