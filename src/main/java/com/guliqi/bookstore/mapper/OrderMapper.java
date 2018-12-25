package com.guliqi.bookstore.mapper;

import com.guliqi.bookstore.model.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderMapper {
    @Delete({
            "delete from Order",
            "where order_id = #{order_id,jdbcType=VARCHAR}"
    })
    int deleteById(String order_id);

    @Update({
            "update Order",
            "set user_id = #{user_id,jdbcType=VARCHAR},",
            "book_id = #{book_id,jdbcType=VARCHAR},",
            "create_time = #{create_time,jdbcType=TIMESTAMP},",
            "amount = #{amount,jdbcType=INTEGER},",
            "address_id = #{address_id,jdbcType=VARCHAR},",
            "comments = #{comments,jdbcType=VARCHAR},",
            "state = #{state,jdbcType=VARCHAR},",
            "store_id = #{store_id,jdbcType=VARCHAR},",
            "payment = #{payment,jdbcType=REAL}",
            "where order_id = #{order_id,jdbcType=VARCHAR}"
    })
    int updateById(Order record);

    @Insert({
            "insert into Order (order_id, user_id, book_id, create_time, amount, address_id, comments, state, store_id, payment)",
            "values (#{order_id,jdbcType=VARCHAR}, #{user.user_id,jdbcType=VARCHAR}, ",
            "#{book.book_id,jdbcType=VARCHAR}, #{create_time,jdbcType=TIMESTAMP}, ",
            "#{amount,jdbcType=INTEGER}, #{address.address_id,jdbcType=VARCHAR}, ",
            "#{comments,jdbcType=VARCHAR}, #{state,jdbcType=VARCHAR}, ",
            "#{store.store_id,jdbcType=VARCHAR}, #{payment,jdbcType=REAL})"
    })
    int insert(Order record);

    @Select({"select * from Order where order_id = #{order_id,jdbcType=VARCHAR}"})
    @Results({
            @Result(column="order_id", property="order_id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="user_id", property="user", jdbcType=JdbcType.VARCHAR,
                    one = @One(select = "com.guliqi.bookstore.mapper.UserMapper.selectById",
                            fetchType = FetchType.LAZY)),
            @Result(column="book_id", property="book", jdbcType=JdbcType.VARCHAR,
                    one = @One(select = "com.guliqi.bookstore.mapper.BookMapper.selectById",
                            fetchType = FetchType.LAZY)),
            @Result(column="address_id", property="address", jdbcType=JdbcType.VARCHAR,
                    one = @One(select = "com.guliqi.bookstore.mapper.AddressMapper.selectById",
                            fetchType = FetchType.LAZY)),
            @Result(column="store_id", property="store", jdbcType=JdbcType.VARCHAR,
                    one = @One(select = "com.guliqi.bookstore.mapper.StoreMapper.selectById",
                            fetchType = FetchType.LAZY)),
    })
    Order selectById(String order_id);

    @Select({"select * from Order where user_id = #{user_id,jdbcType=VARCHAR}"})
    @Results({
            @Result(column="order_id", property="order_id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="user_id", property="user", jdbcType=JdbcType.VARCHAR,
                    one = @One(select = "com.guliqi.bookstore.mapper.UserMapper.selectById",
                            fetchType = FetchType.EAGER)),
            @Result(column="book_id", property="book", jdbcType=JdbcType.VARCHAR,
                    one = @One(select = "com.guliqi.bookstore.mapper.BookMapper.selectById",
                            fetchType = FetchType.LAZY)),
            @Result(column="address_id", property="address", jdbcType=JdbcType.VARCHAR,
                    one = @One(select = "com.guliqi.bookstore.mapper.AddressMapper.selectById",
                            fetchType = FetchType.LAZY)),
            @Result(column="store_id", property="store", jdbcType=JdbcType.VARCHAR,
                    one = @One(select = "com.guliqi.bookstore.mapper.StoreMapper.selectById",
                            fetchType = FetchType.LAZY)),
    })
    Set<Order> selectByUserId(String user_id);
}