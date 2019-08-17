package cn.bite.travel.dao.impl;

import cn.bite.travel.dao.UserDao;
import cn.bite.travel.domain.User;
import cn.bite.travel.util.JDBCUtils;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findByUsername(String username) {
        String sql="select * from tab_user where username=?";

        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        return user;
    }

    @Override
    public void save(User user) {

        String sql="insert into tab_User(username,password,name,birthday,sex,temephont,email) value(?,?,?,?,?,?.?)";

        template.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail()
                );
    }
}
