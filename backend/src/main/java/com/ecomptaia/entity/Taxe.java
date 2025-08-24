package com.ecomptaia.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 🏛️ Entité taxe
 * 
 * Représente une taxe dans le système comptable international
 */
@Entity
@Table(name = "taxes", indexes = {
    @Index(name = "idx_taxes_code", columnList = "code"),
    @Index(name = "idx_taxes_name", columnList = "name"),
    @Index(name = "idx_taxes_country", columnList = "country_id"),
    @Index(name = "idx_taxes_type", columnList = "type"),
    @Index(name = "idx_taxes_active", columnList = "is_active")
})
@EntityListeners(AuditingEntityListener.class)
public class Taxe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, length = 20)
    private String code;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "name_en", length = 200)
    private String nameEn;

    @Column(name = "name_fr", length = 200)
    private String nameFr;

    @Column(name = "description", length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type = Type.TVA;

    @Column(name = "taux", precision = 5, scale = 2, nullable = false)
    private BigDecimal taux = BigDecimal.ZERO;

    @Column(name = "taux_reduit", precision = 5, scale = 2)
    private BigDecimal tauxReduit;

    @Column(name = "taux_intermediaire", precision = 5, scale = 2)
    private BigDecimal tauxIntermediaire;

    @Column(name = "taux_super_reduit", precision = 5, scale = 2)
    private BigDecimal tauxSuperReduit;

    @Column(name = "seuil_minimum", precision = 19, scale = 2)
    private BigDecimal seuilMinimum;

    @Column(name = "seuil_maximum", precision = 19, scale = 2)
    private BigDecimal seuilMaximum;

    @Column(name = "compte_comptable", length = 20)
    private String compteComptable;

    @Column(name = "compte_contrepartie", length = 20)
    private String compteContrepartie;

    @Column(name = "devise", length = 3)
    private String devise = "XOF";

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "is_system", nullable = false)
    private Boolean isSystem = false;

    @Column(name = "is_obligatoire", nullable = false)
    private Boolean isObligatoire = true;

    @Column(name = "periode_declaration", length = 20)
    private String periodeDeclaration = "MENSUEL";

    @Column(name = "date_effet")
    private LocalDateTime dateEffet;

    @Column(name = "date_fin")
    private LocalDateTime dateFin;

    @Column(name = "numero_article", length = 50)
    private String numeroArticle;

    @Column(name = "reference_legale", length = 200)
    private String referenceLegale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "taxe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LigneEcriture> lignesEcriture;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Enum pour les types de taxes
    public enum Type {
        TVA,                    // Taxe sur la Valeur Ajoutée
        TPS,                    // Taxe sur les Prestations de Services
        TVQ,                    // Taxe de Vente du Québec
        IS,                     // Impôt sur les Sociétés
        IR,                     // Impôt sur le Revenu
        TSS,                    // Taxe sur les Salaires et Services
        TPA,                    // Taxe sur les Produits Alimentaires
        TIC,                    // Taxe sur les Importations et Consommations
        DROITS_DOUANE,          // Droits de douane
        ACCISES,                // Accises
        TIMBRE_FISCAL,          // Timbre fiscal
        PATENTE,                // Patente
        LICENCE,                // Licence
        AUTRES                  // Autres taxes
    }

    // Constructeurs
    public Taxe() {}

    public Taxe(String code, String name, Type type, BigDecimal taux) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.taux = taux;
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

    public BigDecimal getTaux() {
        return taux;
    }

    public void setTaux(BigDecimal taux) {
        this.taux = taux;
    }

    public BigDecimal getTauxReduit() {
        return tauxReduit;
    }

    public void setTauxReduit(BigDecimal tauxReduit) {
        this.tauxReduit = tauxReduit;
    }

    public BigDecimal getTauxIntermediaire() {
        return tauxIntermediaire;
    }

    public void setTauxIntermediaire(BigDecimal tauxIntermediaire) {
        this.tauxIntermediaire = tauxIntermediaire;
    }

    public BigDecimal getTauxSuperReduit() {
        return tauxSuperReduit;
    }

    public void setTauxSuperReduit(BigDecimal tauxSuperReduit) {
        this.tauxSuperReduit = tauxSuperReduit;
    }

    public BigDecimal getSeuilMinimum() {
        return seuilMinimum;
    }

    public void setSeuilMinimum(BigDecimal seuilMinimum) {
        this.seuilMinimum = seuilMinimum;
    }

    public BigDecimal getSeuilMaximum() {
        return seuilMaximum;
    }

    public void setSeuilMaximum(BigDecimal seuilMaximum) {
        this.seuilMaximum = seuilMaximum;
    }

    public String getCompteComptable() {
        return compteComptable;
    }

    public void setCompteComptable(String compteComptable) {
        this.compteComptable = compteComptable;
    }

    public String getCompteContrepartie() {
        return compteContrepartie;
    }

    public void setCompteContrepartie(String compteContrepartie) {
        this.compteContrepartie = compteContrepartie;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
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

    public Boolean getIsObligatoire() {
        return isObligatoire;
    }

    public void setIsObligatoire(Boolean isObligatoire) {
        this.isObligatoire = isObligatoire;
    }

    public String getPeriodeDeclaration() {
        return periodeDeclaration;
    }

    public void setPeriodeDeclaration(String periodeDeclaration) {
        this.periodeDeclaration = periodeDeclaration;
    }

    public LocalDateTime getDateEffet() {
        return dateEffet;
    }

    public void setDateEffet(LocalDateTime dateEffet) {
        this.dateEffet = dateEffet;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public String getNumeroArticle() {
        return numeroArticle;
    }

    public void setNumeroArticle(String numeroArticle) {
        this.numeroArticle = numeroArticle;
    }

    public String getReferenceLegale() {
        return referenceLegale;
    }

    public void setReferenceLegale(String referenceLegale) {
        this.referenceLegale = referenceLegale;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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
        return code + " - " + name + " (" + taux + "%)";
    }

    public String getNameByLocale(String locale) {
        if ("fr".equals(locale) && nameFr != null) {
            return nameFr;
        } else if ("en".equals(locale) && nameEn != null) {
            return nameEn;
        }
        return name;
    }

    public boolean isTVA() {
        return Type.TVA.equals(type);
    }

    public boolean isTPS() {
        return Type.TPS.equals(type);
    }

    public boolean isTVQ() {
        return Type.TVQ.equals(type);
    }

    public boolean isIS() {
        return Type.IS.equals(type);
    }

    public boolean isIR() {
        return Type.IR.equals(type);
    }

    public boolean isSystemTaxe() {
        return Boolean.TRUE.equals(isSystem);
    }

    public boolean isObligatoire() {
        return Boolean.TRUE.equals(isObligatoire);
    }

    public boolean isActive() {
        return Boolean.TRUE.equals(isActive);
    }

    public boolean isEnVigueur() {
        LocalDateTime now = LocalDateTime.now();
        return (dateEffet == null || now.isAfter(dateEffet)) && 
               (dateFin == null || now.isBefore(dateFin));
    }

    public BigDecimal calculerTaxe(BigDecimal montantHT) {
        if (montantHT == null || taux == null) {
            return BigDecimal.ZERO;
        }
        return montantHT.multiply(taux).divide(new BigDecimal("100"), 2, java.math.RoundingMode.HALF_UP);
    }

    public BigDecimal calculerTaxeReduite(BigDecimal montantHT) {
        if (montantHT == null || tauxReduit == null) {
            return BigDecimal.ZERO;
        }
        return montantHT.multiply(tauxReduit).divide(new BigDecimal("100"), 2, java.math.RoundingMode.HALF_UP);
    }

    public BigDecimal calculerTaxeIntermediaire(BigDecimal montantHT) {
        if (montantHT == null || tauxIntermediaire == null) {
            return BigDecimal.ZERO;
        }
        return montantHT.multiply(tauxIntermediaire).divide(new BigDecimal("100"), 2, java.math.RoundingMode.HALF_UP);
    }

    public BigDecimal calculerTaxeSuperReduite(BigDecimal montantHT) {
        if (montantHT == null || tauxSuperReduit == null) {
            return BigDecimal.ZERO;
        }
        return montantHT.multiply(tauxSuperReduit).divide(new BigDecimal("100"), 2, java.math.RoundingMode.HALF_UP);
    }

    public boolean estApplicable(BigDecimal montant) {
        if (montant == null) {
            return false;
        }
        
        if (seuilMinimum != null && montant.compareTo(seuilMinimum) < 0) {
            return false;
        }
        
        if (seuilMaximum != null && montant.compareTo(seuilMaximum) > 0) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "Taxe{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", taux=" + taux +
                ", isActive=" + isActive +
                ", country=" + (country != null ? country.getId() : null) +
                '}';
    }
}
