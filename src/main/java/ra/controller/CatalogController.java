package ra.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Category;
import ra.model.service.CategoryService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/")
public class CatalogController {
    @Autowired
    private CategoryService categoryService;
    private static final Gson GSON = new GsonBuilder().create();

    @GetMapping({"/", "category"})
    public String getAllCatalog(Model model) {
        List<Category> catalogList = categoryService.getAll();
        model.addAttribute("listCatalog", catalogList);
        return "admin/category";
    }

    @GetMapping ("edit/{id}")
    public void findById(HttpServletResponse response, @PathVariable("id") int id){
        // gọi qua database để lấy dữ liệu
        Category category = categoryService.getById(id);
        String data = GSON.toJson(category);
        response.setHeader("Content-Type","application/json");
        response.setStatus(200);
        PrintWriter out=null;
        try {
            out = response.getWriter();
            out.write(data);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            out.close();
        }
    }
    @PostMapping("/update")
    public String updateCate(@ModelAttribute Category category){
        categoryService.saveOfUpdate(category);
        return "redirect:/category";
    }


    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.saveOfUpdate(category);
        return "redirect:/category";
    }



    @PostMapping("/delete")
    public String deleteCategory(@RequestParam("catalogId") Integer categoryId) {
        categoryService.delete(categoryId);
        return "redirect:/category";
    }
    @GetMapping("/search")
    public String searchCategory(@RequestParam("name") String name, Model model) {
        List<Category> searchResult = categoryService.searchByName(name);
        model.addAttribute("listCatalog", searchResult);
        return "admin/category"; // Đổi thành tên view của bạn nếu cần
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