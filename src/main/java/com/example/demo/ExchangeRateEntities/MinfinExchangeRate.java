package com.example.demo.ExchangeRateEntities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="minfin_exchange_rates", schema = "public")
public class MinfinExchangeRate extends ExchangeRate
{
    @Id
    @Column(name="id")
    private String id;
    @Column(name="pointdate")
    private String pointDate;
    @Column(name="date")
    private String date;
    @Column(name="ask")
    private String ask;
    @Column(name="bid")
    private String bid;
    @Column(name="trendask")
    private String trendAsk;
    @Column(name="trendbid")
    private String trendBid;
    @Column(name="currency")
    private String currency;

    public MinfinExchangeRate()
    {

    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPointDate()
    {
        return pointDate;
    }

    public void setPointDate(String pointDate)
    {
        this.pointDate = pointDate;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getAsk()
    {
        return ask;
    }

    public void setAsk(String ask)
    {
        this.ask = ask;
    }

    public String getBid()
    {
        return bid;
    }

    public void setBid(String bid)
    {
        this.bid = bid;
    }

    public String getTrendAsk()
    {
        return trendAsk;
    }

    public void setTrendAsk(String trendAsk)
    {
        this.trendAsk = trendAsk;
    }

    public String getTrendBid()
    {
        return trendBid;
    }

    public void setTrendBid(String trendBid)
    {
        this.trendBid = trendBid;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MinfinExchangeRate that = (MinfinExchangeRate) o;
        return id.equals(that.id) && pointDate.equals(that.pointDate) && date.equals(that.date) && ask.equals(that.ask) && bid.equals(that.bid) && trendAsk.equals(that.trendAsk) && trendBid.equals(that.trendBid) && currency.equals(that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pointDate, date, ask, bid, trendAsk, trendBid, currency);
    }

    @Override
    public String toString() {
        return "MinfinExchangeRate{" +
                "id='" + id + '\'' +
                ", pointDate='" + pointDate + '\'' +
                ", date='" + date + '\'' +
                ", ask='" + ask + '\'' +
                ", bid='" + bid + '\'' +
                ", trendAsk='" + trendAsk + '\'' +
                ", trendBid='" + trendBid + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
