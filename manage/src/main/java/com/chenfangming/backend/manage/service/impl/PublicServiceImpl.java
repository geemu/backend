package com.chenfangming.backend.manage.service.impl;

import com.chenfangming.backend.manage.domain.request.LoginRequest;
import com.chenfangming.backend.manage.domain.response.VerificationCodeResponse;
import com.chenfangming.backend.manage.persistence.entity.UserEntity;
import com.chenfangming.backend.manage.persistence.mapper.UserMapper;
import com.chenfangming.backend.manage.service.PublicService;
import com.chenfangming.common.constant.BaseResponseStatusEnums;
import com.chenfangming.common.constant.Constants;
import com.chenfangming.common.model.ClientException;
import com.chenfangming.common.model.ServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 公共操作。包括登录，登出等
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-01 18:01
 */
@Slf4j
@Service
public class PublicServiceImpl implements PublicService {
    /** 自定义RedisTemplate **/
    private RedisTemplate<String, Object> redisTemplate;
    /** 用户数据库操作 **/
    private UserMapper userMapper;

    /**
     * 构造器注入
     * @param redisTemplate 模板
     * @param userMapper    用户数据库操作
     */
    @Autowired
    public PublicServiceImpl(RedisTemplate<String, Object> redisTemplate, UserMapper userMapper) {
        this.redisTemplate = redisTemplate;
        this.userMapper = userMapper;
    }

    /**
     * 获取验证码
     * @return 验证码token以及base64格式的图片
     */
    @Override
    public VerificationCodeResponse getVerificationCode() {
        VerificationCodeResponse response = new VerificationCodeResponse();
        String token = UUID.randomUUID().toString().replace("-", "");
        String base64 = "data:image/gif;base64,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";
        response.setToken(token);
        response.setBase64(base64);
        try {
            redisTemplate.opsForValue().set(Constants.RedisConstant.VERIFICATION_CODE_PREFIX + token, "1111", 60, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error(">>缓存验证码到Redis异常:{}", e);
            throw new ServerException(BaseResponseStatusEnums.VerificationCodeEnum.CACHE_ERROR);
        }
        return response;
    }

    /**
     * 登录
     * @param loginRequest 登录请求实体
     * @return 登录返回实体
     */
    @Override
    public String login(LoginRequest loginRequest) {
        //  先检查验证码是否正确
        checkVerificationCode(loginRequest.getToken(), loginRequest.getValue());
        //  执行登录
        UserEntity entity = userMapper.selectByName(loginRequest.getName());
        if (null == entity) {
            throw new ClientException(BaseResponseStatusEnums.UserEnum.USER_NOT_FOUND_ERROR);
        }
        if (!loginRequest.getPassword().equals(entity.getPassword())) {
            throw new ClientException(BaseResponseStatusEnums.UserEnum.PASSWORD_NOT_MATCH_ERROR);
        }
        String token = "loginUser:accessToken";
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        try {
            operations.set(token, entity, 180, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("缓存用户到Redis异常", e);
            throw new ServerException(BaseResponseStatusEnums.SystemEnum.INTERVAL_SERVER_ERROR);
        }
        return token;
    }

    /**
     * 检查验证码是否正确
     * @param token 验证码标识
     * @param value 用户输入的验证码值
     */
    private void checkVerificationCode(String token, String value) {
        String redisImageValue;
        String key = Constants.RedisConstant.VERIFICATION_CODE_PREFIX + token;
        try {
            redisImageValue = (String) redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error(">>从Redis获取验证码异常:{}", e);
            throw new ServerException(BaseResponseStatusEnums.SystemEnum.INTERVAL_SERVER_ERROR);
        }
        if (null == redisImageValue) {
            log.info("从Redis中未获取到验证码");
            throw new ClientException(BaseResponseStatusEnums.VerificationCodeEnum.NOT_FOUND_ERROR);
        }
        if (!redisImageValue.equalsIgnoreCase(value)) {
            //  将验证码清除
            try {
                redisTemplate.opsForValue().getOperations().delete(key);
            } catch (Exception e) {
                log.error("从Redis删除验证码异常：{}", e);
            }
            throw new ClientException(BaseResponseStatusEnums.VerificationCodeEnum.NOT_MATCH_ERROR);
        }
    }

}