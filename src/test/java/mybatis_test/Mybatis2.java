package mybatis_test;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import cn.blue.mapper.EmployeeMapper;

/**
 * 
 * 
 * 
 * @author JAVA
 *
 */
public class Mybatis2 {

	MapperScannerConfigurer msc;
	SqlSessionFactoryBean ssfb;
	
	public ClassPathXmlApplicationContext ac;
	public EmployeeMapper empMapper;
	
	
	
	@Before
	public void doBefore() {
		ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		empMapper = ac.getBean("employeeMapper", EmployeeMapper.class);
	}
	@Test
	public void getConnection() throws SQLException {
		BasicDataSource ds = ac.getBean(
			"dataSource", BasicDataSource.class);
		Connection conn = ds.getConnection();
		System.out.println(conn);
	}
	
	@Test
	public void test2() throws IOException{
		
	}
}
