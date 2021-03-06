// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.um.canario.models;

import com.um.canario.models.Following;
import com.um.canario.models.Mention;
import com.um.canario.models.Tweet;
import com.um.canario.models.Tweeter;
import java.util.Date;
import java.util.Set;

privileged aspect Tweeter_Roo_JavaBean {
    
    public String Tweeter.getUsername() {
        return this.username;
    }
    
    public void Tweeter.setUsername(String username) {
        this.username = username;
    }
    
    public String Tweeter.getEmail() {
        return this.email;
    }
    
    public void Tweeter.setEmail(String email) {
        this.email = email;
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
    
    public String Tweeter.getPhotoUrl() {
        return this.photoUrl;
    }
    
    public void Tweeter.setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    
    public Boolean Tweeter.getAllowGeolocation() {
        return this.allowGeolocation;
    }
    
    public void Tweeter.setAllowGeolocation(Boolean allowGeolocation) {
        this.allowGeolocation = allowGeolocation;
    }
    
    public String Tweeter.getPassword() {
        return this.password;
    }
    
    public void Tweeter.setPassword(String password) {
        this.password = password;
    }
    
    public Date Tweeter.getBirthDate() {
        return this.birthDate;
    }
    
    public void Tweeter.setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    public Boolean Tweeter.getEnabled() {
        return this.enabled;
    }
    
    public void Tweeter.setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
    public String Tweeter.getAuthority() {
        return this.authority;
    }
    
    public void Tweeter.setAuthority(String authority) {
        this.authority = authority;
    }
    
    public Set<Tweet> Tweeter.getTweets() {
        return this.tweets;
    }
    
    public void Tweeter.setTweets(Set<Tweet> tweets) {
        this.tweets = tweets;
    }
    
    public Set<Following> Tweeter.getFollowed() {
        return this.followed;
    }
    
    public void Tweeter.setFollowed(Set<Following> followed) {
        this.followed = followed;
    }
    
    public Set<Following> Tweeter.getFollowing() {
        return this.following;
    }
    
    public void Tweeter.setFollowing(Set<Following> following) {
        this.following = following;
    }
    
    public Set<Mention> Tweeter.getMentions() {
        return this.mentions;
    }
    
    public void Tweeter.setMentions(Set<Mention> mentions) {
        this.mentions = mentions;
    }
    
}
