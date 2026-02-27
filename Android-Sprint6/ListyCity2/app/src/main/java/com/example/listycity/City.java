package com.example.listycity;

/**
 * this is a class that defines a city
 */
public class City implements Comparable<City> {
    private String city;
    private String province;

    /**
     * constructor to make a new city object
     * @param city the name of the city
     * @param province the province code
     */
    City(String city, String province){
        this.city = city;
        this.province = province;
    }

    /**
     * gets the city name
     * @return string city name
     */
    String getCityName(){
        return this.city;
    }

    /**
     * gets the province name
     * @return string province name
     */
    String getProvinceName(){
        return this.province;
    }

    /**
     * compares two cities by name for sorting
     * @param city the other city
     * @return int comparison result
     */
    @Override
    public int compareTo(City city) {
        return this.city.compareTo(city.getCityName());
    }

    /**
     * checks if two cities are exactly the same
     * needed for hasCity to work
     * @param o the object to compare
     * @return true if equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city1 = (City) o;
        return city.equals(city1.city) && province.equals(city1.province);
    }

    /**
     * generates hash id for the city
     * @return hashcode int
     */
    @Override
    public int hashCode() {
        return city.hashCode() + province.hashCode();
    }
}