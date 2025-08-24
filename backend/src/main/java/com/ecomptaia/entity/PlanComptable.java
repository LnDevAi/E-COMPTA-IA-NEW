package com.ecomptaia.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 📋 Entité plan comptable
 * 
 * Représente un compte du plan comptable dans le système
 */
@Entity
@Table(name = "plan_comptable", indexes = {
    @Index(name = "idx_plan_comptable_numero", columnList = "numero"),
    @Index(name = "idx_plan_comptable_intitule", columnList = "intitule"),
    @Index(name = "idx_plan_comptable_company", columnList = "company_id"),
    @Index(name = "idx_plan_comptable_standard", columnList = "standard_comptable"),
    @Index(name = "idx_plan_comptable_actif", columnList = "is_actif")
})
@EntityListeners(AuditingEntityListener.class)
public class PlanComptable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero", nullable = false, length = 20)
    private String numero;

    @Column(name = "intitule", nullable = false, length = 200)
    private String intitule;

    @Column(name = "description", length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_compte", nullable = false)
    private TypeCompte typeCompte = TypeCompte.ACTIF;

    @Enumerated(EnumType.STRING)
    @Column(name = "categorie", nullable = false)
    private Categorie categorie = Categorie.GENERAL;

    @Column(name = "niveau", nullable = false)
    private Integer niveau = 1;

    @Column(name = "compte_parent")
    private String compteParent;

    @Column(name = "solde_initial", precision = 19, scale = 2)
    private BigDecimal soldeInitial = BigDecimal.ZERO;

    @Column(name = "solde_actuel", precision = 19, scale = 2)
    private BigDecimal soldeActuel = BigDecimal.ZERO;

    @Column(name = "devise", length = 3)
    private String devise = "XOF";

    @Column(name = "taux_change", precision = 10, scale = 6)
    private BigDecimal tauxChange = BigDecimal.ONE;

    @Column(name = "is_actif", nullable = false)
    private Boolean isActif = true;

    @Column(name = "is_system", nullable = false)
    private Boolean isSystem = false;

    @Column(name = "is_analytique", nullable = false)
    private Boolean isAnalytique = false;

    @Column(name = "is_tiers", nullable = false)
    private Boolean isTiers = false;

    @Column(name = "is_taxe", nullable = false)
    private Boolean isTaxe = false;

    @Column(name = "is_banque", nullable = false)
    private Boolean isBanque = false;

    @Column(name = "is_caisse", nullable = false)
    private Boolean isCaisse = false;

    @Column(name = "standard_comptable", length = 20)
    private String standardComptable = "OHADA";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LigneEcriture> lignesEcriture;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Enum pour les types de comptes
    public enum TypeCompte {
        ACTIF,      // Comptes d'actif
        PASSIF,     // Comptes de passif
        CHARGES,    // Comptes de charges
        PRODUITS,   // Comptes de produits
        AUTRES      // Autres comptes
    }

    // Enum pour les catégories de comptes
    public enum Categorie {
        GENERAL,        // Comptes généraux
        ANALYTIQUE,     // Comptes analytiques
        TIERS,          // Comptes tiers
        BANQUE,         // Comptes de banque
        CAISSE,         // Comptes de caisse
        TAXE,           // Comptes de taxes
        AMORTISSEMENT,  // Comptes d'amortissement
        PROVISION,      // Comptes de provision
        AUTRES          // Autres catégories
    }

    // Constructeurs
    public PlanComptable() {}

    public PlanComptable(String numero, String intitule, TypeCompte typeCompte) {
        this.numero = numero;
        this.intitule = intitule;
        this.typeCompte = typeCompte;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeCompte getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(TypeCompte typeCompte) {
        this.typeCompte = typeCompte;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public String getCompteParent() {
        return compteParent;
    }

    public void setCompteParent(String compteParent) {
        this.compteParent = compteParent;
    }

    public BigDecimal getSoldeInitial() {
        return soldeInitial;
    }

    public void setSoldeInitial(BigDecimal soldeInitial) {
        this.soldeInitial = soldeInitial;
    }

    public BigDecimal getSoldeActuel() {
        return soldeActuel;
    }

    public void setSoldeActuel(BigDecimal soldeActuel) {
        this.soldeActuel = soldeActuel;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public BigDecimal getTauxChange() {
        return tauxChange;
    }

    public void setTauxChange(BigDecimal tauxChange) {
        this.tauxChange = tauxChange;
    }

    public Boolean getIsActif() {
        return isActif;
    }

    public void setIsActif(Boolean isActif) {
        this.isActif = isActif;
    }

    public Boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }

    public Boolean getIsAnalytique() {
        return isAnalytique;
    }

    public void setIsAnalytique(Boolean isAnalytique) {
        this.isAnalytique = isAnalytique;
    }

    public Boolean getIsTiers() {
        return isTiers;
    }

    public void setIsTiers(Boolean isTiers) {
        this.isTiers = isTiers;
    }

    public Boolean getIsTaxe() {
        return isTaxe;
    }

    public void setIsTaxe(Boolean isTaxe) {
        this.isTaxe = isTaxe;
    }

    public Boolean getIsBanque() {
        return isBanque;
    }

    public void setIsBanque(Boolean isBanque) {
        this.isBanque = isBanque;
    }

    public Boolean getIsCaisse() {
        return isCaisse;
    }

    public void setIsCaisse(Boolean isCaisse) {
        this.isCaisse = isCaisse;
    }

    public String getStandardComptable() {
        return standardComptable;
    }

    public void setStandardComptable(String standardComptable) {
        this.standardComptable = standardComptable;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
        return numero + " - " + intitule;
    }

    public boolean isActif() {
        return TypeCompte.ACTIF.equals(typeCompte);
    }

    public boolean isPassif() {
        return TypeCompte.PASSIF.equals(typeCompte);
    }

    public boolean isCharges() {
        return TypeCompte.CHARGES.equals(typeCompte);
    }

    public boolean isProduits() {
        return TypeCompte.PRODUITS.equals(typeCompte);
    }

    public boolean isCompteGeneral() {
        return Categorie.GENERAL.equals(categorie);
    }

    public boolean isCompteAnalytique() {
        return Categorie.ANALYTIQUE.equals(categorie);
    }

    public boolean isCompteTiers() {
        return Categorie.TIERS.equals(categorie);
    }

    public boolean isCompteBanque() {
        return Categorie.BANQUE.equals(categorie);
    }

    public boolean isCompteCaisse() {
        return Categorie.CAISSE.equals(categorie);
    }

    public boolean isCompteTaxe() {
        return Categorie.TAXE.equals(categorie);
    }

    public boolean isCompteAmortissement() {
        return Categorie.AMORTISSEMENT.equals(categorie);
    }

    public boolean isCompteProvision() {
        return Categorie.PROVISION.equals(categorie);
    }

    public boolean isCompteRacine() {
        return compteParent == null || compteParent.isEmpty();
    }

    public boolean isCompteEnfant() {
        return compteParent != null && !compteParent.isEmpty();
    }

    public String getCompteRacine() {
        if (numero == null || numero.isEmpty()) {
            return null;
        }
        String[] parts = numero.split("\\.");
        return parts.length > 0 ? parts[0] : numero;
    }

    public BigDecimal getSoldeNet() {
        return soldeActuel.subtract(soldeInitial);
    }

    public boolean isSoldeDebiteur() {
        return soldeActuel.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isSoldeCrediteur() {
        return soldeActuel.compareTo(BigDecimal.ZERO) < 0;
    }

    public boolean isSoldeNul() {
        return soldeActuel.compareTo(BigDecimal.ZERO) == 0;
    }

    @Override
    public String toString() {
        return "PlanComptable{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", intitule='" + intitule + '\'' +
                ", typeCompte=" + typeCompte +
                ", categorie=" + categorie +
                ", niveau=" + niveau +
                ", isActif=" + isActif +
                ", standardComptable='" + standardComptable + '\'' +
                ", company=" + (company != null ? company.getId() : null) +
                '}';
    }
}
