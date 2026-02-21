package Assignment2;

import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import java.io.FileOutputStream;

import static org.objectweb.asm.Opcodes.*;


public class GenDFlo {
public static void main(String args[]) throws IOException{
        
        ClassWriter cw=new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		cw.visit(V11, ACC_PUBLIC+ACC_SUPER, "DFlo", null, "java/lang/Object", null);

        //Create the class
		{
			MethodVisitor mv=cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0); //load the first local variable: this
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
			mv.visitInsn(RETURN);
			mv.visitMaxs(0,0);
			mv.visitEnd();
		}
		
		MethodVisitor methodVisitor=cw.visitMethod(ACC_PUBLIC+ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        methodVisitor.visitCode();
        
        //Push 10.5, store in 1
        methodVisitor.visitLdcInsn(10.5F);
        methodVisitor.visitVarInsn(Opcodes.FSTORE, 1);
        
        //Push 5.5, store in 2
        methodVisitor.visitLdcInsn(5.5F);
        methodVisitor.visitVarInsn(Opcodes.FSTORE, 2);
        
        //Divide the Floats, store in 3
        methodVisitor.visitVarInsn(Opcodes.FLOAD, 1);
        methodVisitor.visitVarInsn(Opcodes.FLOAD, 2);
        methodVisitor.visitInsn(Opcodes.FDIV);
        methodVisitor.visitVarInsn(Opcodes.FSTORE, 3);
        
        //Print 10.5 - 5.5
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(Opcodes.FLOAD, 3);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(F)V", false);
        
        //Push 10.5, store in 4 and 5
        methodVisitor.visitLdcInsn((Double) 10.5);
        methodVisitor.visitVarInsn(Opcodes.DSTORE, 4);
        
        //Push 5.5, store in 6 and 7
        methodVisitor.visitLdcInsn((Double) 5.5);
        methodVisitor.visitVarInsn(Opcodes.DSTORE, 6);
        
        //Divide the double, store in 8 and 9
        methodVisitor.visitVarInsn(Opcodes.DLOAD, 4);
        methodVisitor.visitVarInsn(Opcodes.DLOAD, 6);
        methodVisitor.visitInsn(Opcodes.DDIV);
        methodVisitor.visitVarInsn(Opcodes.DSTORE, 8);
        
        //Print result
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(Opcodes.DLOAD, 8);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(D)V", false);
        
        
        methodVisitor.visitInsn(Opcodes.RETURN);
		methodVisitor.visitMaxs(0, 0);
		methodVisitor.visitEnd();
        
        cw.visitEnd();
        
        //save bytecode into disk
		FileOutputStream out=new FileOutputStream("./DFlo.class");
		out.write(cw.toByteArray());
		out.close();
	}
}



