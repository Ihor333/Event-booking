package com.events.serviceclient.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long client_id;

    @NotNull
    @Column(name = "client_first_name")
    private String clientFirstName;

    @NotNull
    @Column(name = "client_second_name")
    private String clientSecondName;

    @NotNull
    @Column(name = "client_email")
    private String clientEmail;

    @NotNull
    @Column(name = "client_phone")
    private String clientPhone;

    @NotNull
    @Column(name = "client_password")
    private String clientPassword;

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientSecondName() {
        return clientSecondName;
    }

    public void setClientSecondName(String clientSecondName) {
        this.clientSecondName = clientSecondName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmailName) {
        this.clientEmail = clientEmailName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }
}
