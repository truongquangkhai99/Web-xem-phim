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
@Table(name = "CTPHIM")
public class CTPhim {
	@Id @GeneratedValue
	@Column(name = "ID")
	private long id;
	
	@Column(name = "TAP")
	private int tap;
	
	@Column(name = "LINK")
	private String link;
	
	@Column(name = "NGAY")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
	private Date ngay;
	
	@ManyToOne
	@JoinColumn(name = "MAPHIM")
	private Phim phimCT;

	public CTPhim() {}

	public CTPhim(long id, int tap, String link, Date ngay, Phim phimCT) {
		this.id = id;
		this.tap = tap;
		this.link = link;
		this.ngay = ngay;
		this.phimCT = phimCT;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTap() {
		return tap;
	}

	public void setTap(int tap) {
		this.tap = tap;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public Phim getPhimCT() {
		return phimCT;
	}

	public void setPhimCT(Phim phimCT) {
		this.phimCT = phimCT;
	}
	
	
}
