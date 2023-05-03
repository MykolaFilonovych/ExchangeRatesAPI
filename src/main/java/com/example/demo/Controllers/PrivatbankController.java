package com.example.demo.Controllers;

import com.example.demo.ExchangeRateEntities.ExchangeRate;
import com.example.demo.ExchangeRateEntities.PrivatbankExchangeRate;
import com.example.demo.Repositories.PrivatbankRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/privatbank")
@RestController
@Service
public class PrivatbankController implements ExchangeRatesAPI
{
    @Autowired
    private PrivatbankRepository privatbankRepository;
    private List<ExchangeRate> privatbankExchangeRates;

    @Override
    @RequestMapping(value = "/exchangeRates")
    public List<ExchangeRate> getListOfExchangeRatesWithAverageMarketRates()
    {
        privatbankExchangeRates = new ArrayList<>();
        privatbankRepository.deleteAll();

        String url = "https://api.privatbank.ua/p24api/exchange_rates?date=";
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String todayDate = dateFormat.format(date);
        url += todayDate;

        List<PrivatbankExchangeRate> exchangeRatesList = getExchangeRates(url);

        privatbankExchangeRates.addAll(exchangeRatesList);

        return privatbankExchangeRates;
    }

    @Override
    @RequestMapping(value = "/exchangeRates/{date}") // Date format: dd.MM.yyyy
    public List<ExchangeRate> getListOfAverageExchangeRatesForThePeriod(@PathVariable String date)
    {
        privatbankExchangeRates = new ArrayList<>();
        privatbankRepository.deleteAll();

        String url = "https://api.privatbank.ua/p24api/exchange_rates?date=" + date;

        List<PrivatbankExchangeRate> exchangeRatesList = getExchangeRates(url);
        privatbankExchangeRates = new ArrayList<>();
        privatbankExchangeRates.addAll(exchangeRatesList);

        return privatbankExchangeRates;
    }

    @Override
    public List<ExchangeRate> getListOfAverageExchangeRatesForThePeriod(long from, long to)
    {
        // This method is useful for MonobankExchangeRates, because there time is represented in milliseconds,
        // but in Privatbank and Minfin APIs it is possible to get data by date.
        return new ArrayList<>();
    }

    private List<PrivatbankExchangeRate> getExchangeRates(String url)
    {
        ResponseEntity<Map> response = new RestTemplate().getForEntity(url, Map.class);
        Object exchangeRates = response.getBody().get("exchangeRate");

        List<Object> exchangeRatesStrings = new ArrayList<>();

        if(exchangeRates instanceof List)
        {
            exchangeRatesStrings = (List)exchangeRates;
        }

        Gson gsonToExchangeRates = new Gson();

        for(Object element: exchangeRatesStrings)
        {
            PrivatbankExchangeRate privatbankExchangeRate = gsonToExchangeRates.fromJson(element.toString(), PrivatbankExchangeRate.class);
            privatbankRepository.save(privatbankExchangeRate);
        }

        return privatbankRepository.findAll();
    }
}
