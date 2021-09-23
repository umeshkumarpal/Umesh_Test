package com.example.umesh_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;


import com.android.volley.toolbox.Volley;
import com.example.umesh_test.ModelClass.Model;
import com.example.umesh_test.ModelClass.PostOffice;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText editText_MobileNo, editText_FullName, editText_dob, editText_Addres1, editText_Addres2, editText_Pincode;
    TextView check_Button, register_Button;
    TextView textView_District, textView_State;
    AppCompatSpinner spinner;
    CalendarView calendarView;
    List<Model> list;
    List<PostOffice> plist;
    String state, dis;
    String pinc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_MobileNo = findViewById(R.id.mobile_number);
        editText_FullName = findViewById(R.id.full_name);
        spinner = findViewById(R.id.gender);
        editText_dob = findViewById(R.id.dob);
        editText_Addres1 = findViewById(R.id.address1);
        editText_Addres2 = findViewById(R.id.address2);
        editText_Pincode = findViewById(R.id.pincode);
        textView_District = findViewById(R.id.district);
        textView_State = findViewById(R.id.state);

        check_Button = findViewById(R.id.check_pincode);
        register_Button = findViewById(R.id.registration);
        list = new ArrayList<>();
        plist = new ArrayList<>();

        String[] arraySpinner = new String[]{"Gender", "Male", "Female"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final Calendar myCalendar = Calendar.getInstance();


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                editText_dob.setText(sdf.format(myCalendar.getTime()));
            }

        };
        editText_dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        editText_Pincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                check_Button.setEnabled(!editText_Pincode.getText().toString().trim().isEmpty());
                check_Button.setBackground(ContextCompat.getDrawable(MainActivity.this, R.color.black));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Registraion();
        check_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinc = editText_Pincode.getText().toString();
                if (pinc.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Fill Pincode", Toast.LENGTH_SHORT).show();
                } else {
                    LoadData();
                }
            }
        });
    }

    private void Registraion() {

        register_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobileNo = editText_MobileNo.getText().toString().trim();
                String Name = editText_FullName.getText().toString().trim();
                String Spinner = spinner.getPopupBackground().toString().trim();
                String dob = editText_dob.getText().toString().trim();
                String addres1 = editText_Addres1.getText().toString().trim();
                String address2 = editText_Addres2.getText().toString().trim();
                String pincode = editText_Pincode.getText().toString().trim();
                String district = textView_District.getText().toString().trim();
                String state = textView_State.getText().toString().trim();

                if (mobileNo.isEmpty()) {
                    editText_MobileNo.setError("enter Mobile");
                    editText_MobileNo.requestFocus();
                    return;
                } else if (Name.isEmpty()) {
                    editText_FullName.setError("Enter Name");
                    editText_FullName.requestFocus();
                    return;
                } else if (Spinner.isEmpty()) {
                } else if (dob.isEmpty()) {
                    editText_dob.setError("Enter dob");
                    editText_dob.requestFocus();
                    return;
                } else if (addres1.isEmpty()) {
                    editText_Addres1.setError("Enter address");
                    editText_Addres1.requestFocus();
                    return;
                } else if (addres1.length() < 3) {
                    editText_Addres1.setError("Enter address");
                    editText_Addres1.requestFocus();
                } else if (pincode.isEmpty()) {
                    editText_Pincode.setError("Please Fill Pincode");
                    return;
                } else {
                    Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                    startActivity(intent);
                }
            }

        });
    }


    private void LoadData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.postalpincode.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Model>> call = api.getData(pinc);
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, retrofit2.Response<List<Model>> response) {
                List<Model> list = response.body();
                Log.d("list", "onResponse: " + response.code() + " " + new Gson().toJson(response.body()));
                state = list.get(0).getPostOffice().get(0).getState();
                dis = list.get(0).getPostOffice().get(0).getDistrict();
                Log.d("lis", response.body().toString());
                textView_District.setText(state);
                textView_State.setText(dis);
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
