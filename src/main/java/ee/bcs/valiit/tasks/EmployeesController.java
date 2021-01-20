package ee.bcs.valiit.tasks;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeesController {

    List<Employees> employeesList = new ArrayList<>();

    @GetMapping("employee/{name}/{address}")
    public Employees empl(@PathVariable("name") String name, @PathVariable("address") String address) {
        Employees employees = new Employees();
        employees.setName(name);
        employees.setAddress(address);
        return employees;
    }

    @PostMapping("test")
    public Employees test2(@RequestBody Employees employees) {
        return employees;
    }

    @PostMapping("employee")
    public void addEmployee(@RequestBody Employees employees) {
        employeesList.add(employees);
    }

    @GetMapping("employee/")
    public List<Employees> printEmployees() {
        return employeesList;
    }

    @GetMapping("employee/{id}")
    public Employees printEmployees(@PathVariable("id") int id) {
        return employeesList.get(id);
    }

    @PutMapping("employee/{id}")
    public void updateEmployee(@PathVariable("id") int id, @RequestBody Employees employees) {
        employeesList.set(id, employees);
    }

    @DeleteMapping("employee/{id}")
    public void deleteEmployee(@PathVariable("id") int id) {
        employeesList.remove(id);
    }


}