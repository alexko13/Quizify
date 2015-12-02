package entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinTable(name = "QUIZ_QUESTION", joinColumns = @JoinColumn(name = "QUIZ_ID") , inverseJoinColumns = @JoinColumn(name = "QUESTION_ID") )
	private List<Question> questions;
	
	@OneToMany(mappedBy = "quiz", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Submission> submissions;

	@ManyToOne
	@JoinColumn(name = "CREATOR")
	private Account account;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Submission> getSubmissions() {
		return submissions;
	}

	public void setSubmissions(List<Submission> submissions) {
		this.submissions = submissions;
	}

	public Account getAccount() {
		return account;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	@Override
	public String toString() {
		return "Quiz id: " + id + "\nName: " + name;
	}
}