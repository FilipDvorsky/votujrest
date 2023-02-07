package sk.upjs.ics.votuj_rest.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sk.upjs.ics.votuj.storage.DaoFactory;
import sk.upjs.ics.votuj.storage.Region;
import sk.upjs.ics.votuj.storage.RegionDao;

@RestController
@CrossOrigin
public class RegionController {
	
	RegionDao regionDao = DaoFactory.INSTANCE.getRegionDao();
	
	@DeleteMapping("/region/delete/{id}")
	public void delete(@PathVariable Long id) throws ObjectNotFoundException {
		boolean result = regionDao.delete(id);
		if (!result) {
			throw new ObjectNotFoundException("Region with id " + id+ " not found.");
		}
	}
	
	@PostMapping("/region/save")
	public Region save(@RequestBody Region region){
		return regionDao.save(region);
	}
	
	@GetMapping("/region/getall")
	public List<Region> getAll() {
		return regionDao.getAll();
	}
	
	@GetMapping("/region/getbyid/{id}")
	public Region getById(@PathVariable Long id) throws ObjectNotFoundException {
		Region region = regionDao.getById(id);
		if (region == null) {
			throw new ObjectNotFoundException("Region with id " + id+ " not found.");
		}
		return region;
	}

}
