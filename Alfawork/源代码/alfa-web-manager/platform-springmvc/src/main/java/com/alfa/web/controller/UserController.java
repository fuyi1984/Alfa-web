package com.alfa.web.controller;

import com.alfa.web.model.User;
import com.alfa.web.model.UserException;
import com.alfa.web.pojo.SysUsers;
import com.alfa.web.service.sys.SysUsersService;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private SysUsersService sysUsersService;

    private Map<String, User> users = new HashMap<String, User>();

    public UserController() {
        users.put("sdy", new User("sdy", "123", "宋冬野", "asss"));
        users.put("ldm", new User("ldm", "123", "刘东明", "asss"));
        users.put("zyp", new User("zyp", "123", "周云蓬", "asss"));
        users.put("zww", new User("zww", "123", "张玮玮", "asss"));
        users.put("wt", new User("wt", "123", "吴吞", "asss"));
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String list(Model model) {

        /*Criteria criteria=new Criteria();
        List<SysUsers> SysUsersLst=this.sysUsersService.selectByParams(criteria);

        if (SysUsersLst.size()>0){
            for (SysUsers user:SysUsersLst) {
                users.put(user.getUsername(),user);
            }
        }*/

        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@ModelAttribute("user") User user) {
        return "user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Validated User user, BindingResult br) {
        if (br.hasErrors()) {
            return "user/add";
        }
        users.put(user.getUsername(), user);
        return "redirect:/user/users";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String show(@PathVariable String username,Model model) {
        model.addAttribute(users.get(username));
        return "user/show";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET,params = "json")
    @ResponseBody
    public User show(@PathVariable String username){
        return users.get(username);
    }

    @RequestMapping(value="/{username}/update",method=RequestMethod.GET)
    public String update(@PathVariable String username,Model model) {
        model.addAttribute(users.get(username));
        return "user/update";
    }

    @RequestMapping(value="/{username}/update",method=RequestMethod.POST)
    public String update(@PathVariable String username,@Validated User user,BindingResult br) {
        if(br.hasErrors()) {
            //如果有错误直接跳转到add视图
            return "user/update";
        }
        users.put(username, user);
        return "redirect:/user/users";
    }

    @RequestMapping(value="/{username}/delete",method=RequestMethod.GET)
    public String delete(@PathVariable String username) {
        users.remove(username);
        return "redirect:/user/users";
    }

    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String login(String username,String password,HttpSession session) {
        if(!users.containsKey(username)) {
            throw new UserException("用户名不存在");
        }
        User u = users.get(username);
        if(!u.getPassword().equals(password)) {
            throw new UserException("用户密码不正确");
        }
        session.setAttribute("loginUser", u);
        return "redirect:/user/users";
    }
}
