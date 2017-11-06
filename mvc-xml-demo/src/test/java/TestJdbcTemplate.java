import com.twx.db.MyJdbcRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by twx on 2017/11/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml",
        "classpath:dispatcher-servlet.xml"
})
public class TestJdbcTemplate {

    @Autowired
    private MyJdbcRepository jdbcRepository;

    @Test
    public void testFindOne() {
        jdbcRepository.find();
    }


}
