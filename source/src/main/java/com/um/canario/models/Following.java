package com.um.canario.models;

import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.EntityManager;

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

    public static Following findFollowingByFollowedAndFollower(Tweeter followed, Tweeter follower) {
    	List<Following> follows = entityManager().createQuery("SELECT o FROM Following o WHERE followed=? AND follower = ?", Following.class).setParameter(1, followed).setParameter(2, follower).getResultList();
    	if (follows.isEmpty()) {
    		return null;
    	}
    	return follows.get(0);
	}
    public static boolean isFollowing(Tweeter followed, Tweeter follower) {
    	List<Following> follows = entityManager().createQuery("SELECT o FROM Following o WHERE followed=? AND follower = ?", Following.class).setParameter(1, followed).setParameter(2, follower).getResultList();
    	if (follows.isEmpty()) {
    		return false;
    	}
    	return true;
    }
}
