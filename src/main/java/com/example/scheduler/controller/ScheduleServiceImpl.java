package com.example.scheduler.controller;

import com.example.scheduler.ScheduleRequestDto;
import com.example.scheduler.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto.getWriterName(), requestDto.getWorkToDo(), requestDto.getPassword());
        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(ScheduleResponseDto::getUpdatedAt).reversed())
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long scheduleId) {
        return new ScheduleResponseDto(scheduleRepository.findScheduleById(scheduleId).get());
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long scheduleId, String writerName, String workToDo, String password) {
        int updateRow = scheduleRepository.updateSchedule(scheduleId, writerName, workToDo, password);

        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Schedule schedule = scheduleRepository.findScheduleById(scheduleId).get();
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public void deleteSchedule(Long scheduleId, String password) {
        int updateRow = scheduleRepository.deleteSchedule(scheduleId, password);

        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
