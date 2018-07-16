package com.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.intRepoTrabajoActor;
import com.entidades.Actor;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class impRepoTrabajoActor implements intRepoTrabajoActor {

	public impRepoTrabajoActor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void metodoEjemplo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void metodoResultSetHIKARI() {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM sakila.actor";
		
		HikariDataSource datasource;
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl( "jdbc:mysql://localhost:3306/sakila" );
        config.setUsername( "usuario" );
        config.setPassword( "12345" );
        config.addDataSourceProperty("useSSL", false);
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        datasource = new HikariDataSource( config );
        
        try {
			con = datasource.getConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				System.out.println("Empleado id: "+rs.getInt("actor_id")+" Nombre: "+rs.getString("first_name")+" Apellido: "+rs.getString("last_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					rs.close();
					pst.close();
					con.close();
					datasource.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}

	@Override
	public void metodoResultSetDATASOURCE() {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM sakila.actor";
		
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName("localhost");
	    dataSource.setPortNumber(3306);
	    dataSource.setDatabaseName("sakila");
	    dataSource.setUser("usuario");
	    dataSource.setPassword("12345");
	    dataSource.setUseSSL(false);
	    
	    try {
			con = dataSource.getConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				System.out.println("Empleado id: "+rs.getInt("actor_id")+" Nombre: "+rs.getString("first_name")+" Apellido: "+rs.getString("last_name"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					rs.close();
					pst.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Override
	public void metodoResultSetJDBCTemplate() {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM sakila.actor";
		List<Map<String,Object>> listaActores = jdbctemplate.queryForList(sql);
		
		for(Map<String, Object> row : listaActores) {
			System.out.println("Empleado id: "+row.get("actor_id")+" Nombre: "+row.get("first_name")+" Apellido: "+row.get("last_name"));
		}
	}

	@Override
	public void truncateTable(String tabla) {
		// TODO Auto-generated method stub
	    String sql = "truncate table "+ tabla;  
	    jdbctemplate.execute(sql);
	    System.out.println("Tabla "+tabla+ " borrada y truncada");
	}

}
