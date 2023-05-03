package com.example.demo.Controllers;

import com.example.demo.ExchangeRateEntities.ExchangeRate;
import com.example.demo.ExchangeRateEntities.MinfinExchangeRate;
import com.example.demo.Repositories.MinfinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/minfin")
@RestController
@Service
public class MinfinController implements ExchangeRatesAPI
{
    @Autowired
    private MinfinRepository minfinRepository;
    private List<ExchangeRate> minfinExchangeRates;
    // APIKey might be alternative, so it is put into the separate variable(field).
    private final String APIKey = "77a9c87ae96cdf2f20c7202ef9aa92a196c3eb94";

    @Override
    @RequestMapping("/exchangeRates")
    public List<ExchangeRate> getListOfExchangeRatesWithAverageMarketRates()
    {
        minfinExchangeRates = new ArrayList<>();
        minfinRepository.deleteAll();

        String url = "https://api.minfin.com.ua/mb/" + APIKey + "/";

        List<MinfinExchangeRate> exchangeRatesList = getExchangeRates(url);

        minfinExchangeRates.addAll(exchangeRatesList);
        return minfinExchangeRates;
    }

    @Override
    @RequestMapping("/exchangeRates/{date}") // Date format: yyyy-MM-dd
    public List<ExchangeRate> getListOfAverageExchangeRatesForThePeriod(@PathVariable String date)
    {
        minfinExchangeRates = new ArrayList<>();
        minfinRepository.deleteAll();

        String url = "https://api.minfin.com.ua/mb/" + APIKey + "/" + date + "/";

        List<MinfinExchangeRate> exchangeRatesList = getExchangeRates(url);

        minfinExchangeRates.addAll(exchangeRatesList);
        return minfinExchangeRates;
    }

    @Override
    public List<ExchangeRate> getListOfAverageExchangeRatesForThePeriod(long from, long to)
    {
        // This method is useful for MonobankExchangeRates, because there time is represented in milliseconds,
        // but in Privatbank and Minfin APIs it is possible to get data by date.
        return new ArrayList<>();
    }

    private List<MinfinExchangeRate> getExchangeRates(String url)
    {
        ResponseEntity<List> response = new RestTemplate().getForEntity(url, List.class);
        List<Object> exchangeRatesStrings = response.getBody();

        for(Object element: exchangeRatesStrings)
        {
            Map map = (Map)element;
            Object[] exchangeRateFields = ((Map)element).keySet().toArray();

            MinfinExchangeRate minfinExchangeRate = new MinfinExchangeRate();

            minfinExchangeRate.setId((map.get(exchangeRateFields[0])).toString());
            minfinExchangeRate.setPointDate((map.get(exchangeRateFields[1])).toString());
            minfinExchangeRate.setDate((map.get(exchangeRateFields[2])).toString());
            minfinExchangeRate.setAsk((map.get(exchangeRateFields[3])).toString());
            minfinExchangeRate.setBid((map.get(exchangeRateFields[4])).toString());
            minfinExchangeRate.setTrendAsk((map.get(exchangeRateFields[5])).toString());
            minfinExchangeRate.setTrendBid((map.get(exchangeRateFields[6])).toString());
            minfinExchangeRate.setCurrency((map.get(exchangeRateFields[7])).toString());

            minfinRepository.save(minfinExchangeRate);
        }
        return minfinRepository.findAll();
    }
}
