package com.tekup.ticketsproject.Services;

import com.tekup.ticketsproject.Entities.Intervention;

import java.util.List;

public interface InterventionService {

    public List<Intervention> getAllIntervention();


    public Intervention getById(Long id);
}
