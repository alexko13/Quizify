package controllers;

import java.util.ArrayList;
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
	
	//TODO:Fix sign out to disable accessing pages via back button?
	@RequestMapping(value="SignOut.do", method = RequestMethod.POST)
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
		int quizSize = 4;
		int questionSize = 4;
		Quiz newQuiz = new Quiz();
		List<Question> newQuestionsList = new ArrayList<>();
		for(int i = 0; i < quizSize; i++) {
			Question newQuestion = new Question();
			List<Answer> newAnswersList = new ArrayList<>();
			for(int j = 0; j < questionSize; j ++) {
				Answer newAnswer = new Answer();
				newAnswer.setIsCorrect('N');
				newAnswer.setQuestion(newQuestion);
				newAnswersList.add(newAnswer);
				System.out.println("Added new Q" + i + "A" + j);
			}
			newQuestion.setQuizzes(new ArrayList<Quiz>());
			newQuestion.getQuizzes().add(newQuiz);
			newQuestion.setAnswers(newAnswersList);
			newQuestionsList.add(newQuestion);
			System.out.println("Added Q" + i) ;
		}
		newQuiz.setQuestions(newQuestionsList);
		ModelAndView mav = new ModelAndView("CreateNewQuiz.jsp");
		mav.addObject("newQuiz", newQuiz);
		System.out.println("Generated Quiz");
		return mav;
	}
	
	@RequestMapping("SaveNewQuiz.do")
	public String saveNewQuiz(Quiz newQuiz) {
		System.out.println("Recieved New Quiz");
		System.out.println(newQuiz.getName());
		for (Question question : newQuiz.getQuestions()) {
			System.out.println(question.getText());
			for (Answer answer : question.getAnswers()) {
				System.out.println(answer.getText() + " " + answer.getIsCorrect());
			}
		}
		
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
		quizifyDAO.setSubmission(qv.getSubmission());
		mav.addObject("score", qv.getScore());
		mav.addObject("submission", qv.getSubmission());
		return mav;
	}
}