package com.ecomptaia.service;

import com.ecomptaia.entity.Company;
import com.ecomptaia.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 🏢 Service pour la gestion des entreprises
 * 
 * Gère les entreprises avec leurs configurations comptables
 */
@Service
@Transactional
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    /**
     * Trouve toutes les entreprises actives
     */
    @Cacheable("companies")
    public List<Company> findAllActive() {
        return companyRepository.findByIsActiveTrue();
    }

    /**
     * Trouve une entreprise par son ID
     */
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    /**
     * Trouve une entreprise par son nom
     */
    @Cacheable("companies")
    public Optional<Company> findByName(String name) {
        return companyRepository.findByNameAndIsActiveTrue(name);
    }

    /**
     * Trouve une entreprise par son nom légal
     */
    public Optional<Company> findByLegalName(String legalName) {
        return companyRepository.findByLegalNameAndIsActiveTrue(legalName);
    }

    /**
     * Trouve une entreprise par son numéro fiscal
     */
    public Optional<Company> findByTaxId(String taxId) {
        return companyRepository.findByTaxIdAndIsActiveTrue(taxId);
    }

    /**
     * Trouve une entreprise par son numéro d'enregistrement
     */
    public Optional<Company> findByRegistrationNumber(String registrationNumber) {
        return companyRepository.findByRegistrationNumberAndIsActiveTrue(registrationNumber);
    }

    /**
     * Trouve toutes les entreprises par pays
     */
    public List<Company> findByCountryId(Long countryId) {
        return companyRepository.findByCountryIdAndIsActiveTrue(countryId);
    }

    /**
     * Trouve toutes les entreprises par standard comptable
     */
    public List<Company> findByAccountingStandard(String accountingStandard) {
        return companyRepository.findByAccountingStandardAndIsActiveTrue(accountingStandard);
    }

    /**
     * Trouve toutes les entreprises par devise
     */
    public List<Company> findByCurrencyCode(String currencyCode) {
        return companyRepository.findByCurrencyCodeAndIsActiveTrue(currencyCode);
    }

    /**
     * Trouve toutes les entreprises par langue
     */
    public List<Company> findByLanguage(String language) {
        return companyRepository.findByLanguageAndIsActiveTrue(language);
    }

    /**
     * Trouve toutes les entreprises par fuseau horaire
     */
    public List<Company> findByTimezone(String timezone) {
        return companyRepository.findByTimezoneAndIsActiveTrue(timezone);
    }

    /**
     * Trouve toutes les entreprises par ville
     */
    public List<Company> findByCity(String city) {
        return companyRepository.findByCityAndIsActiveTrue(city);
    }

    /**
     * Trouve toutes les entreprises par code postal
     */
    public List<Company> findByPostalCode(String postalCode) {
        return companyRepository.findByPostalCodeAndIsActiveTrue(postalCode);
    }

    /**
     * Trouve une entreprise par email
     */
    public Optional<Company> findByEmail(String email) {
        return companyRepository.findByEmailAndIsActiveTrue(email);
    }

    /**
     * Trouve une entreprise par téléphone
     */
    public Optional<Company> findByPhone(String phone) {
        return companyRepository.findByPhoneAndIsActiveTrue(phone);
    }

    /**
     * Trouve une entreprise par site web
     */
    public Optional<Company> findByWebsite(String website) {
        return companyRepository.findByWebsiteAndIsActiveTrue(website);
    }

    /**
     * Recherche des entreprises par nom
     */
    public List<Company> searchByName(String name) {
        return companyRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Trouve les entreprises créées récemment
     */
    public List<Company> findRecentlyCreatedCompanies(LocalDateTime since) {
        return companyRepository.findRecentlyCreatedCompanies(since);
    }

    /**
     * Trouve les entreprises mises à jour récemment
     */
    public List<Company> findRecentlyUpdatedCompanies(LocalDateTime since) {
        return companyRepository.findRecentlyUpdatedCompanies(since);
    }

    /**
     * Vérifie si un nom d'entreprise existe
     */
    public boolean existsByName(String name) {
        return companyRepository.existsByName(name);
    }

    /**
     * Vérifie si un nom légal d'entreprise existe
     */
    public boolean existsByLegalName(String legalName) {
        return companyRepository.existsByLegalName(legalName);
    }

    /**
     * Vérifie si un numéro fiscal existe
     */
    public boolean existsByTaxId(String taxId) {
        return companyRepository.existsByTaxId(taxId);
    }

    /**
     * Vérifie si un numéro d'enregistrement existe
     */
    public boolean existsByRegistrationNumber(String registrationNumber) {
        return companyRepository.existsByRegistrationNumber(registrationNumber);
    }

    /**
     * Compte les entreprises par standard comptable
     */
    public List<Object[]> countByAccountingStandard() {
        return companyRepository.countByAccountingStandard();
    }

    /**
     * Compte les entreprises par devise
     */
    public List<Object[]> countByCurrencyCode() {
        return companyRepository.countByCurrencyCode();
    }

    /**
     * Compte les entreprises par pays
     */
    public List<Object[]> countByCountry() {
        return companyRepository.countByCountry();
    }

    /**
     * Trouve toutes les entreprises OHADA
     */
    @Cacheable("companies-ohada")
    public List<Company> findOHADACompanies() {
        return companyRepository.findOHADACompanies();
    }

    /**
     * Trouve toutes les entreprises IFRS
     */
    @Cacheable("companies-ifrs")
    public List<Company> findIFRSCompanies() {
        return companyRepository.findIFRSCompanies();
    }

    /**
     * Trouve toutes les entreprises GAAP
     */
    @Cacheable("companies-gaap")
    public List<Company> findGAAPCompanies() {
        return companyRepository.findGAAPCompanies();
    }

    /**
     * Trouve toutes les entreprises FRENCH
     */
    @Cacheable("companies-french")
    public List<Company> findFrenchCompanies() {
        return companyRepository.findFrenchCompanies();
    }

    /**
     * Crée une nouvelle entreprise
     */
    @CacheEvict(value = {"companies", "companies-ohada", "companies-ifrs", "companies-gaap", "companies-french"}, allEntries = true)
    public Company createCompany(Company company) {
        if (companyRepository.existsByName(company.getName())) {
            throw new RuntimeException("Le nom d'entreprise existe déjà: " + company.getName());
        }
        if (company.getTaxId() != null && companyRepository.existsByTaxId(company.getTaxId())) {
            throw new RuntimeException("Le numéro fiscal existe déjà: " + company.getTaxId());
        }
        if (company.getRegistrationNumber() != null && companyRepository.existsByRegistrationNumber(company.getRegistrationNumber())) {
            throw new RuntimeException("Le numéro d'enregistrement existe déjà: " + company.getRegistrationNumber());
        }
        return companyRepository.save(company);
    }

    /**
     * Met à jour une entreprise
     */
    @CacheEvict(value = {"companies", "companies-ohada", "companies-ifrs", "companies-gaap", "companies-french"}, allEntries = true)
    public Company updateCompany(Long id, Company companyDetails) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée"));

        if (companyDetails.getName() != null) {
            company.setName(companyDetails.getName());
        }
        if (companyDetails.getLegalName() != null) {
            company.setLegalName(companyDetails.getLegalName());
        }
        if (companyDetails.getTaxId() != null) {
            company.setTaxId(companyDetails.getTaxId());
        }
        if (companyDetails.getRegistrationNumber() != null) {
            company.setRegistrationNumber(companyDetails.getRegistrationNumber());
        }
        if (companyDetails.getAddress() != null) {
            company.setAddress(companyDetails.getAddress());
        }
        if (companyDetails.getCity() != null) {
            company.setCity(companyDetails.getCity());
        }
        if (companyDetails.getPostalCode() != null) {
            company.setPostalCode(companyDetails.getPostalCode());
        }
        if (companyDetails.getPhone() != null) {
            company.setPhone(companyDetails.getPhone());
        }
        if (companyDetails.getEmail() != null) {
            company.setEmail(companyDetails.getEmail());
        }
        if (companyDetails.getWebsite() != null) {
            company.setWebsite(companyDetails.getWebsite());
        }
        if (companyDetails.getFiscalYearStart() != null) {
            company.setFiscalYearStart(companyDetails.getFiscalYearStart());
        }
        if (companyDetails.getAccountingStandard() != null) {
            company.setAccountingStandard(companyDetails.getAccountingStandard());
        }
        if (companyDetails.getCurrencyCode() != null) {
            company.setCurrencyCode(companyDetails.getCurrencyCode());
        }
        if (companyDetails.getLanguage() != null) {
            company.setLanguage(companyDetails.getLanguage());
        }
        if (companyDetails.getTimezone() != null) {
            company.setTimezone(companyDetails.getTimezone());
        }
        if (companyDetails.getCountry() != null) {
            company.setCountry(companyDetails.getCountry());
        }

        return companyRepository.save(company);
    }

    /**
     * Désactive une entreprise (soft delete)
     */
    @CacheEvict(value = {"companies", "companies-ohada", "companies-ifrs", "companies-gaap", "companies-french"}, allEntries = true)
    public Company deactivateCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée"));

        company.setIsActive(false);
        return companyRepository.save(company);
    }

    /**
     * Réactive une entreprise
     */
    @CacheEvict(value = {"companies", "companies-ohada", "companies-ifrs", "companies-gaap", "companies-french"}, allEntries = true)
    public Company activateCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée"));

        company.setIsActive(true);
        return companyRepository.save(company);
    }

    /**
     * Vérifie si une entreprise utilise le standard OHADA
     */
    public boolean isOHADACompany(Long companyId) {
        return companyRepository.findById(companyId)
                .map(Company::isOHADA)
                .orElse(false);
    }

    /**
     * Vérifie si une entreprise utilise le standard IFRS
     */
    public boolean isIFRSCompany(Long companyId) {
        return companyRepository.findById(companyId)
                .map(Company::isIFRS)
                .orElse(false);
    }

    /**
     * Vérifie si une entreprise utilise le standard GAAP
     */
    public boolean isGAAPCompany(Long companyId) {
        return companyRepository.findById(companyId)
                .map(Company::isGAAP)
                .orElse(false);
    }

    /**
     * Vérifie si une entreprise utilise le standard FRENCH
     */
    public boolean isFrenchCompany(Long companyId) {
        return companyRepository.findById(companyId)
                .map(Company::isFrench)
                .orElse(false);
    }

    /**
     * Obtient la devise d'une entreprise
     */
    public String getCompanyCurrency(Long companyId) {
        return companyRepository.findById(companyId)
                .map(Company::getCurrencyCode)
                .orElse("XOF");
    }

    /**
     * Obtient le standard comptable d'une entreprise
     */
    public String getCompanyAccountingStandard(Long companyId) {
        return companyRepository.findById(companyId)
                .map(Company::getAccountingStandard)
                .orElse("OHADA");
    }

    /**
     * Obtient la langue d'une entreprise
     */
    public String getCompanyLanguage(Long companyId) {
        return companyRepository.findById(companyId)
                .map(Company::getLanguage)
                .orElse("fr");
    }

    /**
     * Obtient le fuseau horaire d'une entreprise
     */
    public String getCompanyTimezone(Long companyId) {
        return companyRepository.findById(companyId)
                .map(Company::getTimezone)
                .orElse("UTC");
    }
}
