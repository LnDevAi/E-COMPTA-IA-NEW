package com.ecomptaia.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 👥 Entité tiers (clients/fournisseurs)
 * 
 * Représente un tiers (client ou fournisseur) dans le système comptable
 */
@Entity
@Table(name = "tiers", indexes = {
    @Index(name = "idx_tiers_code", columnList = "code"),
    @Index(name = "idx_tiers_name", columnList = "name"),
    @Index(name = "idx_tiers_type", columnList = "type"),
    @Index(name = "idx_tiers_company", columnList = "company_id"),
    @Index(name = "idx_tiers_active", columnList = "is_active")
})
@EntityListeners(AuditingEntityListener.class)
public class Tiers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, length = 20)
    private String code;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "short_name", length = 100)
    private String shortName;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type = Type.CLIENT;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "mobile", length = 20)
    private String mobile;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "website", length = 200)
    private String website;

    @Column(name = "tax_number", length = 50)
    private String taxNumber;

    @Column(name = "registration_number", length = 50)
    private String registrationNumber;

    @Column(name = "bank_account", length = 50)
    private String bankAccount;

    @Column(name = "bank_name", length = 100)
    private String bankName;

    @Column(name = "credit_limit", precision = 19, scale = 2)
    private BigDecimal creditLimit = BigDecimal.ZERO;

    @Column(name = "payment_terms", length = 100)
    private String paymentTerms;

    @Column(name = "currency_code", length = 3)
    private String currencyCode;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "is_supplier", nullable = false)
    private Boolean isSupplier = false;

    @Column(name = "is_customer", nullable = false)
    private Boolean isCustomer = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "tiers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ecriture> ecritures;

    @OneToMany(mappedBy = "tiers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LigneEcriture> lignesEcriture;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Enum pour les types de tiers
    public enum Type {
        CLIENT,         // Client
        FOURNISSEUR,    // Fournisseur
        CLIENT_FOURNISSEUR, // Client et fournisseur
        AUTRES          // Autres tiers
    }

    // Constructeurs
    public Tiers() {}

    public Tiers(String code, String name, Type type) {
        this.code = code;
        this.name = name;
        this.type = type;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsSupplier() {
        return isSupplier;
    }

    public void setIsSupplier(Boolean isSupplier) {
        this.isSupplier = isSupplier;
    }

    public Boolean getIsCustomer() {
        return isCustomer;
    }

    public void setIsCustomer(Boolean isCustomer) {
        this.isCustomer = isCustomer;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Ecriture> getEcritures() {
        return ecritures;
    }

    public void setEcritures(List<Ecriture> ecritures) {
        this.ecritures = ecritures;
    }

    public List<LigneEcriture> getLignesEcriture() {
        return lignesEcriture;
    }

    public void setLignesEcriture(List<LigneEcriture> lignesEcriture) {
        this.lignesEcriture = lignesEcriture;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Méthodes utilitaires
    public String getDisplayName() {
        return code + " - " + name;
    }

    public boolean isClient() {
        return Type.CLIENT.equals(type) || Type.CLIENT_FOURNISSEUR.equals(type);
    }

    public boolean isFournisseur() {
        return Type.FOURNISSEUR.equals(type) || Type.CLIENT_FOURNISSEUR.equals(type);
    }

    public boolean isClientEtFournisseur() {
        return Type.CLIENT_FOURNISSEUR.equals(type);
    }

    public boolean isAutres() {
        return Type.AUTRES.equals(type);
    }

    public String getFullAddress() {
        StringBuilder address = new StringBuilder();
        if (this.address != null) {
            address.append(this.address);
        }
        if (this.city != null) {
            if (address.length() > 0) address.append(", ");
            address.append(this.city);
        }
        if (this.postalCode != null) {
            if (address.length() > 0) address.append(" ");
            address.append(this.postalCode);
        }
        if (this.country != null) {
            if (address.length() > 0) address.append(", ");
            address.append(this.country);
        }
        return address.toString();
    }

    public String getContactInfo() {
        StringBuilder contact = new StringBuilder();
        if (this.phone != null) {
            contact.append("Tél: ").append(this.phone);
        }
        if (this.mobile != null) {
            if (contact.length() > 0) contact.append(" | ");
            contact.append("Mobile: ").append(this.mobile);
        }
        if (this.email != null) {
            if (contact.length() > 0) contact.append(" | ");
            contact.append("Email: ").append(this.email);
        }
        return contact.toString();
    }

    @Override
    public String toString() {
        return "Tiers{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", isActive=" + isActive +
                ", isSupplier=" + isSupplier +
                ", isCustomer=" + isCustomer +
                ", company=" + (company != null ? company.getId() : null) +
                '}';
    }
}
