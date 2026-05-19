package ch.studior2.buildingpermitmonitor.api.dto;

import java.time.LocalDate;
import java.util.UUID;

public record BuildingPermitDto(
    UUID id,
    String title,
    String description,
    String category,
    String status,
    String municipality,
    LocalDate publishedDate,
    String address,
    Double latitude,
    Double longitude) {}
