package ch.studior2.buildingpermitmonitor.api.service;

import ch.studior2.buildingpermitmonitor.api.dto.BuildingPermitDto;
import ch.studior2.buildingpermitmonitor.api.repository.BuildingPermitQueryRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BuildingPermitQueryService {

  private final BuildingPermitQueryRepository repository;

  public BuildingPermitQueryService(BuildingPermitQueryRepository repository) {
    this.repository = repository;
  }

  public List<BuildingPermitDto> findBuildingPermits(String municipality, String category) {
    return repository.findBuildingPermits(municipality, category);
  }
}
