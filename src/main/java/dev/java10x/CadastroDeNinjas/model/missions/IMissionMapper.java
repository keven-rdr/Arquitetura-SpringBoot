package dev.java10x.CadastroDeNinjas.model.missions;

import dev.java10x.CadastroDeNinjas.entity.missions.Missions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMissionMapper {

    @Mapping(target = "id", ignore = true)
    Missions toEntity(MissionRequest request);

    MissionResponse toResponse(Missions entity);
}
