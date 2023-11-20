package com.nesflisback.nesflisback.service;

import com.nesflisback.nesflisback.controller.dto.ProfileInputDTO;
import com.nesflisback.nesflisback.controller.dto.ProfileOutputDTO;

import java.util.List;

public interface ProfileService {
    List<ProfileOutputDTO> findAllProfiles(int pageNumber, int pageSize);
    ProfileOutputDTO getProfileById(int idProfile);
    ProfileOutputDTO createProfile(ProfileInputDTO profileDTO);
    void deleteProfile(int idProfile);
    void updateProfile(int idProfile, ProfileInputDTO profileDTO);
}
