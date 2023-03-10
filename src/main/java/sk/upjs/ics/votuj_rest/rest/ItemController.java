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
import sk.upjs.ics.votuj.storage.DaoFactory;
import sk.upjs.ics.votuj.storage.Item;
import sk.upjs.ics.votuj.storage.ItemDao;
import sk.upjs.ics.votuj.storage.Party;
import sk.upjs.ics.votuj.storage.Program;
import sk.upjs.ics.votuj.storage.Term;

@RestController
@CrossOrigin
public class ItemController {

	ItemDao itemDao = DaoFactory.INSTANCE.getItemDao();
	
	public static class ItemListCategories {
		
		private Item item;
		private List<Category> categories;
		
		public Item getItem() {
			return item;
		}
		public void setItem(Item item) {
			this.item = item;
		}
		public List<Category> getCategories() {
			return categories;
		}
		public void setCategories(List<Category> categories) {
			this.categories = categories;
		}
	}
	
	public static class TermParty {
		
		private Term term;
		private Party party;
		
		public Term getTerm() {
			return term;
		}
		public void setTerm(Term term) {
			this.term = term;
		}
		public Party getParty() {
			return party;
		}
		public void setParty(Party party) {
			this.party = party;
		}
	}
	
	public static class TermPartyCategory {
		
		private Term term;
		private Party party;
		private Category category;
		public Term getTerm() {
			return term;
		}
		public void setTerm(Term term) {
			this.term = term;
		}
		public Party getParty() {
			return party;
		}
		public void setParty(Party party) {
			this.party = party;
		}
		public Category getCategory() {
			return category;
		}
		public void setCategory(Category category) {
			this.category = category;
		}
	}
	
	public static class ProgramCategory {
		
		private Program program;
		private Category category;
		
		public Program getProgram() {
			return program;
		}
		public void setProgram(Program program) {
			this.program = program;
		}
		public Category getCategory() {
			return category;
		}
		public void setCategory(Category category) {
			this.category = category;
		}
	}
	
	@GetMapping("/item/getall")
	public List<Item> getAll() {
		return itemDao.getAll();
	}
	
	@GetMapping("/item/getbyid/{id}")
	public Item getById(@PathVariable Long id) throws ObjectNotFoundException {
		Item item = itemDao.getById(id);
		if (item == null) {
			throw new ObjectNotFoundException("Item with id "+id+" was not found.");
		}
		return item;
	}
	
	@PostMapping("/item/save")
	public Item save(@RequestBody ItemListCategories itemListCategories ) {
		return itemDao.save(itemListCategories.getItem(), itemListCategories.getCategories());
	}
	
	@DeleteMapping("/item/delete/{id}")
	public boolean delete(@PathVariable Long id) throws ObjectNotFoundException {
		boolean result = itemDao.delete(id);
		if (!result) {
			throw new ObjectNotFoundException("Item with id "+id+" not found");
		}
		return result;
	}
	
	@PostMapping("/item/getbyprogram")
	public List<Item> getByProgram(@RequestBody Program program) {
		return itemDao.getByProgram(program);
	}
	
	@GetMapping("/item/getbyterm")
	public List<Item> getByTerm(@RequestBody Term term) throws ObjectNotFoundException {
		List<Item> items = itemDao.getByTerm(term);
		if (items.size() == 0 || items == null) {
			throw new ObjectNotFoundException("Items were was not found.");
		}
		return items;
	}
	
	@GetMapping("/item/getbytermparty")
	public List<Item> getByTermParty(@RequestBody TermParty termParty) throws ObjectNotFoundException {
		List<Item> items = itemDao.getByTermParty(termParty.getTerm(), termParty.getParty());
		if (items.size() == 0 || items == null) {
			throw new ObjectNotFoundException("Items were was not found.");
		}
		return items;
	}
	
	@GetMapping("/item/getbytermpartycategory")
	public List<Item> getByTermPartyCategory(@RequestBody TermPartyCategory termPartyCategory) throws ObjectNotFoundException {
		List<Item> items = itemDao.getByTermPartyCategory(termPartyCategory.getTerm(), termPartyCategory.getParty(), termPartyCategory.getCategory());
		if (items.size() == 0 || items == null) {
			throw new ObjectNotFoundException("Items were was not found.");
		}
		return items;
	}
	
	@GetMapping("/item/getbyprogramcategory")
	public List<Item> getByProgramCategory(@RequestBody ProgramCategory programCategory) throws ObjectNotFoundException {
		List<Item> items = itemDao.getByProgramCategory(programCategory.getProgram(), programCategory.getCategory());
		if (items.size() == 0 || items == null) {
			throw new ObjectNotFoundException("Items were was not found.");
		}
		return items;
	}
	
	
}
