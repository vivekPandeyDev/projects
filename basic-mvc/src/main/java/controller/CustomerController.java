package controller;

import config.BeanConfiguration;
import dto.CustomerDto;
import dto.EmiDto;
import entity.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import propertyEditor.LocalDateEditor;
import service.ServiceLayer;
import utility.CustomerDtoToCustomer;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CustomerController {

    private ServiceLayer<Customer> serviceLayer;

    public CustomerController() {
        System.out.println("customer controller ran!!!!");
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        this.serviceLayer = context.getBean(ServiceLayer.class);
    }

    @RequestMapping("/register")
    public String getUserRegister() {


        return "customer-register";
    }

    @RequestMapping(path = "/customerRegister", method = RequestMethod.POST)
    public String postUserRegister(@ModelAttribute("customerInfo") CustomerDto customerDto) {
        Customer customer = CustomerDtoToCustomer.getCustomer(customerDto);
        serviceLayer.save(customer);
        return "success";
    }

    @RequestMapping(path = "/updateRegister", method = RequestMethod.POST)
    public String postUserUpdate(@ModelAttribute("customerInfo") CustomerDto customerDto) {
        Customer customer = CustomerDtoToCustomer.getCustomer(customerDto);
//                serviceLayer.update(customer);
        return "success";
    }

    @RequestMapping(path="/getCustomers")
    public String getAllCustomers(Model model){
        List<Customer> customers =  serviceLayer.getAllEntity();
        List<String> names = customers.stream().map(Customer::getCustomerName).collect(Collectors.toList());
        model.addAttribute("names",names);
        return  "display-customer";

    }

    @RequestMapping(value="/getSingleCustomer/{name}")
    public String getDetail(@PathVariable String name,Model model){
        List<Customer> customers =  serviceLayer.getAllEntityByName(name);
        model.addAttribute("customers",customers);
        return "single-customer";
    }

    @GetMapping("/calculateEmi")
    public String getEmiPage(){
        return "emi-calculator";
    }

    @PostMapping("/calculateEmi")
    @ResponseBody
    public String postEmiPage(@ModelAttribute("emiInfo")EmiDto dto){

        double emi =  calculateEmi(dto);
        return "Hey your emi is : "+emi;
    }
    @InitBinder
    public void init(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, "dob", new LocalDateEditor());
    }


    public double calculateEmi(EmiDto dto){
        double roiDouble = dto.getRoi();
        double tenureDouble = dto.getTenure();
        double loanAmountDouble = dto.getLoanAmount();

        double roiMonth = roiDouble/1200;
        double temp = Math.pow( (roiMonth + 1),tenureDouble*12);
        temp = loanAmountDouble * (roiMonth) * ( temp / (temp-1) );
        return temp;
    }
}
