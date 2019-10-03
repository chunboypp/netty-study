代码生成在build目录下，不是在源文件目录下，每次通过复制到源代码中。

当执行gradel build的时候会报错 类重复，是因为 bulid文件下有生成的对应的源码，src/main/java下也有一份源码，

删除之后，再次执行gradle build 还会在build目录下生成grc源码，还是会报同样的错误。
说明 gradle build 依赖 gradle genaratorProto

思考：在gradle配置中，是否可以配置 直接把生成的源码输出到src/main/java

setOutputSubDir 属性配置，配置生成源码服务的 包名
setOutputBaseDir


