package io.agile.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.agile.ppmtool.domain.Backlog;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {

}
