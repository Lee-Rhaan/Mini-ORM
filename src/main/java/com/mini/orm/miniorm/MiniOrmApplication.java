package com.mini.orm.miniorm;

import com.mini.orm.miniorm.model.Customer;
import java.lang.reflect.*;
import com.mini.orm.miniorm.util.MyCustomAnnotation;

public class MiniOrmApplication {

	public static void main(String[] args) {
		//Testing retrieval of methods
		Method[] customerClassMethods = Customer.class.getDeclaredMethods();
		for (Method method : customerClassMethods) {
			System.out.println("Method = " + method.getName());
		}

		System.out.println();
		System.out.println();

		//Testing retrieval of fields
		Field[] customerClassFields = Customer.class.getDeclaredFields();
		for (Field field : customerClassFields) {
			System.out.println("Field = " + field.getName());
			System.out.println(field.getName() + " return type = " + field.getType().getTypeName());
		}

		System.out.println();
		System.out.println();

		//Testing retrieval of class name
		//Will make this process dynamic. Like the componentScanner -> where it scans the package structure
		//in search for components.
		//Will try to implement a similar approach, but in this case I will search for
		// classes annotated with "MyCustomAnnotation"
		Class customerClass = Customer.class;
		System.out.println("Class Name = " + customerClass.getSimpleName()); //db table name
		//Will use this "annotation check" to determine if I should create a table based off of this class
		boolean containsMarkerAnnotation = customerClass.isAnnotationPresent(MyCustomAnnotation.class);
		System.out.println("My Custom Annotation Is Present = " + containsMarkerAnnotation);

	}

}
