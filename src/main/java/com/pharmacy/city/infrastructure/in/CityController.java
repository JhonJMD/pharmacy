package com.pharmacy.city.infrastructure.in;

import javax.swing.JOptionPane;

import com.pharmacy.city.application.CreateCityUseCase;
import com.pharmacy.city.application.DeleteCityUseCase;
import com.pharmacy.city.application.FindCityByIdUseCase;
import com.pharmacy.city.application.UpdateCityUseCase;
import com.pharmacy.city.domain.entity.City;
import com.pharmacy.city.domain.service.CityService;
import com.pharmacy.city.infrastructure.out.CityRepository;
import com.pharmacy.region.domain.service.RegionService;
import com.pharmacy.region.infrastructure.out.RegionRepository;

public class CityController {
    private CityService cityService;
    private CreateCityUseCase createCityUseCase;
    private UpdateCityUseCase updateCityUseCase;
    private DeleteCityUseCase deleteCityUseCase;
    private FindCityByIdUseCase findCityByIdUseCase;
    private RegionService regionService;

    public CityController() {
        this.cityService = new CityRepository();
        this.createCityUseCase = new CreateCityUseCase(cityService);
        this.updateCityUseCase = new UpdateCityUseCase(cityService);
        this.deleteCityUseCase = new DeleteCityUseCase(cityService);
        this.findCityByIdUseCase = new FindCityByIdUseCase(cityService);
        this.regionService = new RegionRepository();
    }

    public void mainMenu() {
        String options = "1. Create City.\n2. Update City.\n3. Delete City.\n4. Find City By ID.\n5. Return.";
        int op;
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(null, options));
            switch (op) {
                case 1:
                    createCity();
                    break;
                case 2:
                    updateCity();
                    break;
                case 3:
                    deleteCity();
                    break;
                case 4:
                    findCity();
                    break;
                case 5:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalidation enter option", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } while (op != 5);
    }

    public void createCity() {
        String code = JOptionPane.showInputDialog(null, "Code City");
        String name = JOptionPane.showInputDialog(null, "Name City");
        String codereg = JOptionPane.showInputDialog(null, "Code region");

        City city = new City();
        city.setCodecity(code);
        city.setNamecity(name);
        city.setCodereg(codereg);

        createCityUseCase.execute(city);
    }

    public void findCity() {
        try {
            String code = JOptionPane.showInputDialog(null, "Enter City code");

            findCityByIdUseCase.execute(code).ifPresentOrElse(
                cityFound -> {
                    JOptionPane.showMessageDialog(null,cityFound.toString(), "Consultation", JOptionPane.INFORMATION_MESSAGE);
                }, 
                () -> {
                    JOptionPane.showMessageDialog(null, "Code " + code + " not found!", "Not Found", JOptionPane.ERROR_MESSAGE);
                }
                );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCity() {
        try {
            String code = JOptionPane.showInputDialog(null, "Enter the code City");
            
            findCityByIdUseCase.execute(code).ifPresentOrElse(
                currentCity -> {
                    String newName = JOptionPane.showInputDialog(null, "Enter the new City name");
                    String newCoderegion = JOptionPane.showInputDialog(null, "Enter the new Region code");

                    if (regionService.findRegionById(newCoderegion).isPresent()) {
                        currentCity.setNamecity(newName);
                        currentCity.setCodereg(newCoderegion);
                        updateCityUseCase.execute(currentCity);

                        JOptionPane.showMessageDialog(null, "City updated successfully " + newName + " with Region code " + newCoderegion + "!","Successfully",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Region with code " + newCoderegion + " does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }, 
                () -> {
                    JOptionPane.showMessageDialog(null, "City with code " + code + " not found!", "Error", JOptionPane.ERROR_MESSAGE);
                });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCity() {
        try {
            String code = JOptionPane.showInputDialog(null, "Enter the code City");
            findCityByIdUseCase.execute(code).ifPresentOrElse(
                currentCity -> {
                    deleteCityUseCase.execute(code);
                    JOptionPane.showMessageDialog(null, "City deleted successfully " + code + "!","Successfully",JOptionPane.INFORMATION_MESSAGE);
                }, 
            () -> {
                JOptionPane.showMessageDialog(null, "City deletion failed!", "Error", JOptionPane.ERROR_MESSAGE);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
