package org.hackathon.config;

import org.hackathon.entity.Event;
import org.hackathon.entity.Organisation;
import org.hackathon.entity.Principal;
import org.hackathon.entity.Volunteer;
import org.hackathon.repository.*;
import org.hackathon.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class FillUpManager {
    private BCryptPasswordEncoder encoder;

    private PrincipalRepository principalRepository;
    private OrganizationRepository orgRepository;
    private VolunteerRepository volunteerRepository;

    private EventRepository eventRepository;
    private EventMembershipRepository membershipRepository;

    private List<String> volunteerEmails = Arrays.asList(
            "arya.stark@gmail.com",
            "bruce.wayne@gmail.com",
            "jimmy.hendrix@gmail.com",
            "richard.right@gmail.com",
            "george.orvel@gmail.com",
            "marko.diaz@gmail.com",
            "marry.sue@gmail.com",
            "eva.wally@gmail.com");

    private List<LocalDate> volunteerBirthday = Arrays.asList(
            LocalDate.of(1999, 10, 13),
            LocalDate.of(1976, 4, 4),
            LocalDate.of(1945, 1, 22),
            LocalDate.of(1951, 12, 26),
            LocalDate.of(1920, 2, 8),
            LocalDate.of(2001, 7, 17),
            LocalDate.of(2000, 7, 2),
            LocalDate.of(1900, 3, 31)
    );

    private List<String> volunteerFirstNames = Arrays.asList(
            "Ар'я",
            "Брюс",
            "Джиммі",
            "Річард",
            "Джордж",
            "Марко",
            "Меррі",
            "Єва");

    private List<String> volunteerLastNames = Arrays.asList(
            "Старк",
            "Вейн",
            "Хедрікс",
            "Райт",
            "Орвел",
            "Діаз",
            "Сью",
            "Валлі");

    private List<String> orgEmails = Arrays.asList(
            "star.butterfly@gmail.com",
            "jon.doe@gmail.com",
            "jammy.lanyster@gmail.com"
    );

    private List<String> orgUrls = Arrays.asList(
            "https://www.wcs.org",
            "http://krylanadiyi.org.ua",
            "https://www.facebook.com/PatriotGO/"
    );

    private List<String> orgName = Arrays.asList(
            "Спілка збереження дикої природи",
            "Крила надії",
            "Об'єднання\"Патріот\"");

    private List<String> orgFirstName = Arrays.asList(
            "Стар",
            "Джон",
            "Джеймі");

    private List<String> orgLastName = Arrays.asList(
            "Батерфлай",
            "Доу",
            "Ланістер");

    private List<String> orgDescriptions = Arrays.asList(
            "Громадська природоохоронна організація національного рівня. Ми працюємо на захист природних територій, біорізноманіття, для попередження глобальних змін клімату та негативних наслідків енергетичного сектору. НЕЦУ спонукає чиновників та політиків приймати рішення з урахуванням необхідності збереження довкілля та безпеки населення, готує свої оцінки та дослідження і пропонує альтернативні рішення.",
            "Матеріальна та соціальна підтримка військових. На зібрані кошти волонтери купують каски, форму, зброю, амуніцію для солдат на фронті.",
            "Благодійний фонд створений для допомоги важкохворим дітям. Ми мріємо, щоб усі діти, які мають негаразди зі здоров'ям, - обов'язково одужали."
    );

    @Autowired
    public FillUpManager(BCryptPasswordEncoder encoder, PrincipalRepository principalRepository, OrganizationRepository orgRepository,
                         VolunteerRepository volunteerRepository, EventRepository eventRepository, EventMembershipRepository membershipRepository) {
        this.encoder = encoder;
        this.principalRepository = principalRepository;
        this.orgRepository = orgRepository;
        this.volunteerRepository = volunteerRepository;
        this.eventRepository = eventRepository;
        this.membershipRepository = membershipRepository;
    }

    public void fillUp() {
        createUsers();
        createEvents();
    }

    private void createUsers() {

        for (String email : volunteerEmails) {
            Principal p = new Principal();
            p.setPassword(encoder.encode("test"));
            p.setRole(Role.ROLE_VOLUNTEER);
            p.setEmail(email);
            principalRepository.save(p);
        }

        for (String email : orgEmails) {
            Principal p = new Principal();
            p.setPassword(encoder.encode("test"));
            p.setRole(Role.ROLE_VOLUNTEER);
            p.setEmail(email);
            principalRepository.save(p);
        }

        for (int i = 0; i < 3; i++) {
            Organisation org = new Organisation();
            org.setDescription(orgDescriptions.get(i));
            org.setFirstName(orgFirstName.get(i));
            org.setLastName(orgLastName.get(i));
            org.setConfirmed(true);
//            org.setImage( );
            org.setUrl(orgUrls.get(i));
            org.setPrincipal(principalRepository.findByEmail(orgEmails.get(i)).get());
            org.setName(orgName.get(i));
            orgRepository.save(org);
        }

        for (int i = 0; i < 8; i++) {
            Volunteer volunteer = new Volunteer();
            volunteer.setFirstName(volunteerFirstNames.get(i));
            volunteer.setLastName(volunteerLastNames.get(i));
            volunteer.setPrincipal(principalRepository.findByEmail(volunteerEmails.get(i)).get());
            volunteer.setAddress("Львів, вул. Джонна Леннонна, " + i);
            volunteer.setBirthDate(volunteerBirthday.get(i));
            volunteerRepository.save(volunteer);
        }

    }

    private void createEvents() {
        List<String> eventNames = Arrays.asList("Суботник у Стрийському парку", "Тренінг \"Ефективне енергоспоживання\"", "Здача крові", "Візит Миколая до дітей", "Збір продуктів харчування для військових");
        List<String> eventLocations = Arrays.asList("Cтрийському парк, озеро з лебедями",
                "вул. Пекарська 72, Центр зайнятості",
                "5 міська клінічна лікарня",
                "Школа №28",
                "Волонтерський пункт №2, вул.Бойківська 2, перший поверх");
        List<String> eventDesc = Arrays.asList("Запрошуємо всіх небайдужих городян, представників громадських організацій та підприємств, волонтерів, молодь взяти участь у суботнику та провести роботи з благоустрою Стрийського парку. Покажемо приклад нашим дітям і онукам! Адже з таких простих речей, як турбота про природу, і починається любов до рідного міста, країни!",
                "Витрати на енергоспоживання в бюджетних закладах України утричі більші, порівняно із європейськими країнами. Через неефективне споживання енергоносіїв, застарілу інфраструктуру та відсутність енергоменеджменту український бюджет щороку втрачає чималі суми, а також шкодить нашому довкіллю.",
                "Львів'ян просять терміново здати кров для 6-річної Ольги Редьки, яка перебуває у гематологічному відділенні 5-ї лікарні з гострим мієлобластним лейкозом. Протягом півроку дівчинка бореться із важкою хворобою, і зараз у неї гострий період. Після проведення чергової хіміотерапії у Ольги Редьки дуже впали показники крові. Терміново, потрібні донори на тромбоконцентрат третьої негативної групи.",
                "Щорічна благочинна акція, завданням якої є зібрати та роздати подарунки (солодощі, іграшки, книжки, фарби, олівці, зошити, засоби гігієни) для тих діток, які могли би залишитися без нашої або батьківської уваги, а також створити особливу чарівну атмосферу свята.",
                "Оголошується збір продуктів для військовослужбовців які перебувають в зоні АТО, щоб забезпечити необхідним раціоном харчування наших військових та приготуванням  найбільш зручних в бойових умовах продуктів харчування:\n" +
                        "\n" +
                        "- овочів, а саме: капуста, буряк червоний, морква, цибуля, часник, картопля;\n" +
                        "\n" +
                        "- крупи: вівсяні пластівці, рис, гречка, пшоно, вермішель, дрібні макарони;\n" +
                        "\n" +
                        "-грибів, сухих ягід, сухофруктів, меду;\n" +
                        "\n" +
                        "-консервації, сала, тушонки." +
                        "Також необхідна допомога з перевезенням продуктів.");
        List<LocalDateTime> eventStart = Arrays.asList(
                LocalDateTime.of(2019, 4, 27, 10, 0),
                LocalDateTime.of(2019, 2, 5, 18, 0),
                LocalDateTime.of(2019, 4, 8, 9, 0),
                LocalDateTime.of(2019, 4, 8, 9, 0),
                LocalDateTime.of(2019, 4, 8, 9, 0)
        );
        List<LocalDateTime> eventEnd = Arrays.asList(
                LocalDateTime.of(2019, 4, 27, 12, 30),
                LocalDateTime.of(2019, 2, 5, 20, 0),
                LocalDateTime.of(2019, 4, 10, 17, 0),
                LocalDateTime.of(2019, 4, 8, 20, 0),
                LocalDateTime.of(2019, 4, 10, 17, 0));
        for (int i = 0; i < 5; i++) {
            Event event = new Event();
            event.setName(eventNames.get(i));
            String email;
            if (i < 2) {
                email = orgEmails.get(0);
            } else if (i < 4) {
                email = orgEmails.get(1);
            } else {
                email = orgEmails.get(2);
            }
            event.setOwner(orgRepository.findByPrincipalEmail(email).get());
            event.setDescription(eventDesc.get(i));
            event.setLocation(eventLocations.get(i));
            event.setStartDate(eventStart.get(i));
            event.setEndDate(eventEnd.get(i));
            event.setImage(new byte[]{1});
            eventRepository.save(event);
        }
    }
}
