package com.ecomptaia.repository;

import com.ecomptaia.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 🏢 Repository pour la gestion des entreprises
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    /**
     * Trouve une entreprise par son nom
     */
    Optional<Company> findByNameAndIsActiveTrue(String name);

    /**
     * Trouve une entreprise par son nom légal
     */
    Optional<Company> findByLegalNameAndIsActiveTrue(String legalName);

    /**
     * Trouve une entreprise par son numéro fiscal
     */
    Optional<Company> findByTaxIdAndIsActiveTrue(String taxId);

    /**
     * Trouve une entreprise par son numéro d'enregistrement
     */
    Optional<Company> findByRegistrationNumberAndIsActiveTrue(String registrationNumber);

    /**
     * Trouve toutes les entreprises actives
     */
    List<Company> findByIsActiveTrue();

    /**
     * Trouve toutes les entreprises par pays
     */
    List<Company> findByCountryIdAndIsActiveTrue(Long countryId);

    /**
     * Trouve toutes les entreprises par standard comptable
     */
    List<Company> findByAccountingStandardAndIsActiveTrue(String accountingStandard);

    /**
     * Trouve toutes les entreprises par devise
     */
    List<Company> findByCurrencyCodeAndIsActiveTrue(String currencyCode);

    /**
     * Trouve toutes les entreprises par langue
     */
    List<Company> findByLanguageAndIsActiveTrue(String language);

    /**
     * Trouve toutes les entreprises par fuseau horaire
     */
    List<Company> findByTimezoneAndIsActiveTrue(String timezone);

    /**
     * Vérifie si un nom d'entreprise existe
     */
    boolean existsByName(String name);

    /**
     * Vérifie si un nom légal d'entreprise existe
     */
    boolean existsByLegalName(String legalName);

    /**
     * Vérifie si un numéro fiscal existe
     */
    boolean existsByTaxId(String taxId);

    /**
     * Vérifie si un numéro d'enregistrement existe
     */
    boolean existsByRegistrationNumber(String registrationNumber);

    /**
     * Recherche des entreprises par nom (recherche partielle)
     */
    @Query("SELECT c FROM Company c WHERE c.isActive = true AND (LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(c.legalName) LIKE LOWER(CONCAT('%', :name, '%')))")
    List<Company> findByNameContainingIgnoreCase(@Param("name") String name);

    /**
     * Trouve les entreprises créées récemment
     */
    @Query("SELECT c FROM Company c WHERE c.isActive = true AND c.createdAt >= :since")
    List<Company> findRecentlyCreatedCompanies(@Param("since") LocalDateTime since);

    /**
     * Trouve les entreprises mises à jour récemment
     */
    @Query("SELECT c FROM Company c WHERE c.isActive = true AND c.updatedAt >= :since")
    List<Company> findRecentlyUpdatedCompanies(@Param("since") LocalDateTime since);

    /**
     * Compte les entreprises par standard comptable
     */
    @Query("SELECT c.accountingStandard, COUNT(c) FROM Company c WHERE c.isActive = true GROUP BY c.accountingStandard")
    List<Object[]> countByAccountingStandard();

    /**
     * Compte les entreprises par devise
     */
    @Query("SELECT c.currencyCode, COUNT(c) FROM Company c WHERE c.isActive = true GROUP BY c.currencyCode")
    List<Object[]> countByCurrencyCode();

    /**
     * Compte les entreprises par pays
     */
    @Query("SELECT c.country.code, COUNT(c) FROM Company c WHERE c.isActive = true AND c.country IS NOT NULL GROUP BY c.country.code")
    List<Object[]> countByCountry();

    /**
     * Trouve les entreprises OHADA
     */
    @Query("SELECT c FROM Company c WHERE c.isActive = true AND c.accountingStandard = 'OHADA'")
    List<Company> findOHADACompanies();

    /**
     * Trouve les entreprises IFRS
     */
    @Query("SELECT c FROM Company c WHERE c.isActive = true AND c.accountingStandard = 'IFRS'")
    List<Company> findIFRSCompanies();

    /**
     * Trouve les entreprises GAAP
     */
    @Query("SELECT c FROM Company c WHERE c.isActive = true AND c.accountingStandard = 'GAAP'")
    List<Company> findGAAPCompanies();

    /**
     * Trouve les entreprises FRENCH
     */
    @Query("SELECT c FROM Company c WHERE c.isActive = true AND c.accountingStandard = 'FRENCH'")
    List<Company> findFrenchCompanies();

    /**
     * Trouve les entreprises par ville
     */
    List<Company> findByCityAndIsActiveTrue(String city);

    /**
     * Trouve les entreprises par code postal
     */
    List<Company> findByPostalCodeAndIsActiveTrue(String postalCode);

    /**
     * Trouve les entreprises par email
     */
    Optional<Company> findByEmailAndIsActiveTrue(String email);

    /**
     * Trouve les entreprises par téléphone
     */
    Optional<Company> findByPhoneAndIsActiveTrue(String phone);

    /**
     * Trouve les entreprises par site web
     */
    Optional<Company> findByWebsiteAndIsActiveTrue(String website);
}
