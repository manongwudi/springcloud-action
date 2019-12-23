package com.wudimanong.oauth.resource.dao.mapper;

import com.wudimanong.oauth.entity.model.OauthModuleResources;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jiangqiao
 */
@Repository
@Mapper
public interface OauthModuleResourcesDao {

    /**
     * 根据用户ID查询资源菜单
     *
     * @param userId
     * @return
     */
    List<OauthModuleResources> getMenusByUserId(@Param("userId") String userId);

    /**
     * 根据资源ID+系统ID查询资源树
     *
     * @param id
     * @param systemId
     * @return
     */
    List<OauthModuleResources> selectModuleTree(@Param("id") String id, @Param("systemId") String systemId);

    /**
     * 根据角色ID查询角色资源权限
     *
     * @param userId
     * @return
     */
    List<OauthModuleResources> selectModuleByRoleId(@Param("roleId") String roleId, @Param("userId") String userId);

    /**
     * 根据用户ID及父ID获取资源列表
     *
     * @param userId
     * @param parentId
     * @return
     */
    List<OauthModuleResources> selectModuleByParentId(@Param("userId") String userId,
            @Param("parentId") String parentId);


    /**
     * 根据用户ID及父ID获取资源列表
     *
     * @param userId
     * @param parentId
     * @return
     */
    List<OauthModuleResources> selectMenusByParentIdWithOperating(@Param("userId") String userId,
            @Param("parentId") String parentId);
}
