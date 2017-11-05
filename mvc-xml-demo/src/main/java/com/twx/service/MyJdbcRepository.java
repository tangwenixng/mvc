package com.twx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by twx on 2017/11/5.
 */
@Repository
public class MyJdbcRepository {

    private JdbcOperations jdbcOperations;

    @Autowired
    public MyJdbcRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void find() {
        String sql="select * from t_svl";
        List<Map<String, Object>> mapList = jdbcOperations.queryForList(sql);
        for (Map<String, Object> map : mapList) {
            Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                Object object = next.getValue();
                System.out.print(object+"　");
            }
        }
    }
}
