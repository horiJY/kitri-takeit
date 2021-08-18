package vo;

import java.sql.Date;

public class QnaVO {
	
	private String userid;
	private Date qnadate;
	private String question;
	private String answer;
	private String qnatitle;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getQnadate() {
		return qnadate;
	}
	public void setQnadate(Date qnadate) {
		this.qnadate = qnadate;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getQnatitle() {
		return qnatitle;
	}
	public void setQnatitle(String qnatitle) {
		this.qnatitle = qnatitle;
	}
	
	
	
	

}
