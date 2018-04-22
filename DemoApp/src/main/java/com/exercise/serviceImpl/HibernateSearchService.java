package com.exercise.serviceImpl;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import com.exercise.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class HibernateSearchService {
	 @Autowired
	    private final EntityManager entityManager;


	    @Autowired
	    public HibernateSearchService(EntityManager entityManager) {
	        super();
	        this.entityManager = entityManager;
	    }


	    public void initializeHibernateSearch() {

	        try {
	            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
	            fullTextEntityManager.createIndexer().startAndWait();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @Transactional
	    public List<Employee> fuzzySearch(String searchTerm) {

	        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
	        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Employee.class).get();
	        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onFields("name")
	                .matching(searchTerm).createQuery();

	        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Employee.class);

	        // execute search

	        List<Employee> employeeList = null;
	        try {
	            employeeList = jpaQuery.getResultList();
	        } catch (NoResultException nre) {
	            ;// do nothing

	        }

	        return employeeList;


	    }
	}

