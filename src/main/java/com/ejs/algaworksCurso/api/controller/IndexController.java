package com.ejs.algaworksCurso.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;

@Api(tags = "Index")
//@RestController
//@RequestMapping(path = "/", produces = MediaType.TEXT_HTML_VALUE)
@Controller
public class IndexController {

	@RequestMapping("/")
	@ResponseBody
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index.html");
		return modelAndView;
	}
}
