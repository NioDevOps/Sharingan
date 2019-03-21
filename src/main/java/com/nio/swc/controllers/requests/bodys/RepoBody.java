package com.nio.swc.controllers.requests.bodys;

import lombok.Data;

@Data
public class RepoBody {
    private String name;
    private String url;
    private Long repoSiteId;
}
