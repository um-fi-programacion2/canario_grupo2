package com.um.canario.models.utils;

import java.util.List;
import java.lang.Long;
import java.util.ArrayList;
import com.um.canario.models.Tweet;
import com.um.canario.models.Hash;
import com.um.canario.models.HashMention;

public class HashUtils {

	public static void parseHashes(Tweet tweet) {
		String auxContent = "";
		String aux;
		int index, i = 0;
		ArrayList<Hash> hashes = new ArrayList<Hash>();
		String finalContent = new String();
		boolean loop = true, atBegining = false;
		HashMention hMention;

		auxContent = tweet.getContent();
		while(loop) {
			if(i == 0) {
				index = auxContent.indexOf("#");
				if(index != 0) {
					index = auxContent.indexOf(" #");
				} else {
					atBegining = true;
				}
			} else {
				index = auxContent.indexOf(" #");
			}
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
			index = auxContent.indexOf(" ");
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
			hashes.add(Hash.findHash(aux, true));
			finalContent += ("<a href='/tweet?hash=" + aux + "'>#" + aux + "</a>");
			i++;
		}
		for(Hash hash : hashes) {
			hMention = new HashMention();
			hMention.setTweet(tweet);
			hMention.setHash(hash);
			hMention.persist();
		}
		tweet.setContent(finalContent);
	}

}

