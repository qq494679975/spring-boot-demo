package demo.cwd.shell.shell;

import demo.cwd.shell.service.ShellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * Created by chenweida on 2018/1/29.
 */
@ShellComponent
public class TranslationCommands {
    @Autowired
    private ShellService shellService;

    @ShellMethod("Translate text from one language to another.")
    public String translate(
            @ShellOption(value = "text") String text
    ) {
        shellService.shellService(text);
        return "成功";
    }

    @ShellMethod("Translate text from one language to another.")
    public String helloworld(
            @ShellOption(value = "text") String text
    ) {
        return "helloworld !!!";
    }
}
