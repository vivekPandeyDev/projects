package spring.batch.practice.jobManager;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.batch.practice.entity.Customer;
import spring.batch.practice.repo.CustomerRepo;

@Component
public class CustomerItemWriter implements ItemWriter<Customer> {
    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public void write(Chunk<? extends Customer> chunk) throws Exception {
       chunk.getItems().forEach(customerRepo::save);
    }
}
