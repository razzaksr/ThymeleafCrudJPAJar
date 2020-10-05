package crud.thymeleaf.jpa.ThymeleafCrud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HubService 
{
	@Autowired
	RepoHub repo;
	
	public List<Hub> every()
	{
		return repo.findAll();
	}
	
	public Hub add(Hub hub)
	{
		return repo.save(hub);
	}
	
	public Hub update(Hub hub)
	{
		return repo.save(hub);
	}
	
	public Hub read(long id)
	{
		return repo.findById(id).get();
	}
	
	public String remove(long id)
	{
		String name=repo.findById(id).get().getHubName();
		repo.delete(repo.findById(id).get());
		return name;
	}
}
