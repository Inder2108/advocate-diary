package com.adv.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CaseController {

	@RequestMapping("/addCase")
	public String testMethod(){
		return "AddCase";
	}
}
