package com.example.demo.Repositories;

import com.example.demo.ExchangeRateEntities.ExchangeRate;
import com.example.demo.ExchangeRateEntities.MinfinExchangeRate;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Configuration
public interface MinfinRepository extends JpaRepository<MinfinExchangeRate, Integer>
{
}
