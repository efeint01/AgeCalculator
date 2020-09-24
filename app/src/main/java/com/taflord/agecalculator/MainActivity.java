package com.taflord.agecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    TextView age_tw,birth_date_tw;
    Button date_btn;
    LinearLayout age_linear,birth_linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        age_linear = findViewById(R.id.age_linear);
        birth_linear = findViewById(R.id.birth_linear);
        age_linear.setVisibility(View.GONE);
        birth_linear.setVisibility(View.GONE);

        age_tw = findViewById(R.id.age_tw);
        birth_date_tw = findViewById(R.id.birth_date_tw);
        date_btn = findViewById(R.id.date_btn);


        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog();
            }
        });

    }

    private void dateDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth, datePickerListener, year, month, day);

        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        datePickerDialog.show();
    }


    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);
            birth_date_tw.setText(day+"/"+month+"/"+year);
            age_tw.setText(Integer.toString(calculateAge(c.getTimeInMillis())));
            age_tw.setTypeface(Typeface.DEFAULT_BOLD);
            age_linear.setVisibility(View.VISIBLE);
            birth_linear.setVisibility(View.VISIBLE);
        }
    };


    int calculateAge(long date) {
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);
        Calendar today = Calendar.getInstance();


        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }

        if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
            age--;
        }
        return age;
    }
}