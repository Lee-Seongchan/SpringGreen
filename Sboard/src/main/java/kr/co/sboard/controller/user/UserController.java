package kr.co.sboard.controller.user;

import kr.co.sboard.dto.TermsDTO;
import kr.co.sboard.entity.TermsEntity;
import kr.co.sboard.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/login")
    public String login(){
        return "/user/login";
    }


    @GetMapping("/user/terms")
    public String terms(Model model){
        TermsEntity terms = userService.findByTerms();

        log.debug("terms = " + terms);

        model.addAttribute(terms);
        return "/user/terms";
    }
}
