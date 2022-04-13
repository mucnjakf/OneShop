package org.mucnjakf.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "AdminPin", schema = "dbo", catalog = "OneShopDB")
public class AdminPin {

    private int id;
    private String pinSalt;
    private String pinHash;

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
    @Column(name = "PinSalt")
    public String getPinSalt() {
        return pinSalt;
    }

    public void setPinSalt(String pinSalt) {
        this.pinSalt = pinSalt;
    }

    @Basic
    @Column(name = "PinHash")
    public String getPinHash() {
        return pinHash;
    }

    public void setPinHash(String pinHash) {
        this.pinHash = pinHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminPin adminPin = (AdminPin) o;

        if (id != adminPin.id) return false;
        if (!Objects.equals(pinSalt, adminPin.pinSalt)) return false;
        if (!Objects.equals(pinHash, adminPin.pinHash)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pinSalt != null ? pinSalt.hashCode() : 0);
        result = 31 * result + (pinHash != null ? pinHash.hashCode() : 0);
        return result;
    }
}
