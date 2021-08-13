package vo;

public class ScheduleVO {
	private int classId;
	private String day;
	private String startTime;
	private String endTime;
	
	public int getClassID() {
		return classId;
	}
	public void setClassID(int classId) {
		this.classId = classId;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
