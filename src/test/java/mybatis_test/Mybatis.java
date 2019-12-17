package mybatis_test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.blue.bean.Employee;
import cn.blue.mapper.EmployeeMapper;

public class Mybatis {

	@Test
	public void test0() throws IOException{
		String resource = "mybatis-config.xml";
		InputStream inputStream = 
				Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory =
				new SqlSessionFactoryBuilder().build(inputStream);
		//2、获取sqlSession实例 能直接执行已经映射的sql语句
		try(SqlSession openSession = sqlSessionFactory.openSession();){
			Employee employee = openSession.selectOne("CBBESelectEmp",1);
			System.out.println(employee);
		} 
	}
	@Test
	public void test1() throws IOException{
		//1 获取sqlSessionFactory对象 
		//getSqlSessionFactory();
		//SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) new SqlSessionFactoryBuilder();
		//2 获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		//3 获取接口的实现类对象
		EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
		Employee employee = mapper.getEmpById(1);
		System.out.println(employee);
	}
	
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
