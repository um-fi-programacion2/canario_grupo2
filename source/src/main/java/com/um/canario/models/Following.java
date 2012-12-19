package com.um.canario.models;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Following {

    @NotNull
    @ManyToOne
    private Tweeter follower;

    @NotNull
    @ManyToOne
    private Tweeter followed;
}
