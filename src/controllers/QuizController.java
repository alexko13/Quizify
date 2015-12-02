package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.QuizifyDAO;
import entities.Account;
import entities.Answer;
import entities.Question;
import entities.Quiz;
import entities.Submission;
import utility.QuizValidator;

@Controller
public class QuizController {
	@Autowired
	private QuizifyDAO quizifyDAO;

	@RequestMapping("DisplayQuizzes.do")
	public ModelAndView displayQuizzes() {
		return new ModelAndView("DisplayQuizzes.jsp", "allQuizzes", quizifyDAO.getAllQuizzes());
	}
	
	@RequestMapping("DisplayCreatedQuizzes.do")
	public ModelAndView displayCreatedQuizzes(HttpServletRequest req) {
		Account account = (Account) req.getSession().getAttribute("account");
		return new ModelAndView("CreatedQuizzes.jsp", "accountQuizzes", quizifyDAO.getAccountQuizzes(account));
	}
	
	@RequestMapping("CreateNewQuiz.do")
	public ModelAndView createNewQuiz(@RequestParam("numberOfQuestions") int numberOfQuestions) {
		Quiz newQuiz = new Quiz();
		List<Question> newQuestionsList = new ArrayList<>();
		for (int i = 0; i < numberOfQuestions; i++) {
			Question newQuestion = new Question();
			List<Answer> newAnswersList = new ArrayList<>();
			for (int j = 0; j < 4; j++) {
				Answer newAnswer = new Answer();
				newAnswer.setIsCorrect('N');
				newAnswersList.add(newAnswer);
			}
			newQuestion.setAnswers(newAnswersList);
			newQuestionsList.add(newQuestion);
		}
		newQuiz.setQuestions(newQuestionsList);
		return new ModelAndView("CreateNewQuiz.jsp", "newQuiz", newQuiz);
	}
	
	@RequestMapping("DeleteQuiz.do")
	public ModelAndView deleteQuiz(HttpServletRequest req, @RequestParam("quizID") int quizID) {
		quizifyDAO.deleteQuiz(quizID);
		return displayCreatedQuizzes(req);
	}

	@RequestMapping("SaveNewQuiz.do")
	public String saveNewQuiz(HttpServletRequest req, Quiz newQuiz) {
		newQuiz.setAccount((Account) req.getSession().getAttribute("account"));
		for (Question question : newQuiz.getQuestions())
			for (Answer answer : question.getAnswers())
				answer.setQuestion(question);
		quizifyDAO.setQuiz(newQuiz);
		return "HomePage.jsp";
	}

	@RequestMapping(value = "GetQuiz.do", method = RequestMethod.GET)
	public String getQuiz(HttpServletRequest req, @RequestParam("quizID") int quizID) {
		req.getSession().setAttribute("quiz", quizifyDAO.getQuiz(quizID));
		return "QuestionPage.jsp";
	}

	@RequestMapping("GetResult.do")
	public ModelAndView getResult(HttpServletRequest req) {
		QuizValidator qv = new QuizValidator(req, quizifyDAO);
		ModelAndView mav = new ModelAndView("ResultPage.jsp");
		Submission submission = qv.getSubmission();

		submission.setSubmissionTime(new Date());
		quizifyDAO.setSubmission(submission);
		mav.addObject("score", qv.getScore());
		mav.addObject("submission", submission);
		return mav;
	}
	
	//TODO: refactor exception handling
	@ExceptionHandler(Exception.class)
	public ModelAndView defaultExceptionHandler(Exception e, HttpServletRequest req) {
		String redirectPage;
		if(req.getSession().getAttribute("account") == null)
			redirectPage = "index.jsp";
		else
			redirectPage = "HomePage.jsp";
		return new ModelAndView("Error.jsp", "redirectPage", redirectPage);
	}
}
