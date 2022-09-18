package com.example.demo3.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo3.data.entity.Users;

public class AccountUserDetails implements UserDetails {
	private Users user;

	public AccountUserDetails(Users user) {
		this.user = user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("ROLE_" + user.getRoleName());
	}

	// �p�X���[�h��ԋp����
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	// ���[�U�[����ԋp����
	@Override
	public String getUsername() {
		return user.getUserName();
	}

	// �A�J�E���g�̗L�������̏�Ԃ𔻒肷��
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// �A�J�E���g�̃��b�N��Ԃ𔻒肷��
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// ���i���̗L�������̏�Ԃ𔻒肷��
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// �L���ȃ��[�U���𔻒肷��
	@Override
	public boolean isEnabled() {
		return true;
	}

	// Entity��Ԃ�
	public Users getUser() {
		return user;
	}

	// ���O��Ԃ�
	public String getName() {
		return user.getName();
	}
	
	

}
