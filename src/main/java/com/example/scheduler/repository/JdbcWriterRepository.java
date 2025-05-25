package com.example.scheduler.repository;

import com.example.scheduler.dto.WriterRequestDto;
import com.example.scheduler.dto.WriterResponseDto;
import com.example.scheduler.entity.Writer;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcWriterRepository implements WriterRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcWriterRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public WriterResponseDto saveWriter(Writer writer) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .usingGeneratedKeyColumns("writerId")
                .usingColumns("name", "email");
        simpleJdbcInsert.withTableName("writer");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", writer.getName());
        parameters.put("email", writer.getEmail());

        Number key = simpleJdbcInsert.executeAndReturnKey(parameters);

        String query = "select * from writer where writerId = ?";
        Map<String, Object> result = jdbcTemplate.queryForMap(query, key);

        String createdAt = changeTimeStamp((LocalDateTime) result.get("createdAt"));
        String updatedAt = changeTimeStamp((LocalDateTime) result.get("updatedAt"));

        return new WriterResponseDto(new Writer(key.longValue(), writer.getName(), writer.getEmail(), createdAt, updatedAt));
    }

    @Override
    public int countByWriterId(Long writerId) {
        String query = "select * from writer where writerId = ?";
        List<Writer> result = jdbcTemplate.query(query, rowMapper(), writerId);

        return result.size();
    }

    private RowMapper<Writer> rowMapper() {
        return new RowMapper<Writer>() {
            @Override
            public Writer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Writer(
                        rs.getLong("writerId"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt")
                );
            }
        };
    }

    private String changeTimeStamp(LocalDateTime localDateTime) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(Timestamp.valueOf(localDateTime));
    }
}
