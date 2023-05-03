package com.example.demo.Repositories;

import com.example.demo.ExchangeRateEntities.PrivatbankExchangeRate;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Configuration
public interface PrivatbankRepository extends JpaRepository<PrivatbankExchangeRate,Integer>
{
}
