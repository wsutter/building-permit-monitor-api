package ch.studior2.buildingpermitmonitor.api.service;

import ch.studior2.buildingpermitmonitor.api.dto.PermitDTO;
import ch.studior2.buildingpermitmonitor.api.repository.PermitRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PermitService {

  private final PermitRepository permitRepository;

  public PermitService(PermitRepository permitRepository) {
    this.permitRepository = permitRepository;
  }

  public List<PermitDTO> findWithinBoundingBox(
      double minLat, double minLon, double maxLat, double maxLon) {
    return permitRepository.findWithinBoundingBox(minLat, minLon, maxLat, maxLon).stream()
        .map(permit -> new PermitDTO(permit.getId(), permit.getAddress(), permit.getCoordinates()))
        .collect(Collectors.toList());
  }
}
