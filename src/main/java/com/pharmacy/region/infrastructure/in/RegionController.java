package com.pharmacy.region.infrastructure.in;

import javax.swing.JOptionPane;

import com.pharmacy.country.domain.service.CountryService;
import com.pharmacy.country.infrastructure.out.CountryRepository;
import com.pharmacy.region.application.CreateRegionUseCase;
import com.pharmacy.region.application.DeleteRegionUseCase;
import com.pharmacy.region.application.FindRegionByIdUseCase;
import com.pharmacy.region.application.UpdateRegionUseCase;
import com.pharmacy.region.domain.entity.Region;
import com.pharmacy.region.domain.service.RegionService;
import com.pharmacy.region.infrastructure.out.RegionRepository;

public class RegionController {
    private RegionService regionService;
    private CreateRegionUseCase createRegionUseCase;
    private UpdateRegionUseCase updateRegionUseCase;
    private DeleteRegionUseCase deleteRegionUseCase;
    private FindRegionByIdUseCase findRegionByIdUseCase;
    private CountryService countryService;

    public RegionController() {
        this.regionService = new RegionRepository();
        this.createRegionUseCase = new CreateRegionUseCase(regionService);
        this.updateRegionUseCase = new UpdateRegionUseCase(regionService);
        this.deleteRegionUseCase = new DeleteRegionUseCase(regionService);
        this.findRegionByIdUseCase = new FindRegionByIdUseCase(regionService);
        this.countryService = new CountryRepository();
    }

    public void mainMenu() {
        String options = "1. Create Region.\n2. Update Region.\n3. Delete Region.\n4. Find Region By ID.\n5. Return.";
        int op;
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(null, options));
            switch (op) {
                case 1:
                    createRegion();
                    break;
                case 2:
                    updateRegion();
                    break;
                case 3:
                    deleteRegion();
                    break;
                case 4:
                    findRegion();
                    break;
                case 5:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalidation enter option", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } while (op != 5);
    }

    public void createRegion() {
        String code = JOptionPane.showInputDialog(null,"Code Region");
        String name = JOptionPane.showInputDialog(null,"Region Name");
        String codecountry = JOptionPane.showInputDialog(null, "Code country");

        Region region = new Region();
        region.setCodereg(code);
        region.setNamereg(name);
        region.setCodecountry(codecountry);

        createRegionUseCase.execute(region);
    }

    public void findRegion() {
        try {
            String code = JOptionPane.showInputDialog(null, "Enter Region code: ");
            findRegionByIdUseCase.execute(code).ifPresentOrElse(
                regionFound -> {
                        JOptionPane.showMessageDialog(null,regionFound.toString(), "Consultation", JOptionPane.INFORMATION_MESSAGE);
                    },
                    () -> {
                        JOptionPane.showMessageDialog(null, "Code " + code + " not found!", "Not Found", JOptionPane.ERROR_MESSAGE);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateRegion() {
        try {
            String code = JOptionPane.showInputDialog(null, "Enter the code Region");
            
            findRegionByIdUseCase.execute(code).ifPresentOrElse(
                currentRegion -> {
                    String newName = JOptionPane.showInputDialog(null, "Enter the new Region name");
                    String newCodecountry = JOptionPane.showInputDialog(null, "Enter the new Country code");

                    if (countryService.findCountryById(newCodecountry).isPresent()) {
                        currentRegion.setNamereg(newName);
                        currentRegion.setCodecountry(newCodecountry);
                        updateRegionUseCase.execute(currentRegion);

                        JOptionPane.showMessageDialog(null, "Region updated successfully " + newName + " with Country code " + newCodecountry + "!","Successfully",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Country with code " + newCodecountry + " does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }, 
                () -> {
                    JOptionPane.showMessageDialog(null, "Region with code " + code + " not found!", "Error", JOptionPane.ERROR_MESSAGE);
                });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRegion() {
        try {
            String code = JOptionPane.showInputDialog(null, "Enter the code Region");
            findRegionByIdUseCase.execute(code).ifPresentOrElse(
                currentRegion -> {
                    deleteRegionUseCase.execute(code);
                    JOptionPane.showMessageDialog(null, "Region deleted successfully " + code + "!","Successfully",JOptionPane.INFORMATION_MESSAGE);
                }, 
            () -> {
                JOptionPane.showMessageDialog(null, "Region deletion failed!", "Error", JOptionPane.ERROR_MESSAGE);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
