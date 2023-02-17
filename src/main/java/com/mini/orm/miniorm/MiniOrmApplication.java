package com.mini.orm.miniorm;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import com.mini.orm.miniorm.util.MyCustomAnnotation;
import org.reflections.Reflections;

import static org.reflections.scanners.Scanners.*;

public class MiniOrmApplication {

	private static final String PACKAGE_STRUCTURE = MiniOrmApplication.class.getPackageName();

	public static void main(String[] args) {
		//Testing retrieval of methods
//		Method[] customerClassMethods = Customer.class.getDeclaredMethods();
//		for (Method method : customerClassMethods) {
//			System.out.println("Method = " + method.getName());
//		}
//
//		System.out.println();
//		System.out.println();
//
//		//Testing retrieval of fields
//		Field[] customerClassFields = Customer.class.getDeclaredFields();
//		for (Field field : customerClassFields) {
//			System.out.println("Field = " + field.getName());
//			System.out.println(field.getName() + " return type = " + field.getType().getTypeName());
//		}
//
//		System.out.println();
//		System.out.println();
//
//		//Testing retrieval of class name
//		//Will make this process dynamic. Like the componentScanner -> where it scans the package structure
//		//in search for components.
//		//Will try to implement a similar approach, but in this case I will search for
//		// classes annotated with "MyCustomAnnotation"
//		Class customerClass = Customer.class;
//		System.out.println("Class Name = " + customerClass.getSimpleName()); //db table name
//		//Will use this "annotation check" to determine if I should create a table based off of this class
//		boolean containsMarkerAnnotation = customerClass.isAnnotationPresent(MyCustomAnnotation.class);
//		System.out.println("My Custom Annotation Is Present = " + containsMarkerAnnotation);

		//Scan package structure provided in constructor
		Reflections reflections = new Reflections(PACKAGE_STRUCTURE);
		//Create a Set that contains all the classes annotated with my custom annotation
		Set<Class<?>> annotated = reflections.get(SubTypes.of(TypesAnnotated.with(MyCustomAnnotation.class)).asClass());

		for (Class c : annotated) {
			System.out.println(c.getPackageName());
			System.out.println(c.getName());//db name
			retrieveClassFields(c.getDeclaredFields());
			System.out.println();
		}

	}

	/**
	 * Manipulate field name to satisfy MySQL column name naming convention
	 * Example: testData
	 * After Manipulation: test_data
	 * @param fields
	 */
	private static void retrieveClassFields(Field[] fields) {
		for (Field field : fields) {
			String columnName = createDBColumnName(field.getName());
			System.out.println(columnName);
			String fieldDataType = retrieveDBColumnDataTypes(field.getType().getTypeName());
			System.out.println(fieldDataType);
		}
	}

	/**
	 * TODO: the return values should be added to a list for further manipulation
	 * @param field
	 * @return
	 */
	public static String createDBColumnName(String field) {
		String columnName = "";
		for (char character : field.toCharArray()) {
			if (Character.isUpperCase(character)) {
				columnName = columnName + "_" + String.valueOf(character).toLowerCase();
			} else {
				columnName = columnName + character;
			}
		}
		return columnName;
	}

	/**
	 * Extract data type of class fields in order to use it as database column data type identifiers.
	 * TODO: this is just a prototype implementation (refactor it later)
	 * @param dataType
	 * @return
	 */
	private static String retrieveDBColumnDataTypes(String dataType) {
		if (dataType.contains(".")) {
			String alteredDataType = dataType.substring(dataType.lastIndexOf('.') + 1).toLowerCase();
			return convertLongToBigInt(alteredDataType);
		} else {
			return convertLongToBigInt(dataType);
		}
	}

	/**
	 * Convert "long" string to "bigint" string - to be used as database column data type identifier
	 * @param dataType
	 * @return
	 */
	private static String convertLongToBigInt(String dataType) {
		if ("long".equals(dataType)) {
			return "bigint";
		}
		return dataType;
	}

	/**
	 * Converts Application Main Class Name to database name (by following db naming conventions)
	 * @return database name
	 */
	public static String retrieveDBName() {
		String mainClassName = MiniOrmApplication.class.getSimpleName();
		String modifiedMainClassName = Character.toLowerCase(mainClassName.charAt(0)) + mainClassName.substring(1);
		return createDBColumnName(modifiedMainClassName);
	}

}
