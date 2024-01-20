package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BroController {
	@RequestMapping("/bro")
	@ResponseBody
	public String greet() {
		return "Hello bro!!";
	}

	@RequestMapping("/greet")
	public String home() {
		return "greet";
	}

	/*
	 * When we use redirect it will not include the past request and will treat as a
	 * new request, where as when we use forward the past request is added to the
	 * current request as the result we can see the name Ajay printed on the screen
	 * in case of forward!!!!
	 */
	@RequestMapping("/printUser")
	public String printUser(@RequestParam("username") String name, HttpServletRequest req) {
		req.setAttribute("name", name);
//		return "redirect:/public/dispatched.jsp";
		return "forward:/public/dispatched.jsp";
	}

	@RequestMapping("/printUser2")
	public ModelAndView printUser2(@RequestParam("username") String name, ModelAndView modelAndView) {
		modelAndView.addObject("name", name);
		modelAndView.setViewName("display");
		return modelAndView;
	}

	@RequestMapping("/printHeader")
	public String printHeader(@RequestHeader MultiValueMap<String, String> headers

	) {

		headers.forEach((key, value) -> {
			System.out.println("key: " + key + ",value: " + value);
		});
		
		 List<String> list = headers.get("referer");
		 
		 if(list == null) {
			 return "error";
		 }else {
			 return "index";
		 }

		/*
		 * return new ResponseEntity<String>(String.format("Listed %d headers",
		 * headers.size()), HttpStatus.OK);
		 */
		
		
	}

}
