package com.RL.tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantService {
    @Autowired
    private TenantRepository tenantRepository;

    public TenantEntity SaveTenant(TenantEntity tenant){
        return tenantRepository.save(tenant);
    }
}
