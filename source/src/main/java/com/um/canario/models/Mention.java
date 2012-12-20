package com.um.canario.models;

import javax.persistence.ManyToOne;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Mention {

    @ManyToOne
    private Tweeter tweeter;

    @ManyToOne
    private Tweet tweet;
}
