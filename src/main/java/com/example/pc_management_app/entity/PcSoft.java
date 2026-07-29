package com.example.pc_management_app.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import com.example.pc_management_app.entity.id.PcSoftId;

import lombok.Data;

@Entity
@Table(name = "pc_soft")
@Data
public class PcSoft{
	@EmbeddedId
    private PcSoftId id;
}