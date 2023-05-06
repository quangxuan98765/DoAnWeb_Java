package DAO_model;

public class Product_model {
	private String masp, tensp, hinhsp, motasp, img1, igm2, img3;
	private int giasp, brand_name, category_name;
	
	public Product_model(String masp, String tensp, String hinhsp, String motasp, String img1, String igm2, String img3, int giasp) {
		super();
		this.masp = masp;
		this.tensp = tensp;
		this.hinhsp = hinhsp;
		this.motasp = motasp;
		this.img1 = img1;
		this.igm2 = igm2;
		this.img3 = img3;
		this.giasp = giasp;
	}

	public Product_model(String masp, String tensp, String hinhsp, String motasp, String img1, String igm2, String img3,
			int giasp, int brand_name, int category_name) {
		super();
		this.masp = masp;
		this.tensp = tensp;
		this.hinhsp = hinhsp;
		this.motasp = motasp;
		this.img1 = img1;
		this.igm2 = igm2;
		this.img3 = img3;
		this.giasp = giasp;
		this.brand_name = brand_name;
		this.category_name = category_name;
	}

	public String getMasp() {
		return masp;
	}
	public void setMasp(String masp) {
		this.masp = masp;
	}
	public String getTensp() {
		return tensp;
	}
	public void setTensp(String tensp) {
		this.tensp = tensp;
	}
	public String getHinhsp() {
		return hinhsp;
	}
	public void setHinhsp(String hinhsp) {
		this.hinhsp = hinhsp;
	}
	public String getMotasp() {
		return motasp;
	}
	public void setMotasp(String motasp) {
		this.motasp = motasp;
	}
	public String getImg1() {
		return img1;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	public String getIgm2() {
		return igm2;
	}
	public void setIgm2(String igm2) {
		this.igm2 = igm2;
	}
	public String getImg3() {
		return img3;
	}
	public void setImg3(String img3) {
		this.img3 = img3;
	}
	public int getGiasp() {
		return giasp;
	}
	public void setGiasp(int giasp) {
		this.giasp = giasp;
	}
	
	public int getbrand_name() {
		return brand_name;
	}

	public void setbrand_name(int brand_name) {
		this.brand_name = brand_name;
	}

	public int getcategory_name() {
		return category_name;
	}

	public void setcategory_name(int category_name) {
		this.category_name = category_name;
	}

	@Override
	public String toString() {
		return "Product_model [masp=" + masp + ", tensp=" + tensp + ", hinhsp=" + hinhsp + ", motasp=" + motasp
				+ ", img1=" + img1 + ", igm2=" + igm2 + ", img3=" + img3 + ", giasp=" + giasp + ", brand_name=" + brand_name
				+ ", category_name=" + category_name + "]";
	}

}