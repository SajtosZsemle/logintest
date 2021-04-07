package com.logintest.cotroller;

import java.sql.SQLException;

import javax.mail.MessagingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.logintest.db.DBHandler;
import com.logintest.emailsender.EmailSender;



@Controller
public class AppControll {
	
	private static int counter = 0;
	
	
	
	@GetMapping("/")
	public String welcome() throws SQLException {
		counter = 0;
		return "welcome.html";
	}
	
	
	
	@PostMapping("/reg")
	public String reg(Model model, 
			@RequestParam(name="pwd") String pwd, 
			@RequestParam(name="email") String email   ) throws SQLException, MessagingException {
		
		DBHandler myConn = new DBHandler();
		
		boolean addUserResult = myConn.addUser(email, pwd);
		
		if (addUserResult == true) {
			System.out.println(pwd + " - " + email);
			model.addAttribute("email", email);
			EmailSender.sendEmail(email);
		} else {
			System.out.println("Sikertelen reg: " + email);
			email += " Már szerepel az adatbázisban";
			model.addAttribute("email", email);
		}

		return "reg.html";
	}
	
	@GetMapping("/login")
	public String login() throws SQLException {
		
		return "login.html";
	}
	
	
	@PostMapping("/hello")
	public String hello(Model model, 
			@RequestParam(name="pwd") String pwd, 
			@RequestParam(name="email") String email   ) throws SQLException {
		
		DBHandler myConn = new DBHandler();
		
		String result = "login.html";

		if(myConn.loginUser(email, pwd)) {
			result = "helloWorld.html";
			System.out.println("Sikeres belépés");
			counter = 0;
		}
		else if (counter >= 5) {
			result = "welcome.html";
				}
		
		else {
			counter++;
			System.out.println("Sikertelen belépés");
			model.addAttribute("proba", ("próbálkozások száma: " + counter));
			result = "login.html";
		}
		
		return result;
		
	}
	
	
	
	
	
	
	
	

}
