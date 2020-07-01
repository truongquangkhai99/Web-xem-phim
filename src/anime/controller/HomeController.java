package anime.controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import anime.entity.Phim;

@Controller
@Transactional
public class HomeController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String home(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Phim";
		Query query = session.createQuery(hql);
		List<Phim> list = query.list();
		
		String hql2 = "FROM CTPhim c ORDER BY c.ngay DESC ";
		Query query2= session.createQuery(hql2);
		query2.setMaxResults(4);
		List<Phim> list2 = query2.list();
		model.addAttribute("phim", list);
		model.addAttribute("slide", list2);
		return "home";
	}
	
	
}
