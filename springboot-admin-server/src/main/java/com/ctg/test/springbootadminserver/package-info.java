/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/11/23 9:44
 */
/**
 * 1)客户端启动后会实例化RegistrationApplicationListener，listener默认会每隔10s到服务端去注册下，如果已经存在，会refresh
 * 2)在跟服务器注册之前，客户端会先实例化Application信息，获取相应的信息，然后通过restful http post请求跟服务器交互
 * 3)服务端代码逻辑：服务器端首先会根据客户端的HealthUrl，通过SHA-1 算法得到客户端的id值，借此区分不同的客户端节点
 * 4)获取到客户端id后，从服务端保存的ConcurrentHashMap 对象中，根据id获取客户端状态信息，如果存在状态信息，则refresh或者replace，否则往map中新加客户端信息
 * 5)客户端注册到服务端，会由服务端维护一层路由映射，会在路径上添加前缀、客户端id和后缀，默认前缀为：/api/applications  后缀：/**
 */
/**
 * 具体请求执行路径
 * url请求(带有具体的客户端id)  -> 经过服务器端路由映射-> 具体节点的访问路径 -> 调用Spring Boot Autuator 监控接口获取数据返回
 */
package com.ctg.test.springbootadminserver;