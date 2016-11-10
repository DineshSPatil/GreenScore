
package com.ptc.greenscore;

import com.thingworx.data.util.InfoTableInstanceFactory;
import com.thingworx.entities.RootEntity;
import com.thingworx.entities.utils.EntityUtilities;
import com.thingworx.logging.LogUtilities;
import com.thingworx.metadata.annotations.ThingworxPropertyDefinition;
import com.thingworx.metadata.annotations.ThingworxPropertyDefinitions;
import com.thingworx.metadata.annotations.ThingworxServiceDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceParameter;
import com.thingworx.metadata.annotations.ThingworxServiceResult;
import com.thingworx.relationships.RelationshipTypes.ThingworxRelationshipTypes;
import com.thingworx.resources.entities.EntityServices;
import com.thingworx.things.Thing;
import com.thingworx.types.InfoTable;
import com.thingworx.types.collections.ValueCollection;

import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;

@ThingworxPropertyDefinitions(properties = {
		@ThingworxPropertyDefinition(name = "sensorValue", description = "", category = "", baseType = "INTEGER", isLocalOnly = false, aspects = {
				"minimumValue:1", "dataChangeType:Value", "maximumValue:1000" }) })
public class GreenScoreThingTemplate extends Thing {

    final static String USER = "root";
    final static String PASS = "root";
    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    final static String DB_URL = "jdbc:mysql://localhost/greenscore";
    private static final String GREENSCORE_USER_INFORMATION_DATASHAPE = "GreenScoreUserInformationDataShape";
	private static Logger _logger = LogUtilities.getInstance().getApplicationLogger(GreenScoreThingTemplate.class);
	List<MyData> logData = new ArrayList<MyData>();

	public GreenScoreThingTemplate() throws Exception {
		// TODO Auto-generated constructor stub
		//EntityServices es=new EntityServices();
		//es.CreateThing("name", "description", null, "thingTemplateName");
		//Thing re=(Thing) EntityUtilities.findEntity("name", ThingworxRelationshipTypes.Thing);
		//re.EnableThing();
		
	}

	@ThingworxServiceDefinition(name = "getDataFromDatabase", description = "")
	@ThingworxServiceResult(name = "Result", description = "", baseType = "STRING")
	public String getDataFromDatabase() throws ClassNotFoundException, SQLException {
		_logger.trace("Entering Service: getDataFromDatabase");
		_logger.trace("Exiting Service: getDataFromDatabase");
        
        Connection conn = null;
        Statement stmt = null;
        
        //Class.forName("com.mysql.jdbc.Driver");
        conn = getConnection();
        
        stmt = conn.createStatement();
        
        String sql = "SELECT * FROM testtable";
        ResultSet rs = stmt.executeQuery(sql);
           
        while(rs.next()){ 	
            //int id  = rs.getInt("id");
            String name = rs.getString("name");
            return name;
        }
        rs.close();
        conn.close();
        return null;
	}

