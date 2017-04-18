package com.kimby.clinica.web.DAO;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;

public class DbAccess implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String JDBC_DRIVER;
	private String DB_URL;
	private String USER;
	private String PASSWORD;
	private Connection conn = null;

	public DbAccess() throws JAXBException
	{
		this.JDBC_DRIVER = "com.mysql.jdbc.Driver";
		this.DB_URL = "jdbc:mysql://localhost:3306/pos_graduacao?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		this.USER = "root";
		this.PASSWORD = "root";
	}

	public DbAccess(String driver, String url, String user, String pass)
	{
		this.JDBC_DRIVER = driver;
		this.DB_URL = url;
		this.USER = user;
		this.PASSWORD = pass;
	}

	private void open() throws SQLException, NamingException, ClassNotFoundException, JAXBException
	{
		if (this.conn == null || this.conn.isClosed())
		{
			Class.forName(this.JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			conn.setAutoCommit(false);
		}
	}

	private void setParameters(PreparedStatement ps, Object[] data) throws SQLException
	{
		for (int i = 0; i < data.length; i++)
		{
			Object parameterObj = data[i];
			int parameterIndex = (i + 1);

			if (parameterObj == null)
			{
				ps.setNull(parameterIndex, java.sql.Types.OTHER);
			}
			else if (parameterObj instanceof Byte)
			{
				ps.setInt(parameterIndex, ((Byte) parameterObj).intValue());
			}
			else if (parameterObj instanceof String)
			{
				ps.setString(parameterIndex, (String) parameterObj);
			}
			else if (parameterObj instanceof BigDecimal)
			{
				ps.setBigDecimal(parameterIndex, (BigDecimal) parameterObj);
			}
			else if (parameterObj instanceof Short)
			{
				ps.setShort(parameterIndex, ((Short) parameterObj).shortValue());
			}
			else if (parameterObj instanceof Integer)
			{
				ps.setInt(parameterIndex, ((Integer) parameterObj).intValue());
			}
			else if (parameterObj instanceof Long)
			{
				ps.setLong(parameterIndex, ((Long) parameterObj).longValue());
			}
			else if (parameterObj instanceof Float)
			{
				ps.setFloat(parameterIndex, ((Float) parameterObj).floatValue());
			}
			else if (parameterObj instanceof Double)
			{
				ps.setDouble(parameterIndex, ((Double) parameterObj).doubleValue());
			}
			else if (parameterObj instanceof byte[])
			{
				ps.setBytes(parameterIndex, (byte[]) parameterObj);
			}
			else if (parameterObj instanceof java.sql.Date)
			{
				ps.setDate(parameterIndex, (java.sql.Date) parameterObj);
			}
			else if (parameterObj instanceof Time)
			{
				ps.setTime(parameterIndex, (Time) parameterObj);
			}
			else if (parameterObj instanceof Timestamp)
			{
				ps.setTimestamp(parameterIndex, (Timestamp) parameterObj);
			}
			else if (parameterObj instanceof Boolean)
			{
				ps.setBoolean(parameterIndex, ((Boolean) parameterObj).booleanValue());
			}
			else if (parameterObj instanceof InputStream)
			{
				ps.setBinaryStream(parameterIndex, (InputStream) parameterObj, -1);
			}
			else if (parameterObj instanceof java.sql.Blob)
			{
				ps.setBlob(parameterIndex, (java.sql.Blob) parameterObj);
			}
			else if (parameterObj instanceof java.sql.Clob)
			{
				ps.setClob(parameterIndex, (java.sql.Clob) parameterObj);
			}
			else if (parameterObj instanceof java.util.Date)
			{
				ps.setTimestamp(parameterIndex, new Timestamp(((java.util.Date) parameterObj).getTime()));
			}
			else if (parameterObj instanceof BigInteger)
			{
				ps.setString(parameterIndex, parameterObj.toString());
			}
		}
	}

	public void close() throws SQLException
	{
		if (this.conn != null && !this.conn.isClosed())
		{
			this.conn.close();
		}
	}

	public int ExecuteCommand(String query) throws Exception
	{
		this.open();

		Statement stat = conn.createStatement();

		return stat.executeUpdate(query);
	}

	public int ExecuteCommand(String query, Object... data) throws Exception
	{
		this.open();

		PreparedStatement ps = conn.prepareStatement(query);

		this.setParameters(ps, data);

		return ps.executeUpdate();
	}

	public ResultSet ExecuteQuery(String query) throws Exception
	{
		this.open();

		Statement stat = conn.createStatement();

		return stat.executeQuery(query);
	}

	public ResultSet ExecuteQuery(String query, Object... data) throws Exception
	{
		this.open();

		PreparedStatement ps = conn.prepareStatement(query);

		this.setParameters(ps, data);

		return ps.executeQuery();
	}
}
