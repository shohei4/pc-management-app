package com.example.pc_management_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Table(name = "pc_software",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_pc_software_pc_id_software_id",
                columnNames = {"pc_id", "software_id"}))
@Data
public class PcSoft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pc_id")
    private Long pcId;

    @Column(name = "software_id")
    private Long softId;
}