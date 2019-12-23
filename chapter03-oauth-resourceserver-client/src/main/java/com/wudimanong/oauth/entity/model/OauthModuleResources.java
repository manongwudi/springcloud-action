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
public class OauthModuleResources implements Serializable {

    private String moduleId;

    private String moduleName;

    private String moduleCode;

    private String modulePath;

    private String parentId;

    private String moduleIcon;

    private String httpMethod;

    /**
     * 0 否，1 是
     */
    private Integer isOperating;

    private Integer sort;

    private String systemId;

    private Integer active;

    private Date createDate;

    private Date updateDate;

    /**
     * 资源子项
     */
    private List<OauthModuleResources> subModules;
}
