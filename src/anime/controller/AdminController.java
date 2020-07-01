package anime.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import anime.entity.CTPhim;
import anime.entity.Category;
import anime.entity.Phim;
import anime.entity.Record;
import anime.entity.User;
import myFunction.TestInput;

@Controller
@Transactional
@RequestMapping("admin/")
public class AdminController {
	private TestInput testinput = new TestInput();
	
	@Autowired
	SessionFactory factory;
	@RequestMapping("phim")
	public String index(ModelMap model,
				@RequestParam(value = "m", defaultValue="") String message) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Phim p";
		Query query = session.createQuery(hql);
		List<Phim> list = query.list();
		model.addAttribute("phim", list);
		
		if (!message.isEmpty()) {
			String m = "";
			if(message.equals("insert")) {
				m = "Thêm thể loại mới thành công!";
			}
			else if(message.equals("delete")) {
				m = "Xoá thể loại thành công!";
			}
			model.addAttribute("message", m);
		}
		return "admin/phim/index";
	}
	
	@RequestMapping("category")
	public String indexCate(ModelMap model, @RequestParam(value = "m", defaultValue ="") String message) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Category";
		Query query = session.createQuery(hql);
		List<Category> list = query.list();
		model.addAttribute("cate", list);
		
		if (!message.isEmpty()) {
			String m = "";
			if(message.equals("insert")) {
				m = "Thêm phim mới thành công!";
			}
			else if(message.equals("delete")) {
				m = "Xoá phim thành công!";
			}
			model.addAttribute("message", m);
		}
		return "admin/category/index";
	}
	
	@RequestMapping("user")
	public String indexUser(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM User";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		model.addAttribute("users", list);
		return "admin/user/index";
	}
	
	@RequestMapping("record")
	public String indexRecord(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Record r ORDER BY r.ngay DESC";
		Query query = session.createQuery(hql);
		List<Record> list = query.list();
		model.addAttribute("records", list);
		return "admin/record";
		
	}
	
	
	//------------------------------ PHIM --------------------------------------------
	
	@RequestMapping("phim/{maphim}")
	public String detailPhim(@PathVariable("maphim") String maPhim, ModelMap model,
					@RequestParam(value = "m", defaultValue = "") String message) {
		Session session = factory.getCurrentSession();
		Phim phim = (Phim) session.get(Phim.class, maPhim);
		model.addAttribute("phim", phim);
		
		String hql = "FROM CTPhim c WHERE c.phimCT.maPhim=:maPhim ORDER BY c.tap DESC";
		Query query = session.createQuery(hql);
		query.setParameter("maPhim", maPhim);
		List<CTPhim> tapPhim = query.list();
		model.addAttribute("tapPhim", tapPhim);
		
		if (!message.isEmpty()) {
			String m = "";
			if(message.equals("insert")) {
				m = "Thêm tập mới thành công!";
			}
			else if(message.equals("delete")) {
				m = "Xoá tập phim thành công!";
			}
			else if(message.equals("updateLink")) {
				m = "Cập nhật tập phim thành công!";
			}
			else if(message.equals("updateFilm")) {
				m = "Cập nhật thông tin phim thành công!";
			}
			model.addAttribute("message", m);
		}
		return "admin/phim/detail";
	}
	
	@RequestMapping(value = "phim", params = "addFilm")
	public String addPhim(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Category";
		Query query = session.createQuery(hql);
		List<Category> list = query.list();
		model.addAttribute("category", list);
		return "admin/phim/addFilm";
	}
	
	@Autowired
	ServletContext context;
	
	@RequestMapping("phim-newFilm")
	public String addPhim1(ModelMap model,
			@RequestParam(value = "tenPhim", defaultValue = "") String tenPhim,
			@RequestParam(value = "noiDung", defaultValue = "") String noiDung,
			@RequestParam(value = "namPH", defaultValue ="0") Integer namPH,
			@RequestParam(value = "soTap", defaultValue = "0") Integer soTap,
			@RequestParam("hinh1") MultipartFile hinh1,
			@RequestParam("hinh2") MultipartFile hinh2, 
			HttpServletRequest request) {
		
		Boolean test = true;
		if (!tenPhim.isEmpty()) {
			int test_tenPhim = testinput.test_name(tenPhim);
			if (test_tenPhim > 1) {
				test = false;
				switch(test_tenPhim) {
					case 2:{
						model.addAttribute("ten_fail", " *Tên phim chỉ gồm chữ, số và  ! # _ - . ");
						break;
					}
				}
			}
		}
		else {
			test = false;
			model.addAttribute("ten_fail", " *Vui lòng nhập tên phim.");
		}
		
		if (!noiDung.isEmpty()) {
			int test_noiDung = testinput.test_str(noiDung);
			if (test_noiDung > 1) {
				test = false;
				switch(test_noiDung) {
					case 2:{
						model.addAttribute("noidung_fail", " *Nội dung phim chỉ gồm các ký tự chữ, số và ! # @ $ \" * . , : ? ");
						break;
					}
				}
			}
		}
		else {
			test = false;
			model.addAttribute("noidung_fail", " *Vui lòng nhập nội dung phim");
		}
		
		if (namPH > 0) {
			int test_nam = testinput.test_number(Integer.toString(namPH));
			if (test_nam > 1) {
				test = false;
				switch(test_nam) {
					case 2:{
						model.addAttribute("namPH_fail", " *Năm phát hành chỉ gồm ký tự số, không nhỏ hơn 1000 và không lớn hơn năm hiện tại.");
						break;
					}
				}
			}
			if (namPH <1000 || namPH > java.time.LocalDate.now().getYear()) {
				test = false;
				model.addAttribute("namPH_fail", " *Năm phát hành không nhỏ hơn 1000 và không lớn hơn năm hiện tại.");
			}
		}
		else {
			test = false;
			model.addAttribute("namPH_fail", " *Vui lòng nhập năm phát hành");
		}
		
		if (soTap > 0) {
			int test_soTap = testinput.test_number(Integer.toString(soTap));
			if (test_soTap > 1) {
				test = false;
				switch(test_soTap) {
					case 2:{
						model.addAttribute("tap_fail", " *Số tập phim chỉ gồm ký tự số và không âm.");
						break;
					}
				}
			}
		}
		
		if(hinh1.isEmpty()) {
			test = false;
			model.addAttribute("hinh1_fail", " *Vui lòng chọn file!");
		}
		if(hinh2.isEmpty()) {
			test = false;
			model.addAttribute("hinh2_fail", " *Vui lòng chọn file!");
		}
		
		
		
		if (test) {
			try {
				String hinh1Path = hinh1.getOriginalFilename();
				File file1 = new File("F:/Code/Java/web/Anime/WebContent/images", hinh1Path);
				hinh1.transferTo(file1);
				
				String hinh2Path = hinh2.getOriginalFilename();
				File file2 = new File("F:/Code/Java/web/Anime/WebContent/images", hinh2Path);
				hinh2.transferTo(file2);
			}
			catch(Exception ex) {
				System.out.println("Lỗi lưu file!");
				model.addAttribute("message", "Lỗi lưu file! Vui lòng thử lại");
				return "admin/phim/addFilm";
			}
			
			// Insert Phim
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			
			Phim newFilm = new Phim();
			newFilm.setTenPhim(tenPhim);
			newFilm.setNoiDung(noiDung);
			newFilm.setNamPH(namPH);
			newFilm.setSoTap(soTap);
			newFilm.setTrangThai(1);
			newFilm.setHinh(hinh1.getOriginalFilename());
			newFilm.setHinh2(hinh2.getOriginalFilename());
			
			String hql = "FROM Phim p ORDER BY p.ngay DESC";
			Query query = session.createQuery(hql);
			query.setMaxResults(1);
			List<Phim> list = query.list();
			String tmp = list.get(0).getMaPhim();
			Long tmp1 = Long.parseLong(tmp.substring(2));
			String maPhim = "MV" + Long.toString(tmp1 + 1);
			newFilm.setMaPhim(maPhim);
			newFilm.setNgay(new Date());
			
			String hql2 = "FROM Category";
			Query query2 = session.createQuery(hql2);
			List<Category> list2 = query2.list();
			String tmp2 = "";
			
			for (Category c : list2) {
				tmp2 = request.getParameter(c.getMaLoai());
				if(tmp2 != null) {
					newFilm.addCategoryP(c);
				}
			}
			
			try {
				session.save(newFilm);
				t.commit();
				session.close();
				return "redirect:/admin/phim.htm?m=insert";
			}
			catch(Exception e){
				t.rollback();
				System.out.println("Insert Film fail!");
				model.addAttribute("message", "Thêm phim thất bại! Vui lòng thử lại");
				session.close();
				return "admin/phim/addFilm";
			}
			
		}
		else {
			return "admin/phim/addFilm";
		}
	}
	
	@RequestMapping(value ="phim", params = "addLink")
	public String newLink1(ModelMap model,
				@RequestParam("p") String maPhim) {
		model.addAttribute("maPhim", maPhim);
		return "admin/phim/addLink";
	}
	
	@RequestMapping("phim-newLink")
	public String newLink2(ModelMap model,
				@RequestParam("p") String maPhim,
				@RequestParam("tap") String tap,
				@RequestParam("link") String link) {
		
		System.out.println(maPhim);
		Boolean test = true;
		if (!tap.isEmpty()) {
			int test_tap = testinput.test_number(tap);
			if (test_tap > 1) {
				test = false;
				switch(test_tap) {
					case 2:{
						model.addAttribute("tap_fail", " *Tập phim chỉ gồm các ký tự số.");
						break;
					}
				}
			}
		}
		else {
			test = false;
			model.addAttribute("tap_fail", " *Vui lòng nhập số của tập phim.");
		}
		
		if (!link.isEmpty()) {
			int test_link = testinput.test_url(link);
			if (test_link > 1) {
				test = false;
				switch(test_link) {
					case 2:{
						model.addAttribute("link_fail", " *Vui lòng nhập đúng định dạng url");
						break;
					}
				}
			}
		}
		else {
			test = false;
			model.addAttribute("link_fail", " *Vui lòng nhập link tập phim");
		}
		
		if (test) {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			//Kiem tra tap phim
			String hql = "FROM CTPhim c WHERE c.phimCT.maPhim=:maPhim AND c.tap=:tap";
			Query query = session.createQuery(hql);
			query.setParameter("maPhim", maPhim);
			query.setParameter("tap", Integer.parseInt(tap));
			List<CTPhim> list = query.list();
			if(list.size() == 0) {
				CTPhim add = new CTPhim();
				Phim phim = (Phim) session.get(Phim.class, maPhim);
				add.setTap(Integer.parseInt(tap));
				add.setLink(link);
				add.setNgay(new Date());
				add.setPhimCT(phim);
				
				try {
					session.save(add);
					t.commit();
					session.close();
					return "redirect:/admin/phim/" + maPhim + ".htm?m=insert";
				}
				catch(Exception ex) {
					t.rollback();
					System.out.println("Insert new link fail");
					model.addAttribute("message", "Thêm tập mới thất bại. Vui lòng thử lại!");
					session.close();
					return "admin/phim/addLink";
				}
			}
			else {
				session.close();
				model.addAttribute("tap", tap);
				model.addAttribute("link", link);
				model.addAttribute("message", "Tập phim này đã có!");
				return "admin/phim/addLink";
			}
		}
		else {
			model.addAttribute("tap", tap);
			model.addAttribute("link", link);
			return "admin/phim/addLink";
		}
	}
	
	@RequestMapping(value = "tapPhim/{id}", params = "edit")
	public String editLink1(ModelMap model, @PathVariable("id") Long id) {
		Session session = factory.getCurrentSession();
		CTPhim tp = (CTPhim) session.get(CTPhim.class, id);
		model.addAttribute("tapPhim", tp);
		return "admin/phim/editLink";
	}
	
	@RequestMapping(value = "tapPhim-edit")
	public String editLink2(ModelMap model,
					@RequestParam("i") Long id,
					@RequestParam("link") String link) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		CTPhim tapPhim = (CTPhim) session.get(CTPhim.class, id);
		
		Boolean test = true;
		if (!link.isEmpty()) {
			int test_link = testinput.test_url(link);
			if (test_link > 1) {
				test = false;
				switch(test_link) {
					case 2:{
						model.addAttribute("link_fail", " *Vui lòng nhập đúng định dạng url");
						break;
					}
				}
			}
		}
		else {
			test = false;
		}
		
		if(test) {
			tapPhim.setLink(link);
			try {
				session.update(tapPhim);
				t.commit();
				session.close();
				return "redirect:/admin/phim/"+ tapPhim.getPhimCT().getMaPhim() + ".htm?m=updateLink";
			}
			catch(Exception ex) {
				t.rollback();
				System.out.println("Update link fail");
				model.addAttribute("message", "Cập nhật thất bại. Vui lòng thử lại!");
				session.refresh(tapPhim);
				model.addAttribute("tapPhim", tapPhim);
				session.close();
				return "admin/phim/editLink";
			}
		}
		model.addAttribute("tapPhim", tapPhim);
		return "admin/phim/editLink";
	}
	
	@RequestMapping(value = "phim", params = "edit")
	public String editPhim(ModelMap model,
				@RequestParam("p") String maPhim) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Category";
		Query query = session.createQuery(hql);
		List<Category> list = query.list();
		model.addAttribute("category", list);
		
		Phim phim = (Phim) session.get(Phim.class, maPhim);
		model.addAttribute("phim", phim);
		return "admin/phim/editFilm";
	}
	
	@RequestMapping("phim-edit")
	public String editPhim1(ModelMap model,
			@RequestParam("p") String p,
			@RequestParam(value = "tinhTrang", defaultValue = "") String tinhTrang,
			@RequestParam(value = "noiDung", defaultValue = "") String noiDung,
			@RequestParam(value = "soTap", defaultValue = "0") Integer soTap,
			@RequestParam("hinh1") MultipartFile hinh1,
			@RequestParam("hinh2") MultipartFile hinh2, 
			HttpServletRequest request) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		Phim update = (Phim) session.get(Phim.class, p);
		Boolean test = true;
		if (!noiDung.isEmpty()) {
			int test_noiDung = testinput.test_str(noiDung);
			if (test_noiDung > 1) {
				test = false;
				switch(test_noiDung) {
					case 2:{
						model.addAttribute("noidung_fail", " *Nội dung phim chỉ gồm các ký tự chữ, số và ! # @ $ \" * . , : ? ");
						break;
					}
				}
			}
			else
				update.setNoiDung(noiDung);
		}
		
		if (soTap > 0) {
			int test_soTap = testinput.test_number(Integer.toString(soTap));
			if (test_soTap > 1) {
				test = false;
				switch(test_soTap) {
					case 2:{
						model.addAttribute("tap_fail", " *Số tập phim chỉ gồm ký tự số và không âm.");
						break;
					}
				}
			}
			else {
				update.setSoTap(soTap);
			}
		}
		if(!tinhTrang.isEmpty()) {
			int test_status = testinput.test_number(tinhTrang);
			if (test_status > 1) {
				test = false;
				switch(test_status) {
					case 2:{
						model.addAttribute("tt_fail", " *Trạng thái phim chỉ gồm các số 1, 2 và 3");
						break;
					}
				}
			}
			else {
				int tt = Integer.parseInt(tinhTrang);
				if (tt >= 1 && tt <= 3) {
					update.setTrangThai(Integer.parseInt(tinhTrang));
				}
				else {
					test = false;
					model.addAttribute("tt_fail", " *Trạng thái phim chỉ gồm các số 1, 2 và 3");
				}
			}
		}
		
		
		if (test) {
			try {
				if(!hinh1.isEmpty()) {
					String hinh1Path = hinh1.getOriginalFilename();
					File file1 = new File("F:/Code/Java/web/Anime/WebContent/images", hinh1Path);
					hinh1.transferTo(file1);
					update.setHinh(hinh1.getOriginalFilename());
				}
				if(!hinh2.isEmpty()) {
					String hinh2Path = hinh2.getOriginalFilename();
					File file2 = new File("F:/Code/Java/web/Anime/WebContent/images", hinh2Path);
					hinh2.transferTo(file2);
					update.setHinh2(hinh2.getOriginalFilename());
				}
			}
			catch(Exception ex) {
				System.out.println("Lỗi lưu file!");
				model.addAttribute("message", "Lỗi lưu file! Vui lòng thử lại");
				session.refresh(update);
				session.close();
				model.addAttribute("phim", update);
				System.out.println(ex.getMessage());
				
				return "admin/phim/editFilm";
			}
			
			// update Phim
			
			update.removeAllCate();
			
			String hql2 = "FROM Category";
			Query query2 = session.createQuery(hql2);
			List<Category> list2 = query2.list();
			String tmp2 = "";
			
			for (Category c : list2) {
				tmp2 = request.getParameter(c.getMaLoai());
				if(tmp2 != null) {
					update.addCategoryP(c);
				}
			}
			
			try {
				session.update(update);
				t.commit();
				session.close();
				return "redirect:/admin/phim/" + p + ".htm?m=updateFilm";
			}
			catch(Exception e){
				t.rollback();
				System.out.println("Update Film fail!");
				session.refresh(update);
				model.addAttribute("message", "Cập nhật phim thất bại! Vui lòng thử lại");
				session.close();
				model.addAttribute("phim", update);
				return "admin/phim/editFilm";
			}
			
		}
		else {
			return "admin/phim/editFilm";
		}
	}
	
	@RequestMapping(value = "tapPhim/{id}", params = "delete")
	public String deleteLink(@PathVariable("id") Long id) {
		Session session =factory.openSession();
		Transaction t = session.beginTransaction();
		CTPhim delete = (CTPhim) session.get(CTPhim.class, id);
		String maPhim = delete.getPhimCT().getMaPhim();
		try {
			session.delete(delete);
			t.commit();
			return "redirect:/admin/phim/" + maPhim + ".htm?m=delete";
		}
		catch(Exception ex) {
			t.rollback();
			System.out.println("Delete CTPhim fail");
			return "redirect:/admin/phim/" + maPhim + ".htm?m=delete_fail";
		}
	}
	
	@RequestMapping(value = "phim", params = "delete")
	public String deleteFilm(@RequestParam("p") String maPhim) {
		Session session =factory.openSession();
		Transaction t = session.beginTransaction();
		Phim delete = (Phim) session.get(Phim.class, maPhim);
		
		try {
			session.delete(delete);
			t.commit();
			session.close();
			return "redirect:/admin/phim.htm?m=delete";
		}
		catch(Exception ex) {
			t.rollback();
			session.close();
			System.out.println("Delete CTPhim fail");
			return "redirect:/admin/phim.htm?m=delete_fail";
		}
	}
	
	//---------------------------------- CATEGORY ---------------------------------
	@RequestMapping("cate-new")
	public String addCate(ModelMap model,
				@RequestParam(value = "tenLoai", defaultValue = "") String tenLoai) {
		
		tenLoai = tenLoai.trim();
		tenLoai = tenLoai.toLowerCase();
		
		Boolean test = true;
		if (!tenLoai.isEmpty()) {
			int test_tap = testinput.test_name(tenLoai);
			if (test_tap > 1) {
				test = false;
				switch(test_tap) {
					case 2:{
						model.addAttribute("name", tenLoai);
						model.addAttribute("ten_fail", " *Tên thể loại chỉ gồm các ký tự chữ, số.");
						break;
					}
				}
			}
		}
		else {
			test = false;
			model.addAttribute("ten_fail", " *Vui lòng nhập tên thể loại.");
		}
		if (test) {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			
			// check category's name
			String hql = "FROM Category c WHERE c.tenLoai=:tenLoai";
			Query query = session.createQuery(hql);
			query.setParameter("tenLoai", tenLoai);
			List<Category> list = query.list();
			if (list.size() == 0) {
				String hql2 = "FROM Category c ORDER BY c.maLoai DESC";
				Query query2 = session.createQuery(hql2);
				query2.setMaxResults(1);
				Category tmp = (Category) query2.uniqueResult();
				int tmp2 = Integer.parseInt(tmp.getMaLoai().substring(2)) + 1;
				String tmp3 = Integer.toString(tmp2);
				String maLoai = "";
				if (tmp3.length() == 4) {
					maLoai = "TL" + tmp3;
				}
				else if (tmp3.length() == 3) {
					maLoai = "TL0" + tmp3;
				}
				else if (tmp3.length() == 2) {
					maLoai = "TL00" + tmp3;
				}
				else if (tmp3.length() == 1) {
					maLoai = "TL000" + tmp3;
				}
				
				Category addCate = new Category();
				addCate.setMaLoai(maLoai);
				addCate.setTenLoai(tenLoai);
				try {
					session.save(addCate);
					t.commit();
					session.close();
					return "redirect:/admin/category.htm?m=insert";
				}
				catch(Exception ex) {
					t.rollback();
					System.out.println("Insert Category fail");
					session.close();
					model.addAttribute("name", tenLoai);
					return "admin/category/index";
				}
			}
			else {
				model.addAttribute("ten_fail", " *Tên thể loại đã có!");
				return "admin/category/index";
			}
			
		}
		else {
			model.addAttribute("name", tenLoai);
			return "admin/category/index";
		}
	}
	
	@RequestMapping(value = "cate/{maLoai}", params = "delete")
	public String deleteCate(@PathVariable("maLoai") String maLoai) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		Category delete = (Category)session.get(Category.class, maLoai);
		try {
			session.delete(delete);
			t.commit();
			session.close();
			return "redirect:/admin/category.htm?m=delete";
		}
		catch(Exception ex) {
			t.rollback();
			System.out.println("Delete Category fail");
			model.addAtt
			return "admin/category/index";
		}
	}
	
	@ModelAttribute("cate")
	public List<Category> getCate(){
		Session session = factory.getCurrentSession();
		String hql = "FROM Category";
		Query query = session.createQuery(hql);
		List<Category> list = query.list();
		return list;
	}
}
