package com.microsoft.hackthon.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microsoft.hackthon.entity.Store;

public interface StoreRepository extends JpaRepository<Store, UUID> {

}
