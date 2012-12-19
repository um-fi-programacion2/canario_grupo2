package com.um.canario.models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Tweet {

    private String content;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date date;

    @ManyToOne
    private Location location;

    @NotNull
    @ManyToOne
    private Tweeter tweeter;

    @ManyToOne(fetch = FetchType.EAGER)
    private com.um.canario.models.Tweet reTweet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tweet")
    private Set<HashMention> hashes = new HashSet<HashMention>();

    public static List<com.um.canario.models.Tweet> findTweetsFromTweeters(List<com.um.canario.models.Tweeter> tweeters) {
        List<Tweet> tweets;
        Query q;
        q = entityManager().createQuery("SELECT o FROM Tweet o WHERE tweeter in (?1) ORDER BY date DESC", Tweet.class);
        q.setParameter(1, tweeters);
        tweets = q.getResultList();
        return tweets;
    }
}
