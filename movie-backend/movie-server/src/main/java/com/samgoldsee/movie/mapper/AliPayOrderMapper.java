package com.samgoldsee.movie.mapper;

import com.samgoldsee.movie.annotation.AutoFill;
import com.samgoldsee.movie.entity.AliPayOrder;
import com.samgoldsee.movie.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AliPayOrderMapper {

    /**
     * 新增订单
     *
     * @param order 支付宝支付订单
     */
    @AutoFill(operation = OperationType.INSERT)
    @Insert("INSERT INTO `ali_pay_order` " +
            "(`trade_no`, `user_id`, `total_amount`, `subject`, `ali_pay_trade_no`, `status`, `create_time`, `update_time`) " +
            "VALUES (#{tradeNo}, #{userId}, #{totalAmount}, #{subject}, #{aliPayTradeNo}, #{status}, #{createTime}, #{updateTime})")
    void insert(AliPayOrder order);


    /**
     * 根据交易号获取订单对象
     *
     * @param tradeNo 支付宝交易号
     */
    @Select("SELECT * FROM `ali_pay_order` WHERE `trade_no` = #{tradeNo}")
    AliPayOrder getByTradeNo(String tradeNo);
}
