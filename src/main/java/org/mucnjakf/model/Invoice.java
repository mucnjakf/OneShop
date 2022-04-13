package org.mucnjakf.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Invoices", schema = "dbo", catalog = "OneShopDB")
public class Invoice {

    private int id;
    private Date date;
    private Time time;
    private String methodOfPayment;
    private Double totalPrice;
    private User user;
    private Collection<Product> products;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "Time")
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Basic
    @Column(name = "MethodOfPayment")
    public String getMethodOfPayment() {
        return methodOfPayment;
    }

    public void setMethodOfPayment(String methodOfPayment) {
        this.methodOfPayment = methodOfPayment;
    }

    @Basic
    @Column(name = "TotalPrice")
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "Id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToMany(mappedBy = "invoices", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        if (id != invoice.id) return false;
        if (!Objects.equals(date, invoice.date)) return false;
        if (!Objects.equals(time, invoice.time)) return false;
        if (!Objects.equals(methodOfPayment, invoice.methodOfPayment)) return false;
        if (!Objects.equals(totalPrice, invoice.totalPrice)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (methodOfPayment != null ? methodOfPayment.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        return result;
    }
}
