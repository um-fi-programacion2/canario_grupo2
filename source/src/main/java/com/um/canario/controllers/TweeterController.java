package com.um.canario.controllers;

import java.util.List;
import com.um.canario.models.Tweet;
import com.um.canario.models.Tweeter;
import com.um.canario.validators.TweeterValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

@RequestMapping("/tweeter/**")
@Controller
public class TweeterController {

    @RequestMapping(params="view", produces="text/html")
    public String view(Model uiModel, HttpServletRequest request) {
        Tweeter tweeter = Tweeter.findTweeter(new Long(request.getParameter("view")));
        uiModel.addAttribute("tweeter", tweeter);
        return "tweeter/view";
    }

    @RequestMapping
    public String index() {
        return "tweeter/index";
    }

    @RequestMapping(value="/new", produces="text/html", method=RequestMethod.POST)
    public String newTweeterPost(@ModelAttribute Tweeter tweeter, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        new TweeterValidator().validate(tweeter, bindingResult, httpServletRequest.getParameter("rePassword"));
        if(bindingResult.hasErrors()) {
            uiModel.addAttribute("tweeter", tweeter);
            return "tweeter/new";
        }
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
        tweeter.setPassword(encoder.encodePassword(tweeter.getPassword(), null));
        tweeter.setAuthority("ROLE_USER");
        tweeter.setAllowGeolocation(true);
        tweeter.setEnabled(true);
        uiModel.asMap().clear();
        tweeter.persist();
        return "redirect:/site/login";
    }

    @RequestMapping(value="/new", produces="text/html")
    public String newTweeter(Model uiModel){
    	uiModel.addAttribute("tweeter", new Tweeter());
    	return "tweeter/new";
    }
}
