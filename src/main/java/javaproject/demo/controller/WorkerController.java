package javaproject.demo.controller;

import javaproject.demo.entities.Worker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javaproject.demo.service.worker.impls.WorkerService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/workers")
public class WorkerController {
    private final WorkerService workerService;

    @PostMapping
    public ResponseEntity<Worker> create(@RequestBody Worker worker) {
        return ResponseEntity.ok(workerService.create(worker));
    }
    @GetMapping
    public ResponseEntity<List<Worker>> getAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                  @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok(workerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getById(@PathVariable Long id) {
        return ResponseEntity.ok(workerService.getById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Worker> update(@PathVariable Long id, @RequestBody Worker worker){
        return ResponseEntity.ok(workerService.update(id, worker));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        workerService.delete(id);
        return ResponseEntity.ok("Successfully deleted");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> exceptionHandling() {
        return ResponseEntity.badRequest().build();
    }
    
}
