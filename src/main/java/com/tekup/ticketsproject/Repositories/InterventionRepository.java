package com.tekup.ticketsproject.Repositories;

import com.tekup.ticketsproject.Entities.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Long>  {
}
