package org.mql.crowddonating.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mql.crowddonating.business.IDonorBusiness;
import org.mql.crowddonating.dao.ConfirmationTokenRepository;
import org.mql.crowddonating.models.Donor;
import org.mql.crowddonating.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@Autowired
	@Qualifier("donorBusiness")
	private IDonorBusiness donorBusiness;

	@Autowired
	@Qualifier("confirmationTokenRepository")
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	@GetMapping("/login")
	public String login() {
		return "auth/login";
	}

	@RequestMapping("/denied")
	public String accessDenied() {
		return "error/404";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}

	@GetMapping("/donator/register")
	public ModelAndView displayRegistration(ModelAndView modelAndView, Donor donor) {
		modelAndView.addObject("donor", donor);
		modelAndView.setViewName("auth/donator-registration");
		return modelAndView;
	}

	@PostMapping("/register")
	public ModelAndView registerUser(ModelAndView modelAndView, Donor donor) {

		User existingUser = donorBusiness.findByEmailIgnoreCase(donor.getEmail());
		if (existingUser != null) {
			modelAndView.addObject("message", "This email already exists!");
			modelAndView.setViewName("auth/error");
		} else {
			donorBusiness.signup(donor);
			modelAndView.addObject("email", donor.getEmail());
			modelAndView.setViewName("auth/successfulRegisteration");
		}

		return modelAndView;
	}

	
	@GetMapping("/confirm-account/{token}")
	public ModelAndView confirmUserAccount(ModelAndView modelAndView, @PathVariable("token") String confirmationToken) {
		

		if (donorBusiness.confirmation(confirmationToken)) {
			modelAndView.setViewName("auth/accountVerified");
		} else {
			modelAndView.addObject("message", "The link is invalid or broken!");
			modelAndView.setViewName("error");
		}
		
		return modelAndView;
	}

}
