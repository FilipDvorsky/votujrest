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
import sk.upjs.ics.votuj.storage.Party;
import sk.upjs.ics.votuj.storage.PartyDao;

@RestController
@CrossOrigin
public class PartyController {

	PartyDao partyDao = DaoFactory.INSTANCE.getPartyDao();
	
	@DeleteMapping("/party/delete/{id}")
	public boolean delete(@PathVariable Long id) throws ObjectNotFoundException {
		boolean result = partyDao.delete(id);
		if(!result) {
			throw new ObjectNotFoundException("Party with id "+ id + " was not found.");
		}
		return result;
	}
	
	@PostMapping("/party/save")
	public Party save(@RequestBody Party party){
		return partyDao.save(party);
	}
	
	@GetMapping("/party/getall")
	public List<Party> getAll() {
		return partyDao.getAll();
	}
	
	@GetMapping("/party/getbyid/{id}")
	public Party getById(@PathVariable Long id) throws ObjectNotFoundException{
		Party party = partyDao.getById(id);
		if (party == null) {
			throw new ObjectNotFoundException("Party with id "+ id+ "was not found.");
		}
		return party;
	}
	
}
