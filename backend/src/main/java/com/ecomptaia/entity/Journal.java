package com.ecomptaia.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 📚 Entité journal comptable
 * 
 * Représente un journal comptable dans le système
 */
@Entity
@Table(name = "journals", indexes = {
    @Index(name = "idx_journals_code", columnList = "code"),
    @Index(name = "idx_journals_name", columnList = "name"),
    @Index(name = "idx_journals_company", columnList = "company_id"),
    @Index(name = "idx_journals_active", columnList = "is_active")
})
@EntityListeners(AuditingEntityListener.class)
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, length = 10)
    private String code;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type = Type.GENERAL;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "is_system", nullable = false)
    private Boolean isSystem = false;

    @Column(name = "sequence_number", nullable = false)
    private Integer sequenceNumber = 1;

    @Column(name = "last_sequence", nullable = false)
    private Integer lastSequence = 0;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "month")
    private Integer month;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "journal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ecriture> ecritures;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Enum pour les types de journaux
    public enum Type {
        GENERAL,        // Journal général
        ACHATS,         // Journal des achats
        VENTES,         // Journal des ventes
        BANQUE,         // Journal de banque
        CAISSE,         // Journal de caisse
        SALAIRES,       // Journal des salaires
        TAXES,          // Journal des taxes
        AMORTISSEMENTS, // Journal des amortissements
        PROVISIONS,     // Journal des provisions
        AUTRES          // Autres journaux
    }

    // Constructeurs
    public Journal() {}

    public Journal(String code, String name, Type type) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getLastSequence() {
        return lastSequence;
    }

    public void setLastSequence(Integer lastSequence) {
        this.lastSequence = lastSequence;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
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

    public boolean isGeneral() {
        return Type.GENERAL.equals(type);
    }

    public boolean isAchats() {
        return Type.ACHATS.equals(type);
    }

    public boolean isVentes() {
        return Type.VENTES.equals(type);
    }

    public boolean isBanque() {
        return Type.BANQUE.equals(type);
    }

    public boolean isCaisse() {
        return Type.CAISSE.equals(type);
    }

    public boolean isSalaires() {
        return Type.SALAIRES.equals(type);
    }

    public boolean isTaxes() {
        return Type.TAXES.equals(type);
    }

    public boolean isAmortissements() {
        return Type.AMORTISSEMENTS.equals(type);
    }

    public boolean isProvisions() {
        return Type.PROVISIONS.equals(type);
    }

    public boolean isAutres() {
        return Type.AUTRES.equals(type);
    }

    public String getNextSequence() {
        return String.format("%s%04d", code, lastSequence + 1);
    }

    public void incrementSequence() {
        this.lastSequence++;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", isActive=" + isActive +
                ", isSystem=" + isSystem +
                ", year=" + year +
                ", company=" + (company != null ? company.getId() : null) +
                '}';
    }
}
