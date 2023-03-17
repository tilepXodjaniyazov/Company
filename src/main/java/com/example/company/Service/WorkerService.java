package com.example.company.Service;

import com.example.company.Controller.Result;
import com.example.company.Dto.WorkerDto;
import com.example.company.Entity.Address;
import com.example.company.Entity.Department;
import com.example.company.Entity.Worker;
import com.example.company.Repository.DepartmentRepository;
import com.example.company.Repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public Result getWorker() {
        List<Worker> all = workerRepository.findAll();
        return new Result("Topildi",true,all);
    }
    public Result postWorker(WorkerDto workerDto) {
        Worker worker = new Worker();
        Department department = departmentRepository.findById(workerDto.getDepartmentId()).get();
        Address address = new Address(null, workerDto.getStreet(), workerDto.getHomeNumber());
        worker.setName(worker.getName());
        worker.setAddress(address);
        worker.setDepartment(department);
        workerRepository.save(worker);
        return new Result("Worker o'zgardi",true);
    }

    public Result putWorker(Integer id, WorkerDto workerDto) {
        Optional<Worker> byId = workerRepository.findById(id);
        if (byId.isPresent()) {
            Department department = departmentRepository.findById(workerDto.getDepartmentId()).get();
            Address address = new Address(null, workerDto.getStreet(), workerDto.getHomeNumber());
            Worker worker = byId.get();
            worker.setName(worker.getName());
            worker.setAddress(address);
            worker.setDepartment(department);
            workerRepository.save(worker);
            return new Result("Worker o'zgardi",true);
        }
        return new Result("Worker topilmadi",false);
    }
    public Result deleteWorker(Integer id) {
        try {
            if (!workerRepository.existsById(id)) {
                return new Result("Bunday worker topilmadi",false);
            }
            workerRepository.deleteById(id);
            return new Result("Ochirildi",true);
        } catch (Exception e) {
            return new Result("Error",false);
        }
    }

}
