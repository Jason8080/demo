package cn.gmlee.demo.css.proxy.visitor;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * The type Constructor method visitor.
 */
public class ConstructorMethodVisitor extends MethodVisitor {
    private String superClassName;

    /**
     * Instantiates a new Constructor method visitor.
     *
     * @param i              the
     * @param methodVisitor  the method visitor
     * @param superClassName the super class name
     */
    public ConstructorMethodVisitor(int i, MethodVisitor methodVisitor, String superClassName) {
        super(i, methodVisitor);
        this.superClassName = superClassName;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean b) {
        //当开始初始化构造函数时，先访问父类构造函数,类似源码中的super()
        if (opcode == Opcodes.INVOKESPECIAL && name.equals("<init>")) {
            owner = superClassName;
        }
        super.visitMethodInsn(opcode, owner, name, desc, b);
    }
}