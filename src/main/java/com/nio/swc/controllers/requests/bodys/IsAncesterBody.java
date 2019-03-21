package com.nio.swc.controllers.requests.bodys;

import lombok.Data;


@Data
public class IsAncesterBody{
    private String father;
    private String child;
}