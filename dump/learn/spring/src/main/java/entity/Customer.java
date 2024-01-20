package entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component

public class Customer {
    private int customerId;
    private String customerName;
    private double monthlyIncome;
    private String profession;
    private String designation;
    private String companyName;
    private List<String> phoneNumbers;
    private Set<String> emailAddresses;

    @Autowired
    @Qualifier("loanAgreement")
    private List<LoanAgreement> loanAgreement;
//    @Autowired
    private Address addresses;
    private LocalDate dob;

    public Customer() {
    }


    public Customer(int customerId, String customerName, double monthlyIncome, String profession, String designation,
                    String companyName) {
        super();
        this.customerId = customerId;
        this.customerName = customerName;
        this.monthlyIncome = monthlyIncome;
        this.profession = profession;
        this.designation = designation;
        this.companyName = companyName;
    }

//    @PostConstruct
//    public void init() {
//        for (int i = 0; i < phoneNumbers.size(); i++) {
//            if (!phoneNumbers.get(i).startsWith("+91")) {
//                phoneNumbers.set(i, "+91".concat(phoneNumbers.get(i)));
//            }
//        }
//    }
//
//    @PreDestroy
//    public void destroy() {
//        phoneNumbers.clear();
//        emailAddresses.clear();
//    }

    public int getCustomerId() {
        return customerId;
    }

    public Customer setCustomerId(int customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Customer setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public Customer setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
        return this;
    }

    public String getProfession() {
        return profession;
    }

    public Customer setProfession(String profession) {
        this.profession = profession;
        return this;
    }

    public String getDesignation() {
        return designation;
    }

    public Customer setDesignation(String designation) {
        this.designation = designation;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Customer setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public Customer setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
        return this;
    }

    public Set<String> getEmailAddresses() {
        return emailAddresses;
    }

    public Customer setEmailAddresses(Set<String> emailAddresses) {
        this.emailAddresses = emailAddresses;
        return this;
    }

    public List<LoanAgreement> getLoanAgreement() {
        return loanAgreement;
    }

    public Customer setLoanAgreement(List<LoanAgreement> loanAgreement) {
        this.loanAgreement = loanAgreement;
        return this;
    }

    public Address getAddresses() {
        return addresses;
    }

    public Customer setAddresses(Address address) {
        this.addresses = address;
        return this;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Customer setDob(LocalDate dob) {
        this.dob = dob;
        return this;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", monthlyIncome=" + monthlyIncome +
                ", profession='" + profession + '\'' +
                ", designation='" + designation + '\'' +
                ", companyName='" + companyName + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                ", emailAddresses=" + emailAddresses +
                ", loanAgreement=" + loanAgreement +
                ", address=" + addresses +
                ", dob=" + dob +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        return customerId == other.customerId;
    }

}
