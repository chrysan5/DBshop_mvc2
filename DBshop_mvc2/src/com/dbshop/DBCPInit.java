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
		// 서버가 실행되면 자동으로 초기화 시켜주기 위해서 호출됨
		loadJDBCDriver();
		initConnectionPool();
	}
	
	private void loadJDBCDriver() {
		String driverClass = getInitParameter("jdbcDriver");
		// "com.mysql.jdbc.Driver"를 가져옴
		try {
			Class.forName(driverClass);// 값이 없으면 Class.forName(null);
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
			
			//ConnectionFactory 생성
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl, username, password);			
			// 클래스 명에 Factory가 들어가면 공장 처럼 찍어낸다. 자동으로 생성된다.
			
			//PoolableConnection을 생성하는 Factory 생성
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			// DBCP는 커넥션 풀에 커넥션을 보관할때 PoolableConnection을 사용한다.
			// 이 클래스는 내부적으로 실제 커넥션을 담고 있으며, 커넥션 풀을 관히하느데 필요한 기능을 제공.
			// ex) close() 메서드를 실행하면 실제로 메모리에서 날리는게 아니고, pool로 반환함.
			
			// 문제 없는지 테스트 하는 코드
			poolableConnFactory.setValidationQuery("select 1");
			
			//커넥션 풀의 설정 정보
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			//유휴 커넥션 검사 주기
			
			poolConfig.setTestWhileIdle(true);
			// 풀에 보관중인 커넥션이 유한지 검사 할지 여부
			
			poolConfig.setMinIdle(4);
			// 시작했을때 만들어 놓을 최소 커넥션 개수
			
			poolConfig.setMaxTotal(50);
			// 전체 접속 가능 개수
			
			//커넥션 풀 생성 및 연결
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory, poolConfig);
			poolableConnFactory.setPool(connectionPool);
			
			//커넥션 풀을 제공하는 JDBC 드라이버를 등록
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			
			//커넥션 풀 드라이버에 생성한 커넥션 풀을 등록
			PoolingDriver driver = (PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			
			String poolName = getInitParameter("poolName");//xml에 미리 써놨음
			driver.registerPool(poolName, connectionPool);
			// poolName = "pool" 값이 있음
		}catch (Exception e) {
			
		}
	}

}
