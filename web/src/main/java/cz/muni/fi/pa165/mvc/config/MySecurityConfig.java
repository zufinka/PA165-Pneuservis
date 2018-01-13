/*package cz.muni.fi.pa165.mvc.config;

import dto.CustomerDTO;
import facade.CustomerFacade;
import facade.CustomerFacadeImpl;
import facade.OrderFacade;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import services.CustomerService;

import javax.inject.Inject;
import java.util.List;

*//**
 * @author Martin Zilak, 433372@mail.muni.cz
 *//*
@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Inject
    private CustomerFacade customerFacade;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
            //todo: where to allow whom
    }

    @Inject
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        List<CustomerDTO> customers = customerFacade.findAllCustomers();
        PasswordEncoder passwordEncoder = new PasswordEncoderImpl();

        for (CustomerDTO customer : customers) {
            auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).withUser(customer.getEmail())
                .password(customer.getPasswordHash()).roles("CUSTOMER"); //todo: add password hash to customer entity
        }
    }
}*/
