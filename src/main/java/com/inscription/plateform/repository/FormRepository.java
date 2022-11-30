package com.inscription.plateform.repository;

import com.inscription.plateform.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FormRepository extends JpaRepository<Form,Long> {




}
