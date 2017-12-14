package com.shoponline.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shoponline.domain.User;
import com.shoponline.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping(value = "/register")
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/updata")
    public String updata(HttpSession session) {
        if (session.getAttribute("nowUser") == null){
            return "login";
        }
        return "updata";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/main")
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/control")
    public String control(HttpSession session) {
        User nowuser = (User)session.getAttribute("nowUser");
        if (nowuser != null){
            if (nowuser.getRole() == 1){
                return "control";
            }
        }
        return "main";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doLogin(String usernameOrEmail, String password, HttpSession Session) {
        String result;
        User user = userService.getUser(usernameOrEmail);
        if (user == null)
            result = "unexist";
        else {
            if (user.getPassword().equals(password)) {
                result = "success";
                Session.setAttribute("nowUser", user);
            } else
                result = "wrong";
        }
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("result", result);
        return resultMap;
    }

    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doRegister(String username, String password, String email, String phone, String address) {
        String result;
        User user = userService.getUser(username);
        if (user != null) {
            result = "nameExist";
        } else {
            user = userService.getUser(email);
            if (user != null)
                result = "emailExist";
            else {
                User user1 = new User();
                user1.setUsername(username);
                user1.setPassword(password);
                user1.setEmail(email);
                user1.setPhone(phone);
                user1.setAddress(address);
                user1.setRole(0);
                userService.addUser(user1);
                result = "success";
            }
        }
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("result", result);
        return resultMap;
    }

    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doUpdate(String username, String password, String email, String phone, String address) {
        String result;
        User user = userService.getUser(username);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        userService.updateUser(user);
        result = "success";
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("result", result);
        return resultMap;
    }

    @RequestMapping(value = "/getAllUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getAllUser() {
        List<User> userList;
        userList = userService.getAllUser();
        String allUsers = JSONArray.toJSONString(userList);
        Map<String,Object> resultMap = new HashMap();
        resultMap.put("allUsers",allUsers);
        return resultMap;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteUser(int id) {
        String result ="fail";
        if(userService.deleteUser(id)){
            if(userService.deleteUser(id)){
                result="success";
            }
        }
        Map<String,Object> resultMap = new HashMap();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/getAddressAndPhone", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getAddressAndPhone(int id) {
        String address = userService.getUser(id).getAddress();
        String phone = userService.getUser(id).getPhone();
        Map<String,Object> resultMap = new HashMap();
        resultMap.put("address",address);
        resultMap.put("phone",phone);
        return resultMap;
    }

    @RequestMapping(value = "/doLogout")
    public String doLogout(HttpSession httpSession){
        httpSession.setAttribute("nowUser","");
        return "redirect:login";
    }

    @RequestMapping(value = "/getUserById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUserById(int id) {
        User user = userService.getUser(id);
        String result = JSON.toJSONString(user);
        Map<String,Object> resultMap = new HashMap();
        resultMap.put("result",result);
        return resultMap;
    }
}
