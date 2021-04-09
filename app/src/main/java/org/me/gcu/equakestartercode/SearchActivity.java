package org.me.gcu.equakestartercode;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SearchActivity extends AppCompatActivity  {

    private TextView mTextView;
    private static  final String TAG = "SearchActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mDisplayDate = (TextView) findViewById(R.id.date1);


        mDisplayDate.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get (Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(SearchActivity.this, android.R.style.Theme_Black, onDateSetListener, year, month, day);

                dialog.getWindow().setBackgroundDrawable (new ColorDrawable(android.R.color.transparent));
                dialog.show();


            }


        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                mDisplayDate.setText(date);

            }
        };
        //mDisplayDate = (TextView) findViewById(R.id.date2);





        mTextView = (TextView) findViewById(R.id.text);


    }















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent myintent3 = new Intent(SearchActivity.this,  MainActivity.class);
                startActivity(myintent3);
                return false;
            case R.id.action_map:
                Intent myintent = new Intent(SearchActivity.this, MapActivity.class);
                startActivity(myintent);
            case R.id.action_List:
                Intent myintent1 = new Intent(SearchActivity.this, ListActivity.class);
                startActivity(myintent1);
                return false;
            case R.id.action_Search:
                Intent myintent2 = new Intent(SearchActivity.this, SearchActivity.class);
                startActivity(myintent2);
                return false;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}