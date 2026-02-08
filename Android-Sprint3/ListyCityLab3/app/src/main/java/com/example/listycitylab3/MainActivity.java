package com.example.listycitylab3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddCityFragment.OnFragmentInteractionListener {

    // declare variables
    ListView cityList;
    ArrayAdapter<City> cityAdapter;
    ArrayList<City> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init data
        cityList = findViewById(R.id.city_list);
        dataList = new ArrayList<>();

        // add some default data
        dataList.add(new City("Edmonton", "AB"));
        dataList.add(new City("Vancouver", "BC"));
        dataList.add(new City("Toronto", "ON"));

        // init custom adapter
        cityAdapter = new CustomList(this, dataList);
        cityList.setAdapter(cityAdapter);

        // handle add button click
        FloatingActionButton fab = findViewById(R.id.add_city_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open fragment in ADD mode (no city passed)
                new AddCityFragment().show(getSupportFragmentManager(), "ADD_CITY");
            }
        });

        // handle list item click (EDIT MODE)
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // get the city that was clicked
                City selectedCity = dataList.get(position);

                // open fragment in EDIT mode (pass the city)
                new AddCityFragment(selectedCity).show(getSupportFragmentManager(), "EDIT_CITY");
            }
        });
    }

    // uh this is called when OK is pressed in ADD mode
    @Override
    public void onOkPressed(City newCity) {
        cityAdapter.add(newCity);
    }

    // this is called when OK is pressed in EDIT modde
    @Override
    public void onEditPressed(City cityToEdit, String newName, String newProvince) {
        // update the object
        cityToEdit.setCityName(newName);
        cityToEdit.setProvinceName(newProvince);

        // notify adapter to refresh the screen
        cityAdapter.notifyDataSetChanged();
    }
}