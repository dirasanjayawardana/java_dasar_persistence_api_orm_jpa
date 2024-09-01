package com.dirapp.java_dasar_presistence_api_orm_jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dirapp.java_dasar_presistence_api_orm_jpa.entity.Customer;
import com.dirapp.java_dasar_presistence_api_orm_jpa.util.JpaUtil;

public class CrudTest {

  private EntityManagerFactory entityManagerFactory;

  @BeforeEach
  void setUp() {
    entityManagerFactory = JpaUtil.getEntityManagerFactory();
  }

  @Test
  void insert() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();
    entityTransaction.begin();

    Customer customer = new Customer();
    customer.setId("1");
    customer.setName("Dira");

    entityManager.persist(customer);

    entityTransaction.commit();
    entityManager.close();
  }

  @Test
  void find() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();
    entityTransaction.begin();

    Customer customer = entityManager.find(Customer.class, "1");
    Assertions.assertNotNull(customer);
    Assertions.assertEquals("1", customer.getId());
    Assertions.assertEquals("Dira", customer.getName());

    entityTransaction.commit();
    entityManager.close();
  }

  @Test
  void update() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();
    entityTransaction.begin();

    Customer customer = entityManager.find(Customer.class, "1");
    customer.setName("Dira Sanjaya");

    entityManager.merge(customer);

    entityTransaction.commit();
    entityManager.close();
  }

  @Test
  void remove() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();
    entityTransaction.begin();

    Customer customer = entityManager.find(Customer.class, "1");

    entityManager.remove(customer);

    entityTransaction.commit();
    entityManager.close();
  }
}