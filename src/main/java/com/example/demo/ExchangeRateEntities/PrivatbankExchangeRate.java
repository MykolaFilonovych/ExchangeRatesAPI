package com.example.demo.ExchangeRateEntities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "privatbank_exchange_rates", schema = "public")
public class PrivatbankExchangeRate extends ExchangeRate
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="basecurrency")
    private String baseCurrency;
    @Column(name="currency")
    private String currency;
    @Column(name="salerate_nb")
    private float saleRateNB;
    @Column(name="purchaserate_nb")
    private float purchaseRateNB;
    @Column(name="salerate")
    private float saleRate;
    @Column(name="purchaserate")
    private float purchaseRate;

    public PrivatbankExchangeRate()
    {

    }

    public int getId()
    {
        return id;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getSaleRateNB() {
        return saleRateNB;
    }

    public void setSaleRateNB(float saleRateNB) {
        this.saleRateNB = saleRateNB;
    }

    public float getPurchaseRateNB() {
        return purchaseRateNB;
    }

    public void setPurchaseRateNB(float purchaseRateNB) {
        this.purchaseRateNB = purchaseRateNB;
    }

    public float getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(float saleRate) {
        this.saleRate = saleRate;
    }

    public float getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(float purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrivatbankExchangeRate that = (PrivatbankExchangeRate) o;
        return Float.compare(that.saleRateNB, saleRateNB) == 0 && Float.compare(that.purchaseRateNB, purchaseRateNB) == 0 && Float.compare(that.saleRate, saleRate) == 0 && Float.compare(that.purchaseRate, purchaseRate) == 0 && baseCurrency.equals(that.baseCurrency) && currency.equals(that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseCurrency, currency, saleRateNB, purchaseRateNB, saleRate, purchaseRate);
    }

    @Override
    public String toString() {
        return "PrivatbankExchangeRate{ id=" + id +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", currency='" + currency + '\'' +
                ", saleRateNB=" + saleRateNB +
                ", purchaseRateNB=" + purchaseRateNB +
                ", saleRate=" + saleRate +
                ", purchaseRate=" + purchaseRate +
                '}';
    }
}
