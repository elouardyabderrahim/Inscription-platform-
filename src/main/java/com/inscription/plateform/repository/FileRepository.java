package com.inscription.plateform.repository;

import com.inscription.plateform.entity.FormFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FileRepository extends JpaRepository<FormFile,Long> {




}
