package com.demo.streamsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class StreamsApiApplication {

	static List<Employee> employees = new ArrayList<>();
	static {
		employees.add(new Employee("Pasan", "Rathnayake", 75000.0, List.of("Project 1", "Project 2")));
		employees.add(new Employee("Nihal", "Bandara", 100000.0, List.of("Project 3", "Project 1")));
		employees.add(new Employee("Ruwan", "Silva", 55000.0, List.of("Project 3", "Project 4")));
	}

	public static void main(String[] args) {

		//foreach
		employees.stream()
				.forEach(employee -> System.out.println(employee));

		//map
		//collect - toList
		List<Employee> increasedSalary = employees.stream()
				.map(employee -> new Employee(
						employee.getFirstName(),
						employee.getLastName(),
						employee.getSalary() * 1.10,
						employee.getProjects()
				))
				.collect(Collectors.toList());
		System.out.println(increasedSalary);

		//filter
		//map
		//collect - toList
		List<Employee> filterEmployee = employees.stream()
				.filter(employee -> employee.getSalary() > 80000.0)
				.map(employee -> new Employee(
						employee.getFirstName(),
						employee.getLastName(),
						employee.getSalary() * 1.10,
						employee.getProjects()
				))
				.collect(Collectors.toList());
		System.out.println(filterEmployee);

		//filter
		//map
		//findFirst - orElse
		Employee firstEmployee = employees.stream()
				.filter(employee -> employee.getSalary() > 80000.0)
				.map(employee -> new Employee(
						employee.getFirstName(),
						employee.getLastName(),
						employee.getSalary() * 1.10,
						employee.getProjects()
				))
				.findFirst()
						.orElse(null);
		System.out.println(firstEmployee);

		//map
		//flatMap
		//flatMap - joining
		String projects = employees.stream()
				.map(employee -> employee.getProjects())
				.flatMap(strings -> strings.stream())
				.collect(Collectors.joining(","));
		System.out.println(projects);

		//skip
		//limit
		//collect - toList
		List<Employee> shortCircuit = employees.stream()
				.skip(1)
				.limit(1)
				.collect(Collectors.toList());
		System.out.println(shortCircuit);

		//finite data
		Stream.generate(Math::random)
				.limit(5)
				.forEach(value -> System.out.println(value));

		//sorted
		//collect - toList
		List<Employee> sortedEmployees = employees.stream()
				.sorted(((o1, o2) -> o1.getFirstName().compareToIgnoreCase(o2.getFirstName())))
				.collect(Collectors.toList());
		System.out.println(sortedEmployees);

		//max or min
		employees.stream()
				.max(Comparator.comparing(Employee::getSalary))
				.orElseThrow(NoSuchElementException::new);

		//map
		//reduce
		Double totalSalary = employees.stream()
				.map(employee -> employee.getSalary())
				.reduce(0.0, Double::sum);
		System.out.println(totalSalary);
	}
}
