package com.example.deploytestback.controller;

import com.example.deploytestback.domain.Coach;
import com.example.deploytestback.dto.CoachReqDto;
import com.example.deploytestback.dto.CoachResDto;
import com.example.deploytestback.service.CoachService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Coach API", tags = {"코치 API"})
@RestController
@RequestMapping("/api/v1/coach")
@RequiredArgsConstructor
public class CoachController {

    private final CoachService coachService;

    // 코치 생성
    @PostMapping("/create")
    @ApiOperation(value = "코치 생성", notes = "이름과 역할을 입력받아 코치를 생성한다.")
    public ResponseEntity<CoachResDto> createCoach(@RequestBody CoachReqDto coachReqDto) {
        Coach newCoach = coachService.createCoach(coachReqDto.getName(), coachReqDto.getRole());
        CoachResDto coachResDto = new CoachResDto();
        coachResDto.setName(newCoach.name);
        coachResDto.setRole(newCoach.role);
        return ResponseEntity.status(201).body(coachResDto);
    }

    // 코치 업데이트
    @PutMapping("/update/{coachId}")
    @ApiOperation(value = "코치 업데이트", notes = "이름과 역할, 코치 아이디를 입력받아 해당 코치 정보를 업데이트한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "코치 없음"),
    })
    public ResponseEntity<Object> updateCoachRole(@RequestBody CoachReqDto coachReqDto, @PathVariable("coachId") long id) {
        Coach coach = coachService.getCoach(id);
        if (coach == null) {
            return ResponseEntity.status(404).body("해당 코치가 없습니다.");
        }
        coachService.changeName(id, coachReqDto.getName());
        Coach updatedCoach = coachService.changeRole(id, coachReqDto.getRole());
        CoachResDto coachResDto = new CoachResDto();
        coachResDto.setRole(updatedCoach.role);
        coachResDto.setName(updatedCoach.name);
        return ResponseEntity.status(200).body(coachResDto);
    }

    // 개별 코치 조회
    @GetMapping("/{coachId}")
    @ApiOperation(value = "코치 조회", notes = "코치 아이디에 해당하는 코치를 조회한다.")
    public ResponseEntity<Object> getCoach(@PathVariable("coachId") long id) {
        Coach coach = coachService.getCoach(id);
        if (coach == null) {
            return ResponseEntity.status(404).body("해당 코치가 없습니다.");
        }
        CoachResDto coachResDto = new CoachResDto();
        coachResDto.setName(coach.name);
        coachResDto.setRole(coach.role);
        return ResponseEntity.status(200).body(coachResDto);
    }

    // 코치 목록 조회
    @GetMapping("/coaches")
    @ApiOperation(value = "전체 코치 조회", notes = "전체 코치들을 조회한다.")
    public ResponseEntity<List<Coach>> getAllCoaches() {
        List<Coach> coachList = coachService.getAllCoaches();
        return ResponseEntity.status(200).body(coachList);
    }

}