	@ThingworxServiceDefinition(name = "setSensorValueInDatabase", description = "")
	@ThingworxServiceResult(name = "Result", description = "", baseType = "BOOLEAN")
	public Boolean setSensorValueInDatabase(
			@ThingworxServiceParameter(name = "sensorValue", description = "", baseType = "INTEGER") Integer sensorValue) throws ClassNotFoundException, SQLException {
		_logger.trace("Entering Service: setSensorValueInDatabase");
		_logger.trace("Exiting Service: setSensorValueInDatabase");
        Connection conn = null;
        Statement stmt = null;
        if (sensorValue == null)
        	sensorValue = 10;
        //Class.forName("com.mysql.jdbc.Driver");
        conn = getConnection();
        
        stmt = conn.createStatement();
        
        String sql = "INSERT INTO testtable (id, name, sensorvalue) VALUES (?, ?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(sql);
        preparedStmt.setInt    (1, 4);
        preparedStmt.setString (2, "Barney");
        preparedStmt.setInt    (3, sensorValue);
        boolean b = preparedStmt.execute();
        conn.close();
		return b;
	}
	
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
		return DriverManager.getConnection(DB_URL, USER, PASS);
	}

	@ThingworxServiceDefinition(name = "setSensorValueTemp", description = "")
	@ThingworxServiceResult(name = "Result", description = "", baseType = "STRING")
	public String setSensorValueTemp(
			@ThingworxServiceParameter(name = "sensorValue", description = "", baseType = "INTEGER") Integer sensorValue,
			@ThingworxServiceParameter(name = "licenseNumber", description = "", baseType = "STRING") String licenseNumber) {
		_logger.trace("Entering Service: setSensorValueTemp");
		_logger.trace("Exiting Service: setSensorValueTemp");
		if (licenseNumber == null || sensorValue == null) {
			return "please provide license Number and sensor Value";
		}
		MyData mydata = new MyData(sensorValue, licenseNumber);
		logData.add(mydata);
		return "sucessfully added in temp list";
	}

	@ThingworxServiceDefinition(name = "getSensorDataTemp", description = "")
	@ThingworxServiceResult(name = "Result", description = "", baseType = "INTEGER")
	public Integer getSensorDataTemp() {
		_logger.trace("Entering Service: getSensorDataTemp");
		_logger.trace("Exiting Service: getSensorDataTemp");
		int d = 0;
		if (!logData.isEmpty()) {
			d = logData.get(0).sensorData;
		}
		return d;
	}

	@ThingworxServiceDefinition(name = "calculateGreenScore", description = "")
	@ThingworxServiceResult(name = "Result", description = "", baseType = "INTEGER")
	public Integer calculateGreenScore(@ThingworxServiceParameter(name = "licenseNumber", description = "", baseType = "STRING") String licenseNumber) throws Exception {
		_logger.trace("Entering Service: calculateGreenScore");
		_logger.trace("Exiting Service: calculateGreenScore");
		int pollutionPoint = 0;
		
		// calculate pollution point for specific license number
		if (!logData.isEmpty()) {
			for (MyData myData:logData) {
				if (myData.licenseNumber.equalsIgnoreCase(licenseNumber)) {
					if (myData.sensorData >= 450 && myData.sensorData <= 600)
						pollutionPoint += 1;
					else if (myData.sensorData >= 601 && myData.sensorData <= 700)
						pollutionPoint += 2;
					else if (myData.sensorData >= 701 && myData.sensorData <= 800)
						pollutionPoint += 3;
					else if (myData.sensorData >= 801 && myData.sensorData <= 900)
						pollutionPoint += 4;
					else if (myData.sensorData >= 901)
						pollutionPoint += 5;
				}
			}	
		}
		
		InfoTable info= getSpecificeUserInformation(licenseNumber);
		ValueCollection value = info.getRow(0);
		
		int oldGreenScore = Integer.valueOf((String) value.getValue("greenScore"));
		int newGreenScore = oldGreenScore - pollutionPoint;

		Connection conn = null;        
        conn = getConnection();
        
        if (!logData.isEmpty() && pollutionPoint > 0) {
        	Iterator<MyData> it = logData.iterator();
            while (it.hasNext()) {
                if (it.next().licenseNumber.equalsIgnoreCase(licenseNumber)) {
                    it.remove();
                }
            }
            
    		// update new greenscore and old greenscore in the database
            String sql = "update userinformation set greenScore = ? , oldGreenScore = ? where drivingLicense = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setInt(1, newGreenScore);
            preparedStmt.setInt(2, oldGreenScore);
            preparedStmt.setString(3, licenseNumber);
            preparedStmt.executeUpdate();
            
            // insert old green score for maintain history of the data
            String sqlQuery = "INSERT INTO greenscorehistory (oldGreenScore, drivingLicense) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            
            preparedStatement.setInt(1, oldGreenScore);
            preparedStatement.setString(2, licenseNumber);
            
            preparedStatement.execute();
        }
		return newGreenScore;
	}

	@ThingworxServiceDefinition(name = "setUserInformation", description = "")
	@ThingworxServiceResult(name = "Result", description = "", baseType = "BOOLEAN")
	public Boolean setUserInformation(
			@ThingworxServiceParameter(name = "userName", description = "", baseType = "STRING") String userName,
			@ThingworxServiceParameter(name = "mobileNumber", description = "", baseType = "INTEGER") Integer mobileNumber,
			@ThingworxServiceParameter(name = "drivingLicense", description = "", baseType = "STRING") String drivingLicense) throws SQLException, ClassNotFoundException {
        
		Connection conn = null;
        conn = getConnection();
        
        String sql = "INSERT INTO userinformation (userName, mobile, drivingLicense,greenScore) VALUES (?, ?, ?,?)";
        PreparedStatement preparedStmt = conn.prepareStatement(sql);
        preparedStmt.setString(1, userName);
        preparedStmt.setInt(2, mobileNumber);
        preparedStmt.setString(3, drivingLicense);
        preparedStmt.setInt(4, 100);
        boolean b = preparedStmt.execute();
        conn.close();
        return b;
	}

	@ThingworxServiceDefinition(name = "getSpecificeUserInformation", description = "")
	@ThingworxServiceResult(name = "Result", description = "", baseType = "INFOTABLE")
	public InfoTable getSpecificeUserInformation(
			@ThingworxServiceParameter(name = "licenseNumber", description = "", baseType = "STRING") String licenseNumber) throws Exception {

        Connection conn = null;
        conn = getConnection();
        
        String sql = "SELECT * FROM userinformation where drivingLicense = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(sql);
        preparedStmt.setString(1, licenseNumber);
        ResultSet rs = preparedStmt.executeQuery();
        
        InfoTable infoTable = InfoTableInstanceFactory.createInfoTableFromDataShape(GREENSCORE_USER_INFORMATION_DATASHAPE);
        
        while(rs.next()){
        	ValueCollection row = new ValueCollection();
        	row.SetStringValue("userID",rs.getInt("userID"));
        	row.SetStringValue("userName",rs.getString("userName"));
        	row.SetStringValue("mobile",rs.getInt("mobile"));
        	row.SetStringValue("drivingLicense",rs.getString("drivingLicense"));
        	row.SetStringValue("greenScore",rs.getInt("greenScore"));
        	infoTable.addRow(row);
            return infoTable;
        }
        rs.close();
        conn.close();
		return null;
	}

	@ThingworxServiceDefinition(name = "getAllUserInformation", description = "")
	@ThingworxServiceResult(name = "Result", description = "", baseType = "INFOTABLE")
	public InfoTable getAllUserInformation() throws Exception {
        Connection conn = null;
        Statement stmt = null;

        conn = getConnection();
        
        stmt = conn.createStatement();
        
        String sql = "SELECT * FROM userinformation";
        ResultSet rs = stmt.executeQuery(sql);
        InfoTable infoTable = InfoTableInstanceFactory.createInfoTableFromDataShape(GREENSCORE_USER_INFORMATION_DATASHAPE);
        while(rs.next()){
        	ValueCollection row = new ValueCollection();
        	row.SetStringValue("userID",rs.getInt("userID"));
        	row.SetStringValue("userName",rs.getString("userName"));
        	row.SetStringValue("mobile",rs.getInt("mobile"));
        	row.SetStringValue("drivingLicense",rs.getString("drivingLicense"));
        	row.SetStringValue("greenScore",rs.getInt("greenScore"));
        	infoTable.addRow(row);           
        }
        rs.close();
        conn.close();
        return infoTable;
	}
	
	
	

}
