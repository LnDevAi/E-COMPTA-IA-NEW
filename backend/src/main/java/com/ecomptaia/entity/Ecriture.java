package com.ecomptaia.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 📝 Entité écriture comptable
 * 
 * Représente une écriture comptable dans le système
 */
@Entity
@Table(name = "ecritures", indexes = {
    @Index(name = "idx_ecritures_number", columnList = "number"),
    @Index(name = "idx_ecritures_date", columnList = "date"),
    @Index(name = "idx_ecritures_journal", columnList = "journal_id"),
    @Index(name = "idx_ecritures_company", columnList = "company_id"),
    @Index(name = "idx_ecritures_active", columnList = "is_active")
})
@EntityListeners(AuditingEntityListener.class)
public class Ecriture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false, length = 20)
    private String number;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "reference", length = 100)
    private String reference;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "total_debit", nullable = false, precision = 19, scale = 2)
    private BigDecimal totalDebit = BigDecimal.ZERO;

    @Column(name = "total_credit", nullable = false, precision = 19, scale = 2)
    private BigDecimal totalCredit = BigDecimal.ZERO;

    @Column(name = "is_balanced", nullable = false)
    private Boolean isBalanced = false;

    @Column(name = "is_posted", nullable = false)
    private Boolean isPosted = false;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.DRAFT;

    @Column(name = "posted_date")
    private LocalDateTime postedDate;

    @Column(name = "posted_by")
    private String postedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "journal_id", nullable = false)
    private Journal journal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tiers_id")
    private Tiers tiers;

    @OneToMany(mappedBy = "ecriture", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LigneEcriture> lignes;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Enum pour les statuts d'écriture
    public enum Status {
        DRAFT,      // Brouillon
        VALIDATED,  // Validée
        POSTED,     // Comptabilisée
        CANCELLED   // Annulée
    }

    // Constructeurs
    public Ecriture() {}

    public Ecriture(String number, LocalDate date, Journal journal) {
        this.number = number;
        this.date = date;
        this.journal = journal;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(BigDecimal totalDebit) {
        this.totalDebit = totalDebit;
    }

    public BigDecimal getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(BigDecimal totalCredit) {
        this.totalCredit = totalCredit;
    }

    public Boolean getIsBalanced() {
        return isBalanced;
    }

    public void setIsBalanced(Boolean isBalanced) {
        this.isBalanced = isBalanced;
    }

    public Boolean getIsPosted() {
        return isPosted;
    }

    public void setIsPosted(Boolean isPosted) {
        this.isPosted = isPosted;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDateTime postedDate) {
        this.postedDate = postedDate;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Tiers getTiers() {
        return tiers;
    }

    public void setTiers(Tiers tiers) {
        this.tiers = tiers;
    }

    public List<LigneEcriture> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneEcriture> lignes) {
        this.lignes = lignes;
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
    public boolean isDraft() {
        return Status.DRAFT.equals(status);
    }

    public boolean isValidated() {
        return Status.VALIDATED.equals(status);
    }

    public boolean isPosted() {
        return Status.POSTED.equals(status);
    }

    public boolean isCancelled() {
        return Status.CANCELLED.equals(status);
    }

    public BigDecimal getBalance() {
        return totalDebit.subtract(totalCredit);
    }

    public boolean checkBalance() {
        return totalDebit.compareTo(totalCredit) == 0;
    }

    public void calculateTotals() {
        if (lignes != null && !lignes.isEmpty()) {
            this.totalDebit = lignes.stream()
                    .map(LigneEcriture::getDebit)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            
            this.totalCredit = lignes.stream()
                    .map(LigneEcriture::getCredit)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            
            this.isBalanced = checkBalance();
        }
    }

    public void post() {
        this.isPosted = true;
        this.status = Status.POSTED;
        this.postedDate = LocalDateTime.now();
    }

    public void validate() {
        this.status = Status.VALIDATED;
    }

    public void cancel() {
        this.status = Status.CANCELLED;
        this.isActive = false;
    }

    @Override
    public String toString() {
        return "Ecriture{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", date=" + date +
                ", reference='" + reference + '\'' +
                ", totalDebit=" + totalDebit +
                ", totalCredit=" + totalCredit +
                ", isBalanced=" + isBalanced +
                ", isPosted=" + isPosted +
                ", status=" + status +
                ", journal=" + (journal != null ? journal.getCode() : null) +
                ", company=" + (company != null ? company.getId() : null) +
                '}';
    }
}
