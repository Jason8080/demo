package cn.gmlee.demo.css.proxy;

import cn.gmlee.demo.css.proxy.loader.CustomClassLoader;
import cn.gmlee.demo.css.proxy.visitor.CustomClassVisitor;
import cn.hll.tools.base.builder.BytecodeBuilder;
import cn.hll.tools.base.builder.MapBuilder;
import cn.hll.tools.base.util.BeanUtil;
import cn.hll.tools.base.util.JsonUtil;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Map;


public class ProxyTest {


    public static void main(String[] args) throws Exception {
    }

    private static void asm() throws IOException, InstantiationException, IllegalAccessException {
        //1.定义ClassReader
        ClassReader classReader = new ClassReader(Pe.class.getName());
        //2.定义ClassWriter
        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
        //3.定义ClassVisitor
        ClassVisitor classVisitor = new CustomClassVisitor(classWriter);
        // 定义classVisitor输入数据,
        // SKIP_DEBUG 如果设置了此标志，则这些属性既不会被解析也不会被访问
        // EXPAND_FRAMES 依次调用ClassVisitor 接口的各个方法
        classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES);
        // 将最终修改的字节码以byte数组形式返回
        byte[] bytes = classWriter.toByteArray();
        URL url = new CustomClassLoader().getResource("");
        // 通过文件流写入方式覆盖原先的内容，实现class文件的改写
        FileOutputStream fileOutputStream = new FileOutputStream(url.getFile() + "/" + Pe.class.getName().replace(".", "/") + "$ToolsASM" + ".class");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
        Class<?> clazz = new CustomClassLoader().defineClassFromClassFile(Pe.class.getName() + "$ToolsASM", bytes);
        Object o = clazz.newInstance();
        System.out.println(JsonUtil.toJson(o));
    }

    private static void javassist() {
        Class<Pe> clazz = BytecodeBuilder.of(Pe.class)
                .addField("name", String.class)
                .addMethod("setName", Void.class, "this.name == $1;", String.class)
                .addMethod("getName", String.class, "return this.name;")
                .build();
        MapBuilder<String, String> map = MapBuilder.build("name", "666");
        Pe pe = BeanUtil.convert((Map) map, clazz);
        System.out.println(JsonUtil.toJson(pe));
    }
}
