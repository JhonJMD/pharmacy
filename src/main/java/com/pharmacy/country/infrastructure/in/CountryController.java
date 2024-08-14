package com.pharmacy.country.infrastructure.in;

import javax.swing.JOptionPane;

import com.pharmacy.country.application.CreateCountryUseCase;
import com.pharmacy.country.application.DeleteCountryUseCase;
import com.pharmacy.country.application.FindCountryByIdUseCase;
import com.pharmacy.country.application.UpdateCountryUseCase;
import com.pharmacy.country.domain.entity.Country;
import com.pharmacy.country.domain.service.CountryService;
import com.pharmacy.country.infrastructure.out.CountryRepository;

public class CountryController {
    private CountryService countryService;
    private CreateCountryUseCase createCountryUseCase;
    private UpdateCountryUseCase updateCountryUseCase;
    private DeleteCountryUseCase deleteCountryUseCase;
    private FindCountryByIdUseCase findCountryByIdUseCase;
    
    public CountryController() {
        this.countryService = new CountryRepository();
        this.createCountryUseCase = new CreateCountryUseCase(countryService);
        this.updateCountryUseCase = new UpdateCountryUseCase(countryService);
        this.deleteCountryUseCase = new DeleteCountryUseCase(countryService);
        this.findCountryByIdUseCase = new FindCountryByIdUseCase(countryService);
    }

    public void mainMenu() {
        String options = "1. Create Country.\n2. Update Country.\n3. Delete Country.\n4. Find Country By ID.\n5. Return.";
        int op;
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(null, options));
            switch (op) {
                case 1:
                    createCountry();
                    break;
                case 2:
                
                    break;
                case 3:
                
                    break;
                case 4:
                
                    break;
                case 5:
                
                    break;
                default:
                    break;
            }
        } while (op != 5);
    }

    public void createCountry() {
        String name = JOptionPane.showInputDialog(null,"Country Name");

        Country country = new Country();
        country.setNamecountry(name);

        createCountryUseCase.execute(country);
    }

    public void updateCountry() {
        
    }
}
