package com.example.demo3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo3.service.AccountUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AccountUserDetailsService userDetailsService;

	public PasswordEncoder passwordEncoder() {
		// BCrypt�A���S���Y�����g�p���ăp�X���[�h�̃n�b�V�������s��
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// AuthenticationManagerBuilder�ɁA��������UserDetailsService��ݒ肷��
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		super.configure(auth);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// �F�̐ݒ�
		http.exceptionHandling().accessDeniedPage("/accessDeniedPage").and().authorizeRequests()
				.antMatchers("/loginForm").permitAll() // loginForm�́A�S���[�U����̃A�N�Z�X������
				.anyRequest().authenticated(); // loginForm�ȊO�́A�F�؂����߂�

		// ���O�C���ݒ�
		http.formLogin() // �t�H�[���F�؂̗L����
				.loginPage("/loginForm") // ���O�C���t�H�[����\������p�X
				.loginProcessingUrl("/authenticate") // �t�H�[���F�؏����̃p�X
				.usernameParameter("userName") // ���[�U���̃��N�G�X�g�p�����[�^��
				.passwordParameter("password") // �p�X���[�h�̃��N�G�X�g�p�����[�^��
				.defaultSuccessUrl("/posts") // �� ������ύX
				.failureUrl("/loginForm?error=true"); // �F�؎��s���ɑJ�ڂ���p�X

		// ���O�A�E�g�ݒ�
		http.logout().logoutSuccessUrl("/loginForm") // ���O�A�E�g�������ɑJ�ڂ���p�X
				.permitAll(); // �S���[�U�ɑ΂��ċ���
	}

}