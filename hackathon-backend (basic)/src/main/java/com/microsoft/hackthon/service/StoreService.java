package com.microsoft.hackthon.service;

import java.util.List;
import java.util.UUID;

import com.microsoft.hackthon.dto.ProductDto;
import com.microsoft.hackthon.dto.StoreDto;
import com.microsoft.hackthon.entity.Product;
import com.microsoft.hackthon.entity.Store;

public interface StoreService {
    public List<Store> getStores();

    public Store getStore(UUID id);

    public Store addStore(Store store);

    public Store updateStore(Store store);

    public void deleteStore(UUID id);

    public Product dtoToEntity(StoreDto productDto);

    public ProductDto entityToDto(Store product);

}
