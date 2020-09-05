package cn.sj.test;

import cn.sj.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBaties {
    //查询
    @Test
    public void selectUserTest() throws IOException {
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工程对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作 参数：namespace + id
        List<Object> userList = sqlSession.selectList("userMapper.findAll");
        //打印数据
        System.out.println(userList);
        //释放资源
        sqlSession.close();
    }
    
    //添加操作
    @Test
    public void InsertUserTest() throws IOException {
        //模拟user对象
        User user = new User();
        user.setUsername("tom");
        user.setPassword("1234");
        
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工程对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作 参数：namespace + id
        sqlSession.insert("userMapper.save",user);
        //mybaties执行更新操作 提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }
    
    //修改
    @Test
    public void updateUserTest() throws IOException {
        //模拟user对象
        User user = new User();
        user.setId(2);
        user.setUsername("sss");
        user.setPassword("1234");
    
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工程对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作 参数：namespace + id
        sqlSession.update("userMapper.update",user);
        //mybaties执行更新操作 提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }
}
