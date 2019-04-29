```
   ___   _   _     _             _                ___    _                 _____          _   _
  / __| (_) | |_  | |     __ _  | |__     ___    |   \  (_)  _ _    __ _  |_   _|  __ _  | | | |__
 | (_ | | | |  _| | |__  / _` | | '_ \   |___|   | |) | | | | ' \  / _` |   | |   / _` | | | | / /
  \___| |_|  \__| |____| \__,_| |_.__/           |___/  |_| |_||_| \__, |   |_|   \__,_| |_| |_\_\
                                                                   |___/
```

## 概要

解决钉钉自带的Gitlab机器人无法@钉钉用户的问题

### 功能实现

- 提供Webhook接口 `/webhook/v1/send`，对接Gitlab Webhook，然后通过钉钉API推送钉钉消息（markdown类型消息）
- 目前完成的Gitlab事件只限：Push Event 和 Merge Request Event
- 推送Merge Request事件可以指定@钉钉用户（需要数据库中配置Gitlab用户在钉钉注册的手机号）

### 线上运行环境

- SpringBoot 1.5.19.RELEASE
- MySQL 5.7
- Gitlab 11.5.3-ee

### 本地开发环境

- 本地运行 Docker for Gitlab

```
$ docker run --detach \
             --hostname gitlab.example.com \
             --publish 443:443 \
             --publish 80:80 \
             --publish 22:22 \  
             --name gitlab \  
             --restart always \
             gitlab/gitlab-ce:latest
```

- 安装 [ngrok](https://ngrok.com/)

该工具允许生成一个在线URL映射到本地的Webhook API地址（Gitlab Webhook URL不允许设置为localhost）

```
$ ./ngrok http 8088

ngrok by @inconshreveable                                                                                                                                                                                                                                     (Ctrl+C to quit)

Session Status                online
Account                       KUANGSHI YAN (Plan: Free)
Version                       2.3.27
Region                        United States (us)
Web Interface                 http://127.0.0.1:4040
Forwarding                    http://bd7c5c4e.ngrok.io -> http://localhost:8088
Forwarding                    https://bd7c5c4e.ngrok.io -> http://localhost:8088

Connections                   ttl     opn     rt1     rt5     p50     p90
                              26      0       0.00    0.00    0.35    1.13
```

### TODO

- 提供WEB前端页面运行管理员录入Gitlab用户在钉钉注册的手机号

### 参考

- 钉钉群机器人：https://open-doc.dingtalk.com/microapp/serverapi3/pghqkk
- Gitlab Webhook API：https://docs.gitlab.com/ee/user/project/integrations/webhooks.html#overview
- Gitlab API wrapper：https://github.com/gmessner/gitlab4j-api
