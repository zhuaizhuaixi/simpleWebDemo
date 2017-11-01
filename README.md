# simpleWebDemo

##springboot+jsp+mybatis

####该项目所有配置在resources下.

- 数据库:MySQL
- 服务器、框架：springboot
- MVC： SpringMVC
- 数据库ORM： mybatis
- 前端页面： JSP

#### 后端所有文件在src main目录下的java.com.fzu.demo中

- common：项目的通用类、实体
- enums：枚举类存放
- web：项目业务逻辑调用

其中web中：

- controller：控制器，用于跳转前段请求的页面
- entity：ORM所需的实体
- mapper：mybatis映射类
- service：业务逻辑层，严格按照接口，实现的形式

#### 前端所有文件在webapp文件夹下

- css css文件统一存放处
- fonts 字体图标 iconfont 存放处
- image 项目所需图片存放处
- js　项目所需js 存放处
- WEB-INF 项目前端页面JSP及web.xml

