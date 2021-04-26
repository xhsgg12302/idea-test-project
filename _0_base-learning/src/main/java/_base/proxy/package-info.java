/**
 *
 * 1,通过SA(Serviceability Agent)工具ClassDUMP
 *
 * -------------------------------------------------------------------------------------------------------------------
 * #缺省输出该PID下所有已加载的class文件至./目录
 * > java -classpath ".;./bin;%JAVA_HOME%/lib/sa-jdi.jar" sun.jvm.hotspot.tools.jcore.ClassDump <PID>
 *
 * > java -classpath ".;/Users/stevenobelia/IdeaProjects/my-test/_0_base-learning/target/classes;/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/lib/sa-jdi.jar" \
        -Dsun.jvm.hotspot.tools.jcore.filter=_base.proxy._dump_class.FilterClass \
        -Dsun.jvm.hotspot.tools.jcore.outputDir=/Users/stevenobelia/IdeaProjects/my-test/_0_base-learning/src/main/java/_base/proxy/cglib_dynamic \
        sun.jvm.hotspot.tools.jcore.ClassDump 13541
 *
 *
 * 2,//动态代理时生成class文件(只针对JDK代理有效)
 * -------------------------------------------------------------------------------------------------------------------
 *
 * System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
 * 2.0  //对JDK有效的是      ProxyGenerator private static final boolean saveGeneratedFiles = (Boolean)AccessController.doPrivileged(new GetBooleanAction("sun.misc.ProxyGenerator.saveGeneratedFiles"));
 *
 * 2.1  //对cglib有效的是    cglib.debugLocation   /Users/stevenobelia/IdeaProjects/my-test/_0_base-learning/src/main/java/_base/proxy/cglib_dynamic
 *
 *
 * 3，debug
 *
 * -------------------------------------------------------------------------------------------------------------------
 * 有趣的问题, 刚才试了一下, 发现是可行的, 步骤:
 * 1. 在 ClassLoader#defineClass 打条件断点(根据名称)
 * 2. new FileOutputStream("D://test.class").write(b), 将字节流输出到磁盘
 * 3. 用 IDEA(或其他)打开 class 文件
 *
 *
 * 4，参考
 *
 * -------------------------------------------------------------------------------------------------------------------
 * https://www.iteye.com/blog/rednaxelafx-727938
 *
 */

package _base.proxy;
