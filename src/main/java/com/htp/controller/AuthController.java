package com.htp.controller;

import com.htp.domain.jdbc.User;
import com.htp.repository.jdbc.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserDao userService;

    //@Autowired
    //private UserValidator userValidator;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/sign_in", method = RequestMethod.GET)
    public String getSignIn(@RequestParam(value = "error", required = false) Boolean error,
                            Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", error);
        }
        return "/WEB-INF/templates/user/sign_in.html";
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.GET)
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "user/sign_up";
    }

//    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
//    public String signUp(@ModelAttribute("user") @Valid User user,
//                         BindingResult result) {
//        userValidator.validate(user, result);
//        if (result.hasErrors()) {
//            return "user/sign_up";
//        }
//        userService.add(userDao.findById(user.getUserId()));
//        return "redirect:/sign_in";
//    }

}
