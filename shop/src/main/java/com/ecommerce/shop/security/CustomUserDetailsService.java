package com.ecommerce.shop.security;

import com.ecommerce.core.customer.CustomerService;
import com.ecommerce.core.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by english on 5/7/17.
 */
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Customer customer = customerService.getCustomerByEmail(email);
        if(customer == null){
            throw new UsernameNotFoundException("Email "+email+" not found");
        }
        return new AuthenticatedUser(customer);
    }
}
