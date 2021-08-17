package vo;

import java.util.Date;

public class AssignmentVO {
    private int classId;
    private String userId;
    private Date startDate;
    private Date endDate;

    public int getClassId() {
	return classId;
    }

    public void setClassId(int classId) {
	this.classId = classId;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public Date getStartDate() {
	return startDate;
    }

    public void setStartDate(Date startDate) {
	this.startDate = startDate;
    }

    public Date getEndDate() {
	return endDate;
    }

    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }
}
