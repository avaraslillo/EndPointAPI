package com.nisum.endpointapi.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="telefono")
public class Telephone {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="uuid")
    private UUID uuid;
    @Column(name = "phone_number")
    @JsonProperty("number")
    private String phone_number;
    @Column(name = "phone_citycode")
    @JsonProperty("citycode")
    private String phone_citycode;
    @Column(name = "phone_countrycode")
    @JsonProperty("countrycode")
    private String phone_countrycode;
    @ManyToOne
    @JoinColumn(name = "usuario_uuid",referencedColumnName = "uuid")
    private User usuario;

    public UUID getUuid() {
        return uuid;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public String getPhone_citycode() {
        return phone_citycode;
    }
    public void setPhone_citycode(String phone_citycode) {
        this.phone_citycode = phone_citycode;
    }
    public String getPhone_countrycode() {
        return phone_countrycode;
    }
    public void setPhone_countrycode(String phone_countrycode) {
        this.phone_countrycode = phone_countrycode;
    }
    public User getUsuario() {
        return usuario;
    }
    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Telephone(){

    }
    public Telephone(UUID uuid, String phone_number, String phone_citycode, String phone_countrycode, User usuario) {
        this.uuid = uuid;
        this.phone_number = phone_number;
        this.phone_citycode = phone_citycode;
        this.phone_countrycode = phone_countrycode;
        this.usuario = usuario;
    }

    public Telephone(String phone_number, String phone_citycode, String phone_countrycode, User usuario) {
        this.phone_number = phone_number;
        this.phone_citycode = phone_citycode;
        this.phone_countrycode = phone_countrycode;
        this.usuario=usuario;
    }

    public Telephone(String phone_number, String phone_citycode, String phone_countrycode) {
        this.phone_number = phone_number;
        this.phone_citycode = phone_citycode;
        this.phone_countrycode = phone_countrycode;
    }
    @Override
    public String toString() {
        return "Telephone [uuid=" + uuid + ", phone_number=" + phone_number + ", phone_citycode=" + phone_citycode
                + ", phone_countrycode=" + phone_countrycode + ", usuario=" + usuario + "]";
    }




    
    
}
