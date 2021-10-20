package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.board.reply.dao.ReplyDao;
import com.board.reply.dto.ReplyDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class ReplyTest {

	private static final Logger logger = LoggerFactory.getLogger(ReplyTest.class);
	
	@Autowired
	private ReplyDao replyDao;
	
	@Test
	public void testCrate() throws Exception {
		
		for (int i = 1; i <= 20; i++) {
			ReplyDto replyDto = new ReplyDto();
			replyDto.setBoardSeq(40);
			replyDto.setReplyText(i+"번째 댓글 입니다..");
			replyDto.setReplyWriter("user"+i);
			replyDao.create(replyDto);
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ i + "번째 댓글 생성 완료!");
		}
	}
	
	@Test
	public void testList() throws Exception {
		logger.info(replyDao.list(40).toString());
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 댓글 출력 완료!");

	}
	
	@Test
	public void TestUpdate() throws Exception {
		ReplyDto replyDto = new ReplyDto();
		replyDto.setReplyNo(2);
		replyDto.setReplyText("2번째 댓글 수정");
		replyDao.update(replyDto);
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 댓글 수정 완료!");
	}
	
	@Test
	public void testDelete() throws Exception {
		replyDao.delete(2);
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 댓글 삭제 완료!");
	}
}
