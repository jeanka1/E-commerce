package com.E_commerce.product.controller;


import com.E_commerce.product.model.Product;
import com.E_commerce.product.service.ProductService;
import com.E_commerce.product.service.UploadFileService;
import com.E_commerce.user.model.User;
import com.E_commerce.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;


    @Autowired
    private UserService userService;

    @Autowired
    private UploadFileService uploadFileService;

    @GetMapping("")
    public String show(Model model){
        model.addAttribute("products",productService.findAll());
        return "/products/show";
    }

    @GetMapping("/create")
    public String createProduct(){
        return "/products/createProduct";
    }

    @PostMapping("/save")
    public String save(Product product, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
        LOGGER.info("este es el objeto product {}",product);
        User u= userService.findById(Integer.parseInt(session.getAttribute("iduser").toString())).get();
        product.setUser(u);

        //imagen
        if(product.getId()==null){ // cuando se crea un producto
            String nameImage= uploadFileService.saveImage(file);
            product.setImagen(nameImage);
        }else {


        }


        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
       Product product= new Product();
        Optional<Product> optionalProduct=productService.get(id);
        product=optionalProduct.get();
        LOGGER.info("producto buscado: {}",product);
        model.addAttribute("product", product);

        return "products/editProduct";
    }
    @PostMapping("/update")
    public String update(Product product,@RequestParam("img") MultipartFile file) throws IOException{

        Product p = new Product();
        p = productService.get(product.getId()).get();

        if(file.isEmpty()) { // cuando editamos el producto pero no cambiamos la imagen

            product.setImagen(p.getImagen());
        }else {//cuando se edita tambien la imagen

            // para eliminar la imagen por defecto
            if(!p.getImagen().equals("default.jpg")){
                uploadFileService.deleteImage(p.getImagen());

            }

            String nameImage= uploadFileService.saveImage(file);
            product.setImagen(nameImage);
        }
        product.setUser(p.getUser());
        productService.update(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){

        Product p= new Product();
        p=productService.get(id).get();
        // para eliminar la imagen por defecto
        if(!p.getImagen().equals("default.jpg")){
            uploadFileService.deleteImage(p.getImagen());

        }

        productService.delete(id);
        return "redirect:/products";
    }
}
