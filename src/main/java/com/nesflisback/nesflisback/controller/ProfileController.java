package com.nesflisback.nesflisback.controller;

import com.nesflisback.nesflisback.controller.dto.ProfileInputDTO;
import com.nesflisback.nesflisback.controller.dto.ProfileOutputDTO;
import com.nesflisback.nesflisback.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @GetMapping("/search")
    public ResponseEntity<?> findAllProfiles(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                          @RequestParam(defaultValue = "4", required = false) int pageSize){
        return ResponseEntity.ok(profileService.findAllProfiles(pageNumber, pageSize));
    }


    @GetMapping("/search/{idProfile}")
    public ResponseEntity<?> searchProfileById(@PathVariable int idProfile){
        return ResponseEntity.ok(profileService.getProfileById(idProfile));
    }


    @PostMapping("/create")
    public ResponseEntity<?> createProfile(@RequestBody ProfileInputDTO profileDTO) throws URISyntaxException {
        ProfileOutputDTO response = profileService.createProfile(profileDTO);
        return ResponseEntity.created(new URI("/profile/create")).body(response);
    }

    @DeleteMapping("/delete/{idProfile}")
    public ResponseEntity<?> deleteProfile(@PathVariable int idProfile) throws URISyntaxException {
        profileService.deleteProfile(idProfile);
        return ResponseEntity.ok("Profile deleted successfully");
    }

    @PutMapping("/update/{idProfile}")
    public ResponseEntity<?> updateProfile(@PathVariable int idProfile, @RequestBody ProfileInputDTO profileDTO){
        profileService.updateProfile(idProfile, profileDTO);
        return ResponseEntity.ok("Profile updated successfully");
    }
}
