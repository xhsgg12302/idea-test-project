package _framework.crawler4j.mbts;

import _framework.crawler4j.mbts.mapper.StatsGovDataMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2021/12/16
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class Utils {

    private static HikariDataSource pool;
    private static InputStream inputStream = null;
    private static SqlSessionFactory sqlSessionFactory;
    
    static{
        pool = new HikariDataSource();
        pool.setJdbcUrl("jdbc:mysql://example:3306/test");
        pool.setUsername("**");
        pool.setPassword("*******");

        pool.setConnectionTimeout(60000);
        pool.setValidationTimeout(3000);

        pool.setIdleTimeout(30000);
        pool.setMaxLifetime(35000);
        //pool.setLeakDetectionThreshold(300000);
        pool.setMaximumPoolSize(100);
        pool.setMinimumIdle(10);
        pool.setConnectionInitSql("SELECT 1");
        try {
            inputStream = Resources.getResourceAsStream("_framework/crawler4j/mbts/config/ConfigSqlMapper.xml");
            XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(inputStream, null, null);
            Configuration parse = xmlConfigBuilder.parse();
            Environment environment = new Environment("development", new JdbcTransactionFactory(), pool);
            parse.setEnvironment(environment); 
            sqlSessionFactory =  new SqlSessionFactoryBuilder().build(parse);
        } catch (IOException e) { e.printStackTrace();}
    }

    public static void executeSqlInsert(List<StatsGovData> inserts){
        if(inserts.size() == 0){return;}
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        StatsGovDataMapper mapper = sqlSession.getMapper(StatsGovDataMapper.class);
        mapper.insertBatch(inserts);
        sqlSession.close();
    }
    
    public static String getFatherUrl(String origin){
        // "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/65/6543.html";
        if(StringUtils.isEmpty(origin)){ return origin;}
        return origin.substring(49);
    }
    
    public static String getSelfUrl(String url, String fatherUrl){
        if(StringUtils.isEmpty(url) || StringUtils.isEmpty(fatherUrl)){ return "";}
        return fatherUrl.replaceAll("(.*/).*$","$1" + url);
    }

    public static void main(String[] args) {
        /*SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println(sqlSession);
        StatsGovDataMapper userMapper = sqlSession.getMapper(StatsGovDataMapper.class);
        List<Map> students = userMapper.selectByName("zs");
        System.out.println(students);*/
        
        
        String temp = "2020/65/6543.html";
        
        String sub = "43/654325.html";

        temp = null;

        System.out.println();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StatsGovDataMapper mapper = sqlSession.getMapper(StatsGovDataMapper.class);
        List<Map> x = mapper.selectByName("X");
        sqlSession.close();
    }
}
