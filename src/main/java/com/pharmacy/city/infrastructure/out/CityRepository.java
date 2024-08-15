package com.pharmacy.city.infrastructure.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.pharmacy.city.domain.entity.City;
import com.pharmacy.city.domain.service.CityService;

public class CityRepository implements CityService{
    private Connection connection;

    public CityRepository(){
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
    public void createCity(City city) {
        String query = "INSERT INTO city (codecity, namecity, codereg) VALUES(?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, city.getCodecity());
            ps.setString(2, city.getNamecity());
            ps.setString(3, city.getCodereg());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0){
                JOptionPane.showMessageDialog(null, "City added successfully: " + city.getNamecity() + "!", "Successfully", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "City addition failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error during city addition: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
    }

    @Override
    public void deleteCity(String codecity) {
        String query = "DELETE FROM city WHERE codecity = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, codecity);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error during city deletion: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public Optional<City> findCityById(String codecity) {
        String query = "SELECT c.codecity, c.namecity, r.namereg FROM city c JOIN region r ON c.codereg = r.codereg WHERE c.codecity = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, codecity);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    City city = new City(
                        rs.getString("codecity"),
                        rs.getString("namecity"),
                        rs.getString("r.namereg")
                    );
                    return Optional.of(city);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error during find city: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateCity(City city) {
        String query = "UPDATE city SET namecity = ?, codereg = ? WHERE codecity = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, city.getNamecity());
            ps.setString(2, city.getCodereg());
            ps.setString(3, city.getCodecity());
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error during city update: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
    }
}
