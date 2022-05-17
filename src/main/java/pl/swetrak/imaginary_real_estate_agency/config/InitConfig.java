package pl.swetrak.imaginary_real_estate_agency.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.swetrak.imaginary_real_estate_agency.models.Offer;
import pl.swetrak.imaginary_real_estate_agency.repositories.OfferRepository;

import java.util.List;

@Configuration
public class InitConfig {

    @Bean
    CommandLineRunner commandLineRunner(OfferRepository repository) {
        return args -> {
            Offer offer1 = new Offer("Mieszkanie na sprzedaż - Warszawa, Praga-Południe / Saska Kępa", "kamilpietrak123@gmail.com", "Warszawa ul. Królowej Aldony", 670000, 1,new Float(1),31,"\t\n" +
                    "Klimatyczne mieszkanie- prestiżowa Saska Kępa\n" +
                    "Poszukujesz klimatycznego mieszkania w prestiżowej dzielnicy? To będzie ten adres!\n" +
                    "\n");
            Offer offer2 = new Offer("Mieszkanie na sprzedaż - Warszawa, Praga-Południe / Saska Kępa", "kamilpietrak123@gmail.com", "Warszawa ul. Królowej Aldony", 670000, 1,new Float(2),31,"\t\n" +
                    "Klimatyczne mieszkanie- prestiżowa Saska Kępa\n" +
                    "Poszukujesz klimatycznego mieszkania w prestiżowej dzielnicy? To będzie ten adres!\n" +
                    "\n" );


            repository.saveAll(List.of(offer1, offer2));
        };
    }

}
