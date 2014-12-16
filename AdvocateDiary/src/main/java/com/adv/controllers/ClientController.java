package com.adv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.adv.entities.Client;
import com.adv.service.ClientService;

@Controller
public class ClientController {

	private ClientService clientService;

	@Autowired(required = true)
	@Qualifier(value = "clientService")
	public void setClientService(ClientService ps) {
		this.clientService = ps;
	}

	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public String listClients(Model model) {
		model.addAttribute("client", new Client());
		model.addAttribute("listClients", this.clientService.listClients());
		return "clientsPage";
	}

	// For add and update client both
	@RequestMapping(value = "/client/add", method = RequestMethod.POST)
	public String addClient(@ModelAttribute("client") Client c) {

		if (c.getId() == 0) {
			// new client, add it
			this.clientService.addClient(c);
		} else {
			// existing client, call update
			this.clientService.updateClient(c);
		}

		return "redirect:/clients";

	}

	@RequestMapping("/remove/{id}")
	public String removeClient(@PathVariable("id") int id) {

		this.clientService.removeClient(id);
		return "redirect:/clients";
	}

	@RequestMapping("/edit/{id}")
	public String editClient(@PathVariable("id") int id, Model model) {
		model.addAttribute("client", this.clientService.getClientById(id));
		model.addAttribute("listClients", this.clientService.listClients());
		return "clientsPage";
	}

	@RequestMapping("/export")
	public ModelAndView exportData() {
		ModelAndView mv = new ModelAndView("PdfReportView");
		mv.addObject("data", this.clientService.listClients());
		return mv;
	}
}