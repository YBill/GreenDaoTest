package com.bill.greendaotest.db;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

public class StudentsListConverter implements PropertyConverter<List<StudentEntity>, String> {

    @Override
    public List<StudentEntity> convertToEntityProperty(String databaseValue) {
        if (TextUtils.isEmpty(databaseValue))
            return null;

        return new Gson().fromJson(databaseValue, new TypeToken<List<StudentEntity>>() {
        }.getType());
    }

    @Override
    public String convertToDatabaseValue(List<StudentEntity> entityProperty) {
        if (entityProperty == null || entityProperty.isEmpty())
            return "";

        return new Gson().toJson(entityProperty);

    }
}
