package anime.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.ToListResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import anime.entity.BinhLuan;
import anime.entity.CTPhim;
import anime.entity.Category;
import anime.entity.Phim;
import anime.entity.User;

@Controller
@Transactional
public class PhimController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("phim/{maphim}")
	public String indexPhim(ModelMap model, @PathVariable("maphim") String maPhim) {
		Session session = factory.getCurrentSession();
		Phim phim = (Phim) session.get(Phim.class, maPhim);
		model.addAttribute("phim", phim);
		model.addAttribute("title", phim.getTenPhim());
		List<CTPhim> ct = (List<CTPhim>) phim.getCTphim();
//		System.out.println(ct.get(0).getTap());
		model.addAttribute("tap1", ct.get(0));
		
		List<Category> cate = phim.getCategoryP();
		model.addAttribute("loaiPhim", cate);
		System.out.println(phim.getCategoryP().size());
		for(Category i: cate) {
			System.out.println(i.getTenLoai());
		}
		
		String hql = "FROM CTPhim c WHERE c.phimCT.maPhim=:maPhim ORDER BY c.ngay DESC";
		Query query = session.createQuery(hql); 
		query.setParameter("maPhim", maPhim);
		query.setMaxResults(3);
		List<CTPhim> list = query.list();
		model.addAttribute("tapMoi", list);
		
		String hql2 = "FROM BinhLuan b WHERE b.phimBL.maPhim=:maPhim ORDER BY b.ngay DESC";
		Query query2 = session.createQuery(hql2);
		query2.setParameter("maPhim", maPhim);
		List<BinhLuan> list2 = query2.list();
		model.addAttribute("comments", list2);
		
		return "phim/index1";
	}
	
	@RequestMapping("phim/tap/{id}")
	public String viewPhim(@PathVariable("id") long id,
				ModelMap model) {
		Session session = factory.getCurrentSession();
		CTPhim ct = (CTPhim) session.get(CTPhim.class, id);
		String maPhim = ct.getPhimCT().getMaPhim();
		String hql = "FROM CTPhim c WHERE c.phimCT.maPhim=:maPhim";
		Query query = session.createQuery(hql);
		query.setParameter("maPhim", maPhim);
		List<CTPhim> list = query.list();
		model.addAttribute("phim", ct);
		model.addAttribute("listP", list);
		String title = ct.getPhimCT().getTenPhim() + " - Tập " +  ct.getTap();
		model.addAttribute("title", title);
		return "phim/view";
	}
	
	@RequestMapping(value = "user/comment", method = RequestMethod.GET)
	public String comment(ModelMap model, HttpServletRequest request, HttpSession session) {
		String noiDung = request.getParameter("noiDung");
		String maPhim = request.getParameter("maPhim");
		User user = (User) session.getAttribute("user");
		System.out.println("gọi được ajax");
		System.out.println(noiDung);
		System.out.println(maPhim);
		return "phim/comment";
	}
	
	@RequestMapping(value = "phim", params = "catelogy")
	public String catelogyFilm(ModelMap model,
				@RequestParam(value = "maLoai", defaultValue = "") String maLoai) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Phim p WHERE p.catelogyP.maLoai=:maLoai";
		Query query = session.createQuery(hql);
		query.setParameter("maLoai", maLoai);
		List<Phim> list = query.list();
		
		model.addAttribute("phim", list);
		return "search";
	}

}
