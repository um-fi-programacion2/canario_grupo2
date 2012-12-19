package com.um.canario.models;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Hash {

    @NotNull
    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hash")
    private Set<HashMention> tweets = new HashSet<HashMention>();



    public static Hash findHash(String name, boolean create) {
    	Hash hash;
    	List<Hash> hashes = entityManager().createQuery("FROM Hash WHERE name = ?", Hash.class).setParameter(1, name).getResultList();
        if(create) {
        	if (hashes.isEmpty()) {
        		hash = new Hash();
        		hash.setName(name);
        		hash.persist();
        		hash.flush();
        	} else {
                hash = hashes.get(0);
            }
        }
        else {
            if(hashes.isEmpty()) {
                hash = null;
            }
            else {
                hash = hashes.get(0);
            }
        }
    	return hash;
    }

    public static Hash findHash(String name) {
        return findHash(name, false);
    }
}
