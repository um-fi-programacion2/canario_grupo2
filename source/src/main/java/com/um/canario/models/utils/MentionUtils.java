package com.um.canario.models.utils;

import com.um.canario.exceptions.ThisIsNotTheUserYouAreLookingForException;
import java.lang.System;
import java.util.List;
import java.lang.Long;
import java.util.ArrayList;
import com.um.canario.models.Tweet;
import com.um.canario.models.Tweeter;
import com.um.canario.models.Mention;

public class MentionUtils {

	public static void parseMentions(Tweet tweet) throws ThisIsNotTheUserYouAreLookingForException {
		String auxContent = "";
		String aux;
		int index, i = 0;
		ArrayList<Tweeter> mentions = new ArrayList<Tweeter>();
		String finalContent = new String();
		boolean loop = true, atBegining = false;
		Mention mention;
		Tweeter tweeter;

		auxContent = tweet.getContent();
		while(loop) {
			if(i == 0) {
				index = auxContent.indexOf("@");
				if(index != 0) {
					index = auxContent.indexOf(" @");
				} else {
					atBegining = true;
				}
			} else {
				index = auxContent.indexOf(" @");
			}
			System.out.println("primero: " + index);
			if (index < 0) {
				loop=false;
				if(i == 0) {
					finalContent = auxContent;
				} else {
					finalContent += auxContent;
				}
				break;
			}
			if(!atBegining) {
				finalContent += auxContent.substring(0, index + 1);
			}
			auxContent = auxContent.substring(index + 1);
			System.out.println("left1: " + auxContent);
			index = auxContent.indexOf(" ");
			System.out.println("segundo: " + index);
			if(index < 0) {
				if(atBegining) {
					aux = auxContent;
					atBegining = false;
				} else {
					aux = auxContent.substring(1);
				}
				loop = false;
			}
			else {
				if(atBegining) {
					aux = auxContent.substring(0, index);
					auxContent = auxContent.substring(index);
					atBegining = false;
				}else {
					aux = auxContent.substring(1, index);
					auxContent = auxContent.substring(index);
				}
			}
			System.out.println("left2: " + auxContent);
			tweeter = Tweeter.findTweeterByUsername(aux);
			if(tweeter == null) {
				throw new ThisIsNotTheUserYouAreLookingForException();
			}
			//Agregar errores si no existe
			mentions.add(tweeter);
			finalContent += ("<a href='/tweeter?view=" + tweeter.getId() + "'>@" + aux + "</a>");
			i++;
		}
		for(Tweeter t : mentions) {
			mention = new Mention();
			mention.setTweet(tweet);
			mention.setTweeter(t);
			mention.persist();
		}
		System.out.println(finalContent);
		tweet.setContent(finalContent);
	}

}

