package com.Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.String;

public class SqlHelper 
{
	private Connection getConnection() throws SQLException, ClassNotFoundException
	{
		String sConfig[] = null;
		Connection conn = null;
		ConfigXML configXML = null;
		try
		{
			configXML = new ConfigXML();
			sConfig = configXML.getConfig();
			
			//String strDriver = sConfig[0];
			Class.forName(sConfig[0]);
			
			/*S
			Class.forName(strDriver);
			String url = sConfig[1]; 
			String username = sConfig[2];
			String password = sConfig[3];
			*/
			conn = DriverManager.getConnection(sConfig[1], sConfig[2], sConfig[3]);
			
		}
		catch(SQLException ex) { ex.getMessage(); }
		finally {sConfig = null; configXML = null;}
		return conn;
	}
	
	public int get_Exec_Insert_SQL(String strSQL) throws SQLException, ClassNotFoundException
	{
		int iRowsInserted = 0;
		Statement stmtSQL = null;
		try
		{
			stmtSQL = getConnection().createStatement();
			iRowsInserted = stmtSQL.executeUpdate(strSQL);
		}
		catch(Exception ex) {ex.getMessage();}
		return iRowsInserted;
	}
	
	//public int getExec_Insert_Update_Delete_SQL(String strSQL, Object[] arrName, Object[] arrType, Object[] arrVal) throws SQLException, ClassNotFoundException
	public int getExec_Insert_Update_Delete_SQL(String strSQL, String[] arrVal) throws SQLException, ClassNotFoundException
	{
		int iRowsUpdated = 0;
		PreparedStatement prep = null;
		try
		{
			prep = getConnection().prepareStatement(strSQL); //, ResultSet.TYPE_SCROLL_SENSITIVE , ResultSet.CONCUR_UPDATABLE);
			int c = 1;
			for(int i = 0; i<= arrVal.length; i++)
			{
				if(c <= arrVal.length) 
				{
					if(c == 4) 
					{
						prep.setString(c, java.sql.Date.valueOf(arrVal[c - 1]).toString());
					}
					else
					{
						prep.setString(c, arrVal[c - 1]);
					}
					c++;
				}
				//prep.setString(c, arrVal[c - 1]); c++;
			}
			iRowsUpdated = prep.executeUpdate(strSQL);
		}
		catch(Exception ex) {ex.getMessage();}
		return iRowsUpdated;
	}
	
	public int getExec_Insert_Update_Delete_SQL(String strSQL) throws SQLException, ClassNotFoundException
	{
		int iRowsUpdated = 0;
		try
		{
			PreparedStatement prep = getConnection().prepareStatement(strSQL); //, ResultSet.TYPE_SCROLL_SENSITIVE , ResultSet.CONCUR_UPDATABLE);
			iRowsUpdated = prep.executeUpdate(strSQL);
		}
		catch(Exception ex) {ex.getMessage();}
		return iRowsUpdated;
	}
	
	public ResultSet getData(String strSQL) throws SQLException, ClassNotFoundException
	{
		ResultSet rs = null;
		try
		{
			//PreparedStatement prep = getConnection().prepareStatement(strSQL);
			PreparedStatement prep = getConnection().prepareStatement(strSQL, ResultSet.TYPE_SCROLL_SENSITIVE , ResultSet.CONCUR_UPDATABLE);
			rs = prep.executeQuery();
		//	rs = prep.getMaxRows();
		}
		catch(Exception ex) {ex.getMessage();}
		return rs;
	}
	
	public int getRowCount(ResultSet rs)
	{
		int iCount = 0;
		try {
		    while(rs.next()){
		    	//rs.last(); iCount = rs.getRow(); rs.beforeFirst();
		    	iCount++;
		    }
		}
		catch(Exception ex) { ;}
		return iCount;
	}
	
}
