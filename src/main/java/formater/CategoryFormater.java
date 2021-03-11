package formater;

import exception.NotFound;
import javassist.NotFoundException;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import service.category.ICategoryService;

import java.text.ParseException;
import java.util.Locale;

public class CategoryFormater implements Formatter<Category> {
    private ICategoryService categoryService;

    @Autowired
    public CategoryFormater(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Category parse(String text, Locale locale) {
        try {
            return categoryService.findById(Long.parseLong(text));
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }
        return null;
    }

    @Override
    public String print(Category object, Locale locale) {
        return "[" + object.getId() + ", " + object.getName() + "]";
    }
}
