package ch.studior2.buildingpermitmonitor.api.repository;

import ch.studior2.buildingpermitmonitor.api.dto.BuildingPermitDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BuildingPermitQueryRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public BuildingPermitQueryRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<BuildingPermitDto> findBuildingPermits(String municipality, String category) {
    StringBuilder sql =
        new StringBuilder(
            """
            SELECT id, title, description, category, status, municipality,
                   published_date, address, latitude, longitude
            FROM building_permits
            WHERE 1 = 1
            """);

    Map<String, Object> parameters = new HashMap<>();

    if (municipality != null && !municipality.isBlank()) {
      sql.append(" AND municipality = :municipality");
      parameters.put("municipality", municipality);
    }

    if (category != null && !category.isBlank()) {
      sql.append(" AND category = :category");
      parameters.put("category", category);
    }

    sql.append(" ORDER BY published_date DESC NULLS LAST LIMIT 500");

    return jdbcTemplate.query(sql.toString(), parameters, new BuildingPermitRowMapper());
  }

  private static class BuildingPermitRowMapper implements RowMapper<BuildingPermitDto> {

    @Override
    public BuildingPermitDto mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new BuildingPermitDto(
          UUID.fromString(rs.getString("id")),
          rs.getString("title"),
          rs.getString("description"),
          rs.getString("category"),
          rs.getString("status"),
          rs.getString("municipality"),
          rs.getDate("published_date") != null ? rs.getDate("published_date").toLocalDate() : null,
          rs.getString("address"),
          rs.getObject("latitude", Double.class),
          rs.getObject("longitude", Double.class));
    }
  }
}
