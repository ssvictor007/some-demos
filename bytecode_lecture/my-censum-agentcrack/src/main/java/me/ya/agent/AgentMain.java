package me.ya.agent;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;

import static org.objectweb.asm.Opcodes.ASM7;


/**
 * Created By SSvictor at 2019/11/07
 */
public class AgentMain {
    public static class MyMethodVisitor extends AdviceAdapter {

        protected MyMethodVisitor(MethodVisitor mv, int access, String name, String desc) {
            super(ASM7, mv, access, name, desc);
        }

        @Override
        protected void onMethodEnter() {
            // 压栈 int 2016 
            mv.visitIntInsn(SIPUSH, 2016);
            // int return 
            mv.visitInsn(IRETURN);
        }


    }

    public static class MyClassVisitor extends ClassVisitor {

        public MyClassVisitor(ClassVisitor classVisitor) {
            super(ASM7, classVisitor);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
            // 只改写 getYear 函数
            if (name.equals("getYear")) {
                return new MyMethodVisitor(mv, access, name, descriptor);
            }
            return mv;
            
        }
    }

    public static class MyClassFileTransformer implements ClassFileTransformer {
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                ProtectionDomain protectionDomain, byte[] classBytes) throws IllegalClassFormatException {
            // 只注入 CensumStartupChecks 类
            if (className.equals("com/jclarity/censum/CensumStartupChecks")) {
                ClassReader cr = new ClassReader(classBytes);
                ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);
                ClassVisitor cv = new MyClassVisitor(cw);
                cr.accept(cv, ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG);
                byte[] bytes = cw.toByteArray();
                // 把转换以后的文件写入到文件中，方便进行检查是否改写正确
                writerByteArrayToFile(bytes, new File("./CensumStartupChecks-modify.class"));                
                return bytes;
            }
            return classBytes;
        }
    }

    public static void premain(String agentArgs, Instrumentation inst) throws ClassNotFoundException, UnmodifiableClassException {
        inst.addTransformer(new MyClassFileTransformer(), true);
    }
}
