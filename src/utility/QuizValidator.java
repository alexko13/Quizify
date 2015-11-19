package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import data.QuizifyDAO;
import entities.Account;
import entities.Answer;
import entities.Question;
import entities.Quiz;
import entities.Response;
import entities.Submission;
import models.Score;

public class QuizValidator {
	private HttpServletRequest req;
	private QuizifyDAO quizifyDAO;
	private Submission submission;
	private Score score;

	public QuizValidator(HttpServletRequest req, QuizifyDAO quizifyDAO) {
		this.req = req;
		this.quizifyDAO = quizifyDAO;
		this.submission = new Submission();
		validate();
	}

	public Score getScore() {
		return this.score;
	}

	public Submission getSubmission() {
		return this.submission;
	}

	private void validate() {
		Account account = (Account) req.getSession().getAttribute("account");
		Quiz quiz = (Quiz) req.getSession().getAttribute("quiz");
		List<Response> responses = new ArrayList<>();
		List<Question> questions = quiz.getQuestions();
		Map<String, String[]> userAnswers = req.getParameterMap();
		int numberCorrect = 0;
		
		for (Question question : questions) {
			String questionID = Integer.toString(question.getId());
			Response response = new Response();
			response.setSubmission(submission);
			response.setQuestion(question);
			if (userAnswers.containsKey(questionID)) {
				for (Answer answer : question.getAnswers()) {
					if (answer.getId() == Integer.parseInt(userAnswers.get(questionID)[0])) {
						response.setAnswer(answer);
						if (answer.getIsCorrect() == 'Y') {
							numberCorrect++;
						}
					}
				}
			} else {
				response.setQuestion(question);
				response.setAnswer(quizifyDAO.getSkippedAnswer());
			}
			responses.add(response);
		}
		
		submission.setAccount(account);
		submission.setQuiz(quiz);
		submission.setResponses(responses);
		account.getSubmissions().add(submission);
		score = new Score(numberCorrect, questions.size());
	}
}
