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
import sk.upjs.ics.votuj.storage.CandidateDao;
import sk.upjs.ics.votuj.storage.Party;
import sk.upjs.ics.votuj.storage.Term;
import sk.upjs.ics.votuj.storage.DaoFactory;

@RestController
@CrossOrigin
public class CandidateController {
	
	public static class CandiListTerm {
		private Candidate candidate;
		private List<Term> listTerm;
		
		public CandiListTerm(Candidate candidate, List<Term> listTerm) {
			this.candidate = candidate;
			this.listTerm = listTerm;
		}

		public Candidate getCandidate() {
			return candidate;
		}

		public void setCandidate(Candidate candidate) {
			this.candidate = candidate;
		}

		public List<Term> getListTerm() {
			return listTerm;
		}

		public void setListTerm(List<Term> listTerm) {
			this.listTerm = listTerm;
		}	
	}
	
	public static class PartyTerm {
		private Party party;
		private Term term;
		
		public PartyTerm(Party party, Term term) {
			this.party = party;
			this.term = term;
		}

		public Party getParty() {
			return party;
		}

		public void setParty(Party party) {
			this.party = party;
		}

		public Term getTerm() {
			return term;
		}

		public void setTerm(Term term) {
			this.term = term;
		}
	}
	
	CandidateDao candidateDao = DaoFactory.INSTANCE.getCandidateDao();
	
	@GetMapping("/candidate/getall")
	public List<Candidate> getAll() {
		return candidateDao.getAll();
	}
	
	@PostMapping("/candidate/save")
	public Candidate save(@RequestBody CandiListTerm candiListTerm) {
		return candidateDao.save(candiListTerm.getCandidate(), candiListTerm.getListTerm());
	}
	
	@DeleteMapping("/candidate/delete/{id}")
	public void delete(@PathVariable Long id) throws ObjectNotFoundException {
		boolean result = candidateDao.delete(id);
		if (!result) {
			throw new ObjectNotFoundException("Candidate "+id+" not found");
		}
	}
	
	@GetMapping("/candidate/getbyid/{id}")
	public Candidate getById(@PathVariable Long id) throws ObjectNotFoundException  {
		Candidate candidate = candidateDao.getById(id);
		if (candidate == null) {
			throw new ObjectNotFoundException("Candidate with id " + id+ " not found.");
		}
		return candidate;
	}
	
	@GetMapping("/candidate/getbytermparty")
	public List<Candidate> getByTermParty(@RequestBody PartyTerm partyTerm) throws ObjectNotFoundException {
		List<Candidate> candidates = candidateDao.getByTermParty(partyTerm.getParty(), partyTerm.getTerm());
		if (candidates.size() == 0 || candidates == null) {
			throw new ObjectNotFoundException("Candidate with " + partyTerm.getParty().getName()+" "+ partyTerm.getTerm().getSince()+" to "+partyTerm.getTerm().getTo()+" not found.");
		}
		return candidates;
	}
}
