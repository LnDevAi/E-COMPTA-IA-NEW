package com.ecomptaia.repository;

import com.ecomptaia.entity.Tiers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 👥 Repository pour l'entité Tiers
 */
@Repository
public interface TiersRepository extends JpaRepository<Tiers, Long> {

    /**
     * Trouve un tiers par son code et son entreprise
     */
    Optional<Tiers> findByCodeAndCompanyId(String code, Long companyId);

    /**
     * Trouve un tiers par son code, son entreprise et son statut actif
     */
    Optional<Tiers> findByCodeAndCompanyIdAndIsActiveTrue(String code, Long companyId);

    /**
     * Trouve tous les tiers actifs d'une entreprise
     */
    List<Tiers> findByCompanyIdAndIsActiveTrue(Long companyId);

    /**
     * Trouve tous les tiers d'une entreprise par type
     */
    List<Tiers> findByCompanyIdAndTypeAndIsActiveTrue(Long companyId, Tiers.Type type);

    /**
     * Trouve tous les clients d'une entreprise
     */
    @Query("SELECT t FROM Tiers t WHERE t.company.id = :companyId AND t.isActive = true AND (t.type = 'CLIENT' OR t.type = 'CLIENT_FOURNISSEUR')")
    List<Tiers> findClientsByCompanyId(@Param("companyId") Long companyId);

    /**
     * Trouve tous les fournisseurs d'une entreprise
     */
    @Query("SELECT t FROM Tiers t WHERE t.company.id = :companyId AND t.isActive = true AND (t.type = 'FOURNISSEUR' OR t.type = 'CLIENT_FOURNISSEUR')")
    List<Tiers> findFournisseursByCompanyId(@Param("companyId") Long companyId);

    /**
     * Trouve tous les tiers par nom (recherche partielle)
     */
    @Query("SELECT t FROM Tiers t WHERE t.company.id = :companyId AND t.isActive = true AND (LOWER(t.name) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(t.code) LIKE LOWER(CONCAT('%', :name, '%')))")
    List<Tiers> findByNameContainingIgnoreCaseAndCompanyId(@Param("name") String name, @Param("companyId") Long companyId);

    /**
     * Trouve tous les tiers par email
     */
    Optional<Tiers> findByEmailAndCompanyIdAndIsActiveTrue(String email, Long companyId);

    /**
     * Trouve tous les tiers par numéro de téléphone
     */
    Optional<Tiers> findByPhoneAndCompanyIdAndIsActiveTrue(String phone, Long companyId);

    /**
     * Trouve tous les tiers par numéro fiscal
     */
    Optional<Tiers> findByTaxNumberAndCompanyIdAndIsActiveTrue(String taxNumber, Long companyId);

    /**
     * Trouve tous les tiers par numéro d'enregistrement
     */
    Optional<Tiers> findByRegistrationNumberAndCompanyIdAndIsActiveTrue(String registrationNumber, Long companyId);

    /**
     * Compte le nombre de tiers actifs par entreprise
     */
    long countByCompanyIdAndIsActiveTrue(Long companyId);

    /**
     * Compte le nombre de clients par entreprise
     */
    @Query("SELECT COUNT(t) FROM Tiers t WHERE t.company.id = :companyId AND t.isActive = true AND (t.type = 'CLIENT' OR t.type = 'CLIENT_FOURNISSEUR')")
    long countClientsByCompanyId(@Param("companyId") Long companyId);

    /**
     * Compte le nombre de fournisseurs par entreprise
     */
    @Query("SELECT COUNT(t) FROM Tiers t WHERE t.company.id = :companyId AND t.isActive = true AND (t.type = 'FOURNISSEUR' OR t.type = 'CLIENT_FOURNISSEUR')")
    long countFournisseursByCompanyId(@Param("companyId") Long companyId);

    /**
     * Trouve tous les tiers avec limite de crédit dépassée
     */
    @Query("SELECT t FROM Tiers t WHERE t.company.id = :companyId AND t.isActive = true AND t.creditLimit > 0")
    List<Tiers> findWithCreditLimitByCompanyId(@Param("companyId") Long companyId);

    /**
     * Trouve tous les tiers par ville
     */
    List<Tiers> findByCityAndCompanyIdAndIsActiveTrue(String city, Long companyId);

    /**
     * Trouve tous les tiers par pays
     */
    List<Tiers> findByCountryAndCompanyIdAndIsActiveTrue(String country, Long companyId);

    /**
     * Trouve tous les tiers par devise
     */
    List<Tiers> findByCurrencyCodeAndCompanyIdAndIsActiveTrue(String currencyCode, Long companyId);

    /**
     * Vérifie si un code existe déjà pour une entreprise
     */
    boolean existsByCodeAndCompanyId(String code, Long companyId);

    /**
     * Vérifie si un email existe déjà pour une entreprise
     */
    boolean existsByEmailAndCompanyIdAndIsActiveTrue(String email, Long companyId);

    /**
     * Vérifie si un numéro fiscal existe déjà pour une entreprise
     */
    boolean existsByTaxNumberAndCompanyIdAndIsActiveTrue(String taxNumber, Long companyId);
}
