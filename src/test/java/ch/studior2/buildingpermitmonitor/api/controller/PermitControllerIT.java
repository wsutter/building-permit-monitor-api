package ch.studior2.buildingpermitmonitor.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ch.studior2.buildingpermitmonitor.api.model.Permit;
import ch.studior2.buildingpermitmonitor.api.repository.PermitRepository;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PermitControllerIT {

  @Autowired private MockMvc mockMvc;

  @Autowired private PermitRepository permitRepository;

  @Autowired private GeometryFactory geometryFactory;

  @Test
  public void getPermitsByBoundingBox_ValidInput_ReturnsPermits() throws Exception {
    // Setup test data
    Permit permit = new Permit();
    Point point = geometryFactory.createPoint(new Coordinate(8.5, 47.3));
    permit.setCoordinates(point);
    permit.setAddress("Test Address");
    permitRepository.save(permit);

    // Test valid bounding box
    mockMvc
        .perform(
            get("/api/permits/bbox")
                .param("minLat", "47.0")
                .param("minLon", "8.0")
                .param("maxLat", "47.5")
                .param("maxLon", "9.0"))
        .andExpect(status().isOk());
  }

  @Test
  public void getPermitsByBoundingBox_InvalidInput_ReturnsBadRequest() throws Exception {
    // Test invalid bounding box (minLat >= maxLat)
    mockMvc
        .perform(
            get("/api/permits/bbox")
                .param("minLat", "47.5")
                .param("minLon", "8.0")
                .param("maxLat", "47.0")
                .param("maxLon", "9.0"))
        .andExpect(status().isBadRequest());
  }
}
