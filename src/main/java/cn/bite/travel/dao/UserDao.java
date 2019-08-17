package cn.bite.travel.dao;

import cn.bite.travel.domain.User;

public interface UserDao {

    /**
     * 查找信息
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 保存信息
     * @param user
     */
    public void save(User user);

}
