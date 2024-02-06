## 说明

`ddd-tmp`是一个用于演示领域驱动设计架构的项目。

在阅读这个项目之前，你需要先了解领域驱动设计的基本概念，包括聚合、实体、值对象等。

我推荐你阅读一些资料来了解领域驱动设计：

- 《实现领域驱动设计》
- 《解构领域驱动设计》
- 《复杂软件设计之道：领域驱动设计全面解析与实战》
- 《领域驱动设计：软件核心复杂性应对之道》
- 《领域驱动设计模式、原理与实践》
- 《深入浅出DDD》(掘金小册)
- 《架构整洁之道》

推荐了解完这些资料后再来阅读这个项目，同时你还需要了解一些知识：

- Event Sourcing(事件溯源)
- CQRS(命令查询职责分离)
- Event Storming(事件风暴)
- Event Driven Architecture(事件驱动架构)
- Dependency Inversion Principle(依赖倒置原则)

## 运行

导入`db`文件夹中的`db.sql`数据库文件，然后修改`user-start`中的`application-yml`文件中的数据库连接配置。

然后运行`UserAppStart`即可。

## 结构

```
├── LICENSE
├── README.md
├── db                      数据库文件
├── pom.xml
├── user-api                用户API抽象模块
├── user-application        用户应用模块
├── user-common             用户公共模块
├── user-domain             用户领域模块
├── user-infrastructure     用户基础设施模块
├── user-interaction        用户交互模块
├── user-rpc                用户RPC抽象模块
└── user-start              用户项目启动模块
```

## 阅读

在每个包中存在一份`package-info.java`，其中对当前包结构有说明。
