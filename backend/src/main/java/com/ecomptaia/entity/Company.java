package com.ecomptaia.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 🏢 Entité entreprise
 * 
 * Représente une entreprise dans le système comptable
 */
@Entity
@Table(name = "companies", indexes = {
    @Index(name = "idx_companies_name", columnList = "name"),
    @Index(name = "idx_companies_tax_id", columnList = "tax_id"),
    @Index(name = "idx_companies_country", columnList = "country_id"),
    @Index(name = "idx_companies_active", columnList = "is_active")
})
@EntityListeners(AuditingEntityListener.class)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "legal_name", length = 200)
    private String legalName;

    @Column(name = "tax_id", length = 50)
    private String taxId;

    @Column(name = "registration_number", length = 50)
    private String registrationNumber;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "website", length = 200)
    private String website;

    @Column(name = "fiscal_year_start", length = 10)
    private String fiscalYearStart = "01-01";

    @Column(name = "accounting_standard", length = 20)
    private String accountingStandard = "OHADA";

    @Column(name = "currency_code", length = 3)
    private String currencyCode = "XOF";

    @Column(name = "language", length = 5)
    private String language = "fr";

    @Column(name = "timezone", length = 50)
    private String timezone = "UTC";

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructeurs
    public Company() {}

    public Company(String name, String legalName) {
        this.name = name;
        this.legalName = legalName;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getFiscalYearStart() {
        return fiscalYearStart;
    }

    public void setFiscalYearStart(String fiscalYearStart) {
        this.fiscalYearStart = fiscalYearStart;
    }

    public String getAccountingStandard() {
        return accountingStandard;
    }

    public void setAccountingStandard(String accountingStandard) {
        this.accountingStandard = accountingStandard;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
        return legalName != null ? legalName : name;
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
        return address.toString();
    }

    public boolean isOHADA() {
        return "OHADA".equals(accountingStandard);
    }

    public boolean isIFRS() {
        return "IFRS".equals(accountingStandard);
    }

    public boolean isGAAP() {
        return "GAAP".equals(accountingStandard);
    }

    public boolean isFrench() {
        return "FRENCH".equals(accountingStandard);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", legalName='" + legalName + '\'' +
                ", taxId='" + taxId + '\'' +
                ", accountingStandard='" + accountingStandard + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", isActive=" + isActive +
                ", country=" + (country != null ? country.getCode() : null) +
                '}';
    }
}
