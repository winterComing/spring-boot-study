package com.unicom.boot.baseweb.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dengh
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    /**
    *   execute()执行DDL语句
    */
    public void createUserTable(){
        dataSourceTransactionManager.getTransaction(new DefaultTransactionDefinition());
        jdbcTemplate.execute(createUserSql);
    }

    /**
     *  update()执行，update, insert into, delete from语句
     */
    public int saveUser(User user){
        return jdbcTemplate.update(insertUserSql, user.getName(), user.getAge());
    }

    /**
     *  List<T> query(String, Object[], RowMapper<T>)
     *  其中mapRow()中的i表示 rowNum
     */
    public List<User> queryUserByName(String name){
        return jdbcTemplate.query(queryUserByNameSql, new Object[]{name}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
                return user;
            }
        });
    }

    /**
     *  void query(String, Object[], RowCallbackHandler)
     *  与RowMapper的一个区别是：如果查询的结果集很大，那么产生的List<T>就会很大，会很占内存，而RowCallbackHandler可以认为控制这个过程
     */
    public List<User> queryUserBetweenAge(String minAge, String maxAge){
        List<User> userList = new ArrayList<>();
        jdbcTemplate.query(queryUserBetweenAgeSql, new Object[]{minAge, maxAge}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
                userList.add(user);
            }
        });
        return userList;
    }

    /**
     *  queryForObject(String, ...org, Class<T>)
     */
    public User queryForXXX(String name){
        User user = jdbcTemplate.queryForObject(queryUserByNameSql, new Object[]{name}, User.class);
        return user;
    }

    /**
     * queryForList(String, ...org, Class<T>)
     */
    public List<User> queryForListXXX(String minAge, String maxAge){
        List<User> userList = jdbcTemplate.queryForList(queryUserBetweenAgeSql, new Object[]{minAge, maxAge}, User.class);
        return userList;
    }

    /**
     * queryForRowSet()，SqlRowSet在数据库连接断开后仍然可以获取数据，而ResultSet不行
     */
    public SqlRowSet queryForSqlRowSet(String name){
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryUserByNameSql, new Object[]{name});
        return sqlRowSet;
    }

    private final String createUserSql = "CREATE TABLE user\n" +
            "(\n" +
            "  id   INT AUTO_INCREMENT\n" +
            "    PRIMARY KEY,\n" +
            "  name VARCHAR(20) NOT NULL,\n" +
            "  age  INT         NOT NULL,\n" +
            "  CONSTRAINT user_name_uindex\n" +
            "  UNIQUE (name)\n" +
            ")\n" +
            "  ENGINE = InnoDB;";

    private final String insertUserSql = "insert into user(name, age) values(?, ?)";

    private final String queryUserByNameSql = "select * from user where name = ?";

    private final String queryUserBetweenAgeSql = "select * from user where age between ? and ?";
}
