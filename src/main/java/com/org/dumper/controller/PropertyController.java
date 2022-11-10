package com.org.dumper.controller;

import com.org.dumper.dto.PropertyDto;
import com.org.dumper.payload.request.PropertyRequest;
import com.org.dumper.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/property")
@AllArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping("/create-property")
    public ResponseEntity<String> createProperty(
            @RequestBody PropertyRequest request
    ) throws Exception {
//        @RequestParam MultipartFile[] files,
        propertyService.createProperty(request);
        return ResponseEntity.ok().body("Property created SuccessFully");
    }

    @PostMapping("/add-image/{propertyId}")
    public ResponseEntity<String> addImage(
            @RequestBody PropertyRequest request, @PathVariable Long propertyId
    ) throws Exception {
        propertyService.addPropertyImage(propertyId, request);

        return ResponseEntity.ok("Image added Successfully");
    }

    @GetMapping("/all")
    public Page<PropertyDto> getAllProperty(@RequestParam String email) {
        return propertyService.getAllProperty(email);
    }

    @GetMapping(value = "/{propertyId}")
    public ResponseEntity<PropertyDto> getPropertyById(@PathVariable Long propertyId) {

        PropertyDto property = propertyService.getPropertyById(propertyId);

        return ResponseEntity.ok(property);
    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<String> deleteById(@PathVariable Long propertyId) {
        String success = propertyService.deleteById(propertyId);
        return ResponseEntity.ok(success);
    }

}
