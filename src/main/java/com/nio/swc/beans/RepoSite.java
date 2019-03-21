package com.nio.swc.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.IOException;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
@ApiModel(description="代码site")
public class RepoSite {

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

    @ApiModelProperty(value="代码site",name="name")
    private String name;

    @ApiModelProperty(value="用户",name="username")
    private String username;

    @ApiModelProperty(value="密码",name="password")
    private String password;

    public UsernamePasswordCredentialsProvider GetCredentialsProvider(){
       return new UsernamePasswordCredentialsProvider( this.username, this.password );
    }
}

class SerializerRepoSite extends JsonSerializer<RepoSite> {
    public void serialize(RepoSite repoSite, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeNumber(repoSite.getId());
    }
}
