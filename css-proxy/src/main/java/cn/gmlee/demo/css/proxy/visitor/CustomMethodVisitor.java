package cn.gmlee.demo.css.proxy.visitor;

import org.objectweb.asm.MethodVisitor;

/**
 * The type Custom method visitor.
 */
public class CustomMethodVisitor extends MethodVisitor {
    /**
     * Instantiates a new Custom method visitor.
     *
     * @param api           the api
     * @param methodVisitor the method visitor
     */
    public CustomMethodVisitor(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }
}