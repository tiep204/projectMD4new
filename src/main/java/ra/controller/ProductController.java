package ra.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ra.dto.ProductDTO;
import ra.model.entity.Category;
import ra.model.entity.Product;
import ra.model.service.CategoryService;
import ra.model.service.ProductService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
@PropertySource("classpath:upload.properties")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Value("${upload-path}")
    private String pathUpload;
    @Autowired
    private CategoryService categoryService;
    private static final Gson GSON = new GsonBuilder().create();

    @GetMapping("/product")
    public String getAll(Model model) {
        List<Category> categoryList = categoryService.getAll();
        List<Product> productList = productService.getAll();
        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("product", productDTO);
        model.addAttribute("listCatalog", categoryList);
        model.addAttribute("listProduct", productList);
        return "admin/product";
    }

    @GetMapping("/addProduct")
    public ModelAndView upload() {
        return new ModelAndView("admin/product", "product", new ProductDTO());
    }

    @PostMapping("/create")
    public String doUpload(@ModelAttribute("product") ProductDTO productDTO) {
        System.out.println("------------------------------" + productDTO.getProductName());
        // upload file
        File file = new File(pathUpload);
        if (!file.exists()) {
            // chưa tồn tại folder , khởi tạo 1 folder mới
            file.mkdirs();
        }
        String fileName = productDTO.getImage().getOriginalFilename();
        try {
            FileCopyUtils.copy(productDTO.getImage().getBytes(), new File(pathUpload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //upload listImage
        //luu tru danh sach duong dan cua anh
        List<String> fileListImage = new ArrayList<>();
        for (MultipartFile multipartFile : productDTO.getListImage()) {
            String fileNameListImage = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(pathUpload + fileNameListImage));
                fileListImage.add(fileNameListImage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // chuyen doi thanh doi tuong video
        Product newVideo = new Product();
        newVideo.setProductName(productDTO.getProductName());
        newVideo.setCategory(productDTO.getCategoryId());
        newVideo.setDescription(productDTO.getDescription());
        newVideo.setPrice(productDTO.getPrice());
        newVideo.setImage(fileName);
        newVideo.setStock(productDTO.getStock());
        newVideo.setStatus(productDTO.isStatus());
        newVideo.setCreateProduct(productDTO.getCreateProduct());
        newVideo.setListImage(fileListImage);
        productService.saveOfUpdate(newVideo);
        return "redirect:/product";
    }

    @PostMapping("/updateP")
    public String updateCategory(@ModelAttribute Product product) {
        try {
            productService.saveOfUpdate(product);
            return "redirect:/product";
        } catch (Exception e) {
            e.printStackTrace();
            return "admin/error";
        }
    }

    @PostMapping("/deleteProduct")
    public String deleteCategory(@RequestParam int productId) {
        productService.delete(productId);
        return "redirect:/product";
    }

    @GetMapping("/editProduct/{id}")
    public String edit(@PathVariable int id, Model model) {
        Product product = productService.getById(id);
        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("listCatalog", categoryList);
        model.addAttribute("product", product);
        return "admin/editProduct";
    }

    @PostMapping("/editProduct")
    public String editPro(@ModelAttribute("product") ProductDTO productDTO,@RequestParam("category") int catId) {
        System.out.println("------------------------------" + productDTO.getProductName());
        // upload file
        File file = new File(pathUpload);
        if (!file.exists()) {
            // chưa tồn tại folder , khởi tạo 1 folder mới
            file.mkdirs();
        }
        String fileName = productDTO.getImage().getOriginalFilename();
        try {
            FileCopyUtils.copy(productDTO.getImage().getBytes(), new File(pathUpload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //upload listImage
        //luu tru danh sach duong dan cua anh
        List<String> fileListImage = new ArrayList<>();
        for (MultipartFile multipartFile : productDTO.getListImage()) {
            String fileNameListImage = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(pathUpload + fileNameListImage));
                fileListImage.add(fileNameListImage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // chuyen doi thanh doi tuong video
        Product newVideo = new Product();
        newVideo.setProductId(productDTO.getProductId());
        newVideo.setProductName(productDTO.getProductName());
        newVideo.setCategory(catId);
        newVideo.setDescription(productDTO.getDescription());
        newVideo.setPrice(productDTO.getPrice());
        newVideo.setImage(fileName);
        newVideo.setStock(productDTO.getStock());
        newVideo.setStatus(productDTO.isStatus());
        newVideo.setCreateProduct(productDTO.getCreateProduct());
        newVideo.setListImage(fileListImage);
        productService.saveOfUpdate(newVideo);
        return "redirect:/product";
    }
    @GetMapping("/searchProduct")
    public String searchCategory(@RequestParam("name") String name, Model model) {
        List<Product> searchResult = productService.searchByName(name);
        model.addAttribute("listProduct", searchResult);
        return "admin/product"; // Đổi thành tên view của bạn nếu cần
    }
}