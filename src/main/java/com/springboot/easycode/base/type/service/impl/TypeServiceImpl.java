package com.springboot.easycode.base.type.service.impl;

import com.springboot.easycode.base.type.entity.Type;
import com.springboot.easycode.base.type.dao.TypeDao;
import com.springboot.easycode.base.type.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Type)表服务实现类
 *
 * @author hyhong
 * @since 2020-05-17 16:15:06
 */
@Service("typeService")
public class TypeServiceImpl implements TypeService {
    @Resource
    private TypeDao typeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param typeId 主键
     * @return 实例对象
     */
    @Override
    public Type queryById(Long typeId) {
        return this.typeDao.queryById(typeId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Type> queryAllByLimit(int offset, int limit) {
        return this.typeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    @Override
    public Type insert(Type type) {
        this.typeDao.insert(type);
        return type;
    }

    /**
     * 修改数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    @Override
    public Type update(Type type) {
        this.typeDao.update(type);
        return this.queryById(type.getTypeId());
    }

    /**
     * 通过主键删除数据
     *
     * @param typeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long typeId) {
        return this.typeDao.deleteById(typeId) > 0;
    }
}