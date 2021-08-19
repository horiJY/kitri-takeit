package vo;

import java.sql.Date;

public class ClassVO {
    private int classId;
    private String className;
    private String creater;
    private String classType;
    private int period;
    private int recommend;
    private String detail;
    private int price;
    private int sale;
    private int capacity;
    private String address;
    private String type;
    private int favorite;
    private String category;
    private Date openDate;

    private String introduce;
    private int content_num;
    private String chapter;
    private String creater_info;

    @Override
    public String toString() {
	return "ClassVO [getIntroduce()=" + getIntroduce() + ", getContent_num()=" + getContent_num()
		+ ", getChapter()=" + getChapter() + ", getCreater_info()=" + getCreater_info() + ", getClassId()="
		+ getClassId() + ", getClassName()=" + getClassName() + ", getCreater()=" + getCreater()
		+ ", getClassType()=" + getClassType() + ", getPeriod()=" + getPeriod() + ", getRecommend()="
		+ getRecommend() + ", getDetail()=" + getDetail() + ", getPrice()=" + getPrice() + ", getSale()="
		+ getSale() + ", getCapacity()=" + getCapacity() + ", getAddress()=" + getAddress() + ", getType()="
		+ getType() + ", getFavorite()=" + getFavorite() + ", getCategory()=" + getCategory()
		+ ", getOpenDate()=" + getOpenDate() + "]";
    }

    public String getIntroduce() {
	return introduce;
    }

    public void setIntroduce(String introduce) {
	this.introduce = introduce;
    }

    public int getContent_num() {
	return content_num;
    }

    public void setContent_num(int content_num) {
	this.content_num = content_num;
    }

    public String getChapter() {
	return chapter;
    }

    public void setChapter(String chapter) {
	this.chapter = chapter;
    }

    public String getCreater_info() {
	return creater_info;
    }

    public void setCreater_info(String creater_info) {
	this.creater_info = creater_info;
    }

    public int getClassId() {
	return classId;
    }

    public void setClassId(int classId) {
	this.classId = classId;
    }

    public String getClassName() {
	return className;
    }

    public void setClassName(String className) {
	this.className = className;
    }

    public String getCreater() {
	return creater;
    }

    public void setCreater(String creater) {
	this.creater = creater;
    }

    public String getClassType() {
	return classType;
    }

    public void setClassType(String classType) {
	this.classType = classType;
    }

    public int getPeriod() {
	return period;
    }

    public void setPeriod(int period) {
	this.period = period;
    }

    public int getRecommend() {
	return recommend;
    }

    public void setRecommend(int recommend) {
	this.recommend = recommend;
    }

    public String getDetail() {
	return detail;
    }

    public void setDetail(String detail) {
	this.detail = detail;
    }

    public int getPrice() {
	return price;
    }

    public void setPrice(int price) {
	this.price = price;
    }

    public int getSale() {
	return sale;
    }

    public void setSale(int sale) {
	this.sale = sale;
    }

    public int getCapacity() {
	return capacity;
    }

    public void setCapacity(int capacity) {
	this.capacity = capacity;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public int getFavorite() {
	return favorite;
    }

    public void setFavorite(int favorite) {
	this.favorite = favorite;
    }

    public String getCategory() {
	return category;
    }

    public void setCategory(String category) {
	this.category = category;
    }

    public Date getOpenDate() {
	return openDate;
    }

    public void setOpenDate(Date openDate) {
	this.openDate = openDate;
    }

}
