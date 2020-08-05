# 项目管理后台

两种方式访问。
## 1. 本地部署
默认端口：8001
```shell
$ cd target
$ java -jar java -jar pm-backend-1.0-SNAPSHOT.jar
```



## 2. 云服务器
已使用github action实现CI/CD，部署在云服务器上: 47.97.193.41



## example

### request

POST http://localhost:8001/api/register

```json  {.line-numbers}
{
    "username": "username",
    "password": "password",
    "email": "example@mail.com"
}
```
### response 

```json  {.line-numbers}
{
    "msg": "success",
    "code": 0,
    "user": {
        "userId": 26,
        "username": "username",
        "password": "password",
        "email": "example@mail.com",
        "mobile": null
    }
}
```

## 测试
在CI/CD流水线上，Junit单元测试，jacoco统计覆盖率，然后将测试结果上传到[codecov](https://www.codecov.io/)

## 代码覆盖率

![代码覆盖率](assets/cov.png)