package controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.QuizifyDAO;
import entities.Account;
import utility.QuizValidator;

@Controller
public class QuizifyController {
	@Autowired
	private QuizifyDAO quizifyDAO;

	@RequestMapping(value = "SignIn.do", method = RequestMethod.POST)
	public String signIn(HttpServletRequest req, @RequestParam("username") String username,
			@RequestParam("password") String password) {
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

	@RequestMapping("DisplayQuizzes.do")
	public ModelAndView displayQuizzes() {
		ModelAndView mv = new ModelAndView();
		//TODO: get quizzes from db and add to mv to display
		
		mv.setViewName("DisplayQuizzes.jsp");
		return mv;
	}

	@RequestMapping(value = "GetQuiz.do", method = RequestMethod.GET)
	public String getQuiz(HttpServletRequest req) {
		req.getSession().setAttribute("quiz", quizifyDAO.getQuiz(1));
		return "QuestionPage.jsp";
	}

	@RequestMapping("GetResult.do")
	public ModelAndView getResult(HttpServletRequest req) {
		QuizValidator qv = new QuizValidator(req, quizifyDAO);
		ModelAndView mav = new ModelAndView("ResultPage.jsp");
		quizifyDAO.setSubmission(qv.getSubmission());
		mav.addObject("score", qv.getScore());
		mav.addObject("submission", qv.getSubmission());
		return mav;
	}
}