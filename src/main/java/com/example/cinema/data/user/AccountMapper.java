package com.example.cinema.data.user;

import com.example.cinema.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author huwen
 * @date 2019/3/23
 */
@Mapper
public interface AccountMapper {

    /**
     * 创建一个新的账号
     *
     * @param username 用户名
     * @param password 密码
     * @return 修改数量
     */
    int createNewAccount(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查找账号
     *
     * @param username 用户名
     * @return 对应用户
     */
    UserVO getAccountByName(@Param("username") String username);

    UserVO getAccountById(int id);
}
