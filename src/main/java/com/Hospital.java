package com;

import java.sql.*;

public class Hospital {

    private Connection connect() {

        Connection con = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3308/item", "root", "");

        } catch (Exception e) {

            e.printStackTrace();

        }

        return con;

    }

    public String readHospital() {

        String output = "";

        try {

            Connection con = connect();

            if (con == null) {

                return "Error while connecting to the database for reading. ";

            }

            // Prepare the html table to be displayed
            output = "<table border='1'>"
                      + "<tr><th>Hospital Name</th>"
                      + "<th> Hospital Address</th >"
                      + "<th> Hospital Username</th >"
                      + "<th> Hospital Password</th>"
                      + "<th> Update </th > <th > Remove </th ></tr > ";

            String query = "select * from hospital";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // iterate through the rows in the result set
            while (rs.next()) {

                String hospitalID = Integer.toString(rs.getInt("hospitalID"));
                String hospitalName = rs.getString("hospitalName");
                String hospitalAddress = rs.getString("hospitalAddress");
                String hospitalPassword = rs.getString("hospitalPassword");


                // Add into the html table
                output += "<tr><td><input id='hidHospitalIDUpdate' "
                        + "name = 'hidHospitalIDUpdate'"
                        + "type = 'hidden' value = '"
                        + hospitalID + "'>" + hospitalName + "</td>";
                output += "<td>" + hospitalAddress + "</td>";
                output += "<td>" + hospitalPassword + "</td>";


                // buttons
                output += "<td><input name='btnUpdate' type = 'button' value = 'Update' class='btnUpdate btn btn-secondary' ></td > " + "<td><input name='btnRemove' type = 'button' value = 'Remove' class='btnRemove btn btn-danger' data-hospitalid = '"
                        + hospitalID + "'>" + "</td></tr>";

            }

            con.close();

            // Complete the html table
            output += "</table>";

        } catch (Exception e) {

            output = "Error while reading the hospital visit.";
            System.err.println(e.getMessage());

        }

        return output;

    }

    public String insertHospital(String hospitalName, String hospitalAddress, String
            hospitalUsername, String hospitalPassword) {

        String output = "";

        try {

            Connection con = connect();

            if (con == null) {

                return "Error while connecting to the database for inserting";

            }

            // create a prepared statement
            String query = " insert into hospital (hospitalID,hospitalName,hospitalAddress,hospitalUsername,hospitalPassword)" + " values (?, ?,?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            // binding values
            preparedStmt.setInt(1, 0);
            preparedStmt.setString(2, hospitalName);
            preparedStmt.setString(3, hospitalAddress);
            preparedStmt.setString(4, hospitalUsername);
            preparedStmt.setString(5, hospitalPassword);


            // execute the statement
            preparedStmt.execute();
            con.close();

            String newHospitals = readHospital();
            output = "{\"status\":\"success\", \"data\": \"" + newHospitals + "\"}";

        } catch (Exception e) {

            output = "{\"status\":\"error\", \"data\":\"Error while inserting the hospitals. \"}";
            System.err.println(e.getMessage());

        }

        return output;

    }

    public String UpdateHospital(String hospitalName, String hospitalAddress, String
            hospitalUsername, String hospitalPassword) {

        String output = "";

        try {

            Connection con = connect();

            if (con == null) {

                return "Error while connecting to the database for updating. ";

            }

            // create a prepared statement
            String query = "UPDATE hospitals SET hospitalName =?,hospitalAddress =?,hospitalUsername =?,hospitalPassword=? WHERE hospitalID =?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            // binding values
            preparedStmt.setString(1, hospitalName);
            preparedStmt.setString(2, hospitalAddress);
            preparedStmt.setString(3, hospitalUsername);
            preparedStmt.setString(4, hospitalPassword);


            // execute the statement
            preparedStmt.execute();
            con.close();

            String newHospitals = readHospital();
            output = "{\"status\":\"success\", \"data\": \"" + newHospitals + "\"}";

        } catch (Exception e) {

            output = "{\"status\":\"error\", \"data\":\"Error while updating the hospitals.\"}";
            System.err.println(e.getMessage());

        }

        return output;

    }

    public String deletehospital(String hospitalID) {

        String output = "";

        try {

            Connection con = connect();

            if (con == null) {

                return "Error while connecting to the database for deleting. ";

            }

            // create a prepared statement
            String query = "delete from hospital where hospitalID=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            // binding values
            preparedStmt.setInt(1, Integer.parseInt(hospitalID));

            // execute the statement
            preparedStmt.execute();
            con.close();
            String newHospitals = readHospital();
            output = "{\"status\":\"success\", \"data\": \"" + newHospitals + "\"}";

        } catch (Exception e) {

            output = "{\"status\":\"error\", \"data\":\"Error while deleting the hospitals.\"}";
            System.err.println(e.getMessage());

        }

        return output;

    }

}
