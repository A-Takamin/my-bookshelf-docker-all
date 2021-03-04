package com.example.mybookshelf.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;
/**
 * 送られてくるuser情報
 * 今はnameしかないが、まぁ将来の拡張性のためにクラスを作っておく
 */
@Data
public class UserForm {
    
    private String name;
}
