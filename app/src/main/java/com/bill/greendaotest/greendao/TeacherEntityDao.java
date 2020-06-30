package com.bill.greendaotest.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.bill.greendaotest.db.StudentsListConverter;
import java.util.List;

import com.bill.greendaotest.db.TeacherEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TEACHER_ENTITY".
*/
public class TeacherEntityDao extends AbstractDao<TeacherEntity, Long> {

    public static final String TABLENAME = "TEACHER_ENTITY";

    /**
     * Properties of entity TeacherEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property School = new Property(2, String.class, "school", false, "SCHOOL");
        public final static Property Subject = new Property(3, String.class, "subject", false, "SUBJECT");
        public final static Property Students = new Property(4, String.class, "students", false, "STUDENTS");
    }

    private final StudentsListConverter studentsConverter = new StudentsListConverter();

    public TeacherEntityDao(DaoConfig config) {
        super(config);
    }
    
    public TeacherEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TEACHER_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"SCHOOL\" TEXT," + // 2: school
                "\"SUBJECT\" TEXT," + // 3: subject
                "\"STUDENTS\" TEXT);"); // 4: students
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TEACHER_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TeacherEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String school = entity.getSchool();
        if (school != null) {
            stmt.bindString(3, school);
        }
 
        String subject = entity.getSubject();
        if (subject != null) {
            stmt.bindString(4, subject);
        }
 
        List students = entity.getStudents();
        if (students != null) {
            stmt.bindString(5, studentsConverter.convertToDatabaseValue(students));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TeacherEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String school = entity.getSchool();
        if (school != null) {
            stmt.bindString(3, school);
        }
 
        String subject = entity.getSubject();
        if (subject != null) {
            stmt.bindString(4, subject);
        }
 
        List students = entity.getStudents();
        if (students != null) {
            stmt.bindString(5, studentsConverter.convertToDatabaseValue(students));
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public TeacherEntity readEntity(Cursor cursor, int offset) {
        TeacherEntity entity = new TeacherEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // school
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // subject
            cursor.isNull(offset + 4) ? null : studentsConverter.convertToEntityProperty(cursor.getString(offset + 4)) // students
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TeacherEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSchool(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSubject(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setStudents(cursor.isNull(offset + 4) ? null : studentsConverter.convertToEntityProperty(cursor.getString(offset + 4)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(TeacherEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(TeacherEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TeacherEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
