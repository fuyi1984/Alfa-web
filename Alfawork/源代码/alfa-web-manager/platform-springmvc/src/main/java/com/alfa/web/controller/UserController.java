package com.alfa.web.controller;

import com.alfa.web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private Map<String,User> users = new HashMap<String,User>();

    public UserController() {
        users.put("sdy",new User("sdy","123","宋冬野","asss"));
        users.put("ldm",new User("ldm","123","刘东明","asss"));
        users.put("zyp",new User("zyp","123","周云蓬","asss"));
        users.put("zww",new User("zww","123","张玮玮","asss"));
        users.put("wt",new User("wt","123","吴吞","asss"));
    }

    @RequestMapping(value="/users",method= RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users",users);
        return "user/list";
    }
}
