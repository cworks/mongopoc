/**
 * ============================================================================
 * Project: mongopoc
 * Created: 2016-01-07 21:10:30
 * Class:   cworks.mongo.App
 *
 * Baked with love
 * ============================================================================
 */
package cworks.mongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.inject.Inject;
import java.util.List;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Inject
    private ComponentRepository componentRepo;

    @Inject
    private ComponentValueRepository componentValueRepo;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        componentRepo.deleteAll();
        componentValueRepo.deleteAll();

        ComponentValue submit = new ComponentValue("Submit");
        componentValueRepo.save(submit);


        ComponentValue cancel = new ComponentValue("Cancel");
        componentValueRepo.save(cancel);

        ComponentValue stop = new ComponentValue("STOP");
        componentValueRepo.save(stop);

        componentRepo.save(new Component("form1.submit.label", submit));
        componentRepo.save(new Component("form2.submit.label", submit));
        componentRepo.save(new Component("form3.submit.label", submit));

        componentRepo.save(new Component("cancel.label", cancel));
        componentRepo.save(new Component("cancel.label", stop));

        Component f1sl = componentRepo.findByName("form1.submit.label");
        Component f2sl = componentRepo.findByName("form2.submit.label");
        Component f3sl = componentRepo.findByName("form3.submit.label");

        System.out.println("form1.submit.label=" + f1sl.getValue().getValue());
        System.out.println("form2.submit.label=" + f2sl.getValue().getValue());
        System.out.println("form3.submit.label=" + f3sl.getValue().getValue());

        List<Component> cancelLabels = componentRepo.findAllByName("cancel.label");
        for(Component component : cancelLabels) {
            System.out.println("cancel.label=" + component.getValue().getValue());
        }

        submit.setValue("Submit It!");
        componentValueRepo.save(submit);

        f1sl = componentRepo.findByName("form1.submit.label");
        f2sl = componentRepo.findByName("form2.submit.label");
        f3sl = componentRepo.findByName("form3.submit.label");

        System.out.println("form1.submit.label=" + f1sl.getValue().getValue());
        System.out.println("form2.submit.label=" + f2sl.getValue().getValue());
        System.out.println("form3.submit.label=" + f3sl.getValue().getValue());

    }


}
