package com.cfg.base;
import com.cfg.base.task.ApplicationStatusTask;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName ApplicationStatusTask
 * @Description 应用存活状态注册任务
 * @Author chenfg
 * @Date 2023/4/18 14:10
 */
@Component
public class ApplicationStartTask implements CommandLineRunner, DisposableBean {

	@Autowired
	private ApplicationStatusTask applicationStatusTask;

	@Override
	public void destroy() throws Exception {
		applicationStatusTask.closeTask();
	}

	@Override
	public void run(String... args) throws Exception {
		Thread statusTaskThread = new Thread(applicationStatusTask);
		statusTaskThread.start();
	}

}
