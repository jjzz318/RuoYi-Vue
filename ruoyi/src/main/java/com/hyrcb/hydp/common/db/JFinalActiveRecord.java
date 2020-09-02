/**
 * File Name: JFinalActiveRecord.java
 * Date: 2020-07-10 18:42:59
 */
package com.hyrcb.hydp.common.db;

import com.hyrcb.hydp.common.core.BeanFactory;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import me.belucky.easytool.task.AbstractTask;


/**
 * Description: 引入JFinal的Avtive Record
 * @author shenzulun
 * @date 2020-07-10
 * @version 1.0
 */
public class JFinalActiveRecord extends AbstractTask{
	
	public JFinalActiveRecord(String taskName) {
		super(taskName);
	}

	public void execute() {
		this.masterDb();
		this.oldDb();
	}
	public void masterDb(){
		DbProperties prop = BeanFactory.getBean(DbProperties.class);
		DruidPlugin druidPlugin = new DruidPlugin(prop.getUrl(), prop.getUsername(), prop.getPassword(),prop.getDriverClassName());
		druidPlugin.start();
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		arp.setShowSql(true);
		arp.setDialect(new AnsiSqlDialect());
		arp.start();
	}
	public void oldDb(){
		DbPropertiesOld prop = BeanFactory.getBean(DbPropertiesOld.class);
		DruidPlugin druidPlugin = new DruidPlugin(prop.getUrl(), prop.getUsername(), prop.getPassword(),prop.getDriverClassName());
		druidPlugin.start();
		ActiveRecordPlugin arp = new ActiveRecordPlugin("oldDb",druidPlugin);
		arp.setShowSql(true);
		arp.setDialect(new AnsiSqlDialect());
		arp.start();
	}
}
