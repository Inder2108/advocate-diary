package com.adv.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
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
import com.adv.util.JsonResponse;
import com.adv.util.PagingCriteria;
import com.adv.util.TableParam;
import com.adv.validation.ClientFormValidation;
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
	public JsonResponse addEditClient(@RequestBody Client client,
			BindingResult result) throws ObjectNotFoundException {

		JsonResponse res = new JsonResponse();
		ClientFormValidation formValidation = new ClientFormValidation();
		formValidation.validate(client, result);
		
		if (!result.hasErrors()) {
			if (client.getId() != 0) {
				clientService.updateClient(client);
			} else {
				clientService.addClient(client);
			}
			res.setStatus("SUCCESS");
			res.setResult(client);
		} else {
			res.setStatus("FAIL");
			res.setResult(result.getAllErrors());
		}

		return res;
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