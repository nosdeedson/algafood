package com.ejs.algaworksCurso.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;

@Api(tags = "Home")
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
