package dev.java10x.CadastroDeNinjas.service.ninja;

import dev.java10x.CadastroDeNinjas.model.ninja.NinjaRequest;
import dev.java10x.CadastroDeNinjas.model.ninja.NinjaResponse;

import java.util.List;

public interface INinjaService {
    NinjaResponse create(NinjaRequest request);

    List<NinjaResponse> getAll();


}
