package com.microsoft.hackthon.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.microsoft.hackthon.dao.StoreRepository;
import com.microsoft.hackthon.entity.Store;
import com.microsoft.hackthon.exception.StoreNotFound;
import com.microsoft.hackthon.service.StoreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public List<Store> getStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store getStore(UUID id) {
        return storeRepository.findById(id).orElseThrow(() -> new StoreNotFound("Store not found with id: " + id));
    }

    @Override
    public Store addStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store updateStore(Store store) {
        final var id = store.getStoreId();
        Store existingStore = storeRepository.findById(id)
                .orElseThrow(() -> new StoreNotFound("Store not found with id: " + id));
        existingStore.setStoreName(store.getStoreName());
        existingStore.setAddress(store.getAddress());
        return storeRepository.save(existingStore);
    }

    @Override
    public void deleteStore(int id) {
        // create a method to delete store to database with store repository
        storeRepository.findById(id).orElseThrow(() -> new StoreNotFound("Store not found"));
        storeRepository.deleteById(id);
        return;
    }
}
