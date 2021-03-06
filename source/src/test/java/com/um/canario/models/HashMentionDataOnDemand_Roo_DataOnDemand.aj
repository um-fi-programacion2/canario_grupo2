// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.um.canario.models;

import com.um.canario.models.HashDataOnDemand;
import com.um.canario.models.HashMention;
import com.um.canario.models.HashMentionDataOnDemand;
import com.um.canario.models.TweetDataOnDemand;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect HashMentionDataOnDemand_Roo_DataOnDemand {
    
    declare @type: HashMentionDataOnDemand: @Component;
    
    private Random HashMentionDataOnDemand.rnd = new SecureRandom();
    
    private List<HashMention> HashMentionDataOnDemand.data;
    
    @Autowired
    private HashDataOnDemand HashMentionDataOnDemand.hashDataOnDemand;
    
    @Autowired
    private TweetDataOnDemand HashMentionDataOnDemand.tweetDataOnDemand;
    
    public HashMention HashMentionDataOnDemand.getNewTransientHashMention(int index) {
        HashMention obj = new HashMention();
        return obj;
    }
    
    public HashMention HashMentionDataOnDemand.getSpecificHashMention(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        HashMention obj = data.get(index);
        Long id = obj.getId();
        return HashMention.findHashMention(id);
    }
    
    public HashMention HashMentionDataOnDemand.getRandomHashMention() {
        init();
        HashMention obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return HashMention.findHashMention(id);
    }
    
    public boolean HashMentionDataOnDemand.modifyHashMention(HashMention obj) {
        return false;
    }
    
    public void HashMentionDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = HashMention.findHashMentionEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'HashMention' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<HashMention>();
        for (int i = 0; i < 10; i++) {
            HashMention obj = getNewTransientHashMention(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
