// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.um.canario.models;

import com.um.canario.models.City;
import com.um.canario.models.Location;

privileged aspect Location_Roo_JavaBean {
    
    public City Location.getCity() {
        return this.city;
    }
    
    public void Location.setCity(City city) {
        this.city = city;
    }
    
    public String Location.getName() {
        return this.name;
    }
    
    public void Location.setName(String name) {
        this.name = name;
    }
    
}
