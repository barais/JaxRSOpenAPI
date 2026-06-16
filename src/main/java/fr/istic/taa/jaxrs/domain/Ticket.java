package fr.istic.taa.jaxrs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.io.Serializable;
import java.util.Date;

@Entity
public class Ticket implements Serializable {
    private Long id;
    private Long price;
    private int quantity;
    private  String buyerEmail;
    private String transferorEmail; // Celui qui a donné le ticket
    private String status; // 'confirmed', 'pending', 'cancelled', 'transferred'
    private boolean isCanceled;
    private boolean isRefunded;
    private Date date;
    private Date cancelDate;
    private Date refundDate;
//    private User user;
    private Concert concert;

    public Ticket() {

    }

    @Id
    @GeneratedValue()
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    public boolean isRefunded() {
        return isRefunded;
    }

    public void setRefunded(boolean isRefunded) {
        this.isRefunded = isRefunded;
    }

    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    @Temporal(TemporalType.DATE)
    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    @Temporal(TemporalType.DATE)
    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getTransferorEmail() { return transferorEmail; }
    public void setTransferorEmail(String transferorEmail) { this.transferorEmail = transferorEmail; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

//    @JsonIgnore
    @ManyToOne
    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }
}
