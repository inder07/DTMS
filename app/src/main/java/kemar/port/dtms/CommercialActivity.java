package kemar.port.dtms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CommercialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commercial);
        getSupportActionBar().setTitle("Commercial");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}