package com.alfa.web.controller;

import com.alfa.web.model.User;
import com.alfa.web.pojo.SysUsers;
import com.alfa.web.service.sys.SysUsersService;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private SysUsersService sysUsersService;

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

        /*Criteria criteria=new Criteria();
        List<SysUsers> SysUsersLst=this.sysUsersService.selectByParams(criteria);

        if (SysUsersLst.size()>0){
            for (SysUsers user:SysUsersLst) {
                users.put(user.getUsername(),user);
            }
        }*/

        model.addAttribute("users",users);
        return "user/list";
    }

    @RequestMapping(value="/add",method=RequestMethod.GET)
    public String add(@ModelAttribute("user")User user){
        return "user/add";
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String add(@Validated User user,BindingResult br){
        if(br.hasErrors()){
            return "user/add";
        }
        users.put(user.getUsername(), user);
        return "redirect:/user/users";
    }
}
