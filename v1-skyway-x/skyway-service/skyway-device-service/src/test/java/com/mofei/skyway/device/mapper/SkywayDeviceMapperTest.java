package com.mofei.skyway.device.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mofei.skyway.device.entity.SkywayDevice;
import com.mofei.skyway.device.utils.MybatisPageUtil;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * insert
 * deleteById
 * deleteByMap
 * delete
 * deleteBatchIds
 * updateById
 * update
 * selectById
 * selectBatchIds
 * selectByMap
 * selectOne
 * selectCount
 * selectList
 * selectMaps
 * selectObjs
 * selectPage
 * selectMapsPage
 * @author mofei
 * @version 2020/12/28 9:51
 */
//@Transactional //增加事务,避免脏数据
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SkywayDeviceMapperTest {
    @Resource
    private SkywayDeviceMapper skywayDeviceMapper;

    private static SkywayDevice skywayDevice;

    @BeforeAll
    static void before() {
        long now = System.currentTimeMillis();
        skywayDevice = new SkywayDevice(now + "", "SN" + now, "mofei");
    }

    @Test
    void selectPage() {
        Page<SkywayDevice> page = MybatisPageUtil.getNewPage(2,2);
        QueryWrapper<SkywayDevice> queryWrapper = MybatisPageUtil.getNewQueryWrapper();
        Page<SkywayDevice> devicePage = skywayDeviceMapper.selectPage(page, queryWrapper);
        Assert.assertEquals(6,devicePage.getTotal());
    }

    @Test
    void delete() {
        LambdaQueryWrapper<SkywayDevice> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SkywayDevice::getCreateUsername,"mofei");
        int delete = skywayDeviceMapper.delete(queryWrapper);
        assertEquals(6,delete);
    }

    @Test
    void deleteByMap() {
        Map<String,Object > map = new HashMap<>();
        map.put("id","1609125626290");
        int result = skywayDeviceMapper.deleteByMap(map);
        assertEquals(1,result);
    }

    @Test
    void deleteById() {
        int result= skywayDeviceMapper.deleteById("1609125626290");
        assertEquals(1,result);
    }

    @Test
    void whenTableEmptyThenDeviceListIsNull() {
        List<SkywayDevice> deviceList = skywayDeviceMapper.selectList(new QueryWrapper<>());
        Assert.assertNotEquals(deviceList.size() , 0);
    }

    @Test
    void whenInsertADeviceThenResultIs1() {
        int insert = skywayDeviceMapper.insert(skywayDevice);
        assertEquals("插入数据测试失败", 1, insert);
    }
}