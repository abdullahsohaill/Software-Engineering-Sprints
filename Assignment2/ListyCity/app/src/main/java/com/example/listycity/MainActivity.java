package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // declare my variables
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int selectedCityIndex = -1; // to keep track of selected city

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // starting list of cities
        String[] cities = {"Edmonton", "Vancouver", "Montréal", "Calgary", "Toronto"};
        dataList = new ArrayList<>(Arrays.asList(cities));

        // connect the list view from xml
        cityList = findViewById(R.id.city_list);

        // this is the adapter
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);

        // link the adapter to the list
        cityList.setAdapter(cityAdapter);


        // get the other views from xml
        final Button addCityButton = findViewById(R.id.add_city_button);
        final Button deleteCityButton = findViewById(R.id.delete_city_button);
        final LinearLayout addCityLayout = findViewById(R.id.add_city_layout);
        final EditText addCityEditText = findViewById(R.id.add_city_edit_text);
        final Button confirmButton = findViewById(R.id.confirm_button);


        // handle add city button click
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show the little add menu
                addCityLayout.setVisibility(View.VISIBLE);
            }
        });

        // handle confirm button click
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the new city name from the text box
                String newCity = addCityEditText.getText().toString();

                // make sure it's not empty
                if (!newCity.isEmpty()) {
                    // add it to the list
                    dataList.add(newCity);

                    // tell the adapter the data changed
                    cityAdapter.notifyDataSetChanged();

                    // hide the add menu again
                    addCityEditText.setText("");
                    addCityLayout.setVisibility(View.GONE);
                }
            }
        });


        // handle clicks on the list items
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // save which one was clicked
                selectedCityIndex = position;
            }
        });

        // handle delete button click
        deleteCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // make sure something was selected
                if (selectedCityIndex != -1) {
                    // remove the city from the list
                    dataList.remove(selectedCityIndex);

                    // refresh the list view
                    cityAdapter.notifyDataSetChanged();

                    // reset selection
                    selectedCityIndex = -1;
                }
            }
        });
    }
}