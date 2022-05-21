package com.shine;

import com.shine.service.FileSystemService;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Resource
	private FileSystemService fileSystemService;


	@Test
	public void contextLoads() throws Exception {
		fileSystemService.downloadFile();
	}





	public static void main(String[] args) {
       new Thread(new Runnable() {
		   @Override
		   public void run() {
			   System.out.println("hello world");
		   }
	   }).start();



//		new Thread(
//				() -> {System.out.println("Thread run1()");
//					System.out.println("Thread run2()");}
//		).start();
//
//		new Thread(){
//
//		}

		new Thread(
				() -> System.out.println("hello wwwww")).start();
	}





}
