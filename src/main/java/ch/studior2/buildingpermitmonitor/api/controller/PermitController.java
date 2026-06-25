package ch.studior2.buildingpermitmonitor.api.controller;

import ch.studior2.buildingpermitmonitor.api.dto.PermitDTO;
import ch.studior2.buildingpermitmonitor.api.service.PermitService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/permits")
public class PermitController {

  private final PermitService permitService;

  public PermitController(PermitService permitService) {
    this.permitService = permitService;
  }

  @GetMapping("/bbox")
  public ResponseEntity<List<PermitDTO>> getPermitsByBoundingBox(
      @RequestParam double minLat,
      @RequestParam double minLon,
      @RequestParam double maxLat,
      @RequestParam double maxLon) {
    if (minLat >= maxLat || minLon >= maxLon) {
      return ResponseEntity.badRequest().build();
    }
    List<PermitDTO> permits = permitService.findWithinBoundingBox(minLat, minLon, maxLat, maxLon);
    return ResponseEntity.ok(permits);
  }
}
