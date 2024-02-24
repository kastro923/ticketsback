package com.tekup.ticketsproject.Services;

import com.tekup.ticketsproject.Entities.Intervention;
import com.tekup.ticketsproject.Repositories.InterventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterventionServiceImp implements InterventionService{


    private final InterventionRepository interventionRepository;

    @Autowired
    public InterventionServiceImp(InterventionRepository interventionRepository) {
        this.interventionRepository = interventionRepository;
    }

    @Override
    public List<Intervention> getAllIntervention() {
        return interventionRepository.findAll();
    }

    @Override
    public Intervention getById(Long id) {
        return new Intervention();
    }
}
