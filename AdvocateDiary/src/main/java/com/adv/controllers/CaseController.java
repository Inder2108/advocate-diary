package com.adv.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.adv.entities.Case;
import com.adv.entities.Client;
import com.adv.entities.Court;
import com.adv.util.CaseStatus;

@Controller
@RequestMapping("/cases")
public class CaseController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView clientsPage() {
		ModelAndView mv = new ModelAndView("casesPage");
		Case c = new Case();
		Client client = new Client();
		client.setAddress("7th street avenue, Dhakoli.");
		client.setContactNo1("32523525");
		client.setContactNo2("98687124");
		client.setEmail("abc@cba.com");
		client.setName("Bangla Babu");
		Court court = new Court();
		court.setAddress("8th street avenue, Mohali");
		court.setName("High Court");
		c.setClient(client);
		c.setCaseStatus(CaseStatus.DECIDED);
		c.setCourt(court);
		c.setOppPartyCouncel("Councel1");
		c.setOppPartyName("party1");
		c.setCaseNumber("314465");
		List<Case> list = new ArrayList<Case>();
		list.add(c);
		Case c1 = new Case();
		c1.setOppPartyCouncel("party2");
		c1.setOppPartyName("counsel2");
		c1.setCaseNumber("321465");
		c1.setCaseStatus(CaseStatus.PENDING);
		list.add(c1);
		list.add(c1);
		list.add(c);
		list.add(c1);
		list.add(c);
		list.add(c1);
		list.add(c);
		mv.addObject("listCases", list);
		return mv;
	}
}
