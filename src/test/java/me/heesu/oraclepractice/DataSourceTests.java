package me.heesu.oraclepractice;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

@RunWith(SpringJUnit4ClassRunner.class)
public class DataSourceTests {
    private static final Logger logger = LoggerFactory.getLogger(OraclePracticeApplication.class);

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testCon(){
        try( Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:49161/xe", "system",
                "oracle")){
            logger.info("" + con);
            logger.info("####################");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testMyBatis() {

    }
}
