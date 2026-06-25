package ch.studior2.buildingpermitmonitor.api.model;

import javax.persistence.*;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "permits")
public class Permit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String address;

  @Column(columnDefinition = "geometry(Point,4326)")
  private Point coordinates;

  // Getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Point getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Point coordinates) {
    this.coordinates = coordinates;
  }
}
