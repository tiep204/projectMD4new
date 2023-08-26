package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.dto.UserLoginDTO;
import ra.model.entity.User;
import ra.model.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String getAll(Model model) {
        List<User> userList = userService.getAll();
        model.addAttribute("listUser", userList);
        System.out.println(userList.size());
        return "admin/user";
    }

    @PostMapping("/block")
    public String blockUser(@RequestParam int userId) {
        userService.updateUserBlock(userId);
        return "redirect:/user";
    }

    @PostMapping("/unlock")
    public String unLock(@RequestParam int userId) {
        userService.updateUserUnlock(userId);
        return "redirect:/user";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        model.addAttribute("userlogin", userLoginDTO);
        return "login";
    }

    @PostMapping("/loginn")
    public String handleLogin(@ModelAttribute("userlogin") UserLoginDTO userLoginDTO, HttpSession session) {
        userService.login(userLoginDTO.getUsername(),userLoginDTO.getPassword());
        if (userLoginDTO.getRole() == 1) {
            return "/admin/index";
        } else {
            if (userLoginDTO.isStatus() == true) {
                return "/admin/uu";
            } else {
                return "/admin/error";
            }
        }
    }
        // đăng nhập thành công

        /*session.setAttribute("cart",new ArrayList<>());*/

}