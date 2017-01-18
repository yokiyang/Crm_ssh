package com.tz.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**工具类-创建SessionFactory,获取Session**/
public class HibernateUtil {
	//2.创建SessionFactory对象,重量级的,线程安全的.一般一个数据库只需要一个SessionFactory即可
	//可以用来创建Session对象,充当数据源的代理,采用的就是工厂模式.
	private static SessionFactory sf = null;
	//通过静态代码块来进行初始化sessionFactory
	static {
		try {
			//1.读取hibernate.cfg.xml的配置文件
			Configuration cfg = new Configuration();
			//2.调用configure()方法来读取src根目录下的hibernate.cfg.xml
			//configure(String resource);//读取指定路径下的hibernate.cfg.xml文件.
			cfg.configure();
			//Hibernate3.x写法,过时
			//sf = cfg.buildSessionFactory();

			//Hibernate4.x写法
			StandardServiceRegistry sr = new StandardServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).build();
			sf = cfg.buildSessionFactory(sr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//throw new RuntimeException("初始化SessionFactory失败!");
			throw e;
		}
	}
	
	/**
	 * 获取session
	 */
	public static Session getSession(){
		return sf==null?null:sf.openSession();
	}
	
	/**
	 * 关闭session
	 */
	public static void closeSession(Session ses){
		if(null!=ses){
			ses.close();
		}
	}
}
