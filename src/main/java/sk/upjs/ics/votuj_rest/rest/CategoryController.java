package sk.upjs.ics.votuj_rest.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sk.upjs.ics.votuj.storage.Category;
import sk.upjs.ics.votuj.storage.CategoryDao;
import sk.upjs.ics.votuj.storage.DaoFactory;
import sk.upjs.ics.votuj.storage.Item;

@RestController
@CrossOrigin
public class CategoryController {
	
	CategoryDao categoryDao = DaoFactory.INSTANCE.getCategoryDao();
	
	@GetMapping("/category/getall")
	public List<Category> getAll() {
		return categoryDao.getAll();
	}
	
	@GetMapping("/category/getbyid/{id}")
	public Category getById(@PathVariable Long id) throws ObjectNotFoundException {
		Category category = categoryDao.getById(id);
		if (category == null) {
			throw new ObjectNotFoundException("Category with id " + id+ " not found.");
		}
		return category;
	}
	
	@DeleteMapping("/category/delete/{id}")
	public void delete(@PathVariable Long id) throws ObjectNotFoundException {
		boolean result = categoryDao.delete(id);
		if (!result) {
			throw new ObjectNotFoundException("Category "+id+" not found");
		}
	}
	
	@PostMapping("/category/save")
	public Category save(@RequestBody Category category) {
		return categoryDao.save(category);
	}
	
	@GetMapping("/category/getbyitem")
	public List<Category> getByItem(@RequestBody Item item) throws ObjectNotFoundException {
		List<Category> list = categoryDao.getByItem(item);
		if (list.size() == 0 || list == null) {
			throw new ObjectNotFoundException("Category with Item name: " + item.getName()+ " not found.");
		}
		return list;
	}
}
