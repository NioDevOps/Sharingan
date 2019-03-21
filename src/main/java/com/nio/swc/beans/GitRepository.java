package com.nio.swc.beans;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface GitRepository extends CrudRepository<RepoBase, Long> {
    @Query("select g from RepoBase g where name=?1" )
    List<RepoBase> findByName(String name);
}



