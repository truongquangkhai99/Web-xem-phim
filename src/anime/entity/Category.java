package anime.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table( name = "CATEGORY")
public class Category {
	@Id
	@Column(name = "MALOAI")
	private String maLoai;
	
	@Column(name="TENLOAI")
	private String tenLoai;
	
	@ManyToMany(mappedBy = "categoryP", fetch = FetchType.EAGER)
	private List<Phim> phimTL = new ArrayList<>();
	
	
	public Category() {}

	public Category(String maLoai, String tenLoai, List<Phim> phimTL) {
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
		this.phimTL = phimTL;
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public List<Phim> getPhimTL() {
		return phimTL;
	}

	public void setPhimTL(List<Phim> phimTL) {
		this.phimTL = phimTL;
	}
	
}
