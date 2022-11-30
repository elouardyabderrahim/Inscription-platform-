package com.inscription.plateform.controllers;

import com.inscription.plateform.dto.FormeDto;
import com.inscription.plateform.entity.Form;
import com.inscription.plateform.entity.FormFile;
import com.inscription.plateform.response.ResponseFile;
import com.inscription.plateform.service.FileService;
import com.inscription.plateform.service.FormService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/forms")
public class FormController {

    private final FormService formService;
    @Autowired
    private final FileService fileService;
    @Autowired
    private ModelMapper modelMapper;

    public FormController(FormService formService, FileService fileService) {
        this.formService = formService;
        this.fileService = fileService;
    }

    @GetMapping
    public List<FormeDto> getAllForm() {
        return formService.getAllForm().stream().map(form -> modelMapper.map(form, FormeDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormeDto> getFormById(@PathVariable(name = "id") Long id) {
        Form form = formService.getFormById(id);

        FormeDto formResponse = modelMapper.map(form, FormeDto.class);

        return ResponseEntity.ok().body(formResponse);
    }

    @PostMapping
    public ResponseEntity<FormeDto> createForm(@RequestBody FormeDto formeDto) {


        Form formRequest = modelMapper.map(formeDto, Form.class);

        Form form = formService.createForm(formRequest);

        FormeDto formResponse = modelMapper.map(form, FormeDto.class);

        return new ResponseEntity<FormeDto>(formResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormeDto> updateForm(@PathVariable long id, @RequestBody FormeDto formDto) {

        Form formRequest = modelMapper.map(formDto, Form.class);

        Form form = formService.updateForm(id, formRequest);

        FormeDto formResponse = modelMapper.map(form, FormeDto.class);

        return ResponseEntity.ok().body(formResponse);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteForm(@PathVariable(name = "id") Long id) {
        formService.deleteForm(id);
        return new ResponseEntity<String>("Form deleted successfully", HttpStatus.OK);
    }


    //Upload File to form
    @PostMapping("/upload")
    public FormFile uploadFile(@RequestParam("file") MultipartFile file) throws IOException {


        return fileService.Upload(file);


    }


    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> fileInfos = fileService.getAllFiles().map(path -> {
            String filename = path.getName();
            String type = path.getType();


            String url = MvcUriComponentsBuilder.fromMethodName(FormController.class, "getFile", path.getName()).build().toString();

            return new ResponseFile(filename, type, url);


        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }


    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileService.getFile(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}