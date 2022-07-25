package cn.gmlee.demo.css.proxy.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * The type Make class visitor.
 */
public class CustomClassVisitor extends ClassVisitor {
    private String className;
    private String superName;

    /**
     * Instantiates a new Make class visitor.
     */
    protected CustomClassVisitor() {
        super(Opcodes.ASM5);
    }


    /**
     * Instantiates a new Make class visitor.
     *
     * @param classVisitor the class visitor
     */
    public CustomClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }


    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.className = name;
        this.superName = superName;
        super.visit(version, access, name + "$ToolsASM", signature, name, interfaces);
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        MethodVisitor wrappedMv = mv;
        //判断当前读取的方法
        if ("working".equals(name)) {
            //如果是working方法，则包装一个方法的Visitor
            wrappedMv = new CustomMethodVisitor(Opcodes.ASM5, mv);
        } else if ("<init>".equals(name)) {
            //如果是构造方法，处理子类中父类的构造函数调用
            wrappedMv = new ConstructorMethodVisitor(Opcodes.ASM5, mv, superName);
        }
        return wrappedMv;
    }
}
