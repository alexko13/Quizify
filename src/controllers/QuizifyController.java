package controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import data.QuizifyDAO;
import entities.Account;
import utility.QuizValidator;

@Controller
@SessionAttributes("account")
public class QuizifyController {
	@Autowired
	private QuizifyDAO quizifyDAO;

	@ModelAttribute("account")
	public Account initTestAccounts() {
		return quizifyDAO.getAccount(1);
	}

	@RequestMapping(value = "GetQuiz.do", method = RequestMethod.GET)
	public String getQuiz(HttpServletRequest req) {
		req.getSession().setAttribute("quiz", quizifyDAO.getQuiz(1));
		return "QuestionPage.jsp";
	}

	@RequestMapping(value = "GetResult.do")
	public ModelAndView getResult(HttpServletRequest req) {
		QuizValidator qv = new QuizValidator(req, quizifyDAO);
		ModelAndView mav = new ModelAndView("ResultPage.jsp");
		quizifyDAO.setSubmission(qv.getSubmission());
		mav.addObject("score", qv.getScore());
		mav.addObject("submission", qv.getSubmission());
		return mav;
	}
}