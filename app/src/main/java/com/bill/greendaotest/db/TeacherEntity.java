package com.bill.greendaotest.db;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.util.List;

/**
 * Convert GreenDao不支持存储List的，所有可以自己处理一下，将List转为json字符串存储
 */

@Entity
public class TeacherEntity {

    @Id(autoincrement = true)
    private Long id;

    private String name;

    private String school;

    private String subject;

    @Convert(columnType = String.class, converter = StudentsListConverter.class)
    private List<StudentEntity> students; // 学生

    @Generated(hash = 979429349)
    public TeacherEntity() {
    }

    @Generated(hash = 1691735601)
    public TeacherEntity(Long id, String name, String school, String subject,
                         List<StudentEntity> students) {
        this.id = id;
        this.name = name;
        this.school = school;
        this.subject = subject;
        this.students = students;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<StudentEntity> getStudents() {
        return this.students;
    }

    public void setStudents(List<StudentEntity> students) {
        this.students = students;
    }

}
