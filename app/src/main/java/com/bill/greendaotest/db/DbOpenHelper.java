package com.bill.greendaotest.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bill.greendaotest.greendao.DaoMaster;
import com.bill.greendaotest.greendao.TeacherEntityDao;

import org.greenrobot.greendao.database.Database;

public class DbOpenHelper extends DaoMaster.OpenHelper {

    public DbOpenHelper(Context context, String name) {
        super(context, name);
    }

    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.e("Bill", "onUpgrade oldVersion:" + oldVersion + " newVersion:" + newVersion);
        super.onUpgrade(db, oldVersion, newVersion);

        // 比修改TeacherEntity，在TeacherEntity中新加一个字段subject（之前没有），则就可以这么写
        /*if (oldVersion == 1 && newVersion == 2) {
            alterColumn(db, TeacherEntityDao.TABLENAME, "SUBJECT", " VARCHAR(50) ");
            alterColumn(db, TeacherEntityDao.TABLENAME, "AGE", " INTEGER default 20 ");
        }*/

    }

    private void alterColumn(Database db, String table, String column, String type) {
        if (isTableExist(db, table)) {
            if (!isColumnExist(db, table, column)) {
                String new_column = "alter table " + table + " add " + column + type;
                db.execSQL(new_column);
            }
        }
    }

    private boolean isTableExist(Database db, String tableName) {
        boolean result = false;
        if (tableName == null) {
            return false;
        }

        Cursor cursor = null;
        try {
            String sql = "select count(*) as c from Sqlite_master where type ='table' and name ='" + tableName.trim()
                    + "' ";
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    result = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cursor.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return result;
    }

    private static boolean isColumnExist(Database db, String tableName, String columnName) {
        boolean result;
        Cursor cursor = null;
        try {
            //查询一行
            cursor = db.rawQuery("SELECT * FROM " + tableName + " LIMIT 0", null);
            result = cursor != null && cursor.getColumnIndex(columnName) != -1;
        } catch (Exception e) {
            result = false;
        } finally {
            if (null != cursor && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return result;
    }

}
