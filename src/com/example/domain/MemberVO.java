package com.example.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
	
	private String id;
	private String passwd;
	private String name;
	private String email;
	private String recvEmail;   // "Y" or "N"
	private Timestamp regDate;
}





