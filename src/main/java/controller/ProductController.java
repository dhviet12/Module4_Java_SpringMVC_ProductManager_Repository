package controller;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.product.IProductService;

import java.util.List;


@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ModelAndView showAll(@PageableDefault Pageable pageable){
        Page<Product> list = productService.findAll(pageable);
        return new ModelAndView("list","list",list);
    }

    @GetMapping("/create")
    public ModelAndView showFormCreateProduct(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product",new Product());
        return  modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@ModelAttribute Product product){
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        productService.save(product);
        return modelAndView;

    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = productService.findById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }
    @PostMapping("/edit/{id}")
    public ModelAndView editCustomer(@ModelAttribute Product product, @PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        product.setId(id);
        productService.save(product);
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView deleteCustomer(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        productService.delete(id);
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView searchByProductName(@RequestParam String search){
        List<Product> list = productService.findProductName(search);
        return new ModelAndView("list","list",list);
    }

}
