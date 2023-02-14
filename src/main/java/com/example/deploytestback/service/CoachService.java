package com.example.deploytestback.service;

import com.example.deploytestback.domain.Coach;
import com.example.deploytestback.domain.CoachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachService {

    private final CoachRepository coachRepository;

    @Transactional
    public Coach getCoach(long id) {
        return coachRepository.findById(id);
    }

    @Transactional
    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    @Transactional
    public Coach createCoach(String name, String role) { // 코치 생성
        // 코치 생성 후 저장
        Coach newCoach = Coach.builder()
                            .name(name)
                            .role(role)
                            .build();
        coachRepository.save(newCoach);

        return newCoach;
    }

    @Transactional
    public Coach changeRole(long id, String role) { // 코치 역할 변경

        Coach coach = coachRepository.findById(id); // id로 코치를 가져와서 null이면 false
        if (coach == null) {
            return coach;
        }

        coach.setRole(role);
        coachRepository.save(coach);

        return coach;
    }

    @Transactional
    public boolean changeName(long id, String name) { // 코치 이름 변경
        Coach coach = coachRepository.findById(id); // id로 코치를 가져와서 null이면 false
        if (coach == null) {
            return false;
        }

        coach.setName(name);
        coachRepository.save(coach);

        return true;

    }

}
