package com.abelyaev.balance.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BalanceInfo {

    private String id;
    private String fileName;
    private String result;
    private Date requestDate;
}
