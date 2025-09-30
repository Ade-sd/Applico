package com.applico.core.controller;

import com.applico.core.controller.statics.CompanyApis;
import com.applico.core.dto.CompanyIn;
import com.applico.core.dto.CompanyOut;
import com.applico.core.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.applico.core.controller.statics.CompanyApis.BASE_URL;
import static com.applico.core.controller.statics.CompanyApis.BY_ID;

@RestController
@RequestMapping(BASE_URL)
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CompanyOut>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(BY_ID)
    public ResponseEntity<CompanyOut> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<CompanyOut> create(@RequestBody @Valid CompanyIn in) {
        CompanyOut created = service.create(in);
        return ResponseEntity
                .created(URI.create(CompanyApis.BASE_URL + "/" + created.getId()))
                .body(created);
    }

    @PutMapping(BY_ID)
    public ResponseEntity<CompanyOut> update(@PathVariable("id") Integer id, @RequestBody @Valid CompanyIn in) {
        return ResponseEntity.ok(service.update(id, in));
    }

    @DeleteMapping(BY_ID)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
