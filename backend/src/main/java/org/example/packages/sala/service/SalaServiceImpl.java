package org.example.packages.sala.service;

import lombok.AllArgsConstructor;
import org.example.packages.sala.Sala;
import org.example.packages.sala.SalaRepository;
import org.example.packages.sala.payload.SalaRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SalaServiceImpl implements SalaService {

    private SalaRepository salaRepository;

    @Override
    public void create(SalaRequest salaRequest) {
        Sala sala = new Sala();

        sala.setNumeroSala(sala.getNumeroSala());

        salaRepository.save(sala);
    }
}
