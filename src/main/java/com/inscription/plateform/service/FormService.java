package com.inscription.plateform.service;

import com.inscription.plateform.entity.Form;
import com.inscription.plateform.repository.FormRepository;
import com.inscription.plateform.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FormService {

    private final FormRepository formRepository;

    public FormService(FormRepository formRepository){
        this.formRepository = formRepository;
    }

    public List<Form> getAllForm(){
        return formRepository.findAll();
    }

    public Form createForm(Form form){
        return formRepository.save(form);
    }

    public Form updateForm(long id, Form formRequest){
        return null;
    }

    public void deleteForm(long id){
        Form form = formRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Form", "id", id));

        formRepository.delete(form);
    }

    public Form getFormById(long id) {
        Optional<Form> result = formRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new ResourceNotFoundException("Form", "id", id);
        }
    }
}