package com.example.pc_management_app.entity.id;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PcSoftId implements Serializable{
	@Column(name = "pc_id")
	private Long pcId;
	@Column(name = "soft_id")
	private Long softId;
}
