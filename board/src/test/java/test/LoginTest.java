package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.board.user.dao.UserDao;
import com.board.user.dto.LoginDto;
import com.board.user.dto.UserDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class LoginTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ReplyTest.class);

	@Autowired
	private UserDao userDao;
	
	@Test
	public void testLogin() throws Exception {
		LoginDto loginDto = new LoginDto();
		loginDto.setUserId("ezoo");
		String hashedPw = BCrypt.hashpw("1234", BCrypt.gensalt());
		loginDto.setUserPw(hashedPw);
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> loginPW" + loginDto.getUserPw());
		
		UserDto userDto = userDao.login(loginDto);
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> getUserPw" + userDto.getUserPw());
		logger.info(">>>>>>>>>>>>>>>>>>>>>" + !BCrypt.checkpw(loginDto.getUserPw(), userDto.getUserPw()));
		
	}


}
