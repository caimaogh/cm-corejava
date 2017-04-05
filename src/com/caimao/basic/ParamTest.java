/**
 * 
 */
package com.caimao.basic;

/**
 * <p>Title: ParamTest.java</p>
 * <p>Description: </p>
 * @author caimao
 *
 * @date 2017年4月5日 下午1:52:42
 * @version 1.0
 * 
 */
public class ParamTest {

	public static void main(String[] args) {
		/*
		 * Test1 一个方法不能修改一个基本数据类型的参数(数值型和布尔型)
		 */
		System.out.println("1:测试一个方法不能修改一个基本数据类型的参数(数值型和布尔型)");
		double percent = 10;
		System.out.println("Before percent="+percent);
		tripValue(percent);
		System.out.println("After percent="+percent);
		/*
		 * Test2一个方法能改变一个对象参数的状态
		 */
		System.out.println("2:测试一个方法能改变一个对象参数的状态");
		Employee tom = new Employee("Tom",5000.0,true);
		System.out.println("Before tom="+tom.getSalary());
		tripleSalary(tom);
		System.out.println("After tom="+tom.getSalary());
		/*
		 * Test3一个方法不能让对象参数引用一个新的状态
		 */
		System.out.println("3:测试一个方法不能让对象参数引用一个新的状态");
		Employee a = new Employee("Alex",5000.0,true);
		Employee b = new Employee("Lili",3000.0,false);
		System.out.println("Before swap a.name="+a.getName());
		System.out.println("Before swap b.name="+b.getName());
		swapTwoObject(a,b);
		System.out.println("After swap a.name="+a.getName());
		System.out.println("After swap b.name="+b.getName());
	}
	
	public static void tripValue(double percent){
		percent = 3*percent;
		System.out.println("End of method tripValue percent="+percent);
	}
	
	public static void tripleSalary(Employee x){
		x.raiseSalary(20);
		System.out.println("End of method tripleSalary Salary="+x.getSalary());
	}
	
	public static void swapTwoObject(Employee m,Employee n){
		System.out.println("Before in swap m.name="+m.getName());
		System.out.println("Before in swap n.name="+n.getName());
		Employee temp = m;
		m=n;
		n=temp;
		System.out.println("End in swap m.name="+m.getName());
		System.out.println("End in swap n.name="+n.getName());
	}

}

class Employee{
	private String name;
	private Double salary;
	private Boolean isMale;
	
	public Employee(String name,Double salary,Boolean isMale){
		this.name = name;
		this.salary = salary;
		this.isMale = isMale;
	}
	public void raiseSalary(double percent){
		double raise = salary*percent/100;
		salary += raise;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Boolean getIsMale() {
		return isMale;
	}
	public void setIsMale(Boolean isMale) {
		this.isMale = isMale;
	}
	
	
}
