package com.cfs.BookMyShow.model;

import jakarta.persistence.*;

@Entity
@Table(name = "theatres")

public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
