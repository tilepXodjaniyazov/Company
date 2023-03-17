package com.example.company.Controller;

import com.example.company.Dto.WorkerDto;
import com.example.company.Entity.Worker;
import com.example.company.Service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    /**
     * Result keladi
     * @return List<Worker></>
     */
    @GetMapping
    public ResponseEntity<?> getWorker() {
       Result result = workerService.getWorker();
       if (result.getActive()) {
           return ResponseEntity.ok().body(result);
       }
       return ResponseEntity.status(204).body(result);
    }

    @PostMapping
    public ResponseEntity<?> postWorker(@RequestBody WorkerDto workerDto) {
        Result result = workerService.postWorker(workerDto);
        if (result.getActive()) {
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.status(204).body(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> putWorker(@PathVariable Integer id, @RequestBody WorkerDto workerDto) {
        Result result = workerService.putWorker(id,workerDto);
        if (result.getActive()) {
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.status(204).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorker(@PathVariable Integer id)  {
        Result result = workerService.deleteWorker(id);
        if (result.getActive()) {
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.status(204).body(result);
    }

}
