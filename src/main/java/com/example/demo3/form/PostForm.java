package com.example.demo3.form;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PostForm {
	// title�ւ̃o���f�[�V�����ݒ��ǉ�
	@Size(min = 1, max = 200)
	private String title;
	// text�ւ̃o���f�[�V�����ݒ��ǉ�
	@Size(min = 1, max = 200)
	private String text;
}