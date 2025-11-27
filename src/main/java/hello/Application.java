package hello;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.jexl3.*;

@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/")
    public String index() {
        return """
                <html>
                <body>
                    <h2>Дія</h2>
                    <form method='post' action='/calc'>
                        <input type='text' name='expr' placeholder='2+2*2'>
                        <button type='submit'>Порахувати</button>
                    </form>				
                </body>
                </html>
                """;
    }

    @PostMapping("/calc")
    public String calc(@RequestParam String expr) {
        try {
            JexlEngine jexl = new JexlBuilder().create();
            JexlExpression e = jexl.createExpression(expr);
            Object result = e.evaluate(null);

            return """
                    <html><body>
                    <h2>Result: %s</h2>
                    <a href='/'>Back</a>
                    </body></html>
                    """.formatted(result);
        } catch (Exception e) {
            return """
                    <html><body>
                    <h2>Error in expression!</h2>
                    <a href='/'>Back</a>
                    </body></html>
                    """;
        }
    }
}
