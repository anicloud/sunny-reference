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
### 应用信息注册
Sunny在能够授信前，首选需要在Anicloud平台注册其相关信息。平台将为其颁发唯一的ID和secret码，作为其之后接入平台的凭证。

### Feature的定义
Feature是由第三方应用（服务）的开发者在开发第三方应用时针对所需要调用的Stub设计好的。应用开发者针对需要调用的Stub进行进一步的抽象，在此基础上生成Sunny可以支持的Feature列表。当用户授信玩第三方应用后，对用户进行初始化时，需要根据Feature列表识别能够处理的用户设备。

### Strategy的管理
Strategy是在Feature的基础上，与各种Trigger混合生成的任务。系统在当前的设计中，有基于时间点的Trigger和基于Location的Trigger。Trigger同时是可以扩展的。Strategy是为具体的可定制的业务场景而定制的。

### 基于OAuth2.0的授信
Sunny的用户都是由在Anicloud平台注册的用户授信后得到的。Sunny系统需要通过**Service-Agent**提供的OAuth服务来实现授信的流程。目前对Sunny来说，是采用**Authorization Code**模式实现授信。

### 用户信息初始化
Sunny系统在用户授信后，将对用户的设备信息初始化。Sunny会先定义需要处理的Stub-to-Feature的映射关系，系统会根据这个关系生成用户-设备-Feature的关联管理。

### Strategy调度系统
Strategy调度系统是为Job的运行提供支撑的。Sunny将基于时间点触发的Trigger组成的Strategy提交给调度系统，由调度系统管理其运行。

### Sunny系统注册的Stub回调
Sunny作为第三方应用可以设计自己的Stub，然后在Anicloud平台注册。当用户授信给Sunny后，Sunny需要绑定其所有的Stub给对应的用户对象。这样平台就可以通过**Service-Agent**反向调用用户对象的Stub，实现平台对应用的调用。

### 用户对象（AccountObject）的状态维护
当用户授信给Sunny，并在Sunny上登录后，Anicloud平台需要维护Bus-App-Account对象的状态。Sunny需要通过**Service-Agent**提供的接口向平台汇报用户的状态。并给用户对象绑定需要为其关联的Stub列表。

## 设计实现
### 技术选择
* Spring Framework
* JMS
* AngularJS
* Hibernate JPA
* Quartz

### 系统设计

### E-R图
![Sunny系统ER图](../docs/sunny_er_model_v3.png)
### 核心业务类
* __AppService__ Sunny在Aniclod平台注册后的信息管理类。Sunny应用要实现授权，首先需要在Anicloud平台注册其基本信息，注册后由其自己保存平台颁发的ID和Secret等信息。本系统采用Json文件的方式来存储。配置文件为AniServiceInfo.json。
 * getAniServiceInfo()
 * update(AniService aniService) 

* __DeviceFeatureInitService__ Feature的元数据是有Sunny开发者事先定义好的，本系统设计为Json文件存储Feature的定义。配置文件为DeviceFeatureConfig.json。在配置文件中不仅需要定义Feature与Stub的之间的映射关系，还需要定义Feature的参数与Stub的参数之间的映射关系。
 * void initDeviceFeature() _该方法被设计为Spring初始化后首先调用，以完成系统的Feature初始化工作。_
 
* __DeviceFeatureJsonUtils__ Feature初始化的工具类，负责从Json文件中读取Feature的配置信息。对Json文件的操作采用**Jackson**实现。
 * static List<DeviceFeatureDto> getDeviceFeatureDtoListFromJsonFile()

* __DeviceFeatureService__ 基于SQL的DeviceFeature管理类。
 * saveDeviceFeature(DeviceFeatureDto deviceFeatureDto)
 * batchSaveDeviceFeature(List<DeviceFeatureDto> deviceFeatureDtoList)
 * modifyDeviceFeature(DeviceFeatureDto deviceFeatureDto)
 * removeDeviceFeature(String deviceFeatureId)
 * getDeviceFeatureById(String deviceFeatureId)
 * getAllDeviceFeature()
 * getAllDeviceFeatureInfo()

* __ApplicationInitService__ 应用的核心初始化类。Sunny系统基于OAuth2.0授信后，需要调用该接口提供的方法对用户的信息进行初始化。包括用户的Token、用户的设备以及设备关联的Feature。
 * initApplication(AniOAuthAccessToken accessToken) _初始化的入口方法，会在该方法中调用本类中的其他方法。_
 * initUser(UserDto userDto)
 * initUserDeviceAndDeviceFeatureRelation(AccountDto accountDto, AniOAuthAccessToken accessToken)
 * isUserNotExists(Long accountId)

* __ConstantInitService__ Sunny应用中的常量初始化服务类。负责获取应用的基本信息，Service-Agent连接的AnicelMeta类，Service-Agent的AniServiceSession类的建立。
 *  constantInitService() _该方法也是被设计为Spring初始化完成后执行。_

* __DeviceAndFeatureRelationService__ Device与Feature关联的服务类。负责持久化Account-Device-Feature之间的关系。
 * save(DeviceAndFeatureRelationDto deviceAndFeatureRelationDto) 
 * batchSave(List<DeviceAndFeatureRelationDto> relationDtoList)
 * findByDeviceIdentificationCode(String identificationCode)
 * findAll()

* __DeviceAndFeatureRelationService__ 基于Device绑定的Stub，基于配置的Device-Type规则，生成Device的默认类别。生成后用户也可以进行编辑。配置文件名DeviceType.json。
 * void initDeviceTypeGeneratorRule() _从配置文件中读取类别生成规则。_
 * generatorDeviceType(ObjectSlaveInfoDto slaveInfoDto) _根据设备信息，生成设备类别。_

* __TriggerTypeService__ Trigger的业务类。主要功能从数据库中读取所有的支持的Trigger类型。
 * List<String> getAllTriggerType()
 
* __StrategyService__ 策略相关的核心业务类。
 * saveStrategy(StrategyDto strategyDto)
 * saveStrategy(Strategy strategy)
 * operateStrategy(String strategyId, StrategyAction action)
 * modifyStrategy(StrategyDto strategyDto)
 * removeStrategy(Long hashUserId, String strategyId)
 * getStrategyDtoById(String strategyId)
 * getStrategyByUser(Long hashUserId)
 * runDeviceFeature(UserDto userDto, DeviceFeatureInstanceDto deviceFeatureInstanceDto)
 
### 消息服务

### 调度系统

### 外部接口
**Sunny** 主要通过Service-Agent 提供的SDK与Service-Bus模块通信。主要需要实现OAuth2.0 授信、设备信息拉取、设备Stub执行、用户状态维护以及平台对应用的反向调用等。详细的内容参见[Service-Agent文档](https://github.com/anicloud/octopus-object-client/blob/master/object-agent/java/service-agent/doc/service%20agent%20document.md)。

### 系统前端
**Sunny**的前端由AngularJS 与BootStrap 开发。AngularJS是属于前端的MVVM框架，用来开发单页面应用。BootStrap是一套响应式的前端CSS 框架。同时前端采用WebSocket 与Sunny服务其端进行全双工的通信。

## 部署要求
* Apache-Tomcat 8.0 以上，需要支持WebScoket
* Java 1.8
* Spring 4.x

## 引用参考
* [FasterXML-Jascson](https://github.com/FasterXML/jackson)
* [Service-Agent文档](https://github.com/anicloud/octopus-object-client/blob/master/object-agent/java/service-agent/doc/service%20agent%20document.md)
* [AngularJS](https://angularjs.org/)
* [Bootstrap中文](http://www.bootcss.com/)
