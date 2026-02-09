package com.RL.auth;

import com.RL.admin.AdminEntity;
import com.RL.admin.AdminService;
import com.RL.customer.CustomerEntity;
import com.RL.customer.CustomerService;
import com.RL.tenant.TenantEntity;
import com.RL.tenant.TenantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity saveUser(UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        log.info("user saved");

        switch (user.getRole().toLowerCase()){
            case "admin":
                AdminEntity admin = new AdminEntity();
                admin.setUserEntity(user);
                adminService.saveAdmin(admin);
                log.info("admin saved");
                break;
            case "user":
                CustomerEntity customer = new CustomerEntity();
                customer.setUserEntity(user);
                customerService.SaveCustomer(customer);
                log.info("customer saved");
                break;
            case "tenant":
                TenantEntity tenant = new TenantEntity();
                tenant.setUserEntity(user);
                tenantService.SaveTenant(tenant);
                log.info("tenant saved");
                break;
            default:
                log.info("an error occurred when saving the user to a specific role " + user.getRole());
                log.debug("no role was foung matching: " + user.getRole());
        }
        return user;
    }
}
