package anime.controller;

import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import anime.entity.User;
import myFunction.TestInput;

@Controller
@Transactional
public class UserController {
	private TestInput testinput = new TestInput();
	
	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value= "dang-ky", method = RequestMethod.GET)
	public String register1() {
		return "user/register";
	}
	
	@RequestMapping(value = "dang-ky", method = RequestMethod.POST)
	public String register2(ModelMap model,
				HttpSession session,
				@RequestParam(value = "name", defaultValue="") String name,
				@RequestParam(value = "password", defaultValue="") String password,
				@RequestParam(value = "nhapLai", defaultValue= "") String nhapLai,
				@RequestParam(value = "email", defaultValue="") String email) {
		
		System.out.println(email);
		System.out.println(password);
		System.out.println(nhapLai);
		System.out.println(name);
		boolean test = true;
		if (!email.isEmpty()) {
			int test_email = testinput.test_email(email);
			if (test_email > 1) {
				test = false;
				switch(test_email) {
					case 2:{
						model.addAttribute("email_fail"," *Hãy nhập đúng định dạng email");
						break;
					}
				}
			}
		}
		else {
			test = false;
			model.addAttribute("email_fail"," *Vui lòng nhập email");
		}
		if (!password.isEmpty()) {
			int test_pass = testinput.test_pass(password);
			if (test_pass > 1) {
				test = false;
				switch(test_pass) {
					case 2:{
						model.addAttribute("pass_fail"," *Mật khẩu có độ dài tối thiếu 8 ký tự");
						break;
					}
					case 3:{
						model.addAttribute("pass_fail"," *Mật khẩu chỉ bao gồm các ký tự: ! # $ _ , - ? + * @");
						break;
					}
				}
			}
			else {
				if (!nhapLai.isEmpty()) {
					if (!nhapLai.equals(password)) {
						test = false;
						model.addAttribute("NL_fail", "Mật khẩu nhập lại không chính xác!");
				
					}
				}
				else {
					test = false;
					model.addAttribute("NL_fail", " *Vui lòng nhập lại mật khẩu!");
				}
			}
		}
		else {
			test = false;
			model.addAttribute("pass_fail"," *Vui lòng nhập mật khẩu");
		}
		
		if (!name.isEmpty()) {
			int test_name = testinput.test_name(name);
			if (test_name > 1) {
				test = false;
				switch(test_name) {
					case 2:{
						model.addAttribute("name_fail"," *Tên chỉ bao gồm các tự: a-z, A-Z, 0-9 ! # _ - . và khoảng trắng");
						break;
					}
				}	
			}
		}
		else {
			test = false;
			model.addAttribute("name_fail"," *Vui lòng nhập tên người dùng");
		}
		if (test) {
			Session ss = factory.openSession();
			Transaction t = ss.beginTransaction();
			
			String hql = "FROM User u WHERE u.email=:email";
			Query query = ss.createQuery(hql);
			query.setParameter("email", email);
			List<User> list = query.list();
			if (list.size() == 0) {
				User user = new User();
				user.setEmail(email);
				user.setPassword(password);
				user.setName(name);
				user.setIsAdmin(false);
				user.setVerify(false);
				try {
					ss.save(user);
					t.commit();
					
				}
				catch(Exception ex) {
					t.rollback();
					System.out.println("Insert user fail");
				}
				finally {
					ss.close();
				}
				session.setAttribute("user", user);
				return "redirect:/index.htm";
			}
			else {
				model.addAttribute("email_fail", " *Email này đã được sử dụng!");
				model.addAttribute("email", email);
				model.addAttribute("name", name);
				return "user/register";
			}
		}
		else {
			model.addAttribute("email", email);
			model.addAttribute("name", name);
			return "user/register";
		}
	}
	
	@RequestMapping(value = "dang-nhap", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "user/login";
	}
	
	@RequestMapping(value = "dang-nhap", method = RequestMethod.POST)
	public String login1(ModelMap model, HttpSession session,
				@RequestParam(value = "email", defaultValue = "") String email,
				@RequestParam(value = "password", defaultValue = "") String password) {
		
		System.out.println(email);
		System.out.println(password);
		boolean test = true;
		if (!email.isEmpty()) {
			int test_email = testinput.test_email(email);
			if (test_email > 1) {
				test = false;
				switch(test_email) {
					case 2:{
						model.addAttribute("email_fail"," *Hãy nhập đúng định dạng email");
						break;
					}
				}
			}
		}
		else {
			test = false;
			model.addAttribute("email_fail"," *Vui lòng nhập email");
		}
		
		if (!password.isEmpty()) {
			int test_pass = testinput.test_pass(password);
			if (test_pass > 1) {
				test = false;
				switch(test_pass) {
					case 2:{
						model.addAttribute("pass_fail","  *Mật khẩu có độ dài tối thiếu 6 ký tự");
						break;
					}
					case 3:{
						model.addAttribute("pass_fail"," *Mật khẩu chỉ bao gồm các ký tự: ! # $ _ , - ? + * @");
						break;
					}
				}
			}
		}
		else {
			test = false;
			model.addAttribute("pass_fail"," *Vui lòng nhập mật khẩu");
		}
		
		if (test) {	
			Session ss = factory.getCurrentSession();
			String hql = "FROM User u WHERE u.email=:email AND u.password=:password";
			Query query = ss.createQuery(hql);
			query.setParameter("email",  email);
			query.setParameter("password",  password);
			List<User> list= query.list();
			if(list.size() == 1) {
				User user = list.get(0);
				session.setAttribute("user", user);
				return "redirect:/index.htm";
			}
			else {
				model.addAttribute("email", email);
				model.addAttribute("password", password);
				model.addAttribute("log_fail", "Tài khoản hoặc mật khẩu không chính xác!");
				return "user/login";
			}
		}
		else {
			model.addAttribute("email", email);
			model.addAttribute("password", password);
			return "user/login";
		}
	}
	
	@RequestMapping(value = "quen-mat-khau", method=RequestMethod.GET)
	public String forgetPass1() {
		return "user/forgetPass";
	}
	
	@RequestMapping(value= "quen-mat-khau", method=RequestMethod.POST)
	public String forgetPass2(ModelMap model, @RequestParam(value = "email", defaultValue="") String email) {
		boolean test = true;
		if (!email.isEmpty()) {
			int test_email = testinput.test_email(email);
			if (test_email > 1) {
				test = false;
				switch(test_email) {
					case 2:{
						model.addAttribute("email_fail"," *Hãy nhập đúng định dạng email");
						break;
					}
				}
			}
		}
		else {
			test = false;
			model.addAttribute("email_fail"," *Vui lòng nhập email");
		}
		if (test) {
			Session session = factory.getCurrentSession();
			String hql = "FROM User u WHERE u.email=:email";
			Query query = session.createQuery(hql);
			query.setParameter("email", email);
			List<User> list = query.list();
			if (list.size() == 1) {
				User user = (User) session.get(User.class, email);
				String body = "Xin chao " + user.getName() + "\n"
						+ "Hệ thống nhận được yêu cầu cấp lại mật khẩu thông qua địa chỉ email " + email + "\n"
						+ "Mật khẩu của bạn là: " + user.getPassword() + "\n"
						+ "Chúc bạn xem phim vui vẻ!\n"
						+ "Ghi chú: Thông báo này được gửi tự động từ hệ thống. Để đảm bảo an toàn bạn cần đổi mật khẩu sau khi đăng nhập thành công.";
			
				System.out.println(body);
				
				String subject = "Thông báo cấp lại mật khẩu đăng nhập Anime Hay";
				try {
					sendMail(user.getEmail(), subject, body);
					System.out.println("Gửi mail thành công");
					model.addAttribute("log_fail", "Vui lòng kiểm tra mail và đăng nhập.");
					return login(model);
				}
				catch(Exception ex){
					System.out.println("Gửi mail thất bại");
					model.addAttribute("message", "Gửi mail thất bại. Vui lòng thử lại sau.");
					return "user/forgetPass";
				}
				
			}
			else {
				model.addAttribute("message", " *Email chưa được đăng ký!");
				return "user/forgetPass";
			}
			
		}
		else{
			return "user/forgetPass";
		}
	}
	
	@RequestMapping("dang-xuat")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/index.htm";
	}
	
	@Autowired
	JavaMailSender mailer;
	public void sendMail( String to, String subject, String body) {
		String from = "judy.mylinh1999@gmail.com";
		try {
			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
			helper.setFrom(from, from);
			helper.setTo(to);
			helper.setReplyTo(from, from);
			helper.setSubject(subject);
			helper.setText(body, true);
			
			mailer.send(mail);
		}
		catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
