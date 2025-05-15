
	@Autowired
	private PaymentService paymentService;

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


	@PostMapping("/savePayment")
	public String savePayment(@ModelAttribute("payment") Payment payment) {
		customerService.updateMembership(payment.getCustomerId(), payment.getMembershipYears());
		paymentService.saveNewPayment(payment);
		return "redirect:/";
	}

