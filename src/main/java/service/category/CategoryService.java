package service.category;

import exception.NotFound;
import model.Category;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ICategoryRepository;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;


    @Override
    public List<Category> findAll() {

        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) throws NotFound {
        Category category = categoryRepository.findOne(id);
        if (category != null) {
            return category;
        } else throw new NotFound();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(id);

    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
