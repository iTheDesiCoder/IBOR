package com.java.ibor.repository;

import com.java.ibor.entity.Position;

import java.sql.*;

public class PositionRepository {

    private static final String DB_URL = "jdbc:mysql://your-database-host:your-database-port/your-database-name";
    private static final String DB_USER = "your-database-username";
    private static final String DB_PASSWORD = "your-database-password";

    public Position getPositionFromDatabase(String securityId, String accountId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT security_id, account_id, quantity FROM positions WHERE security_id = ? AND account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, securityId);
            preparedStatement.setString(2, accountId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Position position = new Position();
                position.setSecurityId(resultSet.getString("security_id"));
                position.setAccountId(resultSet.getString("account_id"));
                position.setQuantity(resultSet.getDouble("quantity"));
                return position;
            } else {
                // Return null or an appropriate default value if the position doesn't exist
                return null;
            }
        } catch (SQLException e) {
            // Handle any database-related exceptions here
            e.printStackTrace();
            return null;
        }
    }



    public void insertPosition(Position posToSave) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String insertQuery = "INSERT INTO positions (security_id, account_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, posToSave.getSecurityId());
            insertStatement.setString(2, posToSave.getAccountId());
            insertStatement.setDouble(3, posToSave.getQuantity());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle any database-related exceptions here
            e.printStackTrace();
        }
    }

    public void updatePosition(Position posToSave) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String updateQuery = "UPDATE positions SET quantity = ? WHERE security_id = ? AND account_id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setDouble(1, posToSave.getQuantity());
            updateStatement.setString(2, posToSave.getSecurityId());
            updateStatement.setString(3, posToSave.getAccountId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle any database-related exceptions here
            e.printStackTrace();
        }
    }
}
