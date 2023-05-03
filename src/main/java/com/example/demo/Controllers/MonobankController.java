package com.example.demo.Controllers;

import com.example.demo.ExchangeRateEntities.ExchangeRate;
import com.example.demo.ExchangeRateEntities.MonobankExchangeRate;
import com.example.demo.Repositories.MonobankRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RequestMapping("/monobank")
@RestController
@Service
public class MonobankController implements ExchangeRatesAPI
{
    @Autowired
    private MonobankRepository monobankRepository;
    private List<ExchangeRate> monobankExchangeRates;

    @RequestMapping(value = "/exchangeRates")
    public List<ExchangeRate> getListOfExchangeRatesWithAverageMarketRates()
    {
        if(monobankExchangeRates == null)
        {
            monobankExchangeRates = new ArrayList<>();
            monobankRepository.deleteAll();

            ResponseEntity<List> response = new RestTemplate().getForEntity(
                    "https://api.monobank.ua/bank/currency",
                    List.class);
            List<Object> exchangeRatesStrings = response.getBody();

            Gson gsonToExchangeRates = new Gson();

            for(Object element: exchangeRatesStrings)
            {
                MonobankExchangeRate monobankExchangeRate = gsonToExchangeRates.fromJson(element.toString(),
                        MonobankExchangeRate.class);
                monobankRepository.save(monobankExchangeRate);
            }

            List<MonobankExchangeRate> exchangeRatesList = monobankRepository.findAll();

            monobankExchangeRates.addAll(exchangeRatesList);
        }

        return monobankExchangeRates;
    }

    @RequestMapping(value = "/exchangeRates/{from}/{to}") // Monobank exchange rates' time is represented in milliseconds
    public List<ExchangeRate> getListOfAverageExchangeRatesForThePeriod(@PathVariable long from, @PathVariable long to)
    {
        if(monobankExchangeRates == null)
        {
            monobankExchangeRates = getListOfExchangeRatesWithAverageMarketRates();
        }

        List<ExchangeRate> exchangeRatesForPeriod = new ArrayList<>();

        for(ExchangeRate exchangeRate: monobankExchangeRates)
        {
            MonobankExchangeRate monobankExchangeRate = (MonobankExchangeRate) exchangeRate;
            if(to == 0)
            {
                if(monobankExchangeRate.getDate()>=from)
                {
                    exchangeRatesForPeriod.add(monobankExchangeRate);
                }
            }
            else
            {
                if((monobankExchangeRate.getDate() >= from) && (monobankExchangeRate.getDate() <= to))
                {
                    exchangeRatesForPeriod.add(monobankExchangeRate);
                }
            }
        }

        return exchangeRatesForPeriod;
    }

    @RequestMapping(value = "/exchangeRates/{from}") // Monobank exchange rates' time is represented in milliseconds
    public List<ExchangeRate> getListOfAverageExchangeRatesForThePeriod(@PathVariable long from)
    {
        if(monobankExchangeRates == null)
        {
            monobankExchangeRates = getListOfExchangeRatesWithAverageMarketRates();
        }

        return getExchangeRates(from);
    }

    @Override
    @RequestMapping(value = "/exchangeRatesByDate/{fromDate}") // Date format: dd.MM.yyyy
    public List<ExchangeRate> getListOfAverageExchangeRatesForThePeriod(@PathVariable String fromDate)
    {
        if(monobankExchangeRates == null)
        {
            monobankExchangeRates = getListOfExchangeRatesWithAverageMarketRates();
        }

        fromDate = fromDate.replaceAll("\\.",",");
        String[] dateParts = fromDate.split(",");

        int year = Integer.parseInt(dateParts[2]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[0]);

        Date date = getDate(year, month, day);

        date.setYear(year-1900);
        date.setMonth(month);

        long fromTime = date.getTime();

        return getExchangeRates(fromTime);
    }

    private List<ExchangeRate> getExchangeRates(long fromTime)
    {
        List<ExchangeRate> exchangeRatesForPeriod = new ArrayList<>();

        for(ExchangeRate exchangeRate: monobankExchangeRates)
        {
            MonobankExchangeRate monobankExchangeRate = (MonobankExchangeRate) exchangeRate;

            if(monobankExchangeRate.getDate()>=fromTime)
            {
                exchangeRatesForPeriod.add(monobankExchangeRate);
            }
        }

        return exchangeRatesForPeriod;
    }

    private Date getDate(int year, int month, int day)
    {
        int calendar;

        switch (month)
        {
            case 1:
                calendar = Calendar.JANUARY;
                break;
            case 2:
                calendar = Calendar.FEBRUARY;
                break;
            case 3:
                calendar = Calendar.MARCH;
                break;
            case 4:
                calendar = Calendar.APRIL;
                break;
            case 5:
                calendar = Calendar.MAY;
                break;
            case 6:
                calendar = Calendar.JUNE;
                break;
            case 7:
                calendar = Calendar.JULY;
                break;
            case 8:
                calendar = Calendar.AUGUST;
                break;
            case 9:
                calendar = Calendar.SEPTEMBER;
                break;
            case 10:
                calendar = Calendar.OCTOBER;
                break;
            case 11:
                calendar = Calendar.NOVEMBER;
                break;
            case 12:
                calendar = Calendar.DECEMBER;
                break;
            default:
                calendar = Calendar.JANUARY;
                break;
        }

        return new Date(year, calendar, day);
    }
}
