package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Doctor {

	//=========================DB CONNECTION=========================================================
	
		// A common method to connect to the DB
		private Connection connect() {
			Connection con = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");
				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/health-system?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
				System.out.print("Successfully connected from Doctor");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Connection Failed");
				System.out.println(e);
			}

			return con;
		}	
		
		//=================================INSERT DOCTOR METHOD=========================================================
		
		public String insertDoctor(String docName, String nic, String address, String mobNo, String email, String spec, String hosp, String dept ) {
			String output = "";

			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}

				// create a prepared statement
				String query = " insert into doctors (`DoctorID`,`DoctorName`,`NIC`,`Address`,`MobileNo`, `Email`,`Specialization`,`HospitalName`,`DepartmentName`)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

						
				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, docName);
				preparedStmt.setString(3, nic);
				preparedStmt.setString(4, address);
				preparedStmt.setInt(5, Integer.parseInt(mobNo));
				preparedStmt.setString(6, email);
				preparedStmt.setString(7, spec);
				preparedStmt.setString(8, hosp);
				preparedStmt.setString(9, dept);

				// execute the statement
				preparedStmt.execute();
				con.close();

				String newDoctor = readDoctors();    
				output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";
				
			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\":\"Error while inserting doctor.\"}";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		//=======================================VIEW ALL DOCTORS METHOD=======================================================
		
		public String readDoctors() {
			String output = "";

			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for reading.";
				}

				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>DoctorName</th><th>NIC</th><th>Address</th><th>MobileNo</th><th>Email</th><th>Specialization</th><th>HospitalName</th><th>DepartmentName</th></tr>";

				String query = "select * from doctors";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				// iterate through the rows in the result set
				while (rs.next()) {
					String docID = Integer.toString(rs.getInt("DoctorID"));
					String docName = rs.getString("DoctorName");
					String nic = rs.getString("NIC");
					String address = rs.getString("Address");
					String mobNo = Integer.toString(rs.getInt("MobileNo"));
					String email = rs.getString("Email");
					String spec = rs.getString("Specialization");
					String hosp = rs.getString("HospitalName");
					String dept = rs.getString("DepartmentName");

					// Add into the html table
					output += "<tr><td><input id='hidDoctorIDUpdate' name='hidDoctorIDUpdate' type='hidden' value='" +docID+ "'>" + docName + "</td>";
					//output += "<tr><td>" + docName + "</td>";
					output += "<td>" + nic + "</td>";
					output += "<td>" + address + "</td>";
					output += "<td>" + mobNo + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + spec + "</td>";
					output += "<td>" + hosp + "</td>";
					output += "<td>" + dept + "</td>";
					
					// buttons    
					/*output += "<td><input name='btnUpdate' type='button\" value='Update\" class='btnUpdate btn btn-secondary'></td>"
							+ "<td><form method=\"post\" action=\"doctors.jsp\">" 
							+ "<input name='btnRemove' type='submit\" value='Remove\" class=\"btn btn-danger\">" 
							+ "<input name=\"hidDoctorIDDelete\" type=\"hidden\" value=\"" + docID + "\">" + "</form></td></tr>"; */

					// buttons     
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='" + docID + "'>" + "</td></tr>"; 
					
				}

				con.close();

				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
			}

			return output;

		}
		
		//=======================================VIEW ALL HOSPITALS==============================================
		
		public String readHospitals() {
			String output = "";

			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for reading.";
				}

				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>HosptalID</th><th>HospitalName</th><th>Address</th><th>City</th><th>PhoneNo</th><th>Email</th><th>Desc</th><th>OpenHours</th></tr>";

				String query = "select * from hospitals";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				// iterate through the rows in the result set
				while (rs.next()) {
					String hosID = Integer.toString(rs.getInt("Hospital_ID"));
					String hosName = rs.getString("Hospital_Name");
					String hosAdd = rs.getString("Hospital_Address");
					String hosCity = rs.getString("Hospital_City");
					String hosPhone = Integer.toString(rs.getInt("Hospital_Phone"));
					String hosEmail = rs.getString("Hospital_Email");
					String hosDesc = rs.getString("Hospital_Description");
					String hosOH = Integer.toString(rs.getInt("Open_Hours"));

					// Add into the html table
					output += "<tr><td>" + hosID + "</td>";
					output += "<td>" + hosName + "</td>";
					output += "<td>" + hosAdd + "</td>";
					output += "<td>" + hosCity + "</td>";
					output += "<td>" + hosPhone + "</td>";
					output += "<td>" + hosEmail + "</td>";
					output += "<td>" + hosDesc + "</td>";
					output += "<td>" + hosOH + "</td>";

				}

				con.close();

				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
			}

			return output;

		}
		
		
		
		//========================================UPDATE DOCTORS METHOD============================================================
		
		public String updateDoctor(String ID, String docName, String nic, String address, String mobNo, String email, String spec, String hosp, String dept ) {
			String output = "";

			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for updating.";
				}

				// create a prepared statement
				String query = "UPDATE doctors SET DoctorName=?,NIC=?,Address=?,MobileNo=?,Email=?,Specialization=?,HospitalName=?,DepartmentName=?WHERE DoctorID=?";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setString(1, docName);
				preparedStmt.setString(2, nic);
				preparedStmt.setString(3, address);
				preparedStmt.setInt(4, Integer.parseInt(mobNo));
				preparedStmt.setString(5, email);
				preparedStmt.setString(6, spec);
				preparedStmt.setString(7, hosp);
				preparedStmt.setString(8, dept);
				preparedStmt.setInt(9, Integer.parseInt(ID));

				// execute the statement
				preparedStmt.execute();
				con.close();

				String newDoctor = readDoctors();    
				output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";
				
			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\":\"Error while updating doctor.\"}";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		//========================================DELETE DOCTORS METHOD============================================================
		
		public String deleteDoctor(String docID) {
			String output = "";

			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}

				// create a prepared statement
				String query = "delete from doctors where DoctorID=?";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setInt(1, Integer.parseInt(docID));

				// execute the statement
				preparedStmt.execute();
				con.close();

				String newDoctor = readDoctors();    
				output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";
				
			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\":\"Error while updating doctor.\"}";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		
}
