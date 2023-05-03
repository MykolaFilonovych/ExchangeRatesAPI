package com.example.demo.Controllers;

import com.example.demo.ExchangeRateEntities.ExchangeRate;

import java.util.List;

public interface ExchangeRatesAPI
{
    List<ExchangeRate> getListOfExchangeRatesWithAverageMarketRates();
    List<ExchangeRate> getListOfAverageExchangeRatesForThePeriod(long from, long to);
    List<ExchangeRate> getListOfAverageExchangeRatesForThePeriod(String from);
}
