package ch.studior2.buildingpermitmonitor.api.repository;

import ch.studior2.buildingpermitmonitor.api.model.Permit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermitRepository extends JpaRepository<Permit, Long> {

  @Query(
      value =
          "SELECT * FROM permits WHERE ST_Within(coordinates, ST_MakeEnvelope(:minLon, :minLat,"
              + " :maxLon, :maxLat, 4326))",
      nativeQuery = true)
  List<Permit> findWithinBoundingBox(
      @Param("minLat") double minLat,
      @Param("minLon") double minLon,
      @Param("maxLat") double maxLat,
      @Param("maxLon") double maxLon);
}
