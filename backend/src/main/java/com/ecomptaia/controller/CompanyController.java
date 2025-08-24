package com.ecomptaia.controller;

import com.ecomptaia.entity.Company;
import com.ecomptaia.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 🏢 Controller pour la gestion des entreprises
 * 
 * API REST pour les entreprises du système comptable
 */
@RestController
@RequestMapping("/companies")
@CrossOrigin(origins = "*")
@Tag(name = "Companies", description = "API pour la gestion des entreprises")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    /**
     * Récupère toutes les entreprises actives
     */
    @GetMapping
    @Operation(summary = "Liste des entreprises", description = "Récupère toutes les entreprises actives")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.findAllActive();
        return ResponseEntity.ok(companies);
    }

    /**
     * Récupère une entreprise par son ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "Entreprise par ID", description = "Récupère une entreprise par son ID")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Optional<Company> company = companyService.findById(id);
        return company.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Récupère une entreprise par son nom
     */
    @GetMapping("/name/{name}")
    @Operation(summary = "Entreprise par nom", description = "Récupère une entreprise par son nom")
    public ResponseEntity<Company> getCompanyByName(@PathVariable String name) {
        Optional<Company> company = companyService.findByName(name);
        return company.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Récupère une entreprise par son nom légal
     */
    @GetMapping("/legal-name/{legalName}")
    @Operation(summary = "Entreprise par nom légal", description = "Récupère une entreprise par son nom légal")
    public ResponseEntity<Company> getCompanyByLegalName(@PathVariable String legalName) {
        Optional<Company> company = companyService.findByLegalName(legalName);
        return company.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Récupère une entreprise par son numéro fiscal
     */
    @GetMapping("/tax-id/{taxId}")
    @Operation(summary = "Entreprise par numéro fiscal", description = "Récupère une entreprise par son numéro fiscal")
    public ResponseEntity<Company> getCompanyByTaxId(@PathVariable String taxId) {
        Optional<Company> company = companyService.findByTaxId(taxId);
        return company.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Récupère une entreprise par son numéro d'enregistrement
     */
    @GetMapping("/registration/{registrationNumber}")
    @Operation(summary = "Entreprise par numéro d'enregistrement", description = "Récupère une entreprise par son numéro d'enregistrement")
    public ResponseEntity<Company> getCompanyByRegistrationNumber(@PathVariable String registrationNumber) {
        Optional<Company> company = companyService.findByRegistrationNumber(registrationNumber);
        return company.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Récupère toutes les entreprises par pays
     */
    @GetMapping("/country/{countryId}")
    @Operation(summary = "Entreprises par pays", description = "Récupère toutes les entreprises d'un pays")
    public ResponseEntity<List<Company>> getCompaniesByCountry(@PathVariable Long countryId) {
        List<Company> companies = companyService.findByCountryId(countryId);
        return ResponseEntity.ok(companies);
    }

    /**
     * Récupère toutes les entreprises par standard comptable
     */
    @GetMapping("/standard/{standard}")
    @Operation(summary = "Entreprises par standard", description = "Récupère toutes les entreprises par standard comptable")
    public ResponseEntity<List<Company>> getCompaniesByStandard(@PathVariable String standard) {
        List<Company> companies = companyService.findByAccountingStandard(standard);
        return ResponseEntity.ok(companies);
    }

    /**
     * Récupère toutes les entreprises par devise
     */
    @GetMapping("/currency/{currency}")
    @Operation(summary = "Entreprises par devise", description = "Récupère toutes les entreprises par devise")
    public ResponseEntity<List<Company>> getCompaniesByCurrency(@PathVariable String currency) {
        List<Company> companies = companyService.findByCurrencyCode(currency);
        return ResponseEntity.ok(companies);
    }

    /**
     * Récupère toutes les entreprises par langue
     */
    @GetMapping("/language/{language}")
    @Operation(summary = "Entreprises par langue", description = "Récupère toutes les entreprises par langue")
    public ResponseEntity<List<Company>> getCompaniesByLanguage(@PathVariable String language) {
        List<Company> companies = companyService.findByLanguage(language);
        return ResponseEntity.ok(companies);
    }

    /**
     * Récupère toutes les entreprises par fuseau horaire
     */
    @GetMapping("/timezone/{timezone}")
    @Operation(summary = "Entreprises par fuseau horaire", description = "Récupère toutes les entreprises par fuseau horaire")
    public ResponseEntity<List<Company>> getCompaniesByTimezone(@PathVariable String timezone) {
        List<Company> companies = companyService.findByTimezone(timezone);
        return ResponseEntity.ok(companies);
    }

    /**
     * Récupère toutes les entreprises par ville
     */
    @GetMapping("/city/{city}")
    @Operation(summary = "Entreprises par ville", description = "Récupère toutes les entreprises d'une ville")
    public ResponseEntity<List<Company>> getCompaniesByCity(@PathVariable String city) {
        List<Company> companies = companyService.findByCity(city);
        return ResponseEntity.ok(companies);
    }

    /**
     * Récupère toutes les entreprises par code postal
     */
    @GetMapping("/postal-code/{postalCode}")
    @Operation(summary = "Entreprises par code postal", description = "Récupère toutes les entreprises par code postal")
    public ResponseEntity<List<Company>> getCompaniesByPostalCode(@PathVariable String postalCode) {
        List<Company> companies = companyService.findByPostalCode(postalCode);
        return ResponseEntity.ok(companies);
    }

    /**
     * Récupère une entreprise par email
     */
    @GetMapping("/email/{email}")
    @Operation(summary = "Entreprise par email", description = "Récupère une entreprise par email")
    public ResponseEntity<Company> getCompanyByEmail(@PathVariable String email) {
        Optional<Company> company = companyService.findByEmail(email);
        return company.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Récupère une entreprise par téléphone
     */
    @GetMapping("/phone/{phone}")
    @Operation(summary = "Entreprise par téléphone", description = "Récupère une entreprise par téléphone")
    public ResponseEntity<Company> getCompanyByPhone(@PathVariable String phone) {
        Optional<Company> company = companyService.findByPhone(phone);
        return company.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Récupère une entreprise par site web
     */
    @GetMapping("/website/{website}")
    @Operation(summary = "Entreprise par site web", description = "Récupère une entreprise par site web")
    public ResponseEntity<Company> getCompanyByWebsite(@PathVariable String website) {
        Optional<Company> company = companyService.findByWebsite(website);
        return company.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Recherche des entreprises par nom
     */
    @GetMapping("/search")
    @Operation(summary = "Recherche entreprises", description = "Recherche des entreprises par nom")
    public ResponseEntity<List<Company>> searchCompanies(@RequestParam String name) {
        List<Company> companies = companyService.searchByName(name);
        return ResponseEntity.ok(companies);
    }

    /**
     * Récupère toutes les entreprises OHADA
     */
    @GetMapping("/ohada")
    @Operation(summary = "Entreprises OHADA", description = "Récupère toutes les entreprises utilisant le standard OHADA")
    public ResponseEntity<List<Company>> getOHADACompanies() {
        List<Company> companies = companyService.findOHADACompanies();
        return ResponseEntity.ok(companies);
    }

    /**
     * Récupère toutes les entreprises IFRS
     */
    @GetMapping("/ifrs")
    @Operation(summary = "Entreprises IFRS", description = "Récupère toutes les entreprises utilisant le standard IFRS")
    public ResponseEntity<List<Company>> getIFRSCompanies() {
        List<Company> companies = companyService.findIFRSCompanies();
        return ResponseEntity.ok(companies);
    }

    /**
     * Récupère toutes les entreprises GAAP
     */
    @GetMapping("/gaap")
    @Operation(summary = "Entreprises GAAP", description = "Récupère toutes les entreprises utilisant le standard GAAP")
    public ResponseEntity<List<Company>> getGAAPCompanies() {
        List<Company> companies = companyService.findGAAPCompanies();
        return ResponseEntity.ok(companies);
    }

    /**
     * Récupère toutes les entreprises FRENCH
     */
    @GetMapping("/french")
    @Operation(summary = "Entreprises FRENCH", description = "Récupère toutes les entreprises utilisant le standard FRENCH")
    public ResponseEntity<List<Company>> getFrenchCompanies() {
        List<Company> companies = companyService.findFrenchCompanies();
        return ResponseEntity.ok(companies);
    }

    /**
     * Vérifie si une entreprise utilise le standard OHADA
     */
    @GetMapping("/{id}/is-ohada")
    @Operation(summary = "Vérification OHADA", description = "Vérifie si une entreprise utilise le standard OHADA")
    public ResponseEntity<Boolean> isOHADACompany(@PathVariable Long id) {
        boolean isOHADA = companyService.isOHADACompany(id);
        return ResponseEntity.ok(isOHADA);
    }

    /**
     * Vérifie si une entreprise utilise le standard IFRS
     */
    @GetMapping("/{id}/is-ifrs")
    @Operation(summary = "Vérification IFRS", description = "Vérifie si une entreprise utilise le standard IFRS")
    public ResponseEntity<Boolean> isIFRSCompany(@PathVariable Long id) {
        boolean isIFRS = companyService.isIFRSCompany(id);
        return ResponseEntity.ok(isIFRS);
    }

    /**
     * Vérifie si une entreprise utilise le standard GAAP
     */
    @GetMapping("/{id}/is-gaap")
    @Operation(summary = "Vérification GAAP", description = "Vérifie si une entreprise utilise le standard GAAP")
    public ResponseEntity<Boolean> isGAAPCompany(@PathVariable Long id) {
        boolean isGAAP = companyService.isGAAPCompany(id);
        return ResponseEntity.ok(isGAAP);
    }

    /**
     * Vérifie si une entreprise utilise le standard FRENCH
     */
    @GetMapping("/{id}/is-french")
    @Operation(summary = "Vérification FRENCH", description = "Vérifie si une entreprise utilise le standard FRENCH")
    public ResponseEntity<Boolean> isFrenchCompany(@PathVariable Long id) {
        boolean isFrench = companyService.isFrenchCompany(id);
        return ResponseEntity.ok(isFrench);
    }

    /**
     * Obtient la devise d'une entreprise
     */
    @GetMapping("/{id}/currency")
    @Operation(summary = "Devise entreprise", description = "Obtient la devise d'une entreprise")
    public ResponseEntity<String> getCompanyCurrency(@PathVariable Long id) {
        String currency = companyService.getCompanyCurrency(id);
        return ResponseEntity.ok(currency);
    }

    /**
     * Obtient le standard comptable d'une entreprise
     */
    @GetMapping("/{id}/accounting-standard")
    @Operation(summary = "Standard comptable", description = "Obtient le standard comptable d'une entreprise")
    public ResponseEntity<String> getCompanyAccountingStandard(@PathVariable Long id) {
        String standard = companyService.getCompanyAccountingStandard(id);
        return ResponseEntity.ok(standard);
    }

    /**
     * Obtient la langue d'une entreprise
     */
    @GetMapping("/{id}/language")
    @Operation(summary = "Langue entreprise", description = "Obtient la langue d'une entreprise")
    public ResponseEntity<String> getCompanyLanguage(@PathVariable Long id) {
        String language = companyService.getCompanyLanguage(id);
        return ResponseEntity.ok(language);
    }

    /**
     * Obtient le fuseau horaire d'une entreprise
     */
    @GetMapping("/{id}/timezone")
    @Operation(summary = "Fuseau horaire", description = "Obtient le fuseau horaire d'une entreprise")
    public ResponseEntity<String> getCompanyTimezone(@PathVariable Long id) {
        String timezone = companyService.getCompanyTimezone(id);
        return ResponseEntity.ok(timezone);
    }

    /**
     * Statistiques des entreprises par standard comptable
     */
    @GetMapping("/stats/standards")
    @Operation(summary = "Statistiques standards", description = "Compte les entreprises par standard comptable")
    public ResponseEntity<List<Object[]>> getCompanyStatsByStandard() {
        List<Object[]> stats = companyService.countByAccountingStandard();
        return ResponseEntity.ok(stats);
    }

    /**
     * Statistiques des entreprises par devise
     */
    @GetMapping("/stats/currencies")
    @Operation(summary = "Statistiques devises", description = "Compte les entreprises par devise")
    public ResponseEntity<List<Object[]>> getCompanyStatsByCurrency() {
        List<Object[]> stats = companyService.countByCurrencyCode();
        return ResponseEntity.ok(stats);
    }

    /**
     * Statistiques des entreprises par pays
     */
    @GetMapping("/stats/countries")
    @Operation(summary = "Statistiques pays", description = "Compte les entreprises par pays")
    public ResponseEntity<List<Object[]>> getCompanyStatsByCountry() {
        List<Object[]> stats = companyService.countByCountry();
        return ResponseEntity.ok(stats);
    }

    /**
     * Crée une nouvelle entreprise
     */
    @PostMapping
    @Operation(summary = "Créer entreprise", description = "Crée une nouvelle entreprise")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company createdCompany = companyService.createCompany(company);
        return ResponseEntity.ok(createdCompany);
    }

    /**
     * Met à jour une entreprise
     */
    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour entreprise", description = "Met à jour une entreprise existante")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company companyDetails) {
        Company updatedCompany = companyService.updateCompany(id, companyDetails);
        return ResponseEntity.ok(updatedCompany);
    }

    /**
     * Désactive une entreprise
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Désactiver entreprise", description = "Désactive une entreprise (soft delete)")
    public ResponseEntity<Company> deactivateCompany(@PathVariable Long id) {
        Company deactivatedCompany = companyService.deactivateCompany(id);
        return ResponseEntity.ok(deactivatedCompany);
    }

    /**
     * Réactive une entreprise
     */
    @PutMapping("/{id}/activate")
    @Operation(summary = "Réactiver entreprise", description = "Réactive une entreprise désactivée")
    public ResponseEntity<Company> activateCompany(@PathVariable Long id) {
        Company activatedCompany = companyService.activateCompany(id);
        return ResponseEntity.ok(activatedCompany);
    }
}
