package com.um.canario.controllers;

import java.lang.System;
import java.util.List;
import java.util.ArrayList;
import com.um.canario.models.Tweet;
import com.um.canario.models.Tweeter;
import com.um.canario.models.Hash;
import com.um.canario.models.HashMention;
import com.um.canario.models.utils.Message;
import com.um.canario.models.utils.TweeterUtils;
import com.um.canario.models.utils.HashUtils;
import com.um.canario.models.utils.MentionUtils;
import com.um.canario.validators.TweetValidator;
import com.um.canario.exceptions.ThisIsNotTheUserYouAreLookingForException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.DataBinder;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/api/tweet/**")
@Controller
public class TweetApiController {

    @RequestMapping
    public @ResponseBody List<Tweet> index(HttpServletResponse response) {
    	response.setContentType("application/json");
    	List<Tweet> tweets =  Tweet.findLastTweets();
    	for(Tweet t : tweets){
    		t.setTweeter(null);
    		t.setMentions(null);
    		t.setHashes(null);
    		if(t.getReTweet() != null) {
				t.getReTweet().setTweeter(null);
    			t.getReTweet().setMentions(null);
    			t.getReTweet().setHashes(null);
			}
    	}
    	return tweets;

    }

    @RequestMapping(params="user")
    public @ResponseBody Object user(HttpServletResponse response, HttpServletRequest request) {
    	response.setContentType("application/json");
    	List<Tweet> tweets;
    	Tweeter tweeter = Tweeter.findTweeterByUsername(request.getParameter("user"));
    	if(tweeter == null) {
    		return new Message("error", "Tweeter does not exist");
    	} else {
    		tweets = new ArrayList<Tweet>(tweeter.getTweets());
    		for(Tweet t : tweets){
	    		t.setTweeter(null);
    			t.setMentions(null);
    			t.setHashes(null);
    			if(t.getReTweet() != null) {
    				t.getReTweet().setTweeter(null);
	    			t.getReTweet().setMentions(null);
	    			t.getReTweet().setHashes(null);
    			}
    		}
    	}
    	return tweets;
    }

    @RequestMapping(params="hash")
    public @ResponseBody Object hash(HttpServletResponse response, HttpServletRequest request) {
    	response.setContentType("application/json");
    	List<HashMention> hMentions;
    	ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    	Tweet t;
    	Hash hash = Hash.findHash(request.getParameter("hash"));

    	if(hash == null) {
    		return new Message("error", "Hash does not exist");
    	} else {
    		hMentions = new ArrayList<HashMention>(hash.getTweets());
    		System.out.println(hMentions.size());
    		for(HashMention h : hMentions){
    			t = h.getTweet();
	    		t.setTweeter(null);
    			t.setMentions(null);
    			t.setHashes(null);
    			if(t.getReTweet() != null) {
    				t.getReTweet().setTweeter(null);
	    			t.getReTweet().setMentions(null);
	    			t.getReTweet().setHashes(null);
    			}
    			tweets.add(t);
    		}
    	}
    	return tweets;
    }

    @RequestMapping(params="new", method=RequestMethod.POST)
    public @ResponseBody Message create(HttpServletResponse response, HttpServletRequest request) {
    	response.setContentType("application/json");
	    String[] strings;
	    String content;
	    ArrayList<String> errors = new ArrayList<String>();
	    Tweeter tweeter;
	    Tweet tweet = new Tweet();
        tweeter = TweeterUtils.checkCredentials(request.getParameter("username"), request.getParameter("password"));
	    if(tweeter == null) {
	    	return new Message("error", "Usuario o Contraseña Incorrectos");
	    }
        tweet.setTweeter(tweeter);
        System.out.println(request.getParameter("content"));
	    tweet.setContent(request.getParameter("content"));
	    errors = new TweetValidator().validate(tweet);
	    if(!errors.isEmpty()) {
	    	return new Message("error", errors.toString());
	    }
	    tweet.setDate(new java.util.Date());
	    tweet.persist();
	    tweet.flush();
	    try {
	        MentionUtils.parseMentions(tweet);
	    } catch (ThisIsNotTheUserYouAreLookingForException e) {
	        tweet.remove();
	        tweet.flush();
	        return new Message("error", "Todos los usuarios mencionados deben existir");
	    }
	    HashUtils.parseHashes(tweet);

	    tweet.persist();
    	return new Message("success", "Tweet creado");
    }
}
