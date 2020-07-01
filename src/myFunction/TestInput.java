package myFunction;

public class TestInput {
	public int test_email(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";// cấu trúc email thông thường
		if (email.length() == 0) {
			return 1;
		}
		if (!email.matches(EMAIL_PATTERN)) {
			return 2;
		}
		return 0;
	}
	
	public int test_pass(String password) {
//		String pattern = "((?=.*d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!.#$@_+,?-]).{8,50})";
		String pattern = "^[a-zA-Z0-9 ! # $ _ , ? + * @]{3,200}$";
		if (password.length() == 0)
			return 1;
		if (password.length() < 3)
			return 2;
		if (!password.matches(pattern)) {
			return 3;
		}
		return 0;
	}
	
	public int test_name(String name) {
		String pattern = "^[a-zA-Z0-9 ! # _ \\- \\. \" \\s\\p{L}]{2,50}$";
		if (name.length() == 0)
			return 1;
		if (!name.matches(pattern))
			return 2;
		return 0;
	}
	
	public int test_add(String add) {
		String pattern = "^[a-zA-Z0-9 ! # $ _ \\- , ? + / \\s\\p{L}]{3,100}$";
		if (add.length() == 0)
			return 1;
		if (!add.matches(pattern))
			return 2;
		return 0;
	}
	
	public int test_sdt(String sdt){
		String pattern = "^[0-9]{10}$";
		if (sdt.length() == 0)
			return 1;
		if (!sdt.matches(pattern))
			return 2;
		return 0;
	}
	
	public int test_str(String str) {
		String pattern = "^[a-zA-Z0-9 ! # @ $ _ \\- , ? + \" ' *  . : / \\s \\p{L}]{1,2000}$";
		if (str.length() == 0)
			return 1;
		if (!str.matches(pattern))
			return 2;
		return 0;
	}
	
	public int test_number(String number) {
		String pattern = "^[0-9]{1,10}$";
		if (number.length() == 0)
			return 1;
		if (!number.matches(pattern))
			return 2;
		return 0;
	}
	
	public int test_gia(String gia) {
		String pattern = "^[0-9]{5,10}$";
		if (gia.length() == 0)
			return 1;
		if (!gia.matches(pattern))
			return 2;
		return 0;
	}
	
	public int test_url(String url) {
		String pattern = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
		if (url.length() == 0)
			return 1;
		if (!url.matches(pattern))
			return 2;
		return 0;
	}
	
}
