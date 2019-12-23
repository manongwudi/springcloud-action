package com.wudimanong.oauth.entity.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jiangqiao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OauthRole implements Serializable {

    private String roleId;

    private String roleCode;

    private String roleName;

    private Date createDate;

    private Date updateDate;

    /**
     * 角色资源
     */
    private List<OauthModuleResources> modules;
}
