package com.onlinebanking.dao;

import com.onlinebanking.model.Customer;
import com.onlinebanking.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private Connection connection;

    // Constructor for DB connection
    public CustomerDAO() {
        this.connection = DBConnection.getConnection();
    }

    // Save a new customer
    public void save(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customers (first_name, last_name, email, phone_number, address, date_of_birth) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setString(5, customer.getAddress());
            stmt.setDate(6, java.sql.Date.valueOf(customer.getDateOfBirth()));
            stmt.executeUpdate();
        }
    }

    // Get customer by ID
    public Customer getById(int id) throws SQLException {
        String sql = "SELECT * FROM Customers WHERE customer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setAddress(rs.getString("address"));
                customer.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                customer.setRegistrationDate(rs.getTimestamp("registration_date"));
                return customer;
            }
        }
        return null;
    }

    // Get all customers
    public List<Customer> getAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customers";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setAddress(rs.getString("address"));
                customer.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                customer.setRegistrationDate(rs.getTimestamp("registration_date"));
                customers.add(customer);
            }
        }
        return customers;
    }

    // Update customer information
    public void update(Customer customer) throws SQLException {
        String sql = "UPDATE Customers SET first_name = ?, last_name = ?, email = ?, phone_number = ?, address = ?, date_of_birth = ? WHERE customer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setString(5, customer.getAddress());
            stmt.setDate(6, java.sql.Date.valueOf(customer.getDateOfBirth()));
            stmt.setInt(7, customer.getCustomerId());
            stmt.executeUpdate();
        }
    }

    // Delete a customer
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Customers WHERE customer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
