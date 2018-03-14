package com.philip;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Setter
@Builder
@EqualsAndHashCode(of = {"orderType","orderNo","orderLineNo"})
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    Integer orderType;
    Integer orderNo;
    Integer orderLineNo;

    Integer qty;

    public static OrderDetail of(Map<String, Object> map){
        return OrderDetail.builder()
                .orderType((Integer)map.get("orderType"))
                .orderNo((Integer)map.get("orderNo"))
                .orderLineNo((Integer)map.get("orderLineNo"))
                .build();
    }

}
