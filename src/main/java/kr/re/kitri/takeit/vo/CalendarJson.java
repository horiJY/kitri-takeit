package vo;

//import dao.ClassDAO;

public class CalendarJson {
	private int groupId;
	private String start;
	private String end;
	private String title = "no name";
	
	public int getGroupId() {
		return groupId;
	}
	public void setGroupid(int groupid) {
		this.groupId = groupid;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void scheduleToCalendar(ScheduleVO svo) {
		this.groupId = svo.getClassId();
		//ClassDAO cdao = new ClassDAO();
		//this.className = cdao.selectClassName(groupid);
		this.start = svo.getStartTime();
		this.end = svo.getEndTime();
		
	}
}
