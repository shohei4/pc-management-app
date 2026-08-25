package com.example.pc_management_app.dto.pc;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PcListItemDto {
	private Long id;
    private String pcNumber;
    private String userName;
    private String userAttr;
    private String maker;
    private String os;
    private String remarks;
    private List<String> softwareNames;
	
}
