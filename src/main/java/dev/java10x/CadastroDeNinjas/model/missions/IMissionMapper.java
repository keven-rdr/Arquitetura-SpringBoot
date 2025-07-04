package dev.java10x.CadastroDeNinjas.model.missions;

import dev.java10x.CadastroDeNinjas.entity.missions.Missions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface IMissionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ninjas", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Missions toEntity(MissionRequest request);

    @Mapping(target = "ninjas", source = "ninjas")
    MissionResponse toResponse(Missions entity);

}