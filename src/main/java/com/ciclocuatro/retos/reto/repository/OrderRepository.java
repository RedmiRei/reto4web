/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ciclocuatro.retos.reto.repository;

import com.ciclocuatro.retos.reto.model.Orders;
import com.ciclocuatro.retos.reto.repository.crud.OrdersCrudRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Order;

/**
 *
 * @author rei
 */
@Repository
public class OrderRepository {
    
    @Autowired
    public OrdersCrudRepository repository;
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public List<Orders> getAll() {
        return (List<Orders>) repository.findAll();
    }
    
    public Optional<Orders> getOrder(int id){
        return repository.findById(id);
    }
    
    public Orders create(Orders order){
        return repository.save(order);
    }
    
    public void update(Orders order){
        repository.save(order);
    }
    
    public void delete(Orders order){
        repository.delete(order);
    }
    
    public List<Orders> findByZone(String zona){
        return repository.findByZone(zona);
    }
    
    public Optional<Orders> lastUserId(){
        return repository.findTopByOrderByIdDesc();
    }
    public List<Order> ordersSalesManByID(Integer id) {
        Query query = new Query();

        Criteria criterio = Criteria.where("salesMan.id").is(id);
        query.addCriteria(criterio);

        List<Order> orders = mongoTemplate.find(query, Order.class);

        return orders;

    }

    public List<Order> ordersSalesManByState(String state, Integer id) {
        Query query = new Query();
        Criteria criterio = Criteria.where("salesMan.id").is(id)
                .and("status").is(state);

        query.addCriteria(criterio);

        List<Order> orders = mongoTemplate.find(query,Order.class);

        return orders;
    }

    public List<Order> ordersSalesManByDate(String dateStr, Integer id) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Query query = new Query();

        Criteria dateCriteria = Criteria.where("registerDay")
                .gte(LocalDate.parse(dateStr, dtf).minusDays(1).atStartOfDay())
                .lt(LocalDate.parse(dateStr, dtf).plusDays(1).atStartOfDay())
                .and("salesMan.id").is(id);

        query.addCriteria(dateCriteria);

        List<Order> orders = mongoTemplate.find(query,Order.class);

        return orders;
    }
    /*
    public List<Orders> ordersSalesManByDate(String dateStr, Integer id){
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        Query query = new Query();
        Criteria dateCriteria = Criteria.where("registerDay")
                .gte(LocalDate.parse(dateStr, dft).minusDays(1).atStartOfDay())
                .lt(LocalDate.parse(dateStr, dft).plusDays(2).atStartOfDay())
                .and("salesMan.id").is(id);
        
        query.addCriteria(dateCriteria);
        List<Orders> orders = mongoTemplate.find(query, Orders.class);
        
        return orders;
    }
    
    public List<Orders> ordersSalesManByState(String state, Integer id){
        
        Query query = new Query();
        Criteria dateCriteria = Criteria.where("salesMan.id").is(id)
                .and("status").is(state);
        
        query.addCriteria(dateCriteria);
        List<Orders> orders = mongoTemplate.find(query, Orders.class);
        
        return orders;
    }
*/
}
