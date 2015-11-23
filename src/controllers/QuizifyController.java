package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class QuizifyController {
	@Autowired
	private QuizifyDAO quizifyDAO;

	@RequestMapping(value = "SignIn.do", method = RequestMethod.POST)
	public String signIn(HttpServletRequest req, @RequestParam("username") String username, @RequestParam("password") String password) {
		try {
			Account account = quizifyDAO.getAccount(username);
			if (password.equals(account.getPassword())) {
				req.getSession().setAttribute("account", account);
				return "HomePage.jsp";
			} else {
				throw new Exception("Invalid Password");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			req.setAttribute("signInError", e.getMessage());
			return "index.jsp";
		}
	}

	// TODO: Make validate account creation
	@RequestMapping(value = "SignUp.do")
	public String signUp(HttpServletRequest req, Account account) {
		account.setSubmissions(new ArrayList<Submission>());
		account.setRegistrationDate(new Date());
		try {
			quizifyDAO.setAccount(account);
			req.getSession().setAttribute("account", account);
			return "HomePage.jsp";
		} catch (Exception e) {
			req.setAttribute("signUpError", "Invalid Sign Up");
			return "index.jsp";
		}
	}

	// TODO:Fix sign out to disable accessing pages via back button?
	@RequestMapping(value = "SignOut.do", method = RequestMethod.POST)
	public String signOut(HttpServletRequest req) {
		req.getSession().removeAttribute("account");
		return "index.jsp";
	}

	@RequestMapping("DisplayQuizzes.do")
	public ModelAndView displayQuizzes() {
		return new ModelAndView("DisplayQuizzes.jsp", "allQuizzes", quizifyDAO.getAllQuizzes());
	}

	@RequestMapping("CreateNewQuiz.do")
	public ModelAndView createNewQuiz() {
		Quiz newQuiz = new Quiz();
		List<Question> newQuestionsList = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
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

	@RequestMapping("SaveNewQuiz.do")
	public String saveNewQuiz(Quiz newQuiz) {
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
}