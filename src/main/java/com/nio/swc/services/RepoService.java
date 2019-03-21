package com.nio.swc.services;

import com.nio.swc.beans.*;
import com.nio.swc.configuration.SharinganProperties;
import com.nio.swc.utils.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
//简化日志
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class RepoService {

    @Autowired
    private SharinganProperties sharinganProperties;

    @Autowired
    private GitRepository gitRepository;

    @Transactional
    public void create(RepoBase repo)throws Exception {
        System.out.println(sharinganProperties.getWorkspace());
        repo.Clone(sharinganProperties.getWorkspace());
        gitRepository.save(repo);
    }
    public Iterable<RepoBase> select(){
        return gitRepository.findAll();
    }

    public boolean isAncester(Long id, String father, String child)throws Exception{
        return gitRepository.findById(id).orElseThrow(Error::new).IsAncester(father,child);
    }
    public RepoBase load(Long id)throws Exception{
        return gitRepository.findById(id).orElseThrow(Error::new);
    }
    public RepoBase loadByName(String name)throws Exception{
        List<RepoBase> reps = gitRepository.findByName(name);
        if(reps.size() == 0){
            throw new ApiException(HttpStatus.NOT_FOUND,"no such repo");
        }
        return reps.get(0);
    }
}

