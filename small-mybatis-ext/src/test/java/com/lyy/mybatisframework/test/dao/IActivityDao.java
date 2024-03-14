package com.lyy.mybatisframework.test.dao;


import com.lyy.mybatisframework.plugin.impl.tableselect.TableSelect;
import com.lyy.mybatisframework.plugin.impl.tableselect.DBRouterStrategy;
import com.lyy.mybatisframework.test.po.Activity;

import java.util.List;

@DBRouterStrategy
public interface IActivityDao {

    @TableSelect(key = "activityId")
    Activity queryActivityById(Activity activity);

    List<Activity> queryActivity();


}
