package kemar.port.dtms;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import kemar.port.dtms.databinding.ActivityInventoryBinding;

public class InventoryActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityInventoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_inventory);
        binding.setListener(this);
        getSupportActionBar().setTitle("Inventory");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.unbind();
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
            startActivity(new Intent(InventoryActivity.this, StackReportingActivity.class));
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_F2) {
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_F3) {
            startActivity(new Intent(InventoryActivity.this, QueryActivity.class));
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
                startActivity(new Intent(InventoryActivity.this, StackReportingActivity.class));
                break;
            case R.id.cardview2:

                break;
            case R.id.cardview3:
                startActivity(new Intent(InventoryActivity.this, QueryActivity.class));
                break;
            case R.id.cardview4:
                finish();
                break;
        }
    }
}