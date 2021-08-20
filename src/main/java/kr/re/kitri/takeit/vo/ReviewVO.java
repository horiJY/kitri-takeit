package vo;

import java.util.Date;

public class ReviewVO {
	private String userId;
	private int classId;
	private String content;
	private Date reviewDate;
	private int recommend;
	private int totalRecommendNum;
	private double avgScore;
	private String userName;
	private String className;

	@Override
	public String toString() {
		return "ReviewVO [userId=" + userId + ", classId=" + classId
				+ ", content=" + content + ", reviewDate=" + reviewDate
				+ ", userName=" + userName + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getTotalRecommendNum() {
		return totalRecommendNum;
	}

	public void setTotalRecommendNum(int totalRecommendNum) {
		this.totalRecommendNum = totalRecommendNum;
	}

	public double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}

	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
}
