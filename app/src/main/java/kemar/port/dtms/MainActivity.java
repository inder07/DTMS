package kemar.port.dtms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import kemar.port.dtms.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    List<MainActivityOptionsModel> mMenuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getSupportActionBar().setTitle("DTMS Main Menu");
        initRecyclerView();
    }


    private void initRecyclerView() {
        //GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        mMenuList = new ArrayList<>();
        mMenuList.add(new MainActivityOptionsModel("Commercial", R.drawable.commercial));
        mMenuList.add(new MainActivityOptionsModel("Train", R.drawable.train));
        mMenuList.add(new MainActivityOptionsModel("Inventory", R.drawable.inventory));

        MainActivityAdapter myAdapter = new MainActivityAdapter(MainActivity.this, mMenuList);
        binding.recyclerview.setAdapter(myAdapter);
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
}