package com.nesflisback.nesflisback.service.impl;

import com.nesflisback.nesflisback.controller.dto.ProfileInputDTO;
import com.nesflisback.nesflisback.controller.dto.ProfileOutputDTO;
import com.nesflisback.nesflisback.domain.*;
import com.nesflisback.nesflisback.domain.Record;
import com.nesflisback.nesflisback.exceptions.EntityNotFoundException;
import com.nesflisback.nesflisback.repository.FilmRepository;
import com.nesflisback.nesflisback.repository.ProfileRepository;
import com.nesflisback.nesflisback.repository.RecordRepository;
import com.nesflisback.nesflisback.repository.UserRepository;
import com.nesflisback.nesflisback.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RecordRepository recordRepository;
    @Autowired
    FilmRepository filmRepository;
    @Override
    public ProfileOutputDTO getProfileById(int idProfile) {
        Profile profile = profileRepository.findById(idProfile)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el perfil con ID: " + idProfile));
        return profile.profileToProfileOutputDto();
    }

    @Override
    public ProfileOutputDTO createProfile(ProfileInputDTO profileDTO) {
        User user = userRepository.findById(profileDTO.getIdUser())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el usuario con ID: " + profileDTO.getIdUser()));
        Profile profile = new Profile(profileDTO);
        profile.setUser(user);
        profileRepository.save(profile);
        Record record = new Record();
        record.setProfile(profile);
        recordRepository.save(record);
        profile.setRecord(record);
        profileRepository.save(profile);
        user.getProfileList().add(profile);
        userRepository.save(user);
        return profile.profileToProfileOutputDto();
    }


    @Override
    public void deleteProfile(int idProfile) {
        Profile profile = profileRepository.findById(idProfile)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el perfil con ID: " + idProfile));
        User user = profile.getUser();
        user.getProfileList().remove(profile);
        userRepository.save(user);
        Record record = profile.getRecord();
        for (Film film : record.getFilmList()) {
            film.getRecordList().remove(record);
            filmRepository.save(film);
        }
        record.setProfile(null);
        profile.setRecord(null);
        recordRepository.delete(record);
        profileRepository.delete(profile);
    }


    @Override
    public void updateProfile(int idProfile, ProfileInputDTO profileDTO) {
        Profile profile = profileRepository.findById(idProfile)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el perfil con ID: " + idProfile));
        if(profileDTO.getName() != null){
            profile.setName(profileDTO.getName());
        }
        if(profileDTO.getImage() != null){
            profile.setImage(profileDTO.getImage());
        }
        profileRepository.save(profile);
    }

    @Override
    public List<ProfileOutputDTO> findAllProfiles(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return profileRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Profile::profileToProfileOutputDto).toList();
    }
}
