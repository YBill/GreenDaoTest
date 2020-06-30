package com.bill.greendaotest.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Entity 修饰类后这个类会变成一个数据库表，默认表名就是实体类名称，可以自定义;
 * 点击 Make Project 后自动生成代码 DaoMaster和DaoSession是所有表公共生成的一个，然后每一个Entity修饰的都会生成一个Dao(如StudentEntityDao)
 * Id 主键
 * Transient 表示不参与数据库字段
 * Generated(Keep被Generated替代) GreenDao自动生成的代码，每次修改实体类后，建议删除 @Generated 修饰的代码重新生成
 */

@Entity()
public class StudentEntity {

    @Id(autoincrement = false)
    private String id;

    private String name;

    private String school;

    @Transient
    public int sex;

    @Generated(hash = 634333355)
    public StudentEntity() {
    }

    @Generated(hash = 883490550)
    public StudentEntity(String id, String name, String school) {
        this.id = id;
        this.name = name;
        this.school = school;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return this.school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
