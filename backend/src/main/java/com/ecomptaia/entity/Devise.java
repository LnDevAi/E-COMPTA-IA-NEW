package com.ecomptaia.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 💰 Entité devise
 * 
 * Représente une devise dans le système comptable international
 */
@Entity
@Table(name = "devises", indexes = {
    @Index(name = "idx_devises_code", columnList = "code"),
    @Index(name = "idx_devises_name", columnList = "name"),
    @Index(name = "idx_devises_active", columnList = "is_active")
})
@EntityListeners(AuditingEntityListener.class)
public class Devise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true, nullable = false, length = 3)
    private String code;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "name_en", length = 100)
    private String nameEn;

    @Column(name = "name_fr", length = 100)
    private String nameFr;

    @Column(name = "symbol", length = 10)
    private String symbol;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "exchange_rate", precision = 19, scale = 6)
    private BigDecimal exchangeRate = BigDecimal.ONE;

    @Column(name = "is_base_currency", nullable = false)
    private Boolean isBaseCurrency = false;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "is_system", nullable = false)
    private Boolean isSystem = false;

    @Column(name = "decimal_places", nullable = false)
    private Integer decimalPlaces = 2;

    @Column(name = "rounding_method", length = 20)
    private String roundingMethod = "HALF_UP";

    @Column(name = "last_update_rate")
    private LocalDateTime lastUpdateRate;

    @Column(name = "source_rate", length = 50)
    private String sourceRate;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructeurs
    public Devise() {}

    public Devise(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Devise(String code, String name, String symbol) {
        this.code = code;
        this.name = name;
        this.symbol = symbol;
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

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameFr() {
        return nameFr;
    }

    public void setNameFr(String nameFr) {
        this.nameFr = nameFr;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Boolean getIsBaseCurrency() {
        return isBaseCurrency;
    }

    public void setIsBaseCurrency(Boolean isBaseCurrency) {
        this.isBaseCurrency = isBaseCurrency;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }

    public Integer getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(Integer decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public String getRoundingMethod() {
        return roundingMethod;
    }

    public void setRoundingMethod(String roundingMethod) {
        this.roundingMethod = roundingMethod;
    }

    public LocalDateTime getLastUpdateRate() {
        return lastUpdateRate;
    }

    public void setLastUpdateRate(LocalDateTime lastUpdateRate) {
        this.lastUpdateRate = lastUpdateRate;
    }

    public String getSourceRate() {
        return sourceRate;
    }

    public void setSourceRate(String sourceRate) {
        this.sourceRate = sourceRate;
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

    public String getDisplayNameWithSymbol() {
        return code + " (" + symbol + ") - " + name;
    }

    public String getNameByLocale(String locale) {
        if ("fr".equals(locale) && nameFr != null) {
            return nameFr;
        } else if ("en".equals(locale) && nameEn != null) {
            return nameEn;
        }
        return name;
    }

    public boolean isBaseCurrency() {
        return Boolean.TRUE.equals(isBaseCurrency);
    }

    public boolean isSystemCurrency() {
        return Boolean.TRUE.equals(isSystem);
    }

    public boolean isActiveCurrency() {
        return Boolean.TRUE.equals(isActive);
    }

    public String getFormattedAmount(BigDecimal amount) {
        if (amount == null) {
            return "0";
        }
        
        String pattern = "#,##0";
        if (decimalPlaces > 0) {
            pattern += "." + "0".repeat(decimalPlaces);
        }
        
        java.text.DecimalFormat formatter = new java.text.DecimalFormat(pattern);
        return formatter.format(amount);
    }

    public String getFormattedAmountWithSymbol(BigDecimal amount) {
        String formattedAmount = getFormattedAmount(amount);
        if (symbol != null && !symbol.isEmpty()) {
            return symbol + " " + formattedAmount;
        }
        return formattedAmount;
    }

    public BigDecimal convertAmount(BigDecimal amount, Devise targetCurrency) {
        if (amount == null || targetCurrency == null) {
            return amount;
        }
        
        if (this.equals(targetCurrency)) {
            return amount;
        }
        
        if (isBaseCurrency()) {
            return amount.multiply(targetCurrency.getExchangeRate());
        } else if (targetCurrency.isBaseCurrency()) {
            return amount.divide(exchangeRate, 6, java.math.RoundingMode.HALF_UP);
        } else {
            // Conversion via la devise de base
            BigDecimal baseAmount = amount.divide(exchangeRate, 6, java.math.RoundingMode.HALF_UP);
            return baseAmount.multiply(targetCurrency.getExchangeRate());
        }
    }

    public void updateExchangeRate(BigDecimal newRate, String source) {
        this.exchangeRate = newRate;
        this.lastUpdateRate = LocalDateTime.now();
        this.sourceRate = source;
    }

    @Override
    public String toString() {
        return "Devise{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", isBaseCurrency=" + isBaseCurrency +
                ", isActive=" + isActive +
                '}';
    }
}
