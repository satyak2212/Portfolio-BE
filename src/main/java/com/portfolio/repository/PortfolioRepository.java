package com.portfolio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.portfolio.model.Portfolio;

public interface PortfolioRepository extends MongoRepository<Portfolio, String> {

}
