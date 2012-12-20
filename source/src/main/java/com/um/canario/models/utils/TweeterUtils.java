package com.um.canario.models.utils;

import java.lang.System;
import com.um.canario.exceptions.ThisAreNotTheCredentialsYouAreLookingForException;
import com.um.canario.models.Tweeter;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;

public class TweeterUtils {

	public static Tweeter checkCredentials(String username, String password) {
		try {
			String hPassword;
			Tweeter tweeter = Tweeter.findTweeterByUsername(username);
			if (tweeter == null) {
				throw new ThisAreNotTheCredentialsYouAreLookingForException();
			}
			ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
			hPassword = encoder.encodePassword(password, null);
			if(!tweeter.getPassword().equals(hPassword)) {
				throw new ThisAreNotTheCredentialsYouAreLookingForException();	
			}

			return tweeter;
		} catch (ThisAreNotTheCredentialsYouAreLookingForException e) {
			return null;
		}
	}
}