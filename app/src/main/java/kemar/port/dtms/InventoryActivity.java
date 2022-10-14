package kemar.port.dtms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import kemar.port.dtms.databinding.ActivityInventoryBinding;

public class InventoryActivity extends AppCompatActivity {

    ActivityInventoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_inventory);
        getSupportActionBar().setTitle("Inventory");

        binding.btn1.setOnClickListener(view -> {
            startActivity(new Intent(InventoryActivity.this,StackReportingActivity.class));
        });
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
}