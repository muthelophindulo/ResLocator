package com.RL.tenant;

import com.RL.auth.UserEntity;
import com.RL.room.roomEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tenants")
public class TenantEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = true)
    private String businessName;

    @Column(nullable = true)
    private String businessAddress;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private UserEntity userEntity;

    @OneToMany(mappedBy = "tenant",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<roomEntity> rooms = new ArrayList<>();
}
