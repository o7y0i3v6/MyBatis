package mybatis_test;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

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

	/**
	 * 该类方法会创建SqlSessionFactory对象
	 * @return new SqlSessionFactoryBuilder().build(inputStream);
	 * @throws IOException
	 */
	public SqlSessionFactory getSqlSessionFactory() throws IOException{
		//"mybatis-config.xml"会关联sql xml
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void test1() throws IOException{
		//1 获取sqlSessionFactory对象 
		//应该使用这种写法但这里并没有调出来
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//2 获取sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//3 获取接口的实现类对象
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		System.out.println(mapper);
		Employee employee = mapper.getEmpById(1);
		System.out.println(employee);
	}

	@Test
	public void test2() throws IOException{
		//1 获取sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		try(SqlSession sqlSession = sqlSessionFactory.openSession();){
			EmployeeMapper mapper = 
					sqlSession.getMapper(EmployeeMapper.class);
			Employee employee = new Employee(null, "1", "2", "3");
			mapper.addEmp(employee);
			System.out.println(employee.getId());
			sqlSession.commit();
			
			/*
			 * 封装数据，方便一会传参
			 * Employee employee = new Employee(2, "3", "2", "3");
			 * 用封装数据的对象更新数据库
			 * boolean updateEmp = mapper.updateEmp(employee);  
			 * 输出返回值
			 * System.out.println(updateEmp);
			 * 
			 */
		} 

	}	
}
