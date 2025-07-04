package dev.java10x.CadastroDeNinjas.model.ninja;

import dev.java10x.CadastroDeNinjas.entity.ninja.Ninja;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface INinjaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "missions", ignore = true)
        // Será tratado no service
    Ninja toEntity(NinjaRequest request);

    NinjaResponse toResponse(Ninja ninja);
}