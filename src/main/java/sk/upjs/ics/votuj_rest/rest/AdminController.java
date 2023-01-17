package sk.upjs.ics.votuj_rest.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sk.upjs.ics.votuj.storage.Admin;
import sk.upjs.ics.votuj.storage.AdminDao;
import sk.upjs.ics.votuj.storage.DaoFactory;

@RestController
@CrossOrigin
public class AdminController {

	private AdminDao adminDao = DaoFactory.INSTANCE.getAdminDao();
	
	@GetMapping("/admin/getbyname/{name}")
	public Admin getByName(@PathVariable String name) throws ObjectNotFoundException {
		Admin admin = adminDao.getByName(name);
		if (admin == null) {
			throw new ObjectNotFoundException("Admin with name " + name+ " not found.");
		}
		return admin;
	}
	
	@PostMapping("/admin/save")
	public Admin save(@RequestBody Admin admin) {
		return adminDao.save(admin);
	}

	@DeleteMapping("/admin/delete/{id}")
	public void delete(@PathVariable Long id) throws ObjectNotFoundException {
		boolean result = adminDao.delete(id);
		if (!result) {
			throw new ObjectNotFoundException("Admin "+id+" not found");
		}
	}
}
