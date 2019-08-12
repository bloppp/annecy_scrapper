package baka.annecy.scraper.application;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baka.annecy.scraper.domain.movie.Category.Category;
import baka.annecy.scraper.domain.movie.Category.CategoryRepository;

@Service
@Transactional
public class CategoryService {

  @Autowired CategoryRepository categoryRepository;

  public void saveList(List<Category> categoryList) {
    for (Category category : categoryList) {
      Category oldMovie = categoryRepository.findForUpdateByName(category.getName());
      if (oldMovie == null) {
        categoryRepository.save(category);
      }
    }
  }
}
