package com.ecomptaia.repository;

import com.ecomptaia.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 🌍 Repository pour la gestion des pays
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    /**
     * Trouve un pays par son code
     */
    Optional<Country> findByCodeAndIsActiveTrue(String code);

    /**
     * Trouve tous les pays actifs
     */
    List<Country> findByIsActiveTrue();

    /**
     * Trouve tous les pays par devise
     */
    List<Country> findByCurrencyCodeAndIsActiveTrue(String currencyCode);

    /**
     * Trouve tous les pays par standard comptable
     */
    List<Country> findByAccountingStandardAndIsActiveTrue(String accountingStandard);

    /**
     * Trouve tous les pays par locale
     */
    List<Country> findByLocaleAndIsActiveTrue(String locale);

    /**
     * Vérifie si un code pays existe
     */
    boolean existsByCode(String code);

    /**
     * Recherche des pays par nom (recherche partielle)
     */
    @Query("SELECT c FROM Country c WHERE c.isActive = true AND (LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(c.nameEn) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(c.nameFr) LIKE LOWER(CONCAT('%', :name, '%')))")
    List<Country> findByNameContainingIgnoreCase(@Param("name") String name);

    /**
     * Trouve tous les pays OHADA
     */
    @Query("SELECT c FROM Country c WHERE c.isActive = true AND c.accountingStandard = 'OHADA'")
    List<Country> findOHADACountries();

    /**
     * Trouve tous les pays IFRS
     */
    @Query("SELECT c FROM Country c WHERE c.isActive = true AND c.accountingStandard = 'IFRS'")
    List<Country> findIFRSCountries();

    /**
     * Trouve tous les pays GAAP
     */
    @Query("SELECT c FROM Country c WHERE c.isActive = true AND c.accountingStandard = 'GAAP'")
    List<Country> findGAAPCountries();

    /**
     * Trouve tous les pays FRENCH
     */
    @Query("SELECT c FROM Country c WHERE c.isActive = true AND c.accountingStandard = 'FRENCH'")
    List<Country> findFrenchCountries();

    /**
     * Trouve tous les pays par fuseau horaire
     */
    List<Country> findByTimezoneAndIsActiveTrue(String timezone);

    /**
     * Trouve tous les pays par indicatif téléphonique
     */
    List<Country> findByPhoneCodeAndIsActiveTrue(String phoneCode);

    /**
     * Compte les pays par standard comptable
     */
    @Query("SELECT c.accountingStandard, COUNT(c) FROM Country c WHERE c.isActive = true GROUP BY c.accountingStandard")
    List<Object[]> countByAccountingStandard();

    /**
     * Compte les pays par devise
     */
    @Query("SELECT c.currencyCode, COUNT(c) FROM Country c WHERE c.isActive = true GROUP BY c.currencyCode")
    List<Object[]> countByCurrencyCode();

    /**
     * Compte les pays par locale
     */
    @Query("SELECT c.locale, COUNT(c) FROM Country c WHERE c.isActive = true GROUP BY c.locale")
    List<Object[]> countByLocale();

    /**
     * Trouve les pays par nom en français
     */
    List<Country> findByNameFrAndIsActiveTrue(String nameFr);

    /**
     * Trouve les pays par nom en anglais
     */
    List<Country> findByNameEnAndIsActiveTrue(String nameEn);

    /**
     * Trouve les pays par nom exact
     */
    List<Country> findByNameAndIsActiveTrue(String name);

    /**
     * Trouve les pays par nom de devise
     */
    List<Country> findByCurrencyNameAndIsActiveTrue(String currencyName);

    /**
     * Trouve les pays inactifs
     */
    List<Country> findByIsActiveFalse();

    /**
     * Trouve les pays par code de devise et standard comptable
     */
    List<Country> findByCurrencyCodeAndAccountingStandardAndIsActiveTrue(String currencyCode, String accountingStandard);

    /**
     * Trouve les pays par locale et standard comptable
     */
    List<Country> findByLocaleAndAccountingStandardAndIsActiveTrue(String locale, String accountingStandard);
}
