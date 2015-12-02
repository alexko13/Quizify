package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	

	@RequestMapping(value = "SignIn.do", method = RequestMethod.POST)
	public String signIn(HttpServletRequest req, @RequestParam("username") String username, @RequestParam("password") String password) {
		try {
			Account account = quizifyDAO.getAccount(username);
			if (password.equals(account.getPassword())) {
				req.getSession().setAttribute("account", account);
				return "HomePage.jsp";
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			req.setAttribute("signInError", "Invalid Account / Password");
			return "index.jsp";
		}
	}

	@RequestMapping("GoToSignUp.do")
	public ModelAndView goToSignUp() {
		return new ModelAndView("SignUp.jsp", "newAccount", new Account());
	}

	@RequestMapping(value = "SignUp.do")
	public ModelAndView signUp(HttpServletRequest req, @ModelAttribute("newAccount") @Valid Account newAccount,
			Errors errors) {
		if (errors.hasErrors()) {
			ModelAndView mav = new ModelAndView("SignUp.jsp", "newAccount", newAccount);
			return mav;
		} else {
			try {
				newAccount.setRegistrationDate(new Date());
				quizifyDAO.setAccount(newAccount);
				req.getSession().setAttribute("account", newAccount);
				return new ModelAndView("HomePage.jsp");
			} catch (Exception e) {
				ModelAndView mav = new ModelAndView("SignUp.jsp", "newAccount", newAccount);
				mav.addObject("signUpError", "Account already in use");
				return mav;
			}
		}
	}

	// TODO:Fix sign out to disable accessing pages via back button?
	@RequestMapping(value = "SignOut.do", method = RequestMethod.POST)
	public String signOut(HttpServletRequest req) {
		req.getSession().removeAttribute("account");
		return "index.jsp";
	}
}