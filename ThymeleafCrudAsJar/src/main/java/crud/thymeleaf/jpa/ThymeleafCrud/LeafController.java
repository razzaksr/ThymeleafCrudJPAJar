package crud.thymeleaf.jpa.ThymeleafCrud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/leaf")
public class LeafController 
{
	@Autowired
	HubService serv;
	
	List<Hub> got;
	
	@RequestMapping("/")
	public String list(Model model)
	{
		System.out.println("Controller invoked");
		got=serv.every();
		model.addAttribute("record", got);
		System.out.println(got+" will be forwarded to page");
		//return new ModelAndView("index").addObject("record", got);
		return "index";
	}
	
	@RequestMapping("/newhub")
	public String newone(Model model)
	{
		Hub hub=new Hub();
		model.addAttribute("hub", hub);
		return "createHub";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String added(Model model,@ModelAttribute("hub") Hub hub)
	{
		serv.add(hub);
		model.addAttribute("msg", hub.getHubName()+" has added to base");
		return "redirect:/leaf/";
	}
	
	@RequestMapping("/letedit/{id}")
	public ModelAndView letEdit(@PathVariable("id") long id)
	{
		return new ModelAndView("edit").addObject("single", serv.read(id));
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(Model model,@ModelAttribute("single") Hub single)
	{
		serv.update(single);
		model.addAttribute("msg", single.getHubName()+" has updated in base");
		return "redirect:/leaf/";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(Model model,@PathVariable("id") long id)
	{
		String got=serv.remove(id);
		model.addAttribute("msg", got+" has deleted from base");
		return "redirect:/leaf/"; 
	}
}
