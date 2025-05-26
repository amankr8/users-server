package com.example.demo.entity;

import lombok.Data;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.*;

@Data
@Entity
@Table(name = "app_user")
@Indexed(index = "idx_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @FullTextField
    private String firstName;

    @FullTextField
    private String lastName;

    private String email;

    private String role;

    private int age;

    @FullTextField
    private String ssn;
}
