package com.nio.swc.beans;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.revwalk.filter.RevFilter;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;


public class RepoBaseTest {
    public static void main(String[] args) throws Exception {
//        Git.cloneRepository()
//                .setCredentialsProvider(new UsernamePasswordCredentialsProvider( "bozhi.gu", "Gbz@1989727.4" ))
//                .setCloneAllBranches(true)
//                .setNoCheckout(false)
//                .setDirectory(new File("/tmp/test-dj"))
//                .setURI("https://git.nevint.com/SQE_SE/DavyJones.git")
//                .call();
        Git git = Git.open(new File("/tmp/test-repo/",".git")) ;
        git.fetch().setCredentialsProvider(new UsernamePasswordCredentialsProvider( "bozhi.gu", "Gbz@1989727.5" )).call();
        RevWalk walk = new RevWalk(git.getRepository());
//        RevCommit commit1 = walk.parseCommit(git.getRepository().resolve("40f5928d"));
//        RevCommit commit2 = walk.parseCommit(git.getRepository().resolve("6f28c99b"));
//        walk.setRevFilter(RevFilter.MERGE_BASE);
//        RevCommit rev = walk.parseCommit(git.getRepository().resolve("remotes/origin/masters"));
        //System.out.println(rev.toString());
//        for (RevCommit rev :git.log().add(git.getRepository().resolve("remotes/origin/test3")).call()){
//            System.out.println(rev.toString());
//        }
    }
}
