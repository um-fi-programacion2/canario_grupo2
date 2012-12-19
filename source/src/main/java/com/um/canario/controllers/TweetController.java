package com.um.canario.controllers;

import java.lang.System;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import com.um.canario.exceptions.ThisIsNotTheUserYouAreLookingForException;
import com.um.canario.models.Tweet;
import com.um.canario.models.Tweeter;
import com.um.canario.models.Following;
import com.um.canario.validators.TweetValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.security.core.context.SecurityContextHolder;

@RequestMapping("/tweet/**")
@Controller
public class TweetController {

    @RequestMapping(params="retweet=1", method=RequestMethod.POST)
    public String retweet(Model uiModel, HttpServletRequest request) {
        Tweet newTweet = new Tweet();
        Tweet tweet;
        Tweeter tweeter;

        try {
            tweeter = getTweeter();
        } catch (ThisIsNotTheUserYouAreLookingForException e) {
            return "redirect: tweet/index";
        }
        tweet = Tweet.findTweet(new Long(request.getParameter("tweet")));

        newTweet.setTweeter(tweeter);
        newTweet.setReTweet(tweet);
        newTweet.setDate(new java.util.Date());
        newTweet.persist();

        return "redirect:tweet/index";
    }

    @RequestMapping
    public String index(Model uiModel) {
        Set<Following> following;
        Tweeter user;
        List<Tweeter> tweeters = new ArrayList<Tweeter>();
        List<Tweet> tweets;
        int i = 0;
        try {
            user = getTweeter();
        } catch (ThisIsNotTheUserYouAreLookingForException e) {
            return "redirect: tweet/index";
        }
        following = user.getFollowing();
        for(Following f : following) {
            tweeters.add(f.getFollowed());
        }
        tweeters.add(user);

        tweets = Tweet.findTweetsFromTweeters(tweeters);
        uiModel.addAttribute("tweets", tweets);
        return "tweet/index";
    }

    @RequestMapping(value="/new", produces="text/html", method=RequestMethod.POST)
    public String newTweeterPost(@ModelAttribute Tweet tweet, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        new TweetValidator().validate(tweet, bindingResult);
        if(bindingResult.hasErrors()) {
            uiModel.addAttribute("tweet", tweet);
            return "tweet/new";
        }
        try {
            tweet.setTweeter(getTweeter());
        } catch (ThisIsNotTheUserYouAreLookingForException exception){
        	//TODO cambiar por página de error
            return "tweet/index";
        }
        tweet.setDate(new java.util.Date());
        
        uiModel.asMap().clear();
        tweet.persist();
    	return "redirect:tweet/index";
    }

    @RequestMapping(value="/new", produces="text/html")
    public String newTweet(Model uiModel) {
    	uiModel.addAttribute("tweet", new Tweet());
    	return "tweet/new";
    }

    private Tweeter getTweeter() throws ThisIsNotTheUserYouAreLookingForException {
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	Tweeter tweeter = Tweeter.findTweeterByUsername(username);
    	if(tweeter == null) {
    		throw new ThisIsNotTheUserYouAreLookingForException();
    	}
        return tweeter;
    }
}