// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.um.canario.models;

import com.um.canario.models.Hash;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Hash_Roo_Jpa_Entity {
    
    declare @type: Hash: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Hash.id;
    
    @Version
    @Column(name = "version")
    private Integer Hash.version;
    
    public Long Hash.getId() {
        return this.id;
    }
    
    public void Hash.setId(Long id) {
        this.id = id;
    }
    
    public Integer Hash.getVersion() {
        return this.version;
    }
    
    public void Hash.setVersion(Integer version) {
        this.version = version;
    }
    
}
