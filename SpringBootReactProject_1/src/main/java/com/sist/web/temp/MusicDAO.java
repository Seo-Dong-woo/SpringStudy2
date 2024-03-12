package com.sist.web.temp;

import java.sql.*;

public class MusicDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:mysql://localhost:3306/mydb?autoReconnection=true";
	
	public MusicDAO()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception ex) {}
	}
	
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL, "root", "root");
		}catch(Exception ex) {}
	}
	
	public void disConnection()
	{
		try
		{
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		}catch(Exception ex) {}
	}
	
	public void musicInsert(MusicVO vo)
	{
		try
		{
			getConnection();
			String sql="INSERT INTO music(title, singer, album, poster, state, idcrement) "
					+ "VALUES(?, ?, ?, ?, ?, ?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getSinger());
			ps.setString(3, vo.getAlbum());
			ps.setString(4, vo.getPoster());
			ps.setString(5, vo.getState());
			ps.setInt(6, vo.getIdcrement());
			ps.executeUpdate();
		}catch(Exception ex)
		
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
}
