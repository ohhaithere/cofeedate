package ru.ohhaithere.coffeedate.service

import org.springframework.stereotype.Component
import ru.ohhaithere.coffeedate.dto.PreferenceDto
import ru.ohhaithere.coffeedate.mapper.PreferenceMapper
import ru.ohhaithere.coffeedate.repository.PreferenceRepository
import java.util.*

//TODO: нужно ли?
@Component
class PreferenceService(private var mapper: PreferenceMapper,
                        private var repository: PreferenceRepository) {

    fun save(dto: PreferenceDto): PreferenceDto {
        val preference = mapper.convertToModel(dto);
        preference.id = UUID.randomUUID()
        repository.save(preference);
        return mapper.convertToDto(preference);
    }

    fun update(dto: PreferenceDto): PreferenceDto {
        val preference = mapper.convertToModel(dto);
        repository.save(preference);
        return mapper.convertToDto(preference);
    }

    fun get(id: UUID): PreferenceDto {
        return mapper.convertToDto(repository.getById(id));
    }

    fun get(): List<PreferenceDto> {
        return mapper.convertToDtos(repository.findAll());
    }

}