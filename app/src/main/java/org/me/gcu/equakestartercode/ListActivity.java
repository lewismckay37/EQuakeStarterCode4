package org.me.gcu.equakestartercode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
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
            case R.id.action_map:
                Intent myintent = new Intent(ListActivity.this, MapActivity.class);
                startActivity(myintent);
            case R.id.action_List:
                Intent myintent1 = new Intent(ListActivity.this, ListActivity.class);
                startActivity(myintent1);
                return false;
            case R.id.action_Search:
                Intent myintent2 = new Intent(ListActivity.this, SearchActivity.class);
                startActivity(myintent2);
                return false;
            case R.id.action_home:
                Intent myintent3 = new Intent(ListActivity.this,  MainActivity.class);
                startActivity(myintent3);
                return false;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}