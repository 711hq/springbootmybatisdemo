package com.etc.controller;

import com.etc.entity.User;
import com.etc.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/userlogin")
    public ModelAndView userlogin(@Param("uname") String uname, @Param("pwd") String pwd, HttpSession session, HttpServletResponse res){
        ModelAndView mv = new ModelAndView(new InternalResourceView("/userquery"));
        if (uname == null && pwd == null){
            mv.setViewName("login");
            return mv;
        }

        User u = new User();
        u.setUname(uname);
        u.setPwd(pwd);
        User u1 = userService.loginquery(u);
        if(u1 != null){//登录成功：
            session.setAttribute("user",u);
        }
        else {
            mv.setViewName("login");
            mv.addObject("error","用户名密码错误！");
        }
        return mv;
    }


    @RequestMapping("/userquery")
    public ModelAndView query(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        ModelAndView mv = new ModelAndView("userquery");
        mv.addObject("p",userService.findPage(pageNum,pageSize));
        return mv;
    }

    @RequestMapping("/usertoadd")
    public ModelAndView toadd(){
        ModelAndView mv = new ModelAndView("useradd");
        return mv;
    }


    @RequestMapping("/useradd")
    public ModelAndView add(User u){
      //  ModelAndView mv = new ModelAndView("useradd");
        userService.add(u);
        ModelAndView mv = new ModelAndView(new RedirectView("userget/"+u.getUid()));//重定向到另一个url影射
        mv.addObject("msg","新加成功");
        return mv;
    }

    @RequestMapping("/userget/{uid}")
    public ModelAndView get(@PathVariable int uid){
        ModelAndView mv = new ModelAndView("userget");
        mv.addObject("user",userService.selectByUid(uid));
      //  mv.addObject("user",userService.findByCondition(u));
        return mv;
    }

    @RequestMapping("/usermod")
    public ModelAndView mod(User u){
        ModelAndView mv = new ModelAndView(new InternalResourceView("userget/"+u.getUid()));//转发向到另一个url影射
        userService.mod(u);
        mv.addObject("msg","修改成功");
        return mv;
    }
    @RequestMapping("/userdel/{uid}")
    public ModelAndView del(@PathVariable Integer uid){
        ModelAndView mv = new ModelAndView(new InternalResourceView("/userquery"));//转发向到另一个url影射
      userService.remove(uid);
        mv.addObject("msg","删除成功");
        return mv;
    }


}
