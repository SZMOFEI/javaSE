package com.mofei.skyway.device.service;

import com.mofei.skyway.device.entity.SkywayDevice;
import com.mofei.skyway.device.mapper.SkywayDeviceMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/**
 * @author mofei
 * @date 2020/12/29 20:22
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class SkywayDeviceServiceTest {

    @InjectMocks //注入这个service类
    private SkywayDeviceService skywayDeviceService;

    @Mock
    private SkywayDeviceMapper skywayDeviceMapper;



    @Test
    public void getByID() {
        when(skywayDeviceService.getById(any())).thenReturn(new SkywayDevice("1609126911672", "1609126911672","mofei"));
        SkywayDevice byId = skywayDeviceService.getById("1609126911671");
        Assert.assertEquals("1609126911672",byId.getId());
    }
}
