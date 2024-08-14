package com.pharmacy.country.infrastructure.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.pharmacy.country.domain.entity.Country;
import com.pharmacy.country.domain.service.CountryService;

public class CountryRepository implements CountryService{
    private Connection connection;

    public CountryRepository(){
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createCountry(Country country) {
        String query = "INSERT INTO country (codecountry, namecountry) VALUES(?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, country.getCodecountry());
            ps.setString(2, country.getNamecountry());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0){
                JOptionPane.showMessageDialog(null, "Country added successfully: " + country.getNamecountry() + "!","Successfully",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Country addition failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    @Override
    public void deleteCountry(String codeCountry) {
        String query = "DELETE FROM country WHERE codecountry = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, codeCountry); 
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0){
                JOptionPane.showMessageDialog(null, "Country deleted successfully: " + codeCountry + "!","Successfully",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Country deletion failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Country> findCountryById(String codeCountry) {
        String query = "SELECT c.codecountry, c.namecountry FROM country c WHERE c.codecountry = ?";
        try (PreparedStatement ps = connection.prepareStatement(query);){
            ps.setString(1, codeCountry);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    Country country = new Country(
                        rs.getString("codecountry"),
                        rs.getString("namecountry")
                    );
                    return Optional.of(country);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updtaCountry(Country country) {
        String query = "UPDATE country SET namecountry = ? WHERE codecontry = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, country.getNamecountry());
            ps.setString(2, country.getCodecountry());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0){
                JOptionPane.showMessageDialog(null, "Country updated successfully: " + country.getNamecountry() + "!","Successfully",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Country update failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
