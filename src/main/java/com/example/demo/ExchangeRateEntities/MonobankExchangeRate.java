package com.example.demo.ExchangeRateEntities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "monobank_exchange_rates", schema = "public")
public class MonobankExchangeRate extends ExchangeRate
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name = "currency_codea")
    private int currencyCodeA;
    @Column(name = "currency_codeb")
    private int currencyCodeB;
    @Column(name = "date")
    private long date;
    @Column(name = "ratebuy")
    private float rateBuy;
    @Column(name = "ratecross")
    private float rateCross;
    @Column(name = "ratesell")
    private float rateSell;

    public MonobankExchangeRate()
    {

    }

    public int getId() {
        return id;
    }

    public int getCurrencyCodeA() {
        return currencyCodeA;
    }

    public void setCurrencyCodeA(int currencyCodeA) {
        this.currencyCodeA = currencyCodeA;
    }

    public int getCurrencyCodeB() {
        return currencyCodeB;
    }

    public void setCurrencyCodeB(int currencyCodeB) {
        this.currencyCodeB = currencyCodeB;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public float getRateBuy() {
        return rateBuy;
    }

    public void setRateBuy(float rateBuy) {
        this.rateBuy = rateBuy;
    }

    public float getRateCross() {
        return rateCross;
    }

    public void setRateCross(float rateCross) {
        this.rateCross = rateCross;
    }

    public float getRateSell() {
        return rateSell;
    }

    public void setRateSell(float rateSell) {
        this.rateSell = rateSell;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonobankExchangeRate that = (MonobankExchangeRate) o;
        return currencyCodeA == that.currencyCodeA && currencyCodeB == that.currencyCodeB && date == that.date && Float.compare(that.rateBuy, rateBuy) == 0 && Float.compare(that.rateCross, rateCross) == 0 && Float.compare(that.rateSell, rateSell) == 0;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(currencyCodeA, currencyCodeB, date, rateBuy, rateCross, rateSell);
    }

    @Override
    public String toString() {
        return "MonobankExchangeRate{ id=" + id +
                ", currencyCodeA=" + currencyCodeA +
                ", currencyCodeB=" + currencyCodeB +
                ", date=" + date +
                ", rateBuy=" + rateBuy +
                ", rateCross=" + rateCross +
                ", rateSell=" + rateSell +
                '}';
    }
}
