package mybatis_test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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
			//与spring整合时，底层依然是调用SqlSession的实例。
			Employee employee = (Employee)openSession.selectOne("cn.blue.mapper111.E",1);
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
			//创建对象 用有参构造传值，只要传值就可以，也可以用get set方法。
			Employee employee = new Employee(null, "1", "2", "3");
			mapper.addEmp(employee);
			System.out.println(employee.getId());
			sqlSession.commit();	
		} 
	}
	@Test
	public void test3() throws IOException{
		//1 获取sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		try(SqlSession sqlSession = sqlSessionFactory.openSession();){
			
			EmployeeMapper mapper = 
					sqlSession.getMapper(EmployeeMapper.class);
			Map<String,Object> map = new HashMap<>();
			map.put("id",1);
			//map.put("lastName","Tom");
			Employee employee = mapper.getEmpByMap(map);
			System.out.println(employee);
			//mapper.addEmp(employee);
			//sqlSession.commit();	
	
			
		} 		
	}

	/*
	 *     封装数据，方便一会传参
	 *  Employee employee = new Employee(2, "3", "2", "3");
	 *     用封装数据的对象更新数据库
	 * boolean updateEmp = mapper.updateEmp(employee);  
	 *     输出返回值
	 *  System.out.println(updateEmp);
	 * 
	 */
	@Test
	public void test4() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		try(SqlSession sqlSession = sqlSessionFactory.openSession();){
			EmployeeMapper mapper =  
					sqlSession.getMapper(EmployeeMapper.class);
			Employee employee = new Employee(2, "1", "2", "3");
			boolean updateEmp = mapper.updateEmp(employee);
			System.out.println(updateEmp);
			System.out.println(employee.getId());
			sqlSession.commit();	
		} 
	}
}
