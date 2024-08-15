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
                    updateCountry();
                    break;
                case 3:
                    deleteCountry();
                    break;
                case 4:
                    findCountry();
                    break;
                case 5:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalidation enter option", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } while (op != 5);
    }

    public void createCountry() {
        String code = JOptionPane.showInputDialog(null,"Code Country");
        String name = JOptionPane.showInputDialog(null,"Country Name");

        Country country = new Country();
        country.setCodecountry(code);
        country.setNamecountry(name);

        createCountryUseCase.execute(country);
    }

    public void findCountry() {
        try {
            String code = JOptionPane.showInputDialog(null, "Enter country code: ");
            findCountryByIdUseCase.execute(code).ifPresentOrElse(
                countryFound -> {
                        JOptionPane.showMessageDialog(null,countryFound.toString(), "Consultation", JOptionPane.INFORMATION_MESSAGE);
                    },
                    () -> {
                        JOptionPane.showMessageDialog(null, "Code " + code + " not found!", "Not Found", JOptionPane.ERROR_MESSAGE);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCountry() {
        try {
            String code = JOptionPane.showInputDialog(null, "Enter the code Country");
            
            findCountryByIdUseCase.execute(code).ifPresentOrElse(
                currentCountry -> {
                    String newName = JOptionPane.showInputDialog(null, "Enter the new Country name");

                    currentCountry.setNamecountry(newName);
                    updateCountryUseCase.execute(currentCountry);

                    JOptionPane.showMessageDialog(null, "Country updated successfully " + newName + "!","Successfully",JOptionPane.INFORMATION_MESSAGE);
                }, 
                () -> {
                    JOptionPane.showMessageDialog(null, "Country with code " + code + " not found!", "Error", JOptionPane.ERROR_MESSAGE);
                });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCountry() {
        try {
            String code = JOptionPane.showInputDialog(null, "Enter the code Country");
            findCountryByIdUseCase.execute(code).ifPresentOrElse(
                currentCountry -> {
                    deleteCountryUseCase.execute(code);
                    JOptionPane.showMessageDialog(null, "Country deleted successfully " + code + "!","Successfully",JOptionPane.INFORMATION_MESSAGE);
                }, 
            () -> {
                JOptionPane.showMessageDialog(null, "Country deletion failed!", "Error", JOptionPane.ERROR_MESSAGE);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
