package com.pjmike.mybatis.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pjmike.mybatis.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    private static final Logger logger = LoggerFactory.getLogger(UserMapperTest.class);
    @Autowired
    private UserMapper userMapper;
    @Test
    public void userTest() {
        User user = new User();
        user.setUsername("pjmike");
        user.setPassword("123456");
        userMapper.insert(user);
        logger.info("查询插入的用户信息: {}",userMapper.selectOne(user));

        //模拟分页
        for (int i = 0; i < 20; i++) {
            userMapper.insertSelective(new User("pjmike" + i, "123456" + i));
        }
        PageInfo<User> pageInfo = PageHelper.startPage(1, 10).setOrderBy("id desc").doSelectPageInfo(() -> this.userMapper.selectAll());
        logger.info("[lambda写法]-[分页信息]-[{}]", pageInfo.toString());

        PageHelper.startPage(1, 10).setOrderBy("id desc");
        PageInfo<User> pageInfo1 = new PageInfo<>(this.userMapper.selectAll());
        logger.info("[普通写法]-[分页信息]-[{}]",pageInfo1.toString());
    }
}