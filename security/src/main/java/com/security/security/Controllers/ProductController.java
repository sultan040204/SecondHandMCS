package com.security.security.Controllers;

import com.security.security.Entities.ProductEntity;
import com.security.security.Services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> findAll() {

        return ResponseEntity.ok(productService.findAll());
    }
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws IOException {
        RandomAccessFile f = new RandomAccessFile("C:/Users/User/Desktop/SpringBoot/demo/image/"+filename, "r");
        byte[] bytes = new byte[(int)f.length()];
        f.readFully(bytes);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
    }
    @GetMapping(value = "/image/{image:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> image(@PathVariable String image) throws IOException {
        final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(
                "C:/Users/User/Desktop/SpringBoot/demo/image/"+image
        )));
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(inputStream.contentLength())
                .body(inputStream);

    }
    @PostMapping
    public ResponseEntity post(@RequestBody ProductEntity product) {
        return ResponseEntity.ok(productService.save(product));
    }
    @PostMapping("/search")
    public ResponseEntity searchByName(@RequestBody ProductEntity product) {
        return ResponseEntity.ok(productService.findByName(product));
    }
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody ProductEntity product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> findById(@PathVariable Integer id) {
        Optional<ProductEntity> stock = productService.findById(id);
        if (!stock.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> update(@PathVariable Integer id, @RequestBody ProductEntity product) {
        if (!productService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productService.update(id,product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        if (!productService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
