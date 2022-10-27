package kemar.port.dtms;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.util.ArrayList;

import kemar.port.dtms.databinding.ActivityStackReportingBinding;

public class StackReportingActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityStackReportingBinding binding;
    ArrayList cloth;
    ArrayAdapter<CharSequence> spinnerClothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stack_reporting);
        binding.setListener(this);
        getSupportActionBar().setTitle("Stack Reporting");
        //initSpinner();
        binding.ed1.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(1)});
        binding.ed2.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(20)});
        binding.ed3.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(20)});
        binding.ed4.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(20)});
        binding.ed5.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(20)});

        String manufacturer = android.os.Build.MANUFACTURER;
        if (manufacturer.equals("Honeywell")) {
            binding.btnSave.setVisibility(View.GONE);
            binding.btnNext.setVisibility(View.GONE);
            binding.btnClear.setVisibility(View.GONE);
        } else {
            binding.btnSave.setVisibility(View.VISIBLE);
            binding.btnNext.setVisibility(View.VISIBLE);
            binding.btnClear.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnSave:
                Toast.makeText(this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                binding.ed1.setText("");
                binding.ed2.setText("");
                binding.ed3.setText("");
                binding.ed4.setText("");
                binding.ed5.setText("");
                binding.ed1.requestFocus();
                break;

            case R.id.btnNext:
                if (!binding.ed1.getText().toString().equals(""))
                    binding.ed2.requestFocus();
                if (!binding.ed2.getText().toString().equals(""))
                    binding.ed3.requestFocus();
                if (!binding.ed3.getText().toString().equals(""))
                    binding.ed4.requestFocus();
                if (!binding.ed4.getText().toString().equals(""))
                    binding.ed5.requestFocus();
                break;

            case R.id.btnClear:
                binding.ed1.setText("");
                binding.ed2.setText("");
                binding.ed3.setText("");
                binding.ed4.setText("");
                binding.ed5.setText("");
                binding.ed1.requestFocus();
                break;
        }
    }

    /*public void initSpinner() {
        List<String> clothList = Arrays.asList(getResources().getStringArray(R.array.cloth));
        cloth = new ArrayList();
        cloth.clear();
        cloth.add("Select Shift");
        cloth.addAll(clothList);
        spinnerClothAdapter = new ArrayAdapter<CharSequence>(StackReportingActivity.this, R.layout.spinner_item, cloth);
        spinnerClothAdapter.setDropDownViewResource(R.layout.spinner_item);
        binding.spinnerCloth.setAdapter(spinnerClothAdapter);
    }*/

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //hideKeyboard(this);
        //Toast.makeText(this, "keyCode ==> "+keyCode, Toast.LENGTH_SHORT).show();
        if (keyCode == KeyEvent.KEYCODE_F1) {
            if (!binding.ed1.getText().toString().equals(""))
                binding.ed2.requestFocus();
            if (!binding.ed2.getText().toString().equals(""))
                binding.ed3.requestFocus();
            if (!binding.ed3.getText().toString().equals(""))
                binding.ed4.requestFocus();
            if (!binding.ed4.getText().toString().equals(""))
                binding.ed5.requestFocus();
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_F2) {
            binding.tvAlert.setText("");
            boolean validLoc = validateStackLoc(binding.ed5.getText().toString());
            if (validLoc) {
                Toast.makeText(this, "Valid Location", Toast.LENGTH_SHORT).show();
                binding.tvAlert.setText("Server Not Found!");
            } else {
                Toast.makeText(this, "Invalid Location", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_F3) {
            binding.ed1.setText("");
            binding.ed2.setText("");
            binding.ed3.setText("");
            binding.ed4.setText("");
            binding.ed5.setText("");
            binding.ed1.requestFocus();
            //binding.spinnerCloth.setSelection(0);
            binding.tvAlert.setText("");
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_F4) {
            finish();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    private boolean validateStackLoc(String text) {
        return text.matches("^([A-Z]?[0-9]{2} [0-9]{3} [0-9]{3} [A-Z]{1})$");
    }
}