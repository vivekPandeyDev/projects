package com.vivek.commerce;

import java.time.LocalDate;
import java.util.Collections;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vivek.commerce.user.Address;
import com.vivek.commerce.user.CommerceUser;
import com.vivek.commerce.user.CommerceUserRepository;
import com.vivek.commerce.user.PaymentInformation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class CommerceApplication implements CommandLineRunner {
    private final CacheManager cacheManager;
    private final CommerceUserRepository jpaUserRepository;
    private final PasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(CommerceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Address address = new Address();
        address.setCity("delhi");
        address.setState("delhi");
        address.setCountry("india");
        address.setZipCode("121004");
        address.setStreet("punk street");

        PaymentInformation paymentInformation = new PaymentInformation();
        paymentInformation.setCvv(2002);
        paymentInformation.setCardHolderName("vivek pandey");
        paymentInformation.setExpirationDate(LocalDate.of(2080, 12, 10));
        paymentInformation.setCreditCardNumber("1214-1232-1232");
        CommerceUser user = new CommerceUser();
        user.setFirstName("vivek");
        user.setEmail("1@gmail.com");
        user.setMobile("123456890");
        user.setPassword(encoder.encode("root"));
        user.setAddressList(Collections.singletonList(address));
        user.setPaymentInformationList(Collections.singletonList(paymentInformation));

        address.setUser(user);

        if (jpaUserRepository.count() == 0)
            jpaUserRepository.save(user);

        log.info("********************* INIT APPLICATION CACHE **************************");
        Cache cache = cacheManager.getCache("product");
        assert cache != null;
        // remove all the movie related cache
        cache.invalidate();

    }


}
