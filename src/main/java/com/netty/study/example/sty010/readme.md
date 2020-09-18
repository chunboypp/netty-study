# grpc
基于http2 双向流式通讯-有点像 websocket
git clone -b 版本  地址
拷贝一个版本的分支并在自己本地建立这个版本


# gradlew 命令讲解
 gradle wrapper gradle包装
 通常使用流程：下载gradle ，配置环境变量，才能使用gradle
 
 使得在没有安装grale的机器上，依然可以通过次命令进行项目的构建  
 对gradle版本有要求的时候，此命令
 
 gradle clean build
 如果使用 gradle wrapper命令，则会生成 对应的gradlew文件，其它协作开发者，从git上拉取代码后，因为自己本机没有装
 gradle，但是有这些文件，通过gradle wrapper构建时，会去自动下载gradle
    gradlew
    gradlew.bat
    
    wrapper
        gradle-wrapper.jar
        gradle-wrapper.properties
  
gradle wrappper --gradle-version 3.5 构建的时候指定gradle版本，则gradalw中记录gradle版本的信息会发生改变

gradle clean build /会默认去用户目录 ./gradle/dists 下查找使用的gradle

推荐做法在build.gradle中
 task wrapper(type: wrapper){
    gradleVersion = '3.4'
    distributionType = 'all'
 }
 
 由项目创建人进行对应wrapper文件创建，其它项目人员直接使用gradle wrapper进行构建
 通过 shasum -a 256 gradle-3.4-all.zip 生成对应的校验和
 在 gradle-wrapper.properties 中指定：distributionSha256Sum=校验码
 如果下载下来的gradle 生成的校验码和指定的校验码不一致则会下载失败，安全考虑
 
 ## 
 grpc通过插件生成通过protobuf的文件，生成对应的服务端 客户端 和对应的service
 
 生成命令  gradle generateProto
 
 grpc 定义的方法传输的参数必须是 message类型
 service 方法名{
 rpc 方法名（参数必须是 message类型）
  }
  
  只要客户端向服务器端发送流式数据，这种调用都是异步的。只能是异步的
  
  到底使用那种调用方式：根据场景设定
  
  #视频27包含所有的调用方式
 
 