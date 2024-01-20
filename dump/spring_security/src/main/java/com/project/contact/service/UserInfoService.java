package com.project.contact.service;

import java.util.List;

import com.project.contact.entity.UserInfo;

public interface UserInfoService {
	UserInfo save(UserInfo user);
	UserInfo delete(String userName);
	UserInfo get(String userName);
	List<UserInfo> getAll();
}
