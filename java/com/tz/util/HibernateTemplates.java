package com.tz.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**Hibernate模板**/
public class HibernateTemplates {

	public static Object execute(IHibernateCallBack hcb) {
		Object obj = null;
		Session ses = null;
		Transaction tx = null;
		try {
			//1.获取session
			ses = HibernateUtil.getSession();
			//2.打开事务
			tx = ses.beginTransaction();
			//3.执行CRUD操作..->由session对象
			obj = hcb.execute(ses);
			//4.提交事务
			tx.commit();
		} catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();//回滚事务
			}
			//e.printStackTrace();
			throw e;
		} finally {
			//关闭session
			HibernateUtil.closeSession(ses);
			//if (null != ses) {
				//ses.close();
			//}
		}
		return obj;
	}
}
