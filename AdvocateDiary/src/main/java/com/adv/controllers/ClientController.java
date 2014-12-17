package com.adv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.adv.entities.Client;
import com.adv.service.ClientService;

@Controller
@RequestMapping("/clients")
public class ClientController {

	private ClientService clientService;

	@Autowired(required = true)
	@Qualifier(value = "clientService")
	public void setClientService(ClientService ps) {
		this.clientService = ps;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView clientsPage() {
		ModelAndView mv = new ModelAndView("clientsPage");
		mv.addObject("client", new Client());
		mv.addObject("listClients", this.clientService.listClients());
		return mv;
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Client> listClients() {
		return this.clientService.listClients();
	}

	// For add and update client both
	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "content-type=application/*")
	@ResponseBody
	public Client addClient(@ModelAttribute Client client) {

		if (client.getId() == 0) {
			// new client, add it
			this.clientService.addClient(client);
		} else {
			// existing client, call update
			this.clientService.updateClient(client);
		}

		return client;

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