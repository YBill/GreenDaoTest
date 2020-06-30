package com.bill.greendaotest.db;

import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bill.greendaotest.MyApplication;
import com.bill.greendaotest.greendao.DaoMaster;
import com.bill.greendaotest.greendao.DaoSession;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

public class DbHelper {

    private static class SingletonHolder {
        private final static DbHelper instance = new DbHelper();
    }

    public static DbHelper getInstance() {
        return SingletonHolder.instance;
    }

    private DaoSession mDaoSession;

    private DbHelper() {
//        DbOpenHelper mHelper = new DbOpenHelper(MyApplication.getContext(), "db_student"); // 如果需要自己处理数据库升级
        DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(MyApplication.getContext(), "db_student"); // GreenDao提供的数据库升级方案
        SQLiteDatabase mDatabase = mHelper.getWritableDatabase();
        DaoMaster mDaoMaster = new DaoMaster(mDatabase);
        mDaoSession = mDaoMaster.newSession();

    }

    // Session能获取到对应Bean的Dao，方便对数据库操作
    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    // 想直接写Sql语句了
    public Database getDatabase() {
        return mDaoSession.getDatabase();
    }

    // eg:DbHelper.getInstance().queryList(StudentEntityDao.Properties.Id.eq("id"), StudentEntity.class, StudentEntityDao.Properties.Name);
    public List queryList(@Nullable WhereCondition whereCondition, @NonNull Class<?> cls, @Nullable Property... properties) {
        return queryCondition(whereCondition, cls, properties);
    }

    private List queryCondition(WhereCondition whereCondition, Class<?> cls, Property... properties) {
        if (cls == null) {
            throw new RuntimeException("cls is null");
        }
        AbstractDao abstractDao = mDaoSession.getDao(cls);

        QueryBuilder queryBuilder = abstractDao.queryBuilder();
        if (whereCondition != null) {
            queryBuilder.where(whereCondition);
        }
        if (properties != null) {
            queryBuilder.orderAsc(properties);
        }

        Query greenDaoQuery = queryBuilder.build();
        return greenDaoQuery.list();
    }

    public void deleteList(@Nullable WhereCondition whereCondition, @NonNull Class<?> cls) {
        deleteCondition(whereCondition, cls);
    }


    private void deleteCondition(WhereCondition whereCondition, Class<?> cls) {
        if (cls == null) {
            throw new RuntimeException("cls is null");
        }
        AbstractDao abstractDao = mDaoSession.getDao(cls);

        QueryBuilder queryBuilder = abstractDao.queryBuilder();
        if (whereCondition != null) {
            queryBuilder.where(whereCondition);
        }

        DeleteQuery deleteQuery = queryBuilder.buildDelete();
        if (deleteQuery != null)
            deleteQuery.executeDeleteWithoutDetachingEntities();
    }

}
