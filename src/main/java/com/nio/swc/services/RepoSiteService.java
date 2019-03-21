package com.nio.swc.services;

import com.nio.swc.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//简化日志
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class RepoSiteService {

    @Autowired
    private  RepoSiteRepository repoSiteRepository;

    public void create(RepoSite repoSite){
        repoSiteRepository.save(repoSite);
    }
    public Iterable<RepoSite> select(){
        return repoSiteRepository.findAll();
    }
    public RepoSite load(Long id) {
        return repoSiteRepository.findById(id).orElseThrow(Error::new);
    }
}

