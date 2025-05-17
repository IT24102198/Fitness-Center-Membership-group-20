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

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private RenewQueueService renewQueueService;

	@Autowired
	private ReportService reportService;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private ServletContext servletContext;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		List<Customer> customers = customerService.getUpdatedCustomers();
		model.addAttribute("customers1", customers);
		return "index";
	}

	@GetMapping("/renewqueue")
	public String viewRenewQueue(Model model) {
		renewQueueService.saveRenewQueue(customerService.getCustomers());
		List<RenewQueue> list = renewQueueService.getSortedRenewQueue();
		model.addAttribute("renewqueue", list);
		return "renew-queue";
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


	@GetMapping("/showFormPayment/{id}/{name}")
	public String paymentForm(@PathVariable("id") int id,
							  @PathVariable("name") String name,
							  Model model){
		Payment payment = new Payment();
		payment.setCustomerId(id);
		payment.setCustomerName(name);
		payment.setMembershipYears(1);
		model.addAttribute("payment", payment);
		return "payment";
	}

	@GetMapping("/showReport/{id}")
	public String paymentForm(@PathVariable("id") int id, Model model){
		Report report = reportService.getByCustomerId(id);
		if(report == null) {
			Customer customer = customerService.getById(id);
			Payment payment = paymentService.getByCustomerId(id);
			RenewQueue renewQueue = renewQueueService.getByCustomerId(id);
			report = new Report(customer, payment);
			reportService.saveNewReport(report);
		}
		model.addAttribute("report", report);
		return "customer-report";
	}

	@PostMapping("/savePayment")
	public String savePayment(@ModelAttribute("payment") Payment payment) {
		customerService.updateMembership(payment.getCustomerId(), payment.getMembershipYears());
		paymentService.saveNewPayment(payment);
		return "redirect:/";
	}

	@GetMapping("/download-pdf/{id}")
	public void generatePdf(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String html = reportService.getHTMLForPDF(id);
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=customer_" + id + ".pdf");

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(html);
		renderer.layout();
		renderer.createPDF(response.getOutputStream());
	}

	/**
	 * Notification
	 */

	@GetMapping("/notificationList")
	public String viewNotificationList(Model model) {
		List<Notification> notifications = notificationService.getSortedNotifications();
		model.addAttribute("notifications", notifications);
		return "notification/notification-list";
	}

	@GetMapping("/newNotification")
	public String viewNewNotification(Model model) {
		Notification notification = new Notification();
		model.addAttribute("notification", notification);
		return "notification/new-notification";
	}

	@GetMapping("/editNotification/{id}")
	public String viewEditNotification(@PathVariable(value = "id") int id, Model model) {
		Notification notification = notificationService.getById(id);
		model.addAttribute("notification", notification);
		return "notification/edit-notification";
	}

	@PostMapping("/saveNotification")
	public String saveNotification(@ModelAttribute("notification") Notification notification) {
		notification.setUpdated(new Date());
		notification.setId(notificationService.getNextIdCounter());
		notificationService.saveNewNotification(notification);
		return "redirect:/notificationList";
	}

	@PostMapping("/updateNotification")
	public String updateNotification(@ModelAttribute("notification") Notification notification) {
		notification.setUpdated(new Date());
		notificationService.updateNotification(notification);
		return "redirect:/notificationList";
	}

	@GetMapping("/deleteNotification/{id}")
	public String deleteNotification(@PathVariable("id") int id) {
		notificationService.deleteNotification(id);
		return "redirect:/notificationList";
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
