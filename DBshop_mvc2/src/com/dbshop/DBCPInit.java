package com.dbshop;

import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;


@WebServlet("/DBCPInit")
public class DBCPInit extends HttpServlet {
	@Override
	public void init() throws ServletException {
		// ������ ����Ǹ� �ڵ����� �ʱ�ȭ �����ֱ� ���ؼ� ȣ���
		loadJDBCDriver();
		initConnectionPool();
	}
	
	private void loadJDBCDriver() {
		String driverClass = getInitParameter("jdbcDriver");
		// "com.mysql.jdbc.Driver"�� ������
		try {
			Class.forName(driverClass);// ���� ������ Class.forName(null);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// ClassNotFoundException => can not find jdbc Driver...
		}
    }
	
	private void initConnectionPool(){
		try{
			String jdbcUrl = getInitParameter("jdbcUrl");
			String username = getInitParameter("dbUser");
			String password = getInitParameter("dbPass");
			
			//ConnectionFactory ����
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl, username, password);			
			// Ŭ���� �� Factory�� ���� ���� ó�� ����. �ڵ����� �����ȴ�.
			
			//PoolableConnection�� �����ϴ� Factory ����
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			// DBCP�� Ŀ�ؼ� Ǯ�� Ŀ�ؼ��� �����Ҷ� PoolableConnection�� ����Ѵ�.
			// �� Ŭ������ ���������� ���� Ŀ�ؼ��� ��� ������, Ŀ�ؼ� Ǯ�� �����ϴ��� �ʿ��� ����� ����.
			// ex) close() �޼��带 �����ϸ� ������ �޸𸮿��� �����°� �ƴϰ�, pool�� ��ȯ��.
			
			// ���� ������ �׽�Ʈ �ϴ� �ڵ�
			poolableConnFactory.setValidationQuery("select 1");
			
			//Ŀ�ؼ� Ǯ�� ���� ����
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			//���� Ŀ�ؼ� �˻� �ֱ�
			
			poolConfig.setTestWhileIdle(true);
			// Ǯ�� �������� Ŀ�ؼ��� ������ �˻� ���� ����
			
			poolConfig.setMinIdle(4);
			// ���������� ����� ���� �ּ� Ŀ�ؼ� ����
			
			poolConfig.setMaxTotal(50);
			// ��ü ���� ���� ����
			
			//Ŀ�ؼ� Ǯ ���� �� ����
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory, poolConfig);
			poolableConnFactory.setPool(connectionPool);
			
			//Ŀ�ؼ� Ǯ�� �����ϴ� JDBC ����̹��� ���
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			
			//Ŀ�ؼ� Ǯ ����̹��� ������ Ŀ�ؼ� Ǯ�� ���
			PoolingDriver driver = (PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			
			String poolName = getInitParameter("poolName");//xml�� �̸� �����
			driver.registerPool(poolName, connectionPool);
			// poolName = "pool" ���� ����
		}catch (Exception e) {
			
		}
	}

}
