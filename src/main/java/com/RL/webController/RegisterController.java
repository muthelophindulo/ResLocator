package com.RL.webController;

import com.RL.auth.UserEntity;
import com.RL.auth.UserRepository;
import com.RL.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user",new UserEntity());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserEntity user, RedirectAttributes redirectAttributes,Model model){
       try {
           if(userService.saveUser(user) != null){
               redirectAttributes.addAttribute("successMessage","user added");
               model.addAttribute("successMessage","user added");
               return "redirect:/registration";
           }
       } catch (Exception e) {
           redirectAttributes.addAttribute("errorMessage","error while saving user");
           model.addAttribute("errorMessage","error while saving user");
           System.out.println(e.toString());
           return "redirect:/registration";
       }
       return "redirect:/registration";
    }
}
