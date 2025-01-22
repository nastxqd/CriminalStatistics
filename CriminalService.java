package com.example.criminal3;

import com.example.criminal3.repositories.CriminalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
//@RequiredArgsConstructor
public class CriminalService {
    private final CriminalRepository criminalRepository;
    public CriminalService(CriminalRepository criminalRepository) {
        this.criminalRepository = criminalRepository;
    }
    public List<Criminal> getAll(){
        return criminalRepository.findAll();
    }
    public String[] getAllSubjects(){
        List<Criminal> cr = criminalRepository.findAll();
        List<String> uniqueRegion = new ArrayList<>();
        for (int i = 0; i < cr.size(); i++) {
            uniqueRegion.add(cr.get(i).getSubject());
        }
        return new HashSet<>(uniqueRegion).toArray(String[]::new); // теперь реально юник
    }
}
