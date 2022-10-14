package kemar.port.dtms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TrainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        getSupportActionBar().setTitle("Train");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}