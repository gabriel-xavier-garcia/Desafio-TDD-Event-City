package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.DatabaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    @Transactional(readOnly = true)
    public List<CityDTO> findAll(){
        List<City> result  = repository.findAll(Sort.by("name"));
        return result.stream().map(CityDTO::new).toList();
    }


    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Id: " + id + " Not found!");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity failure!");
        }
    }

    @Transactional
    public CityDTO insert(CityDTO dto) {
        City city = new City();
        city.setName(dto.getName());
        city = repository.save(city);
        return new CityDTO(city);
    }
}
