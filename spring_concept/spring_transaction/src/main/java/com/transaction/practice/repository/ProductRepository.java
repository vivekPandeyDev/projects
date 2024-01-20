package com.transaction.practice.repository;


import com.transaction.practice.entity.Product;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@AllArgsConstructor
public class ProductRepository {
    private SessionFactory sessionFactory;
    /*
     * REQUIRED_NEW -> old transaction will be suspended if exists, always create new transaction
     * REQUIRED -> by default, if old transaction exists then use that else create new transaction
     * MANDATORY -> if no transaction found throw exception else use old transaction => No existing transaction found for transaction marked with propagation 'mandatory'
     * SUPPORTS -> if transaction found then use else run non-transactional
     * NOT-SUPPORTED -> if transaction found then suspend it or else run non-transactional
     * NEVER -> if transaction found throws exception else run non-transactional => Existing transaction found for transaction marked with propagation 'never'
     * MANDATORY && NEVER => THROWS EXCEPTION
     */
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);
    }

    @Transactional(value = Transactional.TxType.MANDATORY)
    public void saveMandatory(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);
    }

    @Transactional(value = Transactional.TxType.SUPPORTS)
//    @Transactional(value = Transactional.TxType.NEVER)
//    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Product getProduct(int id){
        Product product = sessionFactory.getCurrentSession().get(Product.class,id);
        return product;
    }
}
