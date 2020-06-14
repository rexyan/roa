package com.yanrs.mpbdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanrs.mpbdemo.entity.User;
import com.yanrs.mpbdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class MpbDemoApplicationTests {
    @Autowired
    private UserMapper userMapper;

    /**
     * 测试查询
     */
    @Test
    void testSelectList() {
        // 查询所有 user 信息
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    /**
     * 测试插入，主键生成策略
     */
    @Test
    void addUser(){
        User user = new User();
        user.setAge(10);
        user.setEmail("zhaoliu@gmail.com");
        user.setName("zhaoliu");

        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    void updateUser(){
        User user = new User();
        user.setId(1271700428140089345L);
        user.setName("new name");
        // 更新 id 为 1271700428140089345 的 name 为 new name，并观察 update_time
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    @Test
    void testOptimisticLocker(){
        // 查询用户 version 信息
        User user = userMapper.selectById(1271729094970646529L);
        // 更新用户信息
        user.setName("new Name");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    @Test
    void testSelectById(){
        User user = userMapper.selectById(1271729094970646529L);
        System.out.println(user);
    }

    @Test
    void testSelectBatchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1271729094970646529L, 1271700428140089345L));
        System.out.println(users);
    }

    @Test
    void testSelectByMap(){
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("name", "zhangsan");
        // 注意：map中的key对应的是数据库中的列名。例如数据库user_id，实体类是userId，这时map的key需要填写user_id
        condition.put("create_time", new Date());
        List<User> users = userMapper.selectByMap(condition);
        System.out.println(users);
    }


    @Test
    void testPageObject(){
        // 第一个参数是当前页，第二个参数为每页大小
        Page<User> page = new Page<>(1, 3);
        // 第一个参数是 page 对象，第二是查询条件，这里是对所有数据分页，所有 null 就行
        userMapper.selectPage(page, null);

        System.out.println(page.getCurrent());  // 当前页
        System.out.println(page.getPages());  // 可以分多少页
        System.out.println(page.getRecords());  // 分页数据
        System.out.println(page.getSize());  // 每页数据大小
        System.out.println(page.getTotal());  // 总记录数
        System.out.println(page.hasNext());  // 是否有下一页
        System.out.println(page.hasPrevious());  // 是否有上一页
    }

    @Test
    void testPageMap(){
        // 第一个参数是当前页，第二个参数为每页大小
        Page<User> page = new Page<>(1, 3);
        // 第一个参数是 page 对象，第二是查询条件，这里是对所有数据分页，所有 null 就行
        userMapper.selectMapsPage(page, null);

        System.out.println(page.getCurrent());  // 当前页
        System.out.println(page.getPages());  // 可以分多少页
        System.out.println(page.getRecords());  // 分页数据
        System.out.println(page.getSize());  // 每页数据大小
        System.out.println(page.getTotal());  // 总记录数
        System.out.println(page.hasNext());  // 是否有下一页
        System.out.println(page.hasPrevious());  // 是否有上一页
    }

    @Test
    void testDeleteById(){
        userMapper.deleteById(1L);
    }

    @Test
    void testDeleteBatchIds(){
        userMapper.deleteBatchIds(Arrays.asList(2L, 3L));
    }

    @Test
    void testDeleteByMap(){
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("name", "zhangsan");
        userMapper.deleteByMap(condition);
    }

    @Test
    void testDeleteByMapLogic(){
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("name", "zhaoliu");
        userMapper.deleteByMap(condition);
    }

    @Test
    void testQueryWrapper(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "age");
        queryWrapper.eq("name", "zhangsan");
        queryWrapper.gt("age", 12);
        List<User> users = userMapper.selectList(queryWrapper);
        for (User user:users) {
            System.out.println(user);
        }
    }
}
