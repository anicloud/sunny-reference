## 概述
**Sunny**是Anicloud平台开发的示例第三方应用（服务）。第三方应用（服务）通过**ServiceBus**接入Anicloud平台，实现对对象Stub的操作。在**Sunny**中要实现对平台提供的**Service-Agent** SDK的调用，并在基础Stub上抽象了**Feature**与**Strategy**两类跟实际应用相关的对象。**Sunny**主要包括Stub-to-Feature的抽象定义；Feature-to-Strategy的基于触发的抽象定义；应用-用户状态维护；用户信息初始化；基于**Service-Agent**的Stub方法调用；基于**ClientInvokable**接口的平台对第三方应用的调用。**Sunny**还应实现基于平台的消息订阅，实现对用户设备信息的动态更新。

## 名词解释
* __Stub__ 
 
 >Anicloud平台定义的对象功能的抽象描述类。可以由设备提供或者第三方应用（服务）提供。都需要提前在Acniloud平台进行注册，并关联到对应的对象上。

* __Feature__ 
 
 >Sunny应用对底层Stub的进一步抽象，代表一个完整的功能。开发者需要在Sunny中的配置文件进行配置。将用户的Stub列表映射成Feature，形成用户的Feature列表。

* __Strategy__ 

 >Feature的基于触发器（Trigger）的集合。多个Feature在某些条件下被触发执行的一个序列。Strategy重新定义了Stub的上层抽象使用场景，提供了各种可能性。

* __Trigger__ 
 
 >Feature的触发条件，即在满足某种Trigger的条件下，Feature会自动执行。
 
## 功能列表
### Feature的定义
Feature是由第三方应用（服务）的开发者在开发第三方应用时针对所需要调用的Stub设计好的。应用开发者针对需要调用的Stub进行进一步的抽象，在此基础上生成Sunny可以支持的Feature列表。当用户授信玩第三方应用后，对用户进行初始化时，需要根据Feature列表识别能够处理的用户设备。

### Strategy的管理

### 基于OAuth2.0的授信

### 用户信息初始化

### 应用信息注册

### Strategy调度系统

### Sunny系统注册的Stub回调

## 设计实现
### 技术选择
* Spring Framework
* JMS
* AngularJS
* Hibernate JPA
* Quartz

### 系统设计

### 领域对象设计

### 核心对外接口

### 核心业务类

### 外部接口

### Service-Agent调用

## 部署要求

## 引用参考
