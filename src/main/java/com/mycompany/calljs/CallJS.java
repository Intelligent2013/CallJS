package com.mycompany.calljs;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author Alexander Dyuzhev
 */
public class CallJS {

    public static void main(String[] args) throws ScriptException, NoSuchMethodException, URISyntaxException, IOException {
        
        Path jsPath = Paths.get(CallJS.class.getResource("/highlight.min.js").toURI());
        
        ScriptEngine graalEngine = new ScriptEngineManager().getEngineByName("graal.js");
        Bindings bindings = graalEngine.getBindings(ScriptContext.ENGINE_SCOPE);
        //bindings.put("polyglot.js.allowIO", true);
        bindings.put("code", "<h1>Hello World!</h1>");
        try (BufferedReader reader = Files.newBufferedReader(jsPath)) {
          graalEngine.eval(reader);

          Object result = graalEngine.eval("var result = hljs.highlightAuto(code).value;" + 
                "result"
                 , bindings);
          
          System.out.println(result);
        } 
    }
}
