package anime.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BINHLUAN")
public class BinhLuan {
	@Id
	@GeneratedValue
	@Column(name = "MABL")
	private Long maBL;
	
	@Column(name = "NOIDUNG")
	private String noiDung;
	
	@Column(name = "NGAY")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date ngay;
	
	@Column(name = "RECOMMENT")
	private Long recomment;
	
	@ManyToOne
	@JoinColumn(name = "EMAIL")
	private User emailBL;
	
	@ManyToOne
	@JoinColumn(name = "MAPHIM")
	private Phim phimBL;

	public BinhLuan() {}

	public BinhLuan(Long maBL, String noiDung, Date ngay, Long recomment, User emailBL, Phim phimBL) {
		super();
		this.maBL = maBL;
		this.noiDung = noiDung;
		this.ngay = ngay;
		this.recomment = recomment;
		this.emailBL = emailBL;
		this.phimBL = phimBL;
	}

	public Long getMaBL() {
		return maBL;
	}

	public void setMaBL(Long maBL) {
		this.maBL = maBL;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public Long getRecomment() {
		return recomment;
	}

	public void setRecomment(Long recomment) {
		this.recomment = recomment;
	}

	public User getEmailBL() {
		return emailBL;
	}

	public void setEmailBL(User emailBL) {
		this.emailBL = emailBL;
	}

	public Phim getPhimBL() {
		return phimBL;
	}

	public void setPhimBL(Phim phimBL) {
		this.phimBL = phimBL;
	}
	
}
