# Jetpack

该模块是可独立运行的app，包含Jetpack各组件的使用示例

## Room

操作数据库的组件

## Hilt

依赖注入组件

- 普通对象注入：构造方法上加@Inject
- 第三方组件注入：无法在构造方法上加注解，必须创建工具类来实现
- ViewModel组件注入

## 协程

优雅地实现异步任务

- Global.launch: 顶层协程，实际开发中很少使用
- runBlocking：不推荐在正式环境中使用
- CoroutineScope：实际项目中较常用的方式，但无法获取协程的执行结果
- async：可以获取结果
- withContext：可以获取结果，并且可以切换协程环境