package com.example.zeeno.postgres.domains.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping("/")
    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable final Long id ) {
        return employeeRepository.getById(id);
    }

    @PostMapping("/create")
    public Employee createEmployee(@RequestBody final Employee employee ) {
        return employeeRepository.save(employee);
    }


    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable final Long id, @RequestBody final Employee updatedEmployee ) {
        Employee employee = employeeRepository.getById(id);

        employee.setEmail(updatedEmployee.getEmail());
        employee.setFirstname(updatedEmployee.getFirstname());
        employee.setLastname(updatedEmployee.getLastname());

        return employeeRepository.save(employee);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteEmployee(@RequestBody final Employee employee ) {
        employeeRepository.delete(employee);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
