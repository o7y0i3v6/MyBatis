package cn.blue.mapper;

import java.util.Map;
/**
 * ע��Ľӿڣ�
 * ������ӳ���sql������������
 *
 */
import cn.blue.bean.Employee;

public interface EmployeeMapper {
	public Employee getEmpById(Integer uidTest);
	public Employee getEmpByMap(Map<String, Object> map);
	
	public void addEmp(Employee employee);
	public boolean updateEmp(Employee employee);
	public void deleteEmpById(Integer id);

	
}
