package sk.upjs.ics.votuj_rest.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sk.upjs.ics.votuj.storage.Candidate;
import sk.upjs.ics.votuj.storage.DaoFactory;
import sk.upjs.ics.votuj.storage.Term;
import sk.upjs.ics.votuj.storage.TermDao;

@RestController
@CrossOrigin
public class TermController {

	TermDao termDao = DaoFactory.INSTANCE.getTermDao();
	
	@DeleteMapping("/term/delete/{id}")
	public void delete(@PathVariable Long id) throws ObjectNotFoundException {
		boolean result = termDao.delete(id);
		if (!result) {
			throw new ObjectNotFoundException("Term with id " + id+ " not found.");
		}
	}
	
	@PostMapping("/term/save")
	public Term save(@RequestBody Term term){
		return termDao.save(term);
	}
	
	@GetMapping("/term/getall")
	public List<Term> getAll() {
		return termDao.getAll();
	}
	
	@GetMapping("/term/getbyid/{id}")
	public Term getById(@PathVariable Long id) throws ObjectNotFoundException {
		Term term = termDao.getById(id);
		if (term == null) {
			throw new ObjectNotFoundException("Term with id " + id+ " not found.");
		}
		return term;
	}
	
	@PostMapping("/term/getbycandidate")
	public List<Term> getByCandidate(@RequestBody Candidate candidate) throws ObjectNotFoundException{
		List<Term> terms = termDao.getByCandidate(candidate);
		if (terms.size() == 0 || terms == null) {
			throw new ObjectNotFoundException("Term with Candidate id " + candidate.getId()+ " not found.");
		}
		return terms;
	}

}
