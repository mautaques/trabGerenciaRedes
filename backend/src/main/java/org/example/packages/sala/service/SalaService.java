package org.example.packages.sala.service;

import org.example.packages.sala.Sala;
import org.example.packages.sala.payload.SalaRequest;

import java.util.List;

public interface SalaService {

    void create(SalaRequest salaRequest);

    List<Sala> salas();
}
