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
import sk.upjs.ics.votuj.storage.ProgramDao;
import sk.upjs.ics.votuj.storage.Term;
import sk.upjs.ics.votuj.storage.Program;

@RestController
@CrossOrigin
public class ProgramController {
	
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

	ProgramDao programDao = DaoFactory.INSTANCE.getProgramDao();
	
	@GetMapping("/program/getall")
	public List<Program> getAll() {
		return programDao.getAll();
	}
	
	@GetMapping("/program/getbyid/{id}")
	public Program getById(@PathVariable Long id) throws ObjectNotFoundException{
		Program program = programDao.getById(id);
		if (program == null) {
			throw new ObjectNotFoundException("Program with id " + id+ " not found.");
		}
		return program;
	}
	
	@DeleteMapping("/program/delete/{id}")
	public void delete(@PathVariable Long id)throws ObjectNotFoundException {
		boolean result = programDao.delete(id);
		if (!result) {
			throw new ObjectNotFoundException("Program "+id+" not found");
		}
	}
	
	@PostMapping("/program/save")
	public Program save(@RequestBody Program program) {
		return programDao.save(program);
	}
	
	@PostMapping("/program/getbyparty")
	public List<Program> getByParty(@RequestBody Party party) throws ObjectNotFoundException{
		List<Program> program = programDao.getByParty(party);
		if(program.size()==0) {
			throw new ObjectNotFoundException("Programs are not found");
		}
		return program;
	}
	
	@PostMapping("/program/getbytermparty")
	public List<Program> getByParty(@RequestBody TermParty termParty) throws ObjectNotFoundException{
		List<Program> programs = programDao.getByTermParty(termParty.getTerm(), termParty.getParty());
		if(programs.size() == 0 || programs == null) {
			throw new ObjectNotFoundException("Programs are not found");
		}
		return programs;
	}
	
}
