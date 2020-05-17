package com.springboot.easycode.base.type.controller;

import com.springboot.easycode.base.type.entity.Type;
import com.springboot.easycode.base.type.service.TypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Type)表控制层
 *
 * @author hyhong
 * @since 2020-05-17 16:15:06
 */
@RestController
@RequestMapping("type")
public class TypeController {
    /**
     * 服务对象
     */
    @Resource
    private TypeService typeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Type selectOne(Long id) {
        return this.typeService.queryById(id);
    }

}