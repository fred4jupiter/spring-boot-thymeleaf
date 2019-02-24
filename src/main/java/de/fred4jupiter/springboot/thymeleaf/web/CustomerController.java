package de.fred4jupiter.springboot.thymeleaf.web;

import de.fred4jupiter.springboot.thymeleaf.customer.Customer;
import de.fred4jupiter.springboot.thymeleaf.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private static final int BUTTONS_TO_SHOW = 5;

    private static final int INITIAL_PAGE = 0;

    private static final int PAGE_SIZE = 5;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/show")
    public ModelAndView showPage(@RequestParam("page") Optional<Integer> page) {
        final int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        final ModelAndView modelAndView = new ModelAndView("customer");

        Page<Customer> customers = customerService.findAllCustomers(PageRequest.of(evalPage, PAGE_SIZE));
        modelAndView.addObject("customers", customers);

        PagerModel pagerModel = new PagerModel(customers.getTotalPages(), customers.getNumber(), BUTTONS_TO_SHOW);
        modelAndView.addObject("pagerModel", pagerModel);
        return modelAndView;
    }
}
