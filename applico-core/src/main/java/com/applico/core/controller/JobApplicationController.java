package com.applico.core.controller;

import com.applico.core.dto.JobApplicationIn;
import com.applico.core.dto.JobApplicationOut;
import com.applico.core.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.applico.core.controller.statics.JobApplicationApis.BASE_URL;
import static com.applico.core.controller.statics.JobApplicationApis.BY_ID;


@RestController
@RequestMapping(BASE_URL)
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<JobApplicationOut>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(BY_ID)
    public ResponseEntity<JobApplicationOut> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<JobApplicationOut> create(@RequestBody @Valid JobApplicationIn in) {
        JobApplicationOut created = service.create(in);
        return ResponseEntity
                .created(URI.create(BASE_URL + "/" + created.getId()))
                .body(created);
    }

    @PutMapping(BY_ID)
    public ResponseEntity<JobApplicationOut> update(@PathVariable("id") Integer id, @RequestBody @Valid JobApplicationIn in) {
        return ResponseEntity.ok(service.update(id, in));
    }

    @DeleteMapping(BY_ID)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
