// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.um.canario.models;

import com.um.canario.models.City;
import com.um.canario.models.Country;

privileged aspect City_Roo_JavaBean {
    
    public Country City.getCountry() {
        return this.country;
    }
    
    public void City.setCountry(Country country) {
        this.country = country;
    }
    
    public String City.getName() {
        return this.name;
    }
    
    public void City.setName(String name) {
        this.name = name;
    }
    
}