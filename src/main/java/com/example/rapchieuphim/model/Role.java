package com.example.rapchieuphim.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Sử dụng @Enumerated để báo cho Hibernate biết đây là kiểu Enum
    // và lưu vào database dưới dạng chuỗi (String) thay vì số thứ tự.
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    // Constructors
    public Role() {
    }

    public Role(ERole name) {
        this.name = name;
    }

    // ==== GETTERS / SETTERS ====
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}