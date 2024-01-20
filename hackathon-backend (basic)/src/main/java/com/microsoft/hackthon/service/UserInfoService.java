package com.microsoft.hackthon.service;

import java.util.List;

import com.microsoft.hackthon.entity.UserInfo;


public interface UserInfoService {
	UserInfo save(UserInfo user);
	UserInfo delete(String userName);
	UserInfo get(String userName);
	List<UserInfo> getAll();
}
