package com.ecomptaia.repository;

import com.ecomptaia.entity.PlanComptable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * 📋 Repository pour l'entité PlanComptable
 */
@Repository
public interface PlanComptableRepository extends JpaRepository<PlanComptable, Long> {

    /**
     * Trouve un compte par son numéro et son entreprise
     */
    Optional<PlanComptable> findByNumeroAndCompanyId(String numero, Long companyId);

    /**
     * Trouve un compte actif par son numéro et son entreprise
     */
    Optional<PlanComptable> findByNumeroAndCompanyIdAndIsActifTrue(String numero, Long companyId);

    /**
     * Trouve tous les comptes actifs d'une entreprise
     */
    List<PlanComptable> findByCompanyIdAndIsActifTrue(Long companyId);

    /**
     * Trouve tous les comptes d'une entreprise par type
     */
    List<PlanComptable> findByCompanyIdAndTypeCompteAndIsActifTrue(Long companyId, PlanComptable.TypeCompte typeCompte);

    /**
     * Trouve tous les comptes d'une entreprise par catégorie
     */
    List<PlanComptable> findByCompanyIdAndCategorieAndIsActifTrue(Long companyId, PlanComptable.Categorie categorie);

    /**
     * Trouve tous les comptes d'une entreprise par standard comptable
     */
    List<PlanComptable> findByCompanyIdAndStandardComptableAndIsActifTrue(Long companyId, String standardComptable);

    /**
     * Trouve tous les comptes par intitulé (recherche partielle)
     */
    @Query("SELECT p FROM PlanComptable p WHERE p.company.id = :companyId AND p.isActif = true AND (LOWER(p.intitule) LIKE LOWER(CONCAT('%', :intitule, '%')) OR LOWER(p.numero) LIKE LOWER(CONCAT('%', :intitule, '%')))")
    List<PlanComptable> findByIntituleContainingIgnoreCaseAndCompanyId(@Param("intitule") String intitule, @Param("companyId") Long companyId);

    /**
     * Trouve tous les comptes racines (sans parent)
     */
    List<PlanComptable> findByCompanyIdAndCompteParentIsNullAndIsActifTrue(Long companyId);

    /**
     * Trouve tous les comptes enfants d'un compte parent
     */
    List<PlanComptable> findByCompanyIdAndCompteParentAndIsActifTrue(Long companyId, String compteParent);

    /**
     * Trouve tous les comptes par niveau
     */
    List<PlanComptable> findByCompanyIdAndNiveauAndIsActifTrue(Long companyId, Integer niveau);

    /**
     * Trouve tous les comptes de trésorerie (banque et caisse)
     */
    @Query("SELECT p FROM PlanComptable p WHERE p.company.id = :companyId AND p.isActif = true AND (p.isBanque = true OR p.isCaisse = true)")
    List<PlanComptable> findComptesTresorerieByCompanyId(@Param("companyId") Long companyId);

    /**
     * Trouve tous les comptes de taxes
     */
    List<PlanComptable> findByCompanyIdAndIsTaxeTrueAndIsActifTrue(Long companyId);

    /**
     * Trouve tous les comptes tiers
     */
    List<PlanComptable> findByCompanyIdAndIsTiersTrueAndIsActifTrue(Long companyId);

    /**
     * Trouve tous les comptes analytiques
     */
    List<PlanComptable> findByCompanyIdAndIsAnalytiqueTrueAndIsActifTrue(Long companyId);

    /**
     * Trouve tous les comptes système
     */
    List<PlanComptable> findByCompanyIdAndIsSystemTrueAndIsActifTrue(Long companyId);

    /**
     * Trouve tous les comptes avec solde débiteur
     */
    @Query("SELECT p FROM PlanComptable p WHERE p.company.id = :companyId AND p.isActif = true AND p.soldeActuel > 0")
    List<PlanComptable> findComptesDebiteursByCompanyId(@Param("companyId") Long companyId);

    /**
     * Trouve tous les comptes avec solde créditeur
     */
    @Query("SELECT p FROM PlanComptable p WHERE p.company.id = :companyId AND p.isActif = true AND p.soldeActuel < 0")
    List<PlanComptable> findComptesCrediteursByCompanyId(@Param("companyId") Long companyId);

    /**
     * Trouve tous les comptes avec solde nul
     */
    @Query("SELECT p FROM PlanComptable p WHERE p.company.id = :companyId AND p.isActif = true AND p.soldeActuel = 0")
    List<PlanComptable> findComptesSoldeNulByCompanyId(@Param("companyId") Long companyId);

    /**
     * Trouve tous les comptes par devise
     */
    List<PlanComptable> findByCompanyIdAndDeviseAndIsActifTrue(Long companyId, String devise);

    /**
     * Trouve tous les comptes avec solde supérieur à un montant
     */
    @Query("SELECT p FROM PlanComptable p WHERE p.company.id = :companyId AND p.isActif = true AND p.soldeActuel > :montant")
    List<PlanComptable> findComptesSoldeSuperieurByCompanyId(@Param("companyId") Long companyId, @Param("montant") BigDecimal montant);

    /**
     * Trouve tous les comptes avec solde inférieur à un montant
     */
    @Query("SELECT p FROM PlanComptable p WHERE p.company.id = :companyId AND p.isActif = true AND p.soldeActuel < :montant")
    List<PlanComptable> findComptesSoldeInferieurByCompanyId(@Param("companyId") Long companyId, @Param("montant") BigDecimal montant);

    /**
     * Compte le nombre de comptes actifs par entreprise
     */
    long countByCompanyIdAndIsActifTrue(Long companyId);

    /**
     * Compte le nombre de comptes par type
     */
    long countByCompanyIdAndTypeCompteAndIsActifTrue(Long companyId, PlanComptable.TypeCompte typeCompte);

    /**
     * Compte le nombre de comptes par catégorie
     */
    long countByCompanyIdAndCategorieAndIsActifTrue(Long companyId, PlanComptable.Categorie categorie);

    /**
     * Calcule le total des soldes débiteurs
     */
    @Query("SELECT SUM(p.soldeActuel) FROM PlanComptable p WHERE p.company.id = :companyId AND p.isActif = true AND p.soldeActuel > 0")
    BigDecimal sumSoldesDebiteursByCompanyId(@Param("companyId") Long companyId);

    /**
     * Calcule le total des soldes créditeurs
     */
    @Query("SELECT SUM(p.soldeActuel) FROM PlanComptable p WHERE p.company.id = :companyId AND p.isActif = true AND p.soldeActuel < 0")
    BigDecimal sumSoldesCrediteursByCompanyId(@Param("companyId") Long companyId);

    /**
     * Vérifie si un numéro de compte existe déjà pour une entreprise
     */
    boolean existsByNumeroAndCompanyId(String numero, Long companyId);

    /**
     * Trouve le prochain numéro de compte disponible pour un niveau donné
     */
    @Query("SELECT MAX(CAST(p.numero AS integer)) FROM PlanComptable p WHERE p.company.id = :companyId AND p.niveau = :niveau AND p.isActif = true")
    Integer findNextNumeroByNiveauAndCompanyId(@Param("niveau") Integer niveau, @Param("companyId") Long companyId);

    /**
     * Trouve tous les comptes par plage de numéros
     */
    @Query("SELECT p FROM PlanComptable p WHERE p.company.id = :companyId AND p.isActif = true AND p.numero BETWEEN :debut AND :fin ORDER BY p.numero")
    List<PlanComptable> findByNumeroBetweenAndCompanyId(@Param("debut") String debut, @Param("fin") String fin, @Param("companyId") Long companyId);
}
