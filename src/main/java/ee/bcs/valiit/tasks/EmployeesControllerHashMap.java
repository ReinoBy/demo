package ee.bcs.valiit.tasks;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class EmployeesControllerHashMap {

    HashMap<Integer, Employees> employeesList = new HashMap<>();

    Integer sequence = 0;

    @PostMapping("employeeHash")
    public void addEmployee(@RequestBody Employees employees) {
        sequence++;
        employeesList.put(sequence,employees);
    }

    @GetMapping("employeeHash/")
    public HashMap<Integer,Employees> printEmployees() {
        return employeesList;
    }

    @GetMapping("employeeHash/{id}")
    public Employees printEmployees(@PathVariable("id") int id) {
        return employeesList.get(id);
    }

    @PutMapping("employeeHash/{id}")
    public void updateEmployee(@PathVariable("id") int id, @RequestBody Employees employees) {
        employeesList.replace(id, employees);
    }

    @DeleteMapping("employeeHash/{id}")
    public void deleteEmployee(@PathVariable("id") int id) {
        employeesList.remove(id);
    }


}