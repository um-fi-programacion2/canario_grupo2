package com.um.canario.validators;

import java.util.Date;
import java.util.ArrayList;
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

    public ArrayList<String> validate(Tweet tweet) {
        String content = tweet.getContent();
        ArrayList<String> errors = new ArrayList<String>();
        if (!StringUtils.hasText(content)) {
            errors.add("El Tweet no puede estar vacío");
        }
        else if(content.length() > 200){
        	errors.add("El Tweet no puede tener más de caracteres");	
        }
        return errors;
    }

}