package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.User;
import ra.model.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/userController")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping({"", "user"})
    public String getAll(Model model) {
        List<User> userList = userService.getAll();
        model.addAttribute("listUser", userList);
        System.out.println(userList.size());
        return "admin/user";
    }

    @PostMapping("/block")
    public String blockUser(@RequestParam int userId) {
        userService.updateUserBlock(userId);
        return "redirect:/userController/user";
    }

    @PostMapping("/unlock")
    public String unLock(@RequestParam int userId) {
        userService.updateUserUnlock(userId);
        return "redirect:/userController/user";
    }

    @GetMapping("/search")
    public String search(@RequestParam String name ,Model model){
        List<User> userList = userService.searchByName(name);
        model.addAttribute("listUser",userList);
        return "admin/user";
    }
}