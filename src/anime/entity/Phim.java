package anime.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PHIM")
public class Phim {
	@Id
	@Column(name = "MAPHIM")
	private String maPhim;
	
	@Column(name = "TENPHIM")
	private String tenPhim;
	
	@Column(name = "NOIDUNG")
	private String noiDung;
	
	@Column(name = "NAM")
	private int namPH;
	
	@Column(name = "HINH")
	private String hinh;
	
	@Column(name = "HINH2")
	private String hinh2;
	
	@Column(name = "TRANGTHAI")
	private int trangThai;
	
	@Column(name = "SOTAP")
	private int soTap;
	
	@Column(name = "NGAYTHEM")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date ngay;
	
	@OneToMany(mappedBy = "phimBL", fetch = FetchType.LAZY)
	private Collection<BinhLuan> BLphim;
	
	@OneToMany(mappedBy = "phimCT", fetch = FetchType.LAZY)
	private Collection<CTPhim> CTphim;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="CategoryOfPhim",
			joinColumns = @JoinColumn(name="MAPHIM"),
			inverseJoinColumns = @JoinColumn(name = "MALOAI"))
	private List<Category> categoryP = new ArrayList<>();

	public Phim() {}

	public Phim(String maPhim, String tenPhim, String noiDung, int namPH, String hinh, String hinh2, int trangThai,
			int soTap, Date ngay, Collection<BinhLuan> bLphim, Collection<CTPhim> cTphim, List<Category> categoryP) {
		super();
		this.maPhim = maPhim;
		this.tenPhim = tenPhim;
		this.noiDung = noiDung;
		this.namPH = namPH;
		this.hinh = hinh;
		this.hinh2 = hinh2;
		this.trangThai = trangThai;
		this.soTap = soTap;
		this.ngay = ngay;
		BLphim = bLphim;
		CTphim = cTphim;
		this.categoryP = categoryP;
	}

	public String getMaPhim() {
		return maPhim;
	}

	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
	}

	public String getTenPhim() {
		return tenPhim;
	}

	public void setTenPhim(String tenPhim) {
		this.tenPhim = tenPhim;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public int getNamPH() {
		return namPH;
	}

	public void setNamPH(int namPH) {
		this.namPH = namPH;
	}


	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public String getHinh() {
		return hinh;
	}

	public void setHinh(String hinh) {
		this.hinh = hinh;
	}

	public String getHinh2() {
		return hinh2;
	}

	public void setHinh2(String hinh2) {
		this.hinh2 = hinh2;
	}
	
	public int getSoTap() {
		return soTap;
	}

	public void setSoTap(int soTap) {
		this.soTap = soTap;
	}

	public Collection<BinhLuan> getBLphim() {
		return BLphim;
	}

	public void setBLphim(Collection<BinhLuan> bLphim) {
		BLphim = bLphim;
	}

	public Collection<CTPhim> getCTphim() {
		return CTphim;
	}

	public void setCTphim(Collection<CTPhim> cTphim) {
		CTphim = cTphim;
	}

	public List<Category> getCategoryP() {
		return categoryP;
	}

	public void setCategoryP(List<Category> categoryP) {
		this.categoryP = categoryP;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}
	
	public void addCategoryP(Category cate) {
		this.categoryP.add(cate);
	}
	public void removeAllCate() {
		this.categoryP.clear();
	}
}
