package cn.blue.mapper;

import cn.blue.bean.Employee;

public interface EmployeeMapper {
	public Employee getEmpById(Integer uidTest);
	
	public void addEmp(Employee employee);
	public void updateEmp(Employee employee);
	public void deleteEmpById(Integer id);
}
