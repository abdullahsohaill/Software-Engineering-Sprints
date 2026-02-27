package com.example.listycity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * this class keeps a list of city objects
 */
public class CityList {
    private List<City> cities = new ArrayList<>();

    /**
     * adds a city to the list if it doesn't exist
     * @param city candidate city to add
     */
    public void add(City city) {
        if (cities.contains(city)) {
            throw new IllegalArgumentException();
        }
        cities.add(city);
    }

    /**
     * returns a sorted list of cities
     * @return the sorted list
     */
    public List<City> getCities() {
        List<City> list = cities;
        Collections.sort(list);
        return list;
    }

    /**
     * checks if a city belongs in the list
     * @param city the city to check
     * @return true if found
     */
    public boolean hasCity(City city) {
        return cities.contains(city);
    }

    /**
     * deletes a city from the list
     * throws exception if not found
     * @param city the city to remove
     */
    public void delete(City city) {
        if (!cities.contains(city)) {
            throw new IllegalArgumentException();
        }
        cities.remove(city);
    }

    /**
     * counts total cities in list
     * @return number of cities
     */
    public int countCities() {
        return cities.size();
    }
}