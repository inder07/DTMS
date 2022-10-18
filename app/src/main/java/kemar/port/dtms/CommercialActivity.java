package kemar.port.dtms;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import kemar.port.dtms.databinding.ActivityCommercialBinding;

public class CommercialActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityCommercialBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_commercial);
        binding.setListener(this);
        getSupportActionBar().setTitle("Commercial");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //hideKeyboard(this);
        //Toast.makeText(this, "keyCode ==> "+keyCode, Toast.LENGTH_SHORT).show();
        if (keyCode == KeyEvent.KEYCODE_F1) {
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_F2) {

            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_F3) {

            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_F4) {
            finish();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cardview1:

                break;
            case R.id.cardview2:

                break;
            case R.id.cardview3:

                break;
            case R.id.cardview4:
                finish();
                break;
        }
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