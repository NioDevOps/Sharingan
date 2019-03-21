package com.nio.swc.beans;

import java.io.File;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nio.swc.utils.ApiException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.FetchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.revwalk.filter.RevFilter;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.ReceivedPackStatistics;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.CreatedDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;


@Slf4j
@Entity
@Data
@RequiredArgsConstructor
@ApiModel(description="代码库")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}) })
public class RepoBase {
    public static final String GitDirName = ".git";
    public static final String MasterBranchName = "master";

    @ApiModelProperty(value="url",name="url")
    private String url;

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private Long id;

    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

    private Date createdDate;
    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date lastModifiedDate;

    @ApiModelProperty(value="库名",name="name")
    @Column(unique=true,nullable = false)
    private String name;

    @ApiModelProperty(value="代码库类型",name="repoType")
    private String repoType;

    @JsonSerialize(using=SerializerRepoSite.class)
    @JsonProperty(value="repoSiteId")
    @ManyToOne(targetEntity=RepoSite.class,fetch=FetchType.LAZY)
    private RepoSite repoSite;

    private String gitLocalPath;

    private Boolean cached;

    public void Clone(String workspacePath)throws Exception {
        CloneCommand cc = Git.cloneRepository().setURI(this.url).setCloneAllBranches(true);
        cc.setCredentialsProvider(this.repoSite.GetCredentialsProvider());
        File fn = new File(workspacePath ,this.name);
        log.info(String.format("start clone %s into %s", this.url, fn.getAbsolutePath() ));
        cc.setDirectory(fn).call();
        log.info(String.format("finish clone %s into %s", this.url, fn.getAbsolutePath() ));
        this.gitLocalPath = fn.getAbsolutePath();
        this.cached = true;
    }

    private Git GetGitObj()throws Exception{
        return Git.open(new File(this.gitLocalPath, RepoBase.GitDirName));
    }

    public void Fetch()throws Exception {
        this.GetGitObj()
                .fetch()
                .setCredentialsProvider(this.repoSite.GetCredentialsProvider())
                .call();

    }

    public Boolean IsAncester(String father, String child)throws Exception{
        this.Fetch();
        Repository r = this.GetGitObj().getRepository();
        RevWalk walk = new RevWalk(r);
        try {
            RevCommit commitFather = walk.parseCommit(r.resolve(father));
            RevCommit commitChild = walk.parseCommit(r.resolve(child));
            walk.setRevFilter(RevFilter.MERGE_BASE);
            walk.markStart(commitFather);
            walk.markStart(commitChild);
            RevCommit mergeBase = walk.next();
            log.info("the base commit is %s", mergeBase.toString());
            return mergeBase.equals(commitFather);
        }catch (NullPointerException e){
            e.printStackTrace();
            throw new ApiException(HttpStatus.BAD_REQUEST,"commit id do not exist");
        }
    }
}


