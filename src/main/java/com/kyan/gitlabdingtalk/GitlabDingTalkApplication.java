package com.kyan.gitlabdingtalk;

import com.kyan.gitlabdingtalk.commons.property.GitlabProperties;
import com.kyan.gitlabdingtalk.commons.property.WebhookProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * SpringBoot应用入口
 *
 * @author kyan
 * @date 2019/04/25
 */
@SpringBootApplication
@EnableConfigurationProperties({WebhookProperties.class, GitlabProperties.class})
public class GitlabDingTalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitlabDingTalkApplication.class, args);
	}

}
