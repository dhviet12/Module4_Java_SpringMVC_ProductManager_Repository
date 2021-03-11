package controller;

import exception.NotFound;
import model.Category;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.category.ICategoryService;
import service.product.IProductService;

import java.util.List;


@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categorylist")
    public List<Category> categoryList() {
        return categoryService.findAll();
    }

    @ExceptionHandler(NotFound.class)
    public ModelAndView showNotFound() {
        return new ModelAndView("notfound");
    }

    @GetMapping
    public ModelAndView showAll(@PageableDefault Pageable pageable) {
        Page<Product> list = productService.findAll(pageable);
        return new ModelAndView("list", "list", list);

    }

    @GetMapping("/create")
    public ModelAndView showFormCreateProduct() throws NotFound {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@Validated @ModelAttribute Product product, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("create");

        }
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        productService.save(product);
        return modelAndView;

    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) throws NotFound {
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
    public ModelAndView searchByProductName (@RequestParam String search) throws NotFound {
        List<Product> list = productService.findProductName(search);
        return new ModelAndView("list", "list", list);
    }

}
