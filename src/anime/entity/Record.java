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
@Table(name = "RECORD")
public class Record {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "EMAIL")
	private User emailRD;
	
	@Column(name = "NOIDUNG")
	private String noiDung;
	
	@Column(name = "NGAY")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date ngay;

	public Record() {}

	public Record(long id, User emailRD, String noiDung, Date ngay) {
		super();
		this.id = id;
		this.emailRD = emailRD;
		this.noiDung = noiDung;
		this.ngay = ngay;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getEmailRD() {
		return emailRD;
	}

	public void setEmailRD(User emailRD) {
		this.emailRD = emailRD;
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
	
	
}
