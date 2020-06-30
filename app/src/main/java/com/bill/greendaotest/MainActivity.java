package com.bill.greendaotest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bill.greendaotest.db.DbHelper;
import com.bill.greendaotest.db.StudentEntity;
import com.bill.greendaotest.greendao.StudentEntityDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText editText;

    private RecyclerView mRv;
    private MyAdapter myAdapter;
    private List<MyBean> mDataList = new ArrayList<>();
    private int mSelectPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.et_content);
        mRv = findViewById(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter();
        mRv.setAdapter(myAdapter);

        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                mSelectPosition = position;
                MyBean bean = mDataList.get(mSelectPosition);

//                List<StudentEntity> list = DbHelper.getInstance().queryList(StudentEntityDao.Properties.Id.eq(bean.id), StudentEntity.class, null);
//                StudentEntity entity = DbHelper.getInstance().getDaoSession().getStudentEntityDao().load(bean.id);
//                for (StudentEntity studentEntity : list) {
//                    Log.e("Bill", studentEntity.getId()+"|"+studentEntity.getName()+"|"+studentEntity.getSchool());
//                }

                editText.setText(bean.id + "," + bean.title + "," + bean.content);
            }
        });

        searchData();
    }

    private MyBean coverBean(StudentEntity entity) {
        MyBean bean = new MyBean();
        bean.id = entity.getId();
        bean.title = entity.getName();
        bean.content = entity.getSchool();
        return bean;
    }

    private void searchData() {
        mDataList.clear();

//        List<StudentEntity> list = DbHelper.getInstance().queryList(null, StudentEntity.class, null);
        List<StudentEntity> list = DbHelper.getInstance().getDaoSession().getStudentEntityDao().loadAll();

        if (list != null)
            for (StudentEntity entity : list) {
                mDataList.add(coverBean(entity));
            }

        if (!mDataList.isEmpty()) {
            mSelectPosition = 0;
            MyBean bean = mDataList.get(mSelectPosition);
            bean.isSelect = true;
            editText.setText(bean.id + "," + bean.title + "," + bean.content);
        }

        myAdapter.setDataList(mDataList);
    }

    private String[] getInputStr() {
        String[] str = null;
        try {
            String content = editText.getText().toString();
            str = content.split(",");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }

    public void handleInsert(View view) {
        String[] str = getInputStr();
        if (str == null || str.length != 3) {
            Toast.makeText(this, "输入格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        StudentEntity entity = new StudentEntity();
        entity.setId(str[0]);
        entity.setName(str[1]);
        entity.setSchool(str[2]);

        DbHelper.getInstance().getDaoSession().insertOrReplace(entity);

        searchData();
    }

    public void handleUpdate(View view) {
        String[] str = getInputStr();
        if (str == null || str.length != 3) {
            Toast.makeText(this, "输入格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        StudentEntity entity = new StudentEntity();
        entity.setId(str[0]);
        entity.setName(str[1]);
        entity.setSchool(str[2]);

        DbHelper.getInstance().getDaoSession().update(entity);

        searchData();
    }

    public void handleDelete(View view) {
        if (mSelectPosition == -1)
            return;

        MyBean bean = mDataList.get(mSelectPosition);
        DbHelper.getInstance().getDaoSession().getStudentEntityDao().deleteByKey(bean.id);

        mSelectPosition = -1;
        searchData();
    }
}
