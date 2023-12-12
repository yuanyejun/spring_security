package com.security.spring_security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.security.spring_security.entity.Menu;

import java.util.List;

/**
 * @Author 原野
 * @DATE 2023/9/8 10:23
 * @Description:
 * @Version 1.0
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);

}
