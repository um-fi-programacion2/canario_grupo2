// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.um.canario.models;

import com.um.canario.models.Tweeter;
import java.util.Date;

privileged aspect Tweeter_Roo_JavaBean {
    
    public Boolean Tweeter.getLocation() {
        return this.location;
    }
    
    public void Tweeter.setLocation(Boolean location) {
        this.location = location;
    }
    
    public String Tweeter.getUsername() {
        return this.username;
    }
    
    public void Tweeter.setUsername(String username) {
        this.username = username;
    }
    
    public String Tweeter.getName() {
        return this.name;
    }
    
    public void Tweeter.setName(String name) {
        this.name = name;
    }
    
    public String Tweeter.getLname() {
        return this.lname;
    }
    
    public void Tweeter.setLname(String lname) {
        this.lname = lname;
    }
    
    public String Tweeter.getPhoto_url() {
        return this.photo_url;
    }
    
    public void Tweeter.setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
    
    public Date Tweeter.getBirth_date() {
        return this.birth_date;
    }
    
    public void Tweeter.setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }
    
}