package com.ejs.algaworksCurso.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "Index")
@RestController
@RequestMapping("/")
public class IndexController {

	@GetMapping
	public String index() {
		return "Hello world";
	}
}
