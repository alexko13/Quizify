package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "QUIZ_SUBMISSION")
public class Submission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private Account account;
	@ManyToOne
	@JoinColumn(name = "QUIZ_ID")
	private Quiz quiz;
	@OneToMany(mappedBy = "submission", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private List<Response> responses;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMISSION_TIME")
	private Date submissionTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

	public Date getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(Date submissionTime) {
		this.submissionTime = submissionTime;
	}

	public int getNumberOfQuestions() {
		return responses.size();
	}

	public double getPercentCorrect() {
		double totalCorrect = 0;
		for (Response response : responses)
			if(response.getAnswer().getIsCorrect() == 'Y')
				totalCorrect++;
		return (100 * totalCorrect / responses.size());
	}
	
	public int getNumberCorrect() {
		int totalCorrect = 0;
		for (Response response : responses)
			if(response.getAnswer().getIsCorrect() == 'Y')
				totalCorrect++;
		return totalCorrect;
	}
	
	@Override
	public String toString() {
		return "Submission ID: " + id + "\nAccount: " + account.getUsername() + "\nQuiz: " + quiz.getName()
				+ "\nSubmission Time: " + submissionTime;
	}

}
