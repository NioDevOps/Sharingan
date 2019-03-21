package com.nio.swc.controllers;




import com.nio.swc.beans.RepoBase;
import com.nio.swc.beans.RepoSite;
import com.nio.swc.configuration.SharinganProperties;
import com.nio.swc.controllers.requests.bodys.IsAncesterBody;
import com.nio.swc.controllers.requests.bodys.RepoBody;
import com.nio.swc.services.RepoService;
import com.nio.swc.services.RepoSiteService;
import com.nio.swc.utils.aspects.ApiRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value="v1",description="v1接口",tags={"api", "v1"})
@RestController
@RequestMapping(value = {"/v1"})
public class V1 {

    @Autowired
    private RepoService repoService;
    @Autowired
    private RepoSiteService repoSiteService;
    @Autowired
    private SharinganProperties configUtil;

    @ApiRequest
    @GetMapping("/repo")
    public Object getRepo() {
        return repoService.select();
    }

    @ApiRequest
    @PutMapping("/repo/{name}/ancester")
    public Object isAncester(@PathVariable("name") String name ,@RequestBody IsAncesterBody isAncesterBody) throws Exception{
        return repoService.loadByName(name).IsAncester(isAncesterBody.getFather(),isAncesterBody.getChild());
    }

    @ApiRequest
    @GetMapping("/repo/{name}")
    public Object getRepoByName(@PathVariable("name") String name ) throws Exception{
        return repoService.loadByName(name);
    }

    @ApiRequest
    @PostMapping("/repo")
    public Object createRepo(@RequestBody RepoBody repoBody)throws Exception{
        Long repoSiteId = repoBody.getRepoSiteId();
        RepoSite repoSite = repoSiteService.load(repoSiteId);
        RepoBase repo = new RepoBase();
        repo.setName(repoBody.getName());
        repo.setUrl(repoBody.getUrl());
        repo.setRepoSite(repoSite);
        repoService.create(repo);
        return repo;
    }

    @ApiRequest
    @GetMapping("/repoSite")
    public Object getRepoSite() {
        return repoSiteService.select();
    }

    @ApiRequest
    @PostMapping("/repoSite")
    public Object createRepoSite(@RequestBody RepoSite repoSite)throws Exception{
        repoSiteService.create(repoSite);
        return repoSite;
    }


    @GetMapping("/profile")
    public Object getProfile(){
        return configUtil;
    }
}
