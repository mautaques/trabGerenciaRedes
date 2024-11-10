package org.example.packages.sala;

import lombok.AllArgsConstructor;
import org.example.packages.sala.payload.SalaRequest;
import org.example.packages.sala.service.SalaService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sala")
@AllArgsConstructor
public class SalaController {
    private SalaService salaService;

    public void create(@RequestBody SalaRequest salaRequest) {

    }
}
