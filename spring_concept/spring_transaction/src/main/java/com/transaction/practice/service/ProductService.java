package com.transaction.practice.service;

import com.transaction.practice.entity.Product;
import com.transaction.practice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class ProductService {

    /*
      * by default rollback will only work for runtime exception, but we configure it using attributes
     */
    private ProductRepository productRepository;

    public void save(Product product){
        productRepository.save(product);
    }


    public void saveMultiple() throws Exception {
        for(int i=0; i<=6; i++){
            productRepository.save(new Product("test product "+i));
/*            if(i== 5)
                throw new Exception("something went wrong!!!");*/
            if(i== 5)
                throw new RuntimeException("something went wrong!!!");
        }
    }

    /*
       * need a transaction if not found throws exception
       * ->No existing transaction found for transaction marked with propagation 'mandatory'
     */
    public void saveMultipleMandatory() {
        for(int i=0; i<=6; i++){
            productRepository.saveMandatory(new Product("test product "+i));
        }
    }
    public Product getProduct(int id){
        return productRepository.getProduct(id);
    }
}
