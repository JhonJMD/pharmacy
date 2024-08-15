package com.pharmacy;

import javax.swing.JOptionPane;

import com.pharmacy.country.infrastructure.in.CountryController;
import com.pharmacy.region.infrastructure.in.RegionController;

public class Main {
    public static void main(String[] args) {
        String options = "1. Country.\n2. Region.\n3. City.\n4. Customer.\n5. Laboratory.\n6. Pharmacy.\n7. Medicine.\n8. Mode Administration.\n9. Unit Measurement.\n10. Active Principle. \n11. Price Medicine.\n12. Exit.";
        int op;
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(null,options));
            switch (op) {
                case 1:
                    CountryController consoleAdapterCountry = new CountryController();
                    consoleAdapterCountry.mainMenu();
                    break;
                case 2:
                    RegionController consoleAdapterRegion = new RegionController();
                    consoleAdapterRegion.mainMenu();
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    
                    break;
                case 6:
                    
                    break;
                case 7:
                    
                    break;
                case 8:
                    
                    break;
                case 9:
                    
                    break;
                case 10:
                    
                    break;
                case 11:
                    
                    break;
                case 12:
                    JOptionPane.showMessageDialog(null, "Thanks you for use my program", "Exit", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    break;
            }
        } while (op != 12);
    }
}