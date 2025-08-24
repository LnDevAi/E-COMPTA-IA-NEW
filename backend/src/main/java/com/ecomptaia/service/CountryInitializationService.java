package com.ecomptaia.service;

import com.ecomptaia.entity.Country;
import com.ecomptaia.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class CountryInitializationService {

    @Autowired
    private CountryRepository countryRepository;

    @PostConstruct
    @Transactional
    public void initializeCountries() {
        if (countryRepository.count() == 0) {
            List<Country> countries = Arrays.asList(
                createCountry("BEN", "Bénin", "BJ", "229"),
                createCountry("BFA", "Burkina Faso", "BF", "226"),
                createCountry("CMR", "Cameroun", "CM", "237"),
                createCountry("CAF", "République centrafricaine", "CF", "236"),
                createCountry("TCD", "Tchad", "TD", "235"),
                createCountry("COM", "Comores", "KM", "269"),
                createCountry("COG", "République du Congo", "CG", "242"),
                createCountry("COD", "République démocratique du Congo", "CD", "243"),
                createCountry("CIV", "Côte d'Ivoire", "CI", "225"),
                createCountry("GNQ", "Guinée équatoriale", "GQ", "240"),
                createCountry("GAB", "Gabon", "GA", "241"),
                createCountry("GIN", "Guinée", "GN", "224"),
                createCountry("GNB", "Guinée-Bissau", "GW", "245"),
                createCountry("MLI", "Mali", "ML", "223"),
                createCountry("NER", "Niger", "NE", "227"),
                createCountry("SEN", "Sénégal", "SN", "221"),
                createCountry("TGO", "Togo", "TG", "228")
            );

            countryRepository.saveAll(countries);
            System.out.println("✅ Pays OHADA initialisés avec succès: " + countries.size() + " pays");
        } else {
            System.out.println("ℹ️  Les pays sont déjà initialisés dans la base de données");
        }
    }

    private Country createCountry(String code, String name, String isoCode, String phoneCode) {
        Country country = new Country();
        country.setCode(code);
        country.setName(name);
        country.setPhoneCode(phoneCode);
        country.setIsActive(true);
        return country;
    }
}
