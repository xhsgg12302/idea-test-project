/**
 *
 *
 * gcc -shared -o libexample.so example.c
 *
 *
 *
 * System.loadLibrary()和Native.loadLibrary()都可以用于在Java中加载本地库文件（如.so文件）。但它们有一些区别。
 *
 * System.loadLibrary()是Java标准库中的一个方法，用于从运行时库路径加载共享库。如果您将库文件复制到系统默认的库路径（例如/usr/lib），则可以使用此方法加载库文件。
 * 它是Java标准库的一部分，不需要任何其他外部库或工具。
 * 系统级别的库路径。这包括操作系统定义的标准库路径，通常是/usr/lib或/usr/local/lib等。
 * 运行时库路径。运行时库路径是Java虚拟机在启动时设置的库路径。可以通过设置java.library.path系统属性来修改它。
 * java.library.path:/Users/mac/Library/Java/Extensions:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java:.
 * 用户级别的库路径。用户级别的库路径是在启动Java虚拟机时设置的，通过-Djava.library.path命令行选项指定。
 *
 *
 * 例如：
 *  System.loadLibrary("mylib");
 * 这将在运行时库路径中查找名为libmylib.so的库文件并加载它。
 *
 * Native.loadLibrary()是JNA（Java Native Access）库中的一个方法，用于从任意路径加载共享库。它需要引入JNA库，您需要在您的项目中添加JNA库的依赖。
 *
 * 例如：
 *  Native.loadLibrary("/path/to/mylib", MyLibrary.class);
 *
 * 这将从指定路径加载名为mylib的库文件并将其映射到Java接口MyLibrary。
 *
 * 总的来说，System.loadLibrary()比Native.loadLibrary()更简单，更易于使用，但是它的功能有限，只能从默认运行时库路径加载库文件。而Native.loadLibrary()则提供更多的灵活性，
 * 可以从任何路径加载库文件，但使用它需要引入JNA库。
 *
 *
 */
package _jvm.jni;

