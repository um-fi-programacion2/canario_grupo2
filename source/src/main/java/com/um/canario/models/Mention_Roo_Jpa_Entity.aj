// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.um.canario.models;

import com.um.canario.models.Mention;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Mention_Roo_Jpa_Entity {
    
    declare @type: Mention: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Mention.id;
    
    @Version
    @Column(name = "version")
    private Integer Mention.version;
    
    public Long Mention.getId() {
        return this.id;
    }
    
    public void Mention.setId(Long id) {
        this.id = id;
    }
    
    public Integer Mention.getVersion() {
        return this.version;
    }
    
    public void Mention.setVersion(Integer version) {
        this.version = version;
    }
    
}
