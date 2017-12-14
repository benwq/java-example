package python;

import org.python.core.AstList;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * 在java中调用本机python脚本中的函数
 *
 * @author cc
 *
 */
public class JythonTest {

    public static void main(String args[]) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("G:\\workspace\\test\\java-example\\pluginApi\\src\\main\\java\\python\\syspath.py");
        PyFunction func = (PyFunction) interpreter.get("handled_data", PyFunction.class);
        PyObject pyobj  = func.__call__(new AstList());
        System.out.println("anwser = " + pyobj.toString());

    }
}
