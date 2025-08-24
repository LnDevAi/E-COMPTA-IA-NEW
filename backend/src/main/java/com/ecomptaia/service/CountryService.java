package com.ecomptaia.service;

import com.ecomptaia.entity.Country;
import com.ecomptaia.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 🌍 Service pour la gestion des pays et standards comptables
 */
@Service
@Transactional
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Cacheable("countries")
    public List<Country> findAllActive() {
        return countryRepository.findByIsActiveTrue();
    }

    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    public Optional<Country> findByCode(String code) {
        return countryRepository.findByCodeAndIsActiveTrue(code);
    }

    public List<Country> findByCurrencyCode(String currencyCode) {
        return countryRepository.findByCurrencyCodeAndIsActiveTrue(currencyCode);
    }

    public List<Country> findByAccountingStandard(String accountingStandard) {
        return countryRepository.findByAccountingStandardAndIsActiveTrue(accountingStandard);
    }

    public List<Country> findByLocale(String locale) {
        return countryRepository.findByLocaleAndIsActiveTrue(locale);
    }

    public boolean existsByCode(String code) {
        return countryRepository.existsByCode(code);
    }

    public List<Country> searchByName(String name) {
        return countryRepository.findByNameContainingIgnoreCase(name);
    }

    @Cacheable("countries-ohada")
    public List<Country> findOHADACountries() {
        return countryRepository.findOHADACountries();
    }

    @Cacheable("countries-ifrs")
    public List<Country> findIFRSCountries() {
        return countryRepository.findIFRSCountries();
    }

    @Cacheable("countries-gaap")
    public List<Country> findGAAPCountries() {
        return countryRepository.findGAAPCountries();
    }

    @Cacheable("countries-french")
    public List<Country> findFrenchCountries() {
        return countryRepository.findFrenchCountries();
    }

    public List<Country> findByTimezone(String timezone) {
        return countryRepository.findByTimezoneAndIsActiveTrue(timezone);
    }

    public List<Country> findByPhoneCode(String phoneCode) {
        return countryRepository.findByPhoneCodeAndIsActiveTrue(phoneCode);
    }

    public List<Object[]> countByAccountingStandard() {
        return countryRepository.countByAccountingStandard();
    }

    public List<Object[]> countByCurrencyCode() {
        return countryRepository.countByCurrencyCode();
    }

    public List<Object[]> countByLocale() {
        return countryRepository.countByLocale();
    }

    public List<Country> findByNameFr(String nameFr) {
        return countryRepository.findByNameFrAndIsActiveTrue(nameFr);
    }

    public List<Country> findByNameEn(String nameEn) {
        return countryRepository.findByNameEnAndIsActiveTrue(nameEn);
    }

    public List<Country> findByName(String name) {
        return countryRepository.findByNameAndIsActiveTrue(name);
    }

    public List<Country> findByCurrencyName(String currencyName) {
        return countryRepository.findByCurrencyNameAndIsActiveTrue(currencyName);
    }

    public List<Country> findInactiveCountries() {
        return countryRepository.findByIsActiveFalse();
    }

    public List<Country> findByCurrencyCodeAndAccountingStandard(String currencyCode, String accountingStandard) {
        return countryRepository.findByCurrencyCodeAndAccountingStandardAndIsActiveTrue(currencyCode, accountingStandard);
    }

    public List<Country> findByLocaleAndAccountingStandard(String locale, String accountingStandard) {
        return countryRepository.findByLocaleAndAccountingStandardAndIsActiveTrue(locale, accountingStandard);
    }

    @CacheEvict(value = {"countries", "countries-ohada", "countries-ifrs", "countries-gaap", "countries-french"}, allEntries = true)
    public Country createCountry(Country country) {
        if (countryRepository.existsByCode(country.getCode())) {
            throw new RuntimeException("Le code pays existe déjà: " + country.getCode());
        }
        return countryRepository.save(country);
    }

    @CacheEvict(value = {"countries", "countries-ohada", "countries-ifrs", "countries-gaap", "countries-french"}, allEntries = true)
    public Country updateCountry(Long id, Country countryDetails) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pays non trouvé"));

        if (countryDetails.getName() != null) {
            country.setName(countryDetails.getName());
        }
        if (countryDetails.getNameEn() != null) {
            country.setNameEn(countryDetails.getNameEn());
        }
        if (countryDetails.getNameFr() != null) {
            country.setNameFr(countryDetails.getNameFr());
        }
        if (countryDetails.getCurrencyCode() != null) {
            country.setCurrencyCode(countryDetails.getCurrencyCode());
        }
        if (countryDetails.getCurrencyName() != null) {
            country.setCurrencyName(countryDetails.getCurrencyName());
        }
        if (countryDetails.getAccountingStandard() != null) {
            country.setAccountingStandard(countryDetails.getAccountingStandard());
        }
        if (countryDetails.getTimezone() != null) {
            country.setTimezone(countryDetails.getTimezone());
        }
        if (countryDetails.getLocale() != null) {
            country.setLocale(countryDetails.getLocale());
        }
        if (countryDetails.getPhoneCode() != null) {
            country.setPhoneCode(countryDetails.getPhoneCode());
        }

        return countryRepository.save(country);
    }

    @CacheEvict(value = {"countries", "countries-ohada", "countries-ifrs", "countries-gaap", "countries-french"}, allEntries = true)
    public Country deactivateCountry(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pays non trouvé"));

        country.setIsActive(false);
        return countryRepository.save(country);
    }

    @CacheEvict(value = {"countries", "countries-ohada", "countries-ifrs", "countries-gaap", "countries-french"}, allEntries = true)
    public Country activateCountry(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pays non trouvé"));

        country.setIsActive(true);
        return countryRepository.save(country);
    }

    public boolean isOHADACountry(Long countryId) {
        return countryRepository.findById(countryId)
                .map(Country::isOHADA)
                .orElse(false);
    }

    public boolean isIFRSCountry(Long countryId) {
        return countryRepository.findById(countryId)
                .map(Country::isIFRS)
                .orElse(false);
    }

    public boolean isGAAPCountry(Long countryId) {
        return countryRepository.findById(countryId)
                .map(Country::isGAAP)
                .orElse(false);
    }

    public boolean isFrenchCountry(Long countryId) {
        return countryRepository.findById(countryId)
                .map(Country::isFrench)
                .orElse(false);
    }

    public String getCountryCurrency(Long countryId) {
        return countryRepository.findById(countryId)
                .map(Country::getCurrencyCode)
                .orElse("XOF");
    }

    public String getCountryAccountingStandard(Long countryId) {
        return countryRepository.findById(countryId)
                .map(Country::getAccountingStandard)
                .orElse("OHADA");
    }

    public String getCountryTimezone(Long countryId) {
        return countryRepository.findById(countryId)
                .map(Country::getTimezone)
                .orElse("UTC");
    }

    public String getCountryLocale(Long countryId) {
        return countryRepository.findById(countryId)
                .map(Country::getLocale)
                .orElse("fr");
    }
}
