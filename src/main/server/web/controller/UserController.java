package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import web.exception.DBException;
import web.model.Role;
import web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import web.model.UserRole;
import web.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService  userService;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView printCurrentUser() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        String j_username = (String) session.getAttribute("j_username");
        String j_password = (String) session.getAttribute("j_password");
        User user = userService.getUserByNameAndPassword(j_username,  j_password);
        List<Role> roles = userService.getRolesByUser(user);
//        User user = (User) session.getAttribute("user");
//        String name = session.getParameter("name");
//        String password = session.getParameter("password");

        ModelAndView result = new ModelAndView("user");
        result.addObject(roles);
        result.addObject(user);

//        model.addAttribute("roles",roles);
        return result;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView printUsers() {
//        UserService userService = new UserService();
        List<UserRole> userRoles = userService.getAllUsersAndRoles();
        ModelAndView result = new ModelAndView("users");
        result.addObject("userRoles", userRoles);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView deleteUser(@RequestParam String id) {
        try {
            userService.deleteUser(Long.valueOf(id));
        }catch (DBException e){

        }
        List<User> users = userService.getAllUsers();
        ModelAndView result = new ModelAndView("/users");
        result.addObject("users", users);
        return result;
    }

    @RequestMapping(value = "/editing", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView editingUser(@RequestParam String edit_id) {
        User user = userService.getUserById(Long.valueOf(edit_id));
        ModelAndView result = new ModelAndView("/editUser");
        result.addObject("user", user);
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView editUser(@RequestParam String id, String name, String password, String email) {
        try {
            userService.editUser(Long.valueOf(id), name, password, email );
        }catch (DBException e){

        }
        List<User> users = userService.getAllUsers();
        ModelAndView result = new ModelAndView("/users");
        result.addObject("users", users);
        return result;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addUser(@RequestParam String name, String password, String email, String[] roles) {
        ModelAndView result = new ModelAndView("/users");
        try {
            User user = new User(name,password, email);
            userService.addUser(user);
        }catch (DBException e){

        }
        List<User> users = userService.getAllUsers();

        result.addObject("users", users);
        return result;
    }

    @RequestMapping(value = "addUser", method = RequestMethod.GET)
    public String printAdd(ModelMap model) {
        return "addUser";
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "hello";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public ModelAndView loginUser(@RequestParam String j_username, String j_password) {
//        ModelAndView result = new ModelAndView("/user");
//        User user = userService.findByUsername(j_username);
//        result.addObject("user", user);
//        return result;
//    }
}