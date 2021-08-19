package vo;

//import dao.ClassDAO;

public class CalendarJson {
	private String className = "no name";
	private String start;
	private String title;
	private String description;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void scheduleToCalendar(ScheduleVO svo) {
		//ClassDAO cdao = new ClassDAO();
		//this.className = cdao.selectClassName(svo.getClassId());
		this.start = svo.getStartTime().substring(0,10);
		this.description = svo.getStartTime().substring(11,16)+"~"+svo.getEndTime().substring(11,16);
	}
}
