package com.inscription.plateform.controllers;

import com.inscription.plateform.dto.FormeDto;
import com.inscription.plateform.entity.Form;
import com.inscription.plateform.entity.FormFile;
import com.inscription.plateform.response.ResponseFile;
import com.inscription.plateform.response.ResponseMessage;
import com.inscription.plateform.service.FileService;
import com.inscription.plateform.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/forms")
public class FormController {

    @Autowired
    private ModelMapper modelMapper;

    private FormService formService;


    @Autowired
    private FileService fileService;

    public FormController(FormService formService, FileService fileService) {
        this.formService = formService;
        this.fileService = fileService;
    }

    @GetMapping
    public List<FormeDto> getAllForm(){
        return formService.getAllForm().stream().map(form -> modelMapper.map(form, FormeDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormeDto> getFormById(@PathVariable(name = "id") Long id){
        Form form = formService.getFormById(id);

        FormeDto formResponse = modelMapper.map(form, FormeDto.class);

        return ResponseEntity.ok().body(formResponse);
    }

    @PostMapping
    public ResponseEntity<FormeDto> createForm(@RequestBody FormeDto formeDto){



        Form formRequest = modelMapper.map(formeDto, Form.class);

        Form form = formService.createForm(formRequest);

        FormeDto formResponse = modelMapper.map(form, FormeDto.class);

        return new ResponseEntity<FormeDto>(formResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormeDto> updateForm(@PathVariable long id, @RequestBody FormeDto formDto){

        Form formRequest = modelMapper.map(formDto, Form.class);

        Form form = formService.updateForm(id, formRequest);

        FormeDto formResponse = modelMapper.map(form, FormeDto.class);

        return ResponseEntity.ok().body(formResponse);
    }





    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteForm(@PathVariable(name = "id") Long id){
        formService.deleteForm(id);
        return new ResponseEntity<String>("Form deleted successfully", HttpStatus.OK);
    }








    //Upload File to form
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            fileService.Upload(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }



    @GetMapping("/files")
    public ResponseEntity<List<FormFile>> getListFiles() {
        List<FormFile> fileInfos = fileService.getAllFiles().map(path -> {
            String filename = path.getName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FormController.class, "getFile", path.getName().toString()).build().toString();

            return new FormFile(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }





    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileService.getFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}