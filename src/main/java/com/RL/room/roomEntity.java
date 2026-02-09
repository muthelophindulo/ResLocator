package com.RL.room;

import com.RL.tenant.TenantEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "rooms")
@AllArgsConstructor
@NoArgsConstructor
public class roomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type; //apartment hostel etc

    @Column(unique = true)
    private String address;

    @Column
    private int capacity;

    @Column
    private String roomCondition;

    @Column
    private int bed_count;

    @Column
    private boolean is_furnished;

    @Column
    private String description;

    @Column
    private double monthly_rent_amount;

    @ManyToOne
    @JoinColumn(name = "tenant_id",referencedColumnName = "id",nullable = false)
    private TenantEntity tenant;
}
