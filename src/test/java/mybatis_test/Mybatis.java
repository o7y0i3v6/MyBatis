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
		//2����ȡsqlSessionʵ�� ��ֱ��ִ���Ѿ�ӳ���sql���
		try(SqlSession openSession = sqlSessionFactory.openSession();){
			//��spring����ʱ���ײ���Ȼ�ǵ���SqlSession��ʵ����
			Employee employee = (Employee)openSession.selectOne("cn.blue.mapper111.E",1);
			System.out.println(employee);
		} 
	}

	/**
	 * ���෽���ᴴ��SqlSessionFactory����
	 * @return new SqlSessionFactoryBuilder().build(inputStream);
	 * @throws IOException
	 */
	public SqlSessionFactory getSqlSessionFactory() throws IOException{
		//"mybatis-config.xml"�����sql xml
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void test1() throws IOException{
		//1 ��ȡsqlSessionFactory���� 
		//Ӧ��ʹ������д�������ﲢû�е�����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//2 ��ȡsqlSession����
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//3 ��ȡ�ӿڵ�ʵ�������
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		System.out.println(mapper);
		Employee employee = mapper.getEmpById(1);
		System.out.println(employee);
	}

	@Test
	public void test2() throws IOException{
		//1 ��ȡsqlSessionFactory
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		try(SqlSession sqlSession = sqlSessionFactory.openSession();){
			EmployeeMapper mapper = 
					sqlSession.getMapper(EmployeeMapper.class);
			//�������� ���вι��촫ֵ��ֻҪ��ֵ�Ϳ��ԣ�Ҳ������get set������
			Employee employee = new Employee(null, "1", "2", "3");
			mapper.addEmp(employee);
			System.out.println(employee.getId());
			sqlSession.commit();	
		} 
	}
	@Test
	public void test3() throws IOException{
		//1 ��ȡsqlSessionFactory
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
	 *     ��װ���ݣ�����һ�ᴫ��
	 *  Employee employee = new Employee(2, "3", "2", "3");
	 *     �÷�װ���ݵĶ���������ݿ�
	 * boolean updateEmp = mapper.updateEmp(employee);  
	 *     �������ֵ
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
