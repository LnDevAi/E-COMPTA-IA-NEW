package com.ecomptaia.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 📊 Entité ligne d'écriture comptable
 * 
 * Représente une ligne d'écriture comptable dans le système
 */
@Entity
@Table(name = "lignes_ecriture", indexes = {
    @Index(name = "idx_lignes_ecriture_ecriture", columnList = "ecriture_id"),
    @Index(name = "idx_lignes_ecriture_compte", columnList = "compte_id"),
    @Index(name = "idx_lignes_ecriture_tiers", columnList = "tiers_id"),
    @Index(name = "idx_lignes_ecriture_active", columnList = "is_active")
})
@EntityListeners(AuditingEntityListener.class)
public class LigneEcriture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "line_number", nullable = false)
    private Integer lineNumber;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "debit", nullable = false, precision = 19, scale = 2)
    private BigDecimal debit = BigDecimal.ZERO;

    @Column(name = "credit", nullable = false, precision = 19, scale = 2)
    private BigDecimal credit = BigDecimal.ZERO;

    @Column(name = "amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(name = "currency_rate", precision = 10, scale = 6)
    private BigDecimal currencyRate = BigDecimal.ONE;

    @Column(name = "currency_amount", precision = 19, scale = 2)
    private BigDecimal currencyAmount;

    @Column(name = "currency_code", length = 3)
    private String currencyCode;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ecriture_id", nullable = false)
    private Ecriture ecriture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compte_id", nullable = false)
    private PlanComptable compte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tiers_id")
    private Tiers tiers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taxe_id")
    private Taxe taxe;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructeurs
    public LigneEcriture() {}

    public LigneEcriture(Integer lineNumber, String description, PlanComptable compte) {
        this.lineNumber = lineNumber;
        this.description = description;
        this.compte = compte;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
        this.amount = debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
        this.amount = credit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }

    public BigDecimal getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(BigDecimal currencyAmount) {
        this.currencyAmount = currencyAmount;
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

    public Ecriture getEcriture() {
        return ecriture;
    }

    public void setEcriture(Ecriture ecriture) {
        this.ecriture = ecriture;
    }

    public PlanComptable getCompte() {
        return compte;
    }

    public void setCompte(PlanComptable compte) {
        this.compte = compte;
    }

    public Tiers getTiers() {
        return tiers;
    }

    public void setTiers(Tiers tiers) {
        this.tiers = tiers;
    }

    public Taxe getTaxe() {
        return taxe;
    }

    public void setTaxe(Taxe taxe) {
        this.taxe = taxe;
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
    public boolean isDebit() {
        return debit.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isCredit() {
        return credit.compareTo(BigDecimal.ZERO) > 0;
    }

    public BigDecimal getNetAmount() {
        return debit.subtract(credit);
    }

    public void setDebitAmount(BigDecimal amount) {
        this.debit = amount;
        this.credit = BigDecimal.ZERO;
        this.amount = amount;
    }

    public void setCreditAmount(BigDecimal amount) {
        this.credit = amount;
        this.debit = BigDecimal.ZERO;
        this.amount = amount;
    }

    public void calculateCurrencyAmount() {
        if (currencyRate != null && amount != null) {
            this.currencyAmount = amount.multiply(currencyRate);
        }
    }

    @Override
    public String toString() {
        return "LigneEcriture{" +
                "id=" + id +
                ", lineNumber=" + lineNumber +
                ", description='" + description + '\'' +
                ", debit=" + debit +
                ", credit=" + credit +
                ", amount=" + amount +
                ", compte=" + (compte != null ? compte.getNumero() : null) +
                ", ecriture=" + (ecriture != null ? ecriture.getNumber() : null) +
                '}';
    }
}
