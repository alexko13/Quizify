package controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.QuizifyDAO;
import entities.Account;

@Controller
public class AccountController {
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

	@RequestMapping(value = "SignOut.do", method = RequestMethod.POST)
	public String signOut(HttpServletRequest req) {
		req.getSession().removeAttribute("account");
		return "index.jsp";
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
