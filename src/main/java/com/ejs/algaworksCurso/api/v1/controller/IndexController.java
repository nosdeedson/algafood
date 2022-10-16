package com.ejs.algaworksCurso.api.v1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@ResponseBody
@RequestMapping("/")
public class IndexController {

	@GetMapping("inicio")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index.html");
		return modelAndView;
	}
	
}
