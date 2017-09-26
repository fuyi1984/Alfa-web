package com.alfa.python;

import org.junit.Before;
import org.junit.Test;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * jython库测试
 */
public class JavaExecutePython {

    public PythonInterpreter interpreter ;
    public String basePath = JavaExecutePython.class.getResource("").getPath();

    @Before
    public void start(){
        interpreter = new PythonInterpreter();
    }

    //在java中调用本机python脚本中的函数
    @Test
    public void test02(){
        interpreter.execfile(basePath+"my_util.py");
        PyFunction func = (PyFunction) interpreter.get("adder",PyFunction.class);

        int a = 2010, b = 2;
        PyObject pyobj = func.__call__(new PyInteger(a), new PyInteger(b));
        System.out.println("anwser = " + pyobj.toString());
    }

}
