package com.litongjava.spring.boot.webmagic;

import com.litongjava.spring.boot.model.SmzdmModel;
import com.litongjava.spring.boot.services.SmzdmService;
import com.litongjava.spring.boot.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Slf4j
public class MysqlPipeline implements Pipeline {
  @Override
  public void process(ResultItems resultItems, Task task) {
    // 取出processor过程中保存的结果，和Map类似，取出的key为smzdm和blogs
    SmzdmModel smzdmModel = resultItems.get("smzdm");
    if (smzdmModel != null) {
      SmzdmService smzdmService = SpringContextUtils.getBean(SmzdmService.class);
      log.info("smzdmModel:{}", smzdmModel);
      smzdmService.save(smzdmModel);
    }
  }

}
