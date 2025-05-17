package org.example.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.*;
import org.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.util.Date;
import java.util.List;

@Controller
public class IndexController {
	@Autowired
	private CustomerService customerService;






	@GetMapping("/")
	public String viewHomePage(Model model) {
		List<Customer> customers = customerService.getUpdatedCustomers();
		model.addAttribute("customers1", customers);
		return "index";
	}


	@GetMapping("/addnewcustomer")
	public String addNewCustomer(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "new-customer";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		customer.setRegisterNo(customerService.getNextCustomerIdCounter());
		customer.setStatus("INACTIVE");
		customerService.saveNewCustomer(customer);
		return "redirect:/";
	}






	@GetMapping("/manage-members")
	public String manageMembers(@RequestParam(value = "searchKeyword", required = false) String searchKeyword,
								Model model) {
		if (searchKeyword != null && !searchKeyword.isEmpty()) {
			Customer customer = customerService.searchCustomerByName(searchKeyword);
			if (customer != null) {
				model.addAttribute("customer", customer);
			} else {
				model.addAttribute("message", "No member found with Name: " + searchKeyword);
			}
		}
		return "manage-members";
	}

	@PostMapping("/updateCustomer")
	public String updateCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.updateCustomer(customer);
		return "redirect:/manage-members?searchKeyword=" + customer.getName();
	}

	@GetMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable("id") int id) {
		customerService.deleteCustomer(id);
		return "redirect:/";
	}
}