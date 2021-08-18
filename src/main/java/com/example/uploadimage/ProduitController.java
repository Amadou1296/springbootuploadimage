package com.example.uploadimage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;

@RestController
@Service
public class ProduitController {
    @Autowired
    ProduitRepository produitRepository;

    @PostMapping(path="/image/add")
    public String insertProduit(@RequestParam("file") MultipartFile file) throws IOException {
        Produit p = new Produit();

        String uploadDir = "images/";

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        p.setImage(fileName);

        produitRepository.save(p);

        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "ok";
    }
}
