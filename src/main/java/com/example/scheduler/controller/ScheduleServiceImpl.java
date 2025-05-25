package com.example.scheduler.controller;

import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.repository.ScheduleRepository;
import com.example.scheduler.repository.WriterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final WriterRepository writerRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, WriterRepository writerRepository) {
        this.scheduleRepository = scheduleRepository;
        this.writerRepository = writerRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {
        int rowNum = writerRepository.countByWriterId(requestDto.getWriterId());

        if (rowNum == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Schedule schedule = new Schedule(requestDto.getWriterName(), requestDto.getWorkToDo(), requestDto.getPassword(), requestDto.getWriterId());
        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAll(Long writerId) {
        return scheduleRepository.findAll(writerId)
                .stream()
                .sorted(Comparator.comparing(ScheduleResponseDto::getUpdatedAt).reversed())
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long scheduleId) {
        return new ScheduleResponseDto(scheduleRepository.findScheduleByIdOrElseThrow(scheduleId));
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long scheduleId, String writerName, String workToDo, String password) {
        int updateRow = scheduleRepository.updateSchedule(scheduleId, writerName, workToDo, password);

        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);
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
