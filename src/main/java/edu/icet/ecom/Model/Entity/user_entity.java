package edu.icet.ecom.Model.Entity;

import jakarta.persistence.*;
@Entity
@Table(name = "users")
public class user_entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // Using String instead of Enum
    @Column(name = "tier")
    private String tier;
}
