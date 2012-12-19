package com.um.canario.validators;

import java.util.Date;
import com.um.canario.models.Tweet;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class TweetValidator {

    public void validate(Tweet tweet, Errors errors) {
        String content = tweet.getContent();
        
        if (!StringUtils.hasText(content)) {
            errors.rejectValue("content", "required", "El Tweet no puede estar vacío");
        }
        else if(content.length() > 200){
        	errors.rejectValue("content", "too_long", "El Tweet no puede tener más de caracteres");	
        }
    }

}