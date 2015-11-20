package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import entities.Account;
import entities.Answer;
import entities.Quiz;
import entities.Submission;

@Component
@Transactional
public class QuizifyDAO {
	@PersistenceContext
	private EntityManager em;

	public Quiz getQuiz(int id) {
		return em.find(Quiz.class, id);
	}

	public Account getAccount(int id) {

		return em.find(Account.class, id);
	}

	public Account getAccount(String username) throws Exception {
		try {
			return (Account) em.createQuery("SELECT a FROM Account a WHERE a.username = :u").setParameter("u", username).getSingleResult();
		} catch (Exception e) {
			throw new Exception("Invalid Account");
		}
	}

	public Answer getSkippedAnswer() {
		return em.find(Answer.class, 1);
	}

	public void setSubmission(Submission submission) {
		em.persist(submission);
	}
}
