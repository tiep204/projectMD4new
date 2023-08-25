package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping({"/index.html", "/", "/hahahahaha"})
    public String index() {
        return "admin/index";
    }

    @GetMapping("/product")
    public String product() {
        return "admin/product";
    }

    @GetMapping("/category")
    public String category() {
        return "admin/category";
    }

    @GetMapping("/contact")
    public String contact() {
        return "admin/contact";
    }

    @GetMapping("/user")
    public String user() {
        return "admin/user";
    }

    @GetMapping("/error")
    public String error() {
        return "admin/error";
    }
}