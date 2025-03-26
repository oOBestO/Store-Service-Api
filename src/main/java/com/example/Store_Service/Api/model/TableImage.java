package com.example.Store_Service.Api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "table_image")
public class TableImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "index", nullable = false, unique = true)
    private int index;

    @Lob
    @Column(name = "image", columnDefinition = "BYTEA")
    private byte[] image;
}
