package com.example.geographyquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Country {
    private String countryName, capitalCityName;

    public Country(String countryName, String capitalCityName) {
        this.countryName = countryName;
        this.capitalCityName = capitalCityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCapitalCityName() {
        return capitalCityName;
    }
}

public class MainActivity extends AppCompatActivity {

    private TextView countryTextView;
    private ListView capitalListView;
    private ArrayList<Country> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryTextView = findViewById(R.id.countryTextView);
        capitalListView = findViewById(R.id.listView);

        readFile();

        final List<Country> pickedRandomlyCountries = pickRandomCountries();
        final Country quizCountry = pickedRandomlyCountries.get(0);

        updateDisplay(pickedRandomlyCountries, quizCountry);

        capitalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView capitalCity = (TextView) view;

                if (capitalCity.getText().toString().equals(quizCountry.getCapitalCityName())) {
                    Toast.makeText(MainActivity.this, "Succes, your geography done !", Toast.LENGTH_SHORT).show();

                    final List<Country> pickedRandomlyCountries = pickRandomCountries();
                    updateDisplay(pickedRandomlyCountries, pickedRandomlyCountries.get(0));
                } else {
                    Toast.makeText(MainActivity.this, "You're a dick", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void updateDisplay(List<Country> pickedRandomlyCountries, Country quizCountry) {

        // Update the UI
        countryTextView.setText(quizCountry.getCountryName());

        final List<String> capitalCities = new ArrayList<>();

        for (Country country : pickedRandomlyCountries) {
            capitalCities.add(country.getCapitalCityName());
        }
        Collections.shuffle(capitalCities);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                capitalCities
        );

        capitalListView.setAdapter(adapter);


    }

    private void readFile() {
        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.countries_capitals));
        if (countries == null) {
            countries = new ArrayList<>();
        }

        while (scanner.hasNextLine()) {
            String[] rawDetails = scanner.nextLine().split(",");
            Country country = new Country(rawDetails[0], rawDetails[1]);

            countries.add(country);
        }
        scanner.close();
    }

    private List<Country> pickRandomCountries() {
        List<Country> pickedCountries = new ArrayList<>();
        Random random = new Random();

        while(pickedCountries.size() != 5) {
            Country randomyPickedCountry = countries.get(random.nextInt(countries.size()));

            if (!pickedCountries.contains(randomyPickedCountry)) {
                pickedCountries.add(randomyPickedCountry);
            }
        }

        return pickedCountries;
    }
}