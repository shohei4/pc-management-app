package com.example.pc_management_app.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Pc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = true)
	private String pcNumber;
	
	@Column(length = 50, nullable = true)
	private String userName;
	
	@Column(length = 50, nullable = false)
	@Builder.Default
	private String userAttr = "user";
	
	@Column(length = 50, nullable = false)
	private String maker;
	
	@Column(length = 50, nullable = false)
	@Builder.Default
	private String os = "Windows11";
	
	@Column(length = 1000, nullable = true)
	private String remarks;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@Column(nullable = true)
	private LocalDateTime updatedAt;
	
	@PreUpdate
	public void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}	
	
}
