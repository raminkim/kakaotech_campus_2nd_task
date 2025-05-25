package com.example.scheduler.repository;


import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class JdbcScheduleRepository implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {

        // 명시적으로 INSERT에 사용할 COLUMN을 지정: "writerName", "workToDo", "password"
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .usingGeneratedKeyColumns("scheduleId")
                .usingColumns("writerName", "workToDo", "password", "writerId");
        simpleJdbcInsert.withTableName("schedule");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("writerName", schedule.getWriterName());
        parameters.put("workToDo", schedule.getWorkToDo());
        parameters.put("password", schedule.getPassword());
        parameters.put("writerId", schedule.getWriterId());

        Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        // insert 함으로써 자동 생성된 scheduleId가 담긴 key로 createdAt, updatedAt을 가져온다.
        String query = "select createdAt, updatedAt from schedule where scheduleId = ? and writerId = ?";
        // JdbcTemplate의 queryForMap 메소드를 통해 key 값을 scheduleId로 가지는 row의 createdAt, updatedAt 값을 가져온다.
        Map<String, Object> queryResult = jdbcTemplate.queryForMap(query, key, schedule.getWriterId());


        // createdAt, updatedAt은 java.time.LocalDateTime 타입으로 반환된다.
        String createdAt = changeTimestamp((LocalDateTime) queryResult.get("createdAt"));
        String updateAt = changeTimestamp((LocalDateTime) queryResult.get("updatedAt"));

        return new ScheduleResponseDto(key.longValue(), schedule.getWriterName(), schedule.getWorkToDo(), createdAt, updateAt, schedule.getWriterId());
    }

    private String changeTimestamp(LocalDateTime localDateTime) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(Timestamp.valueOf(localDateTime));
    }

    @Override
    public List<ScheduleResponseDto> findAll(Long writerId) {
        String query = "select * from schedule where writerId = ?";

        return jdbcTemplate.query(query, rowMapper(), writerId);
    }

    private RowMapper<ScheduleResponseDto> rowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("scheduleId"),
                        rs.getString("writerName"),
                        rs.getString("workToDo"),
                        changeTimestamp(rs.getTimestamp("createdAt").toLocalDateTime()),
                        changeTimestamp(rs.getTimestamp("updatedAt").toLocalDateTime()),
                        rs.getLong("writerId")
                );
            }
        };
    }

    @Override
    public Optional<Schedule> findScheduleById(Long scheduleId) {
        String query = "select * from schedule where scheduleId = ?";
        List<Schedule> result = jdbcTemplate.query(query, rowMapperV2(), scheduleId);

        return result.stream().findAny();

    }

    @Override
    public Schedule findScheduleByIdOrElseThrow(Long scheduleId) {
        String query = "select * from schedule where scheduleId = ?";
        List<Schedule> result = jdbcTemplate.query(query, rowMapperV2(), scheduleId);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    private RowMapper<Schedule> rowMapperV2() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                    rs.getLong("scheduleId"),
                    rs.getString("writerName"),
                    rs.getString("workToDo"),
                    rs.getString("password"),
                    changeTimestamp(rs.getTimestamp("createdAt").toLocalDateTime()),
                    changeTimestamp(rs.getTimestamp("updatedAt").toLocalDateTime()),
                    rs.getLong("writerId")
                );
            }
        };
    }

    @Override
    public int updateSchedule(Long scheduleId, String writerName, String workToDo, String password) {
        Timestamp updatedAt = Timestamp.from(Instant.now());
        String query = "update schedule set writerName = ?, workToDo = ?, updatedAt = ? where scheduleId = ? and password = ?";

        // 변경된 행의 수를 반환한다.
        return jdbcTemplate.update(query, writerName, workToDo, updatedAt, scheduleId, password);
    }

    @Override
    public int deleteSchedule(Long scheduleId, String password) {
        String query = "delete from schedule where scheduleId = ? and password = ?";

        return jdbcTemplate.update(query, scheduleId, password);
    }
}
