package com.wudimanong.resourceserver.service.impl;

import com.wudimanong.resourceserver.dao.mapper.OauthUserDetailsDao;
import com.wudimanong.resourceserver.dao.model.OauthUserDetailsPO;
import com.wudimanong.resourceserver.entity.GlobalCodeEnum;
import com.wudimanong.resourceserver.entity.bo.CheckPassWordBO;
import com.wudimanong.resourceserver.entity.dto.CheckPassWordDTO;
import com.wudimanong.resourceserver.exception.ServiceException;
import com.wudimanong.resourceserver.service.UserAuthService;
import com.wudimanong.resourceserver.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiangqiao
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

    /**
     * 持久层组件依赖
     */
    @Autowired
    OauthUserDetailsDao oauthUserDetailsDao;

    @Override
    public CheckPassWordBO checkPassWord(CheckPassWordDTO checkPassWordDTO) {
        //获取用户信息
        OauthUserDetailsPO oauthUserDetailsPO = oauthUserDetailsDao.getUserDetails(checkPassWordDTO.getUserName());
        if (oauthUserDetailsPO == null) {
            throw new ServiceException(GlobalCodeEnum.BUSI_USER_NOT_EXIST.getCode(),
                    GlobalCodeEnum.BUSI_USER_NOT_EXIST.getDesc());
        }
        //计算传入的密码MD5值(密码&加盐值)
        String expectPassWord = Md5Utils.md5Hex(checkPassWordDTO.getPassWord() + "&" + oauthUserDetailsPO.getSalt(),
                "UTF-8");
        //密码比较
        if (expectPassWord.equals(oauthUserDetailsPO.getPassword())) {
            return CheckPassWordBO.builder().result(true).build();
        }
        return CheckPassWordBO.builder().result(false).build();
    }
}
