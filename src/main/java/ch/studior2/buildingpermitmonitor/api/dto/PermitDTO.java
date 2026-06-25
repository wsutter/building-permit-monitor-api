package ch.studior2.buildingpermitmonitor.api.dto;

import org.locationtech.jts.geom.Point;

public record PermitDTO(Long id, String address, Point coordinates) {}
