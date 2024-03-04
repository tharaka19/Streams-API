package com.demo.streamsapi;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Employee {

    private String firstName;
    private String lastName;
    private Double salary;
    private List<String> projects;
}
