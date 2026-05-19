package ch.studior2.buildingpermitmonitor.api.controller;

import ch.studior2.buildingpermitmonitor.api.dto.BuildingPermitDto;
import ch.studior2.buildingpermitmonitor.api.service.BuildingPermitQueryService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildingPermitController {

  private final BuildingPermitQueryService queryService;

  public BuildingPermitController(BuildingPermitQueryService queryService) {
    this.queryService = queryService;
  }

  @GetMapping("/api/building-permits")
  public List<BuildingPermitDto> findBuildingPermits(
      @RequestParam(required = false) String municipality,
      @RequestParam(required = false) String category) {
    return queryService.findBuildingPermits(municipality, category);
  }
}
