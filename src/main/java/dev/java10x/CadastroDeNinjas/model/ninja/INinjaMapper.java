package dev.java10x.CadastroDeNinjas.model.ninja;


import dev.java10x.CadastroDeNinjas.entity.ninja.Ninja;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface INinjaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "missions", ignore = true)
    Ninja toEntity(NinjaRequest request);

    @Mapping(target = "missions", source = "missions.name")
    NinjaResponse toResponse(Ninja ninja);
}


