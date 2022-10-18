package kemar.port.dtms;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import kemar.port.dtms.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    List<MainActivityOptionsModel> mMenuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setListener(this);
        getSupportActionBar().setTitle("DTMS Main Menu");
        initRecyclerView();
    }


    private void initRecyclerView() {
        //GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        mMenuList = new ArrayList<>();
//        mMenuList.add(new MainActivityOptionsModel("Commercial", R.drawable.commercial));
//        mMenuList.add(new MainActivityOptionsModel("Train", R.drawable.train));
//        mMenuList.add(new MainActivityOptionsModel("Inventory", R.drawable.inventory));
//        mMenuList.add(new MainActivityOptionsModel("Return", R.drawable.inventory));

//        MainActivityAdapter myAdapter = new MainActivityAdapter(MainActivity.this, mMenuList);
//        binding.recyclerview.setAdapter(myAdapter);
    }

    @Override
    public void onDestroy() {
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
            startActivity(new Intent(MainActivity.this, CommercialActivity.class));
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_F2) {
            startActivity(new Intent(MainActivity.this, TrainActivity.class));
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_F3) {
            startActivity(new Intent(MainActivity.this, InventoryActivity.class));
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
                startActivity(new Intent(MainActivity.this, CommercialActivity.class));
                break;
            case R.id.cardview2:
                startActivity(new Intent(MainActivity.this, TrainActivity.class));
                break;
            case R.id.cardview3:
                startActivity(new Intent(MainActivity.this, InventoryActivity.class));
                break;
            case R.id.cardview4:
                finish();
                break;
        }
    }
}