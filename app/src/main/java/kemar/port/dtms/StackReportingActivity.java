package kemar.port.dtms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class StackReportingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack_reporting);
        getSupportActionBar().setTitle("Stack Reporting");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}