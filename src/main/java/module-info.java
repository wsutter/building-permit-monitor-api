module ch.studior2.buildingpermitmonitor.api {
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.context;
  requires spring.web;
  requires spring.jdbc;
  requires java.sql;
  requires ch.studior2.buildingpermitmonitor.contracts;

  opens ch.studior2.buildingpermitmonitor.api to
      spring.core,
      spring.beans,
      spring.context;
  opens ch.studior2.buildingpermitmonitor.api.controller to
      spring.core,
      spring.beans,
      spring.context;
  opens ch.studior2.buildingpermitmonitor.api.repository to
      spring.core,
      spring.beans,
      spring.context;
  opens ch.studior2.buildingpermitmonitor.api.service to
      spring.core,
      spring.beans,
      spring.context;

  exports ch.studior2.buildingpermitmonitor.api.dto;
}
