package com.example.demo3.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = "password") // �������������toString��password���o�͂��Ȃ�

public class Users {
	@Id
	public String userName;
	public String password;
	public String name;
	public String roleName;

}