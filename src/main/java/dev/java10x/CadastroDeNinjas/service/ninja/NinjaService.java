package dev.java10x.CadastroDeNinjas.service.ninja;

import dev.java10x.CadastroDeNinjas.entity.ninja.Ninja;
import dev.java10x.CadastroDeNinjas.model.ninja.INinjaMapper;
import dev.java10x.CadastroDeNinjas.repository.ninja.NinjaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NinjaService implements INinjaService {

    private final NinjaRepository repository;
    private final INinjaMapper mapper;

    public NinjaService(NinjaRepository repository, INinjaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Ninja create(String name, String email){
       Ninja ninja = new Ninja();
       ninja.setId(UUID.randomUUID().toString());
       ninja.setName(name);
       ninja.setEmail(email);


       return ninja;
   }

}
