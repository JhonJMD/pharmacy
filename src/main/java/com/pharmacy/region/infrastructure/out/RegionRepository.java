package com.pharmacy.region.infrastructure.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.pharmacy.region.domain.entity.Region;
import com.pharmacy.region.domain.service.RegionService;

public class RegionRepository implements RegionService{
    private Connection connection;

    public RegionRepository(){
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
    public void createRegion(Region region) {
        String query = "INSERT INTO region (codereg, namereg, codecountry) VALUES(?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, region.getCodereg());
            ps.setString(2, region.getNamereg());
            ps.setString(3, region.getCodecountry());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0){
                JOptionPane.showMessageDialog(null, "Region added successfully: " + region.getNamereg() + "!","Successfully",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Region addition failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error during region addition: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } 
        
    }

    @Override
    public void deleteRegion(String codereg) {
        String query = "DELETE FROM region WHERE codereg = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, codereg); 
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error during region deletion: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Region> findRegionById(String codeReg) {
        String query = "SELECT r.codereg, r.namereg, r.codecountry FROM region r WHERE r.codereg = ?";
        try (PreparedStatement ps = connection.prepareStatement(query);){
            ps.setString(1, codeReg);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    Region region = new Region(
                        rs.getString("codereg"),
                        rs.getString("namereg"),
                        rs.getString("codecountry")
                    );
                    return Optional.of(region);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error during find Region: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateRegion(Region region) {
        String query = "UPDATE region SET namereg = ?, codecountry = ? WHERE codereg = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, region.getNamereg()); 
            ps.setString(2, region.getCodecountry()); 
            ps.setString(3, region.getCodereg()); 
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error during region update: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    
}
