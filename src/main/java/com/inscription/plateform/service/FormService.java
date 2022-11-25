package com.inscription.plateform.service;


import com.inscription.plateform.entity.Form;
import com.inscription.plateform.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class FormService  implements AppService <Form>{


    @Autowired
    private FormRepository formRepository;

    @Override
    public void save(Form form) {
        formRepository.save(form);
    }

    @Override
    public void update(Form form) {
        formRepository.save(form);

    }

    @Override
    public void delete(Long id) {
        formRepository.deleteById(id);

    }

    @Override
    public Form findById(Long id) {
        return formRepository.findById(id).get();
    }

    @Override
    public List<Form> getAll() {
        return (List<Form>) formRepository.findAll();
    }
}
