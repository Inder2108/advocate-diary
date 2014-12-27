package com.adv.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.adv.entities.Client;
import com.adv.entities.PersonJsonObject;
import com.adv.exceptions.ObjectNotFoundException;
import com.adv.service.ClientService;
import com.adv.util.CommonResponse;
import com.adv.util.PagingCriteria;
import com.adv.util.TableParam;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/clients")
public class ClientController {

	private ClientService clientService;

	@Autowired(required = true)
	@Qualifier(value = "clientService")
	public void setClientService(ClientService ps) {
		clientService = ps;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView clientsPage() {
		ModelAndView mv = new ModelAndView("clientsPage");
		mv.addObject("client", new Client());
		mv.addObject("listClients", clientService.listClients());
		return mv;
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Client> listClients() {
		return clientService.listClients();
	}

	@RequestMapping(value = "/addEdit", method = RequestMethod.POST)
	@ResponseBody
	public Client addClient(@RequestBody Client client) {
		if (client.getId() != 0) {
			clientService.updateClient(client);
		} else {
			clientService.addClient(client);
		}
		return client;
	}

	@RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResponse removeClient(@PathVariable("id") int id)
			throws ObjectNotFoundException {
		clientService.removeClient(id);
		return new CommonResponse();
	}

	@RequestMapping("/export")
	public ModelAndView exportData() {
		ModelAndView mv = new ModelAndView("PdfReportView");
		mv.addObject("data", clientService.listClients());
		return mv;
	}

	@RequestMapping(value = "/springPaginationDataTables.web", method = RequestMethod.GET)
	public @ResponseBody String springPaginationDataTables(
			HttpServletRequest request) throws IOException {

		// Fetch search parameter
		String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request
				.getParameter("iDisplayLength"));

		// Fetch the page number from client
		Integer pageNumber = 0;
		if (null != request.getParameter("iDisplayStart"))
			pageNumber = (Integer
					.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength) + 1;

		// // Create page list data
		// List<Person> personsList = createPaginationData(pageDisplayLength);
		//
		// // Here is server side pagination logic. Based on the page number you
		// // could make call
		// // to the data base create new list and send back to the client. For
		// // demo I am shuffling
		// // the same list to show data randomly
		// if (pageNumber == 1) {
		// Collections.shuffle(personsList);
		// } else if (pageNumber == 2) {
		// Collections.shuffle(personsList);
		// } else {
		// Collections.shuffle(personsList);
		// }
		//
		// // Search functionality: Returns filtered list based on search
		// parameter
		// personsList = getListBasedOnSearchParameter(searchParameter,
		// personsList);

		List<Client> clientList = clientService.listClients(pageNumber,
				pageDisplayLength, "");
		PersonJsonObject personJsonObject = new PersonJsonObject();
		// Set Total display record
		personJsonObject.setiTotalDisplayRecords(500);
		// Set Total record
		personJsonObject.setiTotalRecords(500);
		personJsonObject.setAaData(clientList);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(personJsonObject);

		return json2;
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody String getCustomers(@TableParam PagingCriteria criteria) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(clientService.listClients(criteria));
		return json2;
	}

	@RequestMapping(value = "/dummyData", method = RequestMethod.POST)
	@ResponseBody
	public void addDummydata() {
		for (int i = 1; i <= 500; i++) {
			clientService.addClient(new Client(i + "", i + "@" + i + "." + i, i
					+ "", i + "", i + ""));
		}
	}
}