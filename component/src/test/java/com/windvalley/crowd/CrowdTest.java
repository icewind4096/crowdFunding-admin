package com.windvalley.crowd;

import com.windvalley.crowd.entity.TAdmin;
import com.windvalley.crowd.mapper.TAdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml"})
public class CrowdTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private TAdminMapper adminMapper;

    @Test
    public void testInsertAdmin(){
        TAdmin admin = new TAdmin();
        admin.setUserName("汤姆");
        admin.setLoginAcct("tom");
        admin.setEmail("tom@qq.com");
        admin.setUserPhone("139xxxxxxxx");
        admin.setUserOpenid("tom4096");
        admin.setUserPswd("tomkey");
        int effectCount = adminMapper.insert(admin);
        System.out.println("Effect Count：" + effectCount);
    }

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}