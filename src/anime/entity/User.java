package anime.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
	@Id
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "VERIFY")
	private Boolean verify;
	
	@Column(name = "ISADMIN")
	private Boolean isAdmin;
	
	@OneToMany(mappedBy = "emailBL", fetch= FetchType.EAGER)
	private Collection<BinhLuan> BLemail;

	@OneToMany(mappedBy = "emailRD", fetch = FetchType.EAGER)
	private Collection<Record> RDemail;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getVerify() {
		return verify;
	}

	public void setVerify(Boolean verify) {
		this.verify = verify;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Collection<BinhLuan> getBLemail() {
		return BLemail;
	}

	public void setBLemail(Collection<BinhLuan> bLemail) {
		BLemail = bLemail;
	}

	public Collection<Record> getRDemail() {
		return RDemail;
	}

	public void setRDemail(Collection<Record> rDemail) {
		RDemail = rDemail;
	}
	
	
	
}
