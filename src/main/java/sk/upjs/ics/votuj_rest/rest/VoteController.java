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
import sk.upjs.ics.votuj.storage.Vote;
import sk.upjs.ics.votuj.storage.VoteDao;

@RestController
@CrossOrigin
public class VoteController {

	VoteDao voteDao = DaoFactory.INSTANCE.getVoteDao();
	
	@DeleteMapping("/vote/delete/{id}")
	public void delete(@PathVariable Long id) throws ObjectNotFoundException {
		boolean result = voteDao.delete(id);
		if (!result) {
			throw new ObjectNotFoundException("Vote "+id+" not found");
		}
	}
	
	@PostMapping("/vote/save")
	public Vote save(@RequestBody Vote vote){
		return voteDao.save(vote);
	}
	
	@GetMapping("/vote/getall")
	public List<Vote> getAll() {
		return voteDao.getAll();
	}
	
	@GetMapping("/vote/getbyid/{id}")
	public Vote getById(@PathVariable Long id) throws ObjectNotFoundException {
		Vote vote = voteDao.getById(id);
		if (vote == null) {
			throw new ObjectNotFoundException("Vote with id " + id+ " not found.");
		}
		return vote;
	}
	
	@PostMapping("/vote/getbyparty")
	public List<Vote> getByParty(@RequestBody Party party) throws ObjectNotFoundException {
		List<Vote> votes = voteDao.getByParty(party);
		if (votes.size() == 0 || votes == null) {
			throw new ObjectNotFoundException("Admin with Party name " + party.getName()+ " not found.");
		}
		return votes;
	}
}
