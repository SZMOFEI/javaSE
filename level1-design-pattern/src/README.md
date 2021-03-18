# 设计模式
[DOC]

## Spring中用的设计模式
IOC
- singleton
- beanfactory

AOP
- proxy
MVC
- 适配controller
DAO
- JDBCTemplate
EVENt
- 装饰着模式

## 设计原则  design principle SOLID

- single
- interface segregation 
- dependence inverse
- liskov substitution 
- Demeter's law
- Open and close principle
- composite ＆ reuse principle

## 设计模式的分类 CSB (create structure behavior)
### 创建型 (5)
情景: 两个造圆形丹药的工厂
- 工厂模式
- 抽象工厂
- 原型模式(圆形)
- 单例模式(丹)
- 建造者(造)
### 结构型 (7)
情景: 享受着七天国庆假期, 入住一家不仅外观漂亮, 内饰也很好的度假村, 有小桥流水,
 有组合东西文化美食,适配各国语言的服务员,他们可以代理购买各国的机票. 
 - 外观模式
 - 装饰者
 - 桥接模式
 - 享元模式
 - 组合模式
 - 适配模式
 - 代理模式
### 行为型 (11)
情景: 一名访客来到链家门口观察房屋信息,偶遇店长正在训话新人小莫, "客户满意应做到2点,
1,登记信息使用正确模板,达到备忘目的 
2.房源介绍策略请使用迭代的方式,并且有责任向客户解释清楚房价涨跌状态,不能使用命令的口吻
- 访客模式
- 中介者模式
- 观察者模式
- 责任链模式
- 模板方法模式
- 迭代器模式
- 解释器模式
- 状态模式
- 命令模式
- 策略模式
- 备忘录模式

## uml
- 类图
    - 依赖关系 dependency
    - 泛化关系 (extends)
    - 实现关系 (implementation)
    - 

## 参考资料链接
https://naotu.baidu.com/file/472fb1a3916998e325c92fd36f95af03