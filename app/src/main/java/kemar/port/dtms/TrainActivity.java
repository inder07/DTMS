package kemar.port.dtms;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import kemar.port.dtms.databinding.ActivityTrainBinding;

public class TrainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityTrainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_train);
        binding.setListener(this);
        getSupportActionBar().setTitle("Train");
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
        if (keyCode == KeyEvent.KEYCODE_F7) {

            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_F8) {

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

                break;
            case R.id.cardview5:

                break;
            case R.id.cardview6:
                finish();
                break;
        }
    }
}